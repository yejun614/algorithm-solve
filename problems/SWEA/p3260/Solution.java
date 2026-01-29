package p3260;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * SW Expert Academy - 3260. 두 수의 덧셈
 * @author YeJun, Jung
 *
 * @see #main
 * 1. 입출력 초기화를 한다.
 * 2. 테스트 케이스를 입력 받는다.
 * 3. 솔루션을 실행한다.
 * 
 * @see #Solution(int)
 * 4. 멤버 변수를 초기화 한다.
 * 
 * @see #run()
 * 5. 입력, 해결, 출력 순서로 솔루션을 실행한다.
 * 
 * @see #input()
 * 6. 한 라인을 입력받아 input 변수에 저장한다.
 * 7. input 문자열 내용을 white space를 기준으로 분리한다.
 * 8. BigInteger로 입력받은 정수를 A, B 변수에 저장한다.
 * 
 * @see #solve()
 * 9. A + B 결과를 answer 변수에 저장한다.
 * 
 * @see #print()
 * 10. answer 변수 내용을 화면에 출력한다.
 */
public class Solution {
	static BufferedReader reader;
	static BufferedWriter writer;
	
	public static void main(String[] args) throws IOException {
		// 1. 입출력 초기화를 한다.
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 2. 테스트 케이스를 입력 받는다.
		int testCount = Integer.parseInt(reader.readLine().trim());
		
		for (int testCase = 1; testCase <= testCount; testCase++) {
			// 3. 솔루션을 실행한다.
			Solution solution = new Solution(testCase);
			solution.run();
		}
	}
	
	int testCase;
	BigInteger A;
	BigInteger B;
	BigInteger answer;
	
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
		// 6. 한 라인을 입력받아 input 변수에 저장한다.
		String input = reader.readLine().trim();
		
		// 7. input 문자열 내용을 white space를 기준으로 분리한다.
		StringTokenizer inputTokenizer = new StringTokenizer(input);
		
		// 8. BigInteger로 입력받은 정수를 A, B 변수에 저장한다.
		A = new BigInteger(inputTokenizer.nextToken());
		B = new BigInteger(inputTokenizer.nextToken());
	}
	
	private void solve() {
		// 9. A + B 결과를 answer 변수에 저장한다.
		answer = A.add(B);
	}
	
	private void print() throws IOException {
		// 10. answer 변수 내용을 화면에 출력한다.
		writer.write("#" + testCase + " " + answer + "\n");
		writer.flush();
	}
}
