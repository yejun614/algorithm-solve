package p3499;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * SW Expert Academy - 3499. 퍼펙트 셔플 D3
 * @author YeJun, Jung
 *
 * @see #main(String[])
 * 1. 테스트 케이스 개수를 입력받는다.
 * 
 * @see #Solution(int testCase)
 * 2. 솔루션을 초기화한다.
 * 	2-1. 테스트 케이스를 저장한다.
 * 
 * @see #run()
 * 3. 솔루션을 실행한다. (입력, 해결, 출력)
 * 
 * @see #input()
 * 4. 자연수 N(1 <= N <= 1,000)을 입력받는다.
 * 5. 카드를 입력받는다.
 * 	5-1. 카드를 문자열 배열에 입력받은 순서대로 cards 배열에 저장한다.
 * 
 * @see #solve()
 * 6. 정답을 저장할 문자열 배열 answer를 초기화 한다.
 * 7. 입력받은 카드의 개수를 2로 나누어 halfCardLength 변수에 저장한다.
 * 8. 정수를 저장하는 변수 indexA를 0으로 초기화한다.
 * 9. 정수를 저장하는 변수 indexB를 halfCardLength으로 초기화한다.
 * 10. cardLength가 짝수라면 indexB 변수에 +1을 더한다.
 * 11. 1부터 halfCardLength까지 count 변수를 사용해서 반복한다.
 * 	11-1. cards[indexA], cards[indexB]를 answer 배열에 저장한다.
 * 	11-2. indexA, indexB 변수에 각각 +1을 더한다.
 * 12. cardLength가 짝수라면 cards[indexA]를 answer 배열에 저장한다.
 * 
 * @see #print()
 * 13. 테스트 케이스를 화면에 출력한다.
 * 14. answer 배열을 순회한다.
 * 	14-1. 배열 요소를 화면에 출력한다.
 * 	14-2. 마지막으로 \n을 화면에 출력한다.
 */
public class Solution {
	static BufferedReader reader;
	static BufferedWriter writer;
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 1. 테스트 케이스 개수를 입력받는다.
		int testCount = Integer.parseInt(reader.readLine().trim());
		
		for (int testCase = 1; testCase <= testCount; testCase++) {
			// 2. 솔루션을 초기화한다.
			Solution solution = new Solution(testCase);
			solution.run();
		}
	}
	
	int testCase;
	int N;
	String[] cards;
	String[] answer;
	
	public Solution(int testCase) {
		// 2-1. 테스트 케이스를 저장한다.
		this.testCase = testCase;
	}
	
	public void run() throws IOException {
		// 3. 솔루션을 실행한다. (입력, 해결, 출력)
		input();
		solve();
		print();
	}
	
	private void input() throws IOException {
		// 4. 자연수 N(1 <= N <= 1,000)을 입력받는다.
		N = Integer.parseInt(reader.readLine().trim());
		
		// 5. 카드를 입력받는다.
		cards = new String[N];
		
		// 5-1. 카드를 문자열 배열에 입력받은 순서대로 cards 배열에 저장한다.
		String cardsInput = reader.readLine().trim();
		StringTokenizer cardsTokenizer = new StringTokenizer(cardsInput);
		
		for (int index = 0; index < N; index++) {
			cards[index] = cardsTokenizer.nextToken();
		}
	}
	
	private void solve() throws IOException {
		// 6. 정답을 저장할 문자열 배열 answer를 초기화 한다.
		answer = new String[N];
		
		// 7. 입력받은 카드의 개수를 2로 나누어 halfCardLength 변수에 저장한다.
		int halfCardLength = N / 2;
		
		int index = 0;
		int indexA = 0; // 8. 정수를 저장하는 변수 indexA를 0으로 초기화한다.
		int indexB = halfCardLength; // 9. 정수를 저장하는 변수 indexB를 halfCardLength으로 초기화한다.
		
		// 10. cardLength가 짝수라면 indexB 변수에 +1을 더한다.
		boolean isOdd = N % 2 != 0;
		if (isOdd) indexB++;
		
		// 11. 1부터 halfCardLength까지 count 변수를 사용해서 반복한다.
		for (int count = 0; count < halfCardLength; count++) {
			// 11-1. cards[indexA], cards[indexB]를 answer 배열에 저장한다.
			// 11-2. indexA, indexB 변수에 각각 +1을 더한다.
			answer[index++] = cards[indexA++];
			answer[index++] = cards[indexB++];
		}
		
		// 12. cardLength가 짝수라면 cards[indexA]를 answer 배열에 저장한다.
		if (isOdd) answer[index] = cards[indexA];
	}
	
	private void print() throws IOException {
		// 13. 테스트 케이스를 화면에 출력한다.
		writer.write("#" + testCase + " ");
		
		// 14. answer 배열을 순회한다.
		for (int index = 0; index < N; index++) {
			// 14-1. 배열 요소를 화면에 출력한다.
			writer.write(answer[index] + " ");
		}
		
		// 14-2. 마지막으로 \n을 화면에 출력한다.
		writer.write("\n");
		writer.flush();
	}
}
