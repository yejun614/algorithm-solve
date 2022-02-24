// (220223 ~ 220224) Algorithm Judge v0.1 DEV
// Developer: YeJun, Jung (yejun614@naver.com)
// License  : MIT License

package main

import (
	"io"
	"os"
	"fmt"
	"flag"
	"time"
	"errors"
	"strings"
	"os/exec"
	"strconv"
	"runtime"
	"context"
	"io/ioutil"
)

type TaskResult struct {
	Number      int
	Input       string
	Target      string
	Answer      string
	IsRight     bool
	Err         error
	RunningTime time.Duration
}

func (taskResult *TaskResult) PrintDetails(w io.Writer, isLine bool) {
	fmt.Fprintf(w, "\n [TASK DETAILS]\n")
	fmt.Fprintf(w, " Number       : %v\n", taskResult.Number)
	fmt.Fprintf(w, " Running Time : %v\n", taskResult.RunningTime)
	fmt.Fprintf(w, " IsRight      : %v\n", taskResult.IsRight)
	fmt.Fprintf(w, " LAST ERROR   : %v\n", taskResult.Err)
	
	fmt.Fprintf(w, "\n INPUT:\n")
	if isLine {
		PrintWithLines(w, &taskResult.Input)
	} else {
		fmt.Fprintf(w, "%s\n", taskResult.Input)
	}

	fmt.Fprintf(w, "\n TARGET:\n")
	
	if isLine {
		PrintWithLines(w, &taskResult.Target)
	} else {
		fmt.Fprintf(w, "%s\n", taskResult.Target)
	}

	fmt.Fprintf(w, "\n ANSWER:\n")
	
	if isLine {
		PrintWithLines(w, &taskResult.Answer)
	} else {
		fmt.Fprintf(w, "%s\n", taskResult.Answer)
	}
}

func GetFlags() (targetProg *string, answerProg *string, genProg *string, runningNum *int, isAllResult *bool, saveFileName *string, cpuCount *int) {
	targetProg = flag.String("t", "", "Target program")
	answerProg = flag.String("a", "", "Answer program")
	genProg = flag.String("g", "", "Input Generator")
	runningNum = flag.Int("n", 10, "Number of testing")
	isAllResult = flag.Bool("all", false, "Show All Failed Results")
	saveFileName = flag.String("s", "", "Save output file")
	cpuCount = flag.Int("cpu", -1, "Number of CPU Cores (-1 is All CPU Cores)")

	flag.Parse()

	return
}

func IsFileExist(fileName string, isPanic bool) bool {
	_, err := os.Stat(fileName)
	result := err == nil

	if isPanic && !result {
		panic(err)
	}

	return result
}

func runCmd(command string, input string, timeout time.Duration) ([]byte, error) {
	// Set Command
	ctx, _ := context.WithTimeout(context.Background(), timeout)
	str := strings.Split(command, " ")
	var cmd *exec.Cmd

	if len(str) == 1 {
		cmd = exec.CommandContext(ctx, str[0])
			
	} else {
		cmd = exec.CommandContext(ctx, str[0], str[1:]...)
	}

	// Stdin
	stdin, err := cmd.StdinPipe()
	if err != nil {
		return nil, err
	}
	
	defer stdin.Close()

	// Stdout
	stdout, err := cmd.StdoutPipe()
	if err != nil {
		return nil, err
	}

	defer stdout.Close()

	// Start Command
	if err := cmd.Start(); err != nil {
		return nil, err
	}

	// Input
	if input != "" {
		io.WriteString(stdin, input)
	}

	// Output
	output, err := ioutil.ReadAll(stdout)
	if err != nil {
		return nil, err
	}

	// Timeout
	if ctx.Err() == context.DeadlineExceeded {
		return nil, errors.New("Sub Process Timeout")
	}

	return output, nil
}

func runTask(result chan TaskResult, num int, targetProg *string, answerProg *string, genProg *string) {
	startTime := time.Now()

	var taskResult TaskResult
	taskResult.Number = num

	inputs, err := runCmd(*genProg, strconv.Itoa(num) + "\n", time.Second * 3)
	if err != nil {
		fmt.Printf(" [!] %d Task 'Generator': %s\n", num, err)
		
		taskResult.Err = err
		return
	}
	
	taskResult.Input = string(inputs)

	targetOut, err := runCmd(*targetProg, taskResult.Input, time.Second * 3)
	if err != nil {
		fmt.Printf(" [!] %d Task 'Target Program': %s\n", num, err)
		taskResult.Err = err
	}
	
	answerOut, err := runCmd(*answerProg, taskResult.Input, time.Second * 3)
	if err != nil {
		fmt.Printf(" [!] %d Task 'Answer Program': %s\n", num, err)
		taskResult.Err = err
	}

	taskResult.Target = strings.TrimRight(string(targetOut), "\n")
	taskResult.Answer = strings.TrimRight(string(answerOut), "\n")

	taskResult.IsRight = taskResult.Target == taskResult.Answer
	taskResult.RunningTime = time.Since(startTime)

	result <- taskResult
}

func PrintWithLines(w io.Writer, src *string) {
	lines := strings.Split(*src, "\n")
	count := 1

	for _, line := range lines {
		fmt.Fprintf(w, " %3d | %s\n", count, line)
		count ++
	}
}

func FindDiffPos(A *string, B *string) (row int, col int) {
	row = 1
	col = 1
	
	index := 0

	lenA := len(*A)
	lenB := len(*B)

	for index < lenA && index < lenB {
		if (*A)[index] != (*B)[index] {
			break
			
		} else if (*A)[index] == '\n' {
			row ++
			col = 1
		}
	
		index ++
	}

	return
}

func main() {
	startTime := time.Now()

	// Welcome
	fmt.Println("Algorithm Judge v0.1 DEV\n")

	// Get flags
	targetProg, answerProg, genProg, runningNum, isAllResult, saveFileName, cpuCount := GetFlags()

	// Set CPU Cores
	numCPU := *cpuCount

	if numCPU == -1 {
		numCPU = runtime.NumCPU()
	}
	
	runtime.GOMAXPROCS(numCPU)

	// Set exec limits
	maxExecNums := 10

	// Print flags
	fmt.Println("[Target   ]", *targetProg)
	fmt.Println("[Answer   ]", *answerProg)
	fmt.Println("[Generator]", *genProg)
	fmt.Println("[Nums     ]", *runningNum)
	fmt.Println("[CPUs     ]", numCPU)
	fmt.Println("[MAX EXEC ]", maxExecNums)
	fmt.Print("\n")

	// Testing
	result := make(chan TaskResult)
	doneTaskCount := 0
	wrongAnswerCount := 0

	for i := 0; i < *runningNum; i ++ {
		go runTask(result, i, targetProg, answerProg, genProg)
	}

	// Waiting tasks
	fmt.Println(" * All tasks is working")

	taskResults := []TaskResult {}

	for doneTaskCount < *runningNum {
		current := <- result
		taskResults = append(taskResults, current)
		
		doneTaskCount ++
	}

	close(result)

	fmt.Println(" * All tasks finished")

	// Set writer
	writer := os.Stdout

	// Save File
	var saveFile *os.File
	isSaveFile := *saveFileName != ""
	
	if isSaveFile {
		var err error
		saveFile, err = os.Create(*saveFileName)

		if err != nil {
			fmt.Println(" * SAVE FILE ERROR:", err)
			
		} else {
			writer = saveFile
			defer saveFile.Close()
		}
	}

	// Print or Write Task Results
	for _, taskResult := range taskResults {
		if !taskResult.IsRight || taskResult.Err != nil {
			wrongAnswerCount ++
			
			fmt.Fprintf(writer, "\n----------------------------\n")
			taskResult.PrintDetails(writer, !isSaveFile)
			
			row, col := FindDiffPos(&taskResult.Target, &taskResult.Answer)
			fmt.Fprintf(writer, "\n Diff (%d,%d)\n", row, col)

			if !(*isAllResult) {
				break
			}
		}
	}

	fmt.Printf("\n * %d Wrong Answers Found!\n", wrongAnswerCount)

	// EXIT
	fmt.Printf("Done. ( %s )\n", time.Since(startTime))
}
