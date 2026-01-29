package p1225;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SW Expert Academy - 1225. [S/W 문제해결 기본] 7일차 - 암호생성기
 * @author YeJun, Jung
 * 
 * @see #main
 * 1. 입출력을 초기화 한다.
 * 2. 테스트 케이스를 입력받는다.
 * 3. 솔루션을 실행한다.
 * 
 * @see #Solution(int)
 * 4. 멤버 변수를 초기화 한다.
 * 
 * @see #run()
 * 5. 입력, 해결, 출력 순서로 솔루션을 실행한다.
 * 
 * @see #input()
 * 6. 큐를 하나 준비한다.
 * 7. 한 라인을 입력받는다.
 * 8. 입력받은 내용을 white space 기준으로 분리한다.
 * 9. 분리한 내용을 큐에 삽입한다.
 * 
 * @see #solve()
 * 10. 싸이클을 실행한다.
 * 13. 큐에 저장된 데이터를 answer 배열로 옮긴다.
 * 
 * @see #runCycle()
 * 11. num 정수 변수를 0으로 초기화 한다.
 * 12. 무한 반복하면서
 * 	12-1. 5회 반복을 실시하면서 count 변수로 숫자를 센다.
 * 	12-2. 큐의 제일 앞에 있는 요소를 꺼내어 count만큼 뺀다.
 * 	12-3. 계산을 마친 요소가 0 이하라면 함수를 중단한다.
 * 	12-4. 계산을 마친 요소가 0 초과라면 큐에 삽입하고 다음 반복을 진행한다.
 * 
 * @see #print()
 * 14. 결과 배열 answer 내용을 화면에 출력한다.
 */
public class Solution {
	static BufferedReader reader;
	static BufferedWriter writer;
	
	public static void main(String[] args) throws IOException {
		// 1. 입출력을 초기화 한다.
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testCase = 0;
		
		while (testCase != 10) {
			// 2. 테스트 케이스를 입력받는다.
			testCase = Integer.parseInt(reader.readLine().trim());
			
			// 3. 솔루션을 실행한다.
			Solution solution = new Solution(testCase);
			solution.run();
		}
	}
	
	int testCase;
	final int N = 8; // 주어지는 데이터는 8개로 고정됨
	final int CycleCount = 5;
	Queue<Integer> data;
	int[] answer;
	
	public Solution(int testCase) {
		// 4. 멤버 변수를 초기화 한다.
		this.testCase = testCase;
	}
	
	public void run() throws IOException {
		// 5. 입력, 해결, 출력 순서로 솔루션을 실행한다.
		input();
		solve();
		print();
	}
	
	private void input() throws IOException {
		// 6. 큐를 하나 준비한다.
		data = new ArrayDeque<>();
		
		// 7. 한 라인을 입력받는다.
		String input = reader.readLine().trim();
		
		// 8. 입력받은 내용을 white space 기준으로 분리한다.
		StringTokenizer inputTokenizer = new StringTokenizer(input);
		
		// 9. 분리한 내용을 큐에 삽입한다.
		for (int index = 0; index < N; index++) {
			data.offer(Integer.parseInt(inputTokenizer.nextToken()));
		}
	}
	
	private void solve() {
		// 10. 싸이클을 실행한다.
		runCycle();
		
		// 13. 큐에 저장된 데이터를 answer 배열로 옮긴다.
		answer = new int[N]; // 배열의 각 요소는 기본값 0으로 초기화 됨
		for (int index = 0; index < N - 1; index++) {
			answer[index] = data.poll();
		}
	}
	
	private void runCycle() {
		// 11. num 정수 변수를 0으로 초기화 한다.
		int num = 0;
		
		// 12. 무한 반복하면서
		while (true) {
			// 12-1. 5회 반복을 실시하면서 count 변수로 숫자를 센다.
			for (int count = 1; count <= CycleCount; count++) {
				// 12-2. 큐의 제일 앞에 있는 요소를 꺼내어 count만큼 뺀다.
				num = data.poll();
				num -= count;
				
				// 12-3. 계산을 마친 요소가 0 이하라면 함수를 중단한다.
				if (num <= 0) return;
				
				// 12-4. 계산을 마친 요소가 0 초과라면 큐에 삽입하고 다음 반복을 진행한다.
				data.offer(num);
			}
		}
	}
	
	private void print() throws IOException {
		// 14. 결과 배열 answer 내용을 화면에 출력한다.
		writer.write("#" + testCase + " ");
		for (int index = 0; index < answer.length; index++) {
			writer.write(answer[index] + " ");
		}
		writer.write("\n");
		writer.flush();
	}
}
