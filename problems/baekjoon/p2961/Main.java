/*
 * (2961) 도영이가 만든 맛있는 음식
 * https://www.acmicpc.net/problem/2961
 */

package boj.p2961;

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 도영이가 만든 맛있는 음식
 * @author YeJun, Jung
 *
 * @see #main
 * 1. 솔루션을 실행한다.
 * 
 * @see input()
 * 2. 재료의 개수를 partCount에 저장한다.
 * 3. 각 재료의 신맛은 partS 배열에, 쓴맛은 partB 배열에 저장한다.
 * 
 * @see solve()
 * 4. answer 변수를 최대값으로 초기화한다.
 * 5. 재료의 index를 선택할 비트 마스크를 준비한다.
 * 6. 비트 마스크 select 정수 값을 +1씩 늘려가면서 부분 배열을 생성한다.
 * 7. 선택된 재료를 사용해서 만들어진 음식의 신맛과 쓴맛을 계산한다.
 * 8. 만들어진 음식의 신맛과 쓴맛의 차이가 현재 answer에 저장된 값보다 작다면 값을 업데이트 한다.
 * 
 * @see print()
 * 9. answer 값을 화면에 출력한다.
 */
public class Main {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	private static StringTokenizer input;
	
	public static void main(String[] args) throws IOException {
		// 1. 솔루션을 실행한다.
		new Main().run();
	}
	
	// --------------------------------------------------------
	
	// 모든 재료를 사용해서 요리를 만들었을 때,
	// 그 요리의 신맛과 쓴맛은 모두 1,000,000,000보다 작은 양의 정수이다.
	private final int MAX_VALUE = 1000000100;
	
	private int partCount;
	private int answer;
	
	private int[] partS;
	private int[] partB;
	
	public Main() { }
	
	public void run() throws IOException {
		input();
		solve();
		print();
	}
	
	private void input() throws IOException {
		// 2. 재료의 개수를 partCount에 저장한다.
		partCount = Integer.parseInt(reader.readLine().trim());
		
		// 3. 각 재료의 신맛은 partS 배열에, 쓴맛은 partB 배열에 저장한다.
		partS = new int[partCount];
		partB = new int[partCount];
		
		for (int index = 0; index < partCount; index++) {
			getLine();
			partS[index] = Integer.parseInt(input.nextToken());
			partB[index] = Integer.parseInt(input.nextToken());
		}
	}
	
	private void solve() {
		// 4. answer 변수를 최대값으로 초기화한다.
		answer = MAX_VALUE;
		
		// 5. 재료의 index를 선택할 비트 마스크를 준비한다.
		int select = 1; // 재료를 하나 이상 넣어야 한다
		final int last = 1 << partCount;
		
		// 6. 비트 마스크 select 정수 값을 +1씩 늘려가면서 부분 배열을 생성한다.
		for (; select != last; select++) {
			// 7. 선택된 재료를 사용해서 만들어진 음식의 신맛과 쓴맛을 계산한다.
			int currentS = 1, currentB = 0;
			
			for (int index = 0; index < partCount; index++) {
				if ((select & (1 << index)) == 0) continue;
				currentS *= partS[index];
				currentB += partB[index];
			}
			
			int current = Math.abs(currentS - currentB);
			
			// 8. 만들어진 음식의 신맛과 쓴맛의 차이가 현재 answer에 저장된 값보다 작다면 값을 업데이트 한다.
			if (current < answer) answer = current;
		}
	}
	
	private void print() throws IOException {
		// 9. answer 값을 화면에 출력한다.
		writer.write(answer + "\n");
		writer.flush();
	}
	
	// --------------------------------------------------------
	
	private static void getLine() throws IOException {
		input = new StringTokenizer(reader.readLine().trim());
	}
}
