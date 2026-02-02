// (220221 ~ 220222) Github Bot v0.1 by YeJun Jung
// This source code license is MIT License.

package main

import (
	"os"
	"fmt"
	"flag"
	"time"
	"regexp"
	"context"
	"strings"
	"strconv"
	"net/http"
	"io/ioutil"
	"encoding/json"
	"golang.org/x/oauth2"
	"github.com/google/go-github/v42/github"
)

type Messages struct {
	Messages []Message `json:"messages"`
}

type Message struct {
	Path    string `json:"path"`
	Title   string `json:"title"`
	Target  []string `json:"target"`
	Type    string `json:"type"`
	Message string `json:"message"`
}

func GetFlags() (messageFileName *string, ownerName *string, repoName *string, prNum *int, isConfirm *bool, isSign *bool) {
	messageFileName = flag.String("m", "", "Message File Path (JSON)")
	ownerName = flag.String("owner", "", "Owner of A Target Github Repository")
	repoName = flag.String("repo", "", "Target Github Repository Name")
	prNum = flag.Int("pr", -1, "Number of Pull requests (Default is -1, latest pull requests)")
	isConfirm = flag.Bool("confirm", true, "Confirm before uploading")
	isSign = flag.Bool("sign", true, "Append sign texts")

	flag.Parse()

	return
}

func GetMessage(fileName string) (Messages, error) {
	// Massages struct
	var messages Messages

	// Open a file
	fileHandler, err := os.Open(fileName)
	defer fileHandler.Close()

	if err != nil {
		fmt.Println(" * Message File Open Error:", err)
		return messages, err
	}

	// Read all bytes from a file
	fileByte, err := ioutil.ReadAll(fileHandler)

	if err != nil {
		fmt.Println(" * Message File Read Error:", err)
		return messages, err
	}

	// Conver json data to Messages struct
	json.Unmarshal(fileByte, &messages)

	// Return messages
	return messages, err
}

func NewGithubClient(ctx context.Context, token string) *github.Client {
	tokenSource := oauth2.StaticTokenSource(&oauth2.Token{AccessToken: token})
	tokenClient := oauth2.NewClient(ctx, tokenSource)

	return github.NewClient(tokenClient)
}

func IsMatchFilePath(path string, target string) bool {
	pathLen := len(path)

	if len(target) < pathLen {
		return false
	}
	
	filePath := target[:pathLen]
	return path == filePath
}

func IsMatchFileName(arr []string, target string) bool {
	for _, str := range arr {
		match, _ := regexp.MatchString(str, target)

		if (match) {
			return true
		}
	}

	return false
}

func (messages *Messages) GetSingleFileMessage(file *github.CommitFile, data []byte) (string, int) {
	fileData := string(data)
	fileDataLines := strings.Split(fileData, "\n")
	
	filePath := strings.Split(*file.Filename, "/")
	fullFileName := filePath[len(filePath)-1]
	fileNames := strings.Split(fullFileName, ".")

	result := ""
	key := -1

	for index, message := range messages.Messages {
		if !IsMatchFilePath(message.Path, *file.Filename) || !IsMatchFileName(message.Target, *file.Filename) {
			continue
		}

		result = message.Message
		key = index
		diff := 0

		r, _ := regexp.Compile(`\$(.*?)\$`)
		commands := r.FindAllString(message.Message, -1)
		locations := r.FindAllStringIndex(message.Message, -1)

		r2, _ := regexp.Compile(`\[(.*?)\]`)

		for index, command := range commands {
			command = strings.Trim(command, "$")
			commandIndex := r2.FindAllString(command, -1)
			commandResult := ""
			commandLastLoc := strings.Index(command, "[")
			
			if commandLastLoc >= 0 {
				command = command[:commandLastLoc]
			}

			var commandIndexValue [][]int

			for i, index := range commandIndex {
				index = strings.TrimLeft(index, "[")
				index = strings.TrimRight(index, "]")


				indexRange := strings.Split(index, ":")
				
				commandIndexValue = append(commandIndexValue, []int{0, -1})

				if indexRange[0] != "" {
					num, err := strconv.Atoi(indexRange[0])
					if err != nil {
						panic(err)
					}
					
					commandIndexValue[i][0] = num
				}
				
				if len(indexRange) == 2 && indexRange[1] != "" {
					num, err := strconv.Atoi(indexRange[1])
					if err != nil {
						panic(err)
					}

					commandIndexValue[i][1] = num
				}
			}

			commandIndexValueLen := len(commandIndexValue)

			if command == "File" {
				if (commandIndexValueLen == 0) {
					commandResult = fileData
					
				} else if (commandIndexValueLen == 1) {
					start := commandIndexValue[0][0]
					end := commandIndexValue[0][1]

					if end == -1 {
						commandResult = fileData[start:]
					} else {
						commandResult = fileData[start:end]
					}

					
				} else if (commandIndexValueLen == 2) {
					line := commandIndexValue[0][0]

					start := commandIndexValue[1][0]
					end := commandIndexValue[1][1]

					if end == -1 {
						commandResult = fileDataLines[line][start:]

					} else {
						commandResult = fileDataLines[line][start:end]
					}
					
				} else {
					panic("Wrong Command Index Values: " + commands[index])
				}
				
			} else if command == "FileName" {
				commandResult = fileNames[0]
				
			} else if command == "FullFileName" {
				commandResult = fullFileName
				
			} else if command == "FileExt" {
				commandResult = fileNames[1]
				
			}

			startLoc := locations[index][0] + diff
			endLoc := locations[index][1] + diff

			result = result[:startLoc] + commandResult + result[endLoc:]

			diff += len(commandResult) - len(commands[index])
		}

		if (message.Type == "list") {
			result = " - " + result
		}

		break
	}

	result = strings.Replace(result, "\\$", "$", -1)
	return result, key
}

func (messages *Messages) GetFileMessages(commitFiles []*github.CommitFile) ([]string, []int) {
	texts := []string{}
	sizes := []int{}

	for _, message := range messages.Messages {
		texts = append(texts, message.Title + "\n")
		sizes = append(sizes, 0)
	}

	for _, file := range commitFiles {
		data := GetGithubFileRaw(file)
		str, key := messages.GetSingleFileMessage(file, data)

		if key == -1 {
			continue
		}
		
		texts[key] += str + "\n"
		sizes[key] ++
	}

	return texts, sizes
}

func GetGithubFileRaw(file *github.CommitFile) []byte {
	url := file.GetRawURL()
	response, err := http.Get(url)

	if response.StatusCode != 200 {
		fmt.Println(" * Get Github File Raw Response is not 200 OK:", *file.Filename)
		panic("Response Status: " + response.Status)
	}

	if err != nil {
		fmt.Println(" * Get Github File Raw Error:", err)
		panic(err)
	}

	defer response.Body.Close()

	data, err := ioutil.ReadAll(response.Body)

	if err != nil {
		fmt.Println(" * Read Data Error:", err)
		panic(err)
	}

	return data
}

func main() {
	startTime := time.Now()

	// Welcome
	fmt.Println("Github-Bot v0.1 DEV")

	// Get command line flags
	messageFileName, ownerName, repoName, prNum, isConfirm, isSign := GetFlags();

	fmt.Println("\n[Message]", *messageFileName)
	fmt.Println("[Owner  ]", *ownerName)
	fmt.Println("[Repo   ]", *repoName)
	fmt.Println("[PR Num ]", *prNum)

	// Get Github token from environment variable
	githubToken := os.Getenv("ACCESS_TOKEN")

	if githubToken == "" {
		fmt.Println(" * There is no a Personal Access Token of Github.")
		fmt.Println(" * Please enter the token in \"ACCESS_TOKEN\" a environment variable")

		panic("Github Token Error")
	}

	// Get a message file
	messages, err := GetMessage(*messageFileName)

	if err != nil {
		panic(err)
	}

	// Create a Github client
	ctx := context.Background()
	ghClient := NewGithubClient(ctx, githubToken)

	// Check a pull request number
	if *prNum == -1 {
		// Get latest pull request number
		option := github.PullRequestListOptions{State: "open"}
		pr, _, err := ghClient.PullRequests.List(ctx, *ownerName, *repoName, &option)

		if err != nil {
			panic(err)
		}

		if len(pr) == 0 {
			fmt.Println(" * There are no pull requests in target repository")
			panic("No pull requests (Cannot found latest pull request)")
			
		}

		// Set a pull request number
		prNum = pr[0].Number
	}

	// Get a pull request
	pr, _, err := ghClient.PullRequests.Get(ctx, *ownerName, *repoName, *prNum)

	if err != nil {
		panic(err)
	}

	// Get files from a Github pull request
	prFiles, _, err := ghClient.PullRequests.ListFiles(ctx, *ownerName, *repoName, *prNum, nil)

	if err != nil {
		panic(err)
	}

	// Make messages
	message := ""
	texts, sizes := messages.GetFileMessages(prFiles)
	
	for key, text := range texts {
		if sizes[key] == 0 {
			continue
		}

		message += "\n" + text
	}

	// Append sign texts
	if *isSign && message != "" {
		t := time.Now()
		message += "\nWritten by yejun614 github bot at "
		message += t.Format(time.RFC3339) + "\n"
	}
	
	// Edit pull request body texts
	if pr.Body == nil {
		blank := ""
		pr.Body = &blank
	}

	*pr.Body += message

	fmt.Println(" * Append messages length:", len(message))
	fmt.Println(" * Total PR length:", len(*pr.Body))

	// Confirm a updated pull request
	isContinue := true

	if *isConfirm {
		fmt.Println(" ---------- UPDATED ---------- ")
		fmt.Println(*pr.Body)
		fmt.Println(" ----------   END   ---------- ")

		var confirm = ""
		
		for confirm != "y" && confirm != "Y" && confirm != "n" && confirm != "N" {
			fmt.Print("Do you continue ? (Y/N) ")
			fmt.Scanf("%s", &confirm)
		}

		if confirm == "n" || confirm == "N" {
			isContinue = false
		}
	}

	if isContinue {
		pr, _, err = ghClient.PullRequests.Edit(ctx, *ownerName, *repoName, *prNum, pr)

		if err != nil {
			panic(err)
		}

		fmt.Println(" * Successful update a pull request")
	}

	// EXIT
	fmt.Println("Done. (", time.Since(startTime), ")")
}
