/*
 * (9229) 한빈이와 Spot Mart
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AW8Wj7cqbY0DFAXN&categoryId=AW8Wj7cqbY0DFAXN&categoryType=CODE&problemTitle=9229&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

package swea.p9229;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SW Expert Academy - 9229. 한빈이와 Spot Mart
 * @author YeJun, Jung
 *
 * @see #main(String[])
 * 1. 테스트 케이스를 입력받는다.
 * 2. 솔루션을 실행한다.
 * 
 * @see #input()
 * 3. 과자의 개수, 제한 무게를 입력받는다.
 * 4. 과자 무게를 저장할 배열 snacks를 과자의 개수만큼 초기화 한다.
 * 5. 과자 무게를 입력받아 snacks에 저장한다.
 * 
 * @see #solve()
 * 6. snacks 배열을 오름차순 정렬 한다.
 * 7. 정답 변수 answer를 -1로 초기화 한다. (정답이 없는 경우 -1을 출력하기 위함)
 * 8. snacks 배열 index를 저장할 p0, p1을 초기화 한다.
 * 9. 중간 계산 결과를 저장할 current 변수를 선언한다.
 * 10. p0이 p1보다 왼쪽(작은) 상태가 유지될때까지 반복한다.
 * 11. snacks 배열의 p0, p1 index에 위치한 값들을 더해서 current 변수에 저장한다.
 * 12. current 값이 무게 제한보다 큰 경우 p1을 왼쪽으로 줄인다.
 * 13. current 값이 무게 제한 안에 있는 경우
 * 	13-1. answer 값과 current 값을 비교하여 큰 값으로 answer 변수를 갱신한다.
 * 	13-2. p0을 오른쪽으로 키운다.
 * 
 * @see #print()
 * 14. answer 값을 화면에 출력한다.
 */
public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer line;
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스를 입력받는다.
		int testCount = Integer.parseInt(reader.readLine().trim());
		
		for (int testCase = 1; testCase <= testCount; testCase++) {
			// 2. 솔루션을 실행한다.
			new Solution(testCase).run();
		}
	}
	
	private static StringTokenizer getLine() throws IOException {
		line = new StringTokenizer(reader.readLine().trim());
		return line;
	}
	
	// --------------------------------------------------------
	
	private int testCase;
	private int answer;
	
	private int snackCount;
	private int weightLimit;
	private int[] snacks;
	
	public Solution(int testCase) {
		this.testCase = testCase;
	}
	
	public void run() throws IOException {
		input();
		solve();
		print();
	}
	
	private void input() throws IOException {
		// 3. 과자의 개수, 제한 무게를 입력받는다.
		getLine();
		snackCount = Integer.parseInt(line.nextToken());
		weightLimit = Integer.parseInt(line.nextToken());
		
		// 4. 과자 무게를 저장할 배열 snacks를 과자의 개수만큼 초기화 한다.
		snacks = new int[snackCount];
		
		// 5. 과자 무게를 입력받아 snacks에 저장한다.
		getLine();
		for (int index = 0; index < snackCount; index++) {
			snacks[index] = Integer.parseInt(line.nextToken());
		}
	}
	
	private void solve() {
		// 6. snacks 배열을 오름차순 정렬 한다.
		Arrays.sort(snacks);
		
		// 7. 정답 변수 answer를 -1로 초기화 한다. (정답이 없는 경우 -1을 출력하기 위함)
		answer = -1;
		
		// 8. snacks 배열 index를 저장할 p0, p1을 초기화 한다.
		int p0 = 0;
		int p1 = snackCount - 1;
		
		// 9. 중간 계산 결과를 저장할 current 변수를 선언한다.
		int current;
		
		// 10. p0이 p1보다 왼쪽(작은) 상태가 유지될때까지 반복한다.
		while (p0 < p1) {
			// 11. snacks 배열의 p0, p1 index에 위치한 값들을 더해서 current 변수에 저장한다.
			current = snacks[p0] + snacks[p1];
			
			if (current > weightLimit) {
				// 12. current 값이 무게 제한보다 큰 경우 p1을 왼쪽으로 줄인다.
				p1--;
			} else {
				// 13. current 값이 무게 제한 안에 있는 경우
				
				// 13-1. answer 값과 current 값을 비교하여 큰 값으로 answer 변수를 갱신한다.
				answer = Math.max(answer, current);
				// 13-2. p0을 오른쪽으로 키운다.
				p0++;
			}
		}
	}
	
	private void print() throws IOException {
		// 14. answer 값을 화면에 출력한다.
		writer.write("#" + testCase + " " + answer + "\n");
		writer.flush();
	}
}
