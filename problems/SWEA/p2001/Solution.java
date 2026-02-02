/*
 * (2001) 파리 퇴치
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PzOCKAigDFAUq&categoryId=AV5PzOCKAigDFAUq&categoryType=CODE&problemTitle=2001&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

package swea.p2001;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * SW Expert Academy - 2001. 파리 퇴치
 * @author YeJun. Jung
 *
 * @see #main(String[])
 * 1. 입출력을 초기화한다.
 * 2. 테스트 케이스를 입력받는다.
 * 3. 솔루션을 실행한다.
 * 
 * @see #Solution(int)
 * 4. 멤버 변수를 초기화 한다.
 * 
 * @see #run()
 * 5. 입력, 해결, 출력 순서로 실행한다.
 * 
 * @see #input()
 * 6. 한줄을 입력받아 띄워쓰기를 기준으로 분리하고 각각 boardSize, cursorSize 변수에 저장한다.
 * 7. boardSize를 +1 늘려서 패딩을 추가한다.
 * 8. cursorSize를 -1 줄여서 현재 좌표를 기준으로한 크기로 수정한다.
 * 9. 파리의 위치 정보를 저장할 board 2차원 배열을 준비한다.
 * 10. board 배열을 입력받는다.
 * 
 * @see #solve()
 * 11. 누적합 행렬을 만든다.
 * 12. 정답 변수인 answer를 초기화 한다.
 * 13. board 배열을 순회하면서 가장 큰 부분합을 찾는다.
 * 
 * @see #print()
 * 14. 정답 배열 내용을 화면에 출력한다.
 */
public class Solution {
	static BufferedReader reader;
	static BufferedWriter writer;
	
	public static void main(String[] args) throws IOException {
		// 1. 입출력을 초기화한다.
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 2. 테스트 케이스를 입력받는다.
		int testCount = Integer.parseInt(reader.readLine().trim());
		
		for (int testCase = 1; testCase <= testCount; testCase++) {
			// 3. 솔루션을 실행한다.
			Solution solution = new Solution(testCase);
			solution.run();
		}
	}
	
	// --------------------------------------------------------
	
	int testCase;
	int answer;
	
	int boardSize;
	int cursorSize;
	int[][] board;
	
	public Solution(int testCase) {
		// 4. 멤버 변수를 초기화 한다.
		this.testCase = testCase;
	}
	
	public void run() throws IOException {
		// 5. 입력, 해결, 출력 순서로 실행한다.
		input();
		solve();
		print();
	}
	
	private void input() throws IOException {
		// 6. 한줄을 입력받아 띄워쓰기를 기준으로 분리하고 각각 boardSize, cursorSize 변수에 저장한다.
		StringTokenizer line;
		line = new StringTokenizer(reader.readLine().trim());
		
		boardSize = Integer.parseInt(line.nextToken());
		cursorSize = Integer.parseInt(line.nextToken());
		
		boardSize++; // 7. boardSize를 +1 늘려서 패딩을 추가한다.
		cursorSize--; // 8. cursorSize를 -1 줄여서 현재 좌표를 기준으로한 크기로 수정한다.
		
		// 9. 파리의 위치 정보를 저장할 board 2차원 배열을 준비한다.
		board = new int[boardSize][boardSize];
		
		// 10. board 배열을 입력받는다.
		for (int y = 1; y < boardSize; y++) {
			line = new StringTokenizer(reader.readLine().trim());
			
			for (int x = 1; x < boardSize; x++) {
				board[y][x] = Integer.parseInt(line.nextToken());
			}
		}
	}
	
	private void solve() {
		// 11. 누적합 행렬을 만든다.
		for (int y = 1; y < boardSize; y++)
			for (int x = 1; x < boardSize; x++)
				board[y][x] += board[y - 1][x] + board[y][x - 1] - board[y - 1][x - 1];

		// 12. 정답 변수인 answer를 초기화 한다.
		answer = 0;
		
		// 13. board 배열을 순회하면서 가장 큰 부분합을 찾는다.
		for (int y = 1; y < boardSize - cursorSize; y++) {
			for (int x = 1; x < boardSize - cursorSize; x++) {
				int current = board[y + cursorSize][x + cursorSize];
				current -= board[y + cursorSize][x - 1] + board[y - 1][x + cursorSize];
				current += board[y - 1][x - 1];
				
				answer = Math.max(answer, current);
			}
		}
	}
	
	private void print() throws IOException {
		// 14. 정답 배열 내용을 화면에 출력한다.
		writer.write("#" + testCase + " " + answer + "\n");
		writer.flush();
	}
}
