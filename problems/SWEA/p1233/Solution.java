/*
 * (1233) [S/W 문제해결 기본] 9일차 - 사칙연산 유효성 검사
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV141176AIwCFAYD&categoryId=AV141176AIwCFAYD&categoryType=CODE&problemTitle=1233&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

package swea.p1233;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * SW Expert Academy - 1233. [S/W 문제해결 기본] 9일차 - 사칙연산 유효성 검사
 * @author YeJun, Jung
 *
 * @see #main(String[])
 * 1. 솔루션을 실행한다.
 * 
 * @see #input()
 * 2. 노드의 개수를 입력받아 nodeCount에 저장한다.
 * 3. 그래프를 저장할 graphValues, graphChildren 배열을 nodeCount 개수 만큼 할당한다.
 * 4. nodeCount 만큼 반복하면서 노드 데이터를 입력받는다.
 * 5. 노드 값은 graphValues 배열에 저장한다.
 * 6. 노드의 자식은 graphChildren 배열에 저장한다.
 * 
 * @see #solve()
 * 7. 문제에서 말하는 올바른 그래프가 되기 위해서 숫자는 모두 리프노드에 위치해야 하며, 리프노드 외의 나머지 노드들은
 *    연산자를 값으로 가지고 있어야 한다.
 * 8. 정답 변수 answer의 값을 0으로 초기화한다.
 * 9. 모든 노드를 방문한다. (노드의 번호는 1부터 시작한다)
 * 	9-1. 현재 노드가 리프노드인지 여부를 isLeafNode에 저장하고, 현재 노드의 값이 숫자인지 여부를 isNumber에 저장한다.
 * 	9-2. 현재 노드가 (리프노드인데 숫자가 아니거나), (리프노드가 아닌데 숫자라면) 잘못된 구조이므로 answer가 0인 상태로 함수를 종료한다.
 * 10. 그래프 구조가 정상인 경우 answer값으로 1로 변경한다.
 * 
 * @see #print()
 * 11. answer값을 화면에 출력한다.
 */
public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer line;
	
	public static void main(String[] args) throws IOException {
		final int testCount = 10;
		
		for (int testCase = 1; testCase <= testCount; testCase++) {
			// 1. 솔루션을 실행한다.
			new Solution(testCase).run();
		}
	}
	
	private static void getLine() throws IOException {
		line = new StringTokenizer(reader.readLine().trim());
	}
	
	// --------------------------------------------------------
	
	private int testCase;
	private int answer;
	
	private int nodeCount;
	private String[] graphValues; // 노드 번호는 1부터 시작한다
	private int[][] graphChildren; // 왼쪽, 오른쪽 순서로 저장(자식이 없다면 0 저장)
	
	public Solution(int testCase) {
		this.testCase = testCase;
	}
	
	public void run() throws IOException {
		input();
		solve();
		print();
	}
	
	private void input() throws IOException {
		// 2. 노드의 개수를 입력받아 nodeCount에 저장한다.
		nodeCount = Integer.parseInt(reader.readLine().trim());
		
		// 3. 그래프를 저장할 graphValues, graphChildren 배열을 nodeCount 개수 만큼 할당한다.
		graphValues = new String[nodeCount + 1];
		graphChildren = new int[nodeCount + 1][2];
		
		// 4. nodeCount 만큼 반복하면서 노드 데이터를 입력받는다.
		for (int lineCount = 0; lineCount < nodeCount; lineCount++) {
			getLine();
			
			// 5. 노드 값은 graphValues 배열에 저장한다.
			int nodeIndex = Integer.parseInt(line.nextToken());
			graphValues[nodeIndex] = line.nextToken();
			
			// 6. 노드의 자식은 graphChildren 배열에 저장한다.
			if (line.hasMoreTokens()) graphChildren[nodeIndex][0] = Integer.parseInt(line.nextToken());
			if (line.hasMoreTokens()) graphChildren[nodeIndex][1] = Integer.parseInt(line.nextToken());
		}
	}
	
	private void solve() {
		// 7. 문제에서 말하는 올바른 그래프가 되기 위해서 숫자는 모두 리프노드에 위치해야 하며, 리프노드 외의 나머지 노드들은
		//    연산자를 값으로 가지고 있어야 한다.
		
		// 8. 정답 변수 answer의 값을 0으로 초기화한다.
		answer = 0;
		
		// 9. 모든 노드를 방문한다. (노드의 번호는 1부터 시작한다)
		for (int index = 1; index <= nodeCount; index++) {
			// 9-1. 현재 노드가 리프노드인지 여부를 isLeafNode에 저장하고, 현재 노드의 값이 숫자인지 여부를 isNumber에 저장한다.
			boolean isLeafNode = graphChildren[index][0] == 0 && graphChildren[index][1] == 0;
			boolean isNumber = Character.isDigit(graphValues[index].charAt(0));
			
			// 9-2. 현재 노드가 (리프노드인데 숫자가 아니거나), (리프노드가 아닌데 숫자라면) 잘못된 구조이므로 answer가 0인 상태로 함수를 종료한다.
			if (isLeafNode ^ isNumber) return;
		}
		
		// 10. 그래프 구조가 정상인 경우 answer값으로 1로 변경한다.
		answer = 1;
	}
	
	private void print() throws IOException {
		// 11. answer값을 화면에 출력한다.
		writer.write("#" + testCase + " " + answer + "\n");
		writer.flush();
	}
}
