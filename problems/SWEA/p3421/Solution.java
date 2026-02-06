/*
 * (3421) 수제 버거 장인
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWErcQmKy6kDFAXi&categoryId=AWErcQmKy6kDFAXi&categoryType=CODE&problemTitle=3421&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

package swea.p3421;

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 3421. 수제 버거 장인
 * @author YeJun, Jung
 *
 * @see #main(String[] args)
 * 1. 테스트 케이스를 입력 받는다.
 * 2. 솔루션을 실행한다.
 * 
 * @see #input()
 * 3. 재료의 개수를 partCount에, 재료 궁합 정보의 개수를 edgeCount에 저장한다.
 * 4. 재료들의 궁합 정보를 저장할 graph 배열을 초기화 한다.
 * 5. 각 재료들의 궁합 정보를 graph 하위 비트 마스크에 기록한다.
 * 
 * @see #solve()
 * 6. 정답을 저장할 answer 변수를 초기화한다.
 * 7. 부분집합을 카운트 한다.
 * 
 * @see #countSubset()
 * 8. 비트마스크 select 배열을 사용해서 부분집합을 만들 요소를 선택한다.
 * 9. 선택된 재료를 bugger 배열에 저장한다.
 * 10. 만들어진 버거의 재료들이 서로 궁합이 맞는지 확인하여 answer 변수를 업데이트 한다.
 * 
 * @see #isCorrectBugger(List)
 * 11. 버거에 들어간 재료의 개수를 N에 저장한다.
 * 12. 재료 두개를 선택해서 서로 궁합이 맞는지 graph에 저장된 비트마스크로 확인한다.
 * 
 * @see #print()
 * 13. answer 값을 화면에 출력한다.
 */
public class Solution {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	private static StringTokenizer input;
	
	// --------------------------------------------------------
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트 케이스를 입력 받는다.
		final int testCount = Integer.parseInt(reader.readLine().trim());
		
		for (int testCase = 1; testCase <= testCount; testCase++) {
			// 2. 솔루션을 실행한다.
			new Solution(testCase).run();
		}
	}
	
	// --------------------------------------------------------
	
	private int testCase;
	private int answer;
	
	private int partCount;
	private List<Integer> graph;
	
	public Solution(int testCase) {
		this.testCase = testCase;
	}
	
	public void run() throws IOException {
		input();
		solve();
		print();
	}
	
	private void input() throws IOException {
		// 3. 재료의 개수를 partCount에, 재료 궁합 정보의 개수를 edgeCount에 저장한다.
		getLine();
		partCount = Integer.parseInt(input.nextToken());
		int edgeCount = Integer.parseInt(input.nextToken());
		
		// 4. 재료들의 궁합 정보를 저장할 graph 배열을 초기화 한다.
		graph = new ArrayList<>(partCount);
		for (int count = 0; count <= partCount; count++) {
			graph.add(0);
		}
		
		for (int count = 0; count < edgeCount; count++) {
			getLine();
			int nodeA = Integer.parseInt(input.nextToken());
			int nodeB = Integer.parseInt(input.nextToken());
			
			if (nodeA == nodeB) continue;
			
			// 5. 각 재료들의 궁합 정보를 graph 하위 비트 마스크에 기록한다.
			graph.set(nodeA, graph.get(nodeA) | (1 << nodeB));
			graph.set(nodeB, graph.get(nodeB) | (1 << nodeA));
		}
	}
	
	private void solve() {
		// 6. 정답을 저장할 answer 변수를 초기화한다.
		answer = 0;
		
		// 7. 부분집합을 카운트 한다.
		countSubset();
	}
	
	private void countSubset() {
		// 8. 비트마스크 select 배열을 사용해서 부분집합을 만들 요소를 선택한다.
		int select = 0;
		int last = 1 << partCount;
		
		for (; select != last; select++) {
			// 9. 선택된 재료를 bugger 배열에 저장한다.
			List<Integer> bugger = new ArrayList<>();
			for (int index = 0; index < partCount; index++) {
				if ((select & (1 << index)) == 0) continue;
				bugger.add(index + 1);
			}
			
			// 10. 만들어진 버거의 재료들이 서로 궁합이 맞는지 확인하여 answer 변수를 업데이트 한다.
			if (isCorrectBugger(bugger)) answer++;
		}
	}
	
	private boolean isCorrectBugger(List<Integer> bugger) {
		// 11. 버거에 들어간 재료의 개수를 N에 저장한다.
		int N = bugger.size();
		
		// 12. 재료 두개를 선택해서 서로 궁합이 맞는지 graph에 저장된 비트마스크로 확인한다.
		for (int indexA = 0; indexA < N; indexA++) {
			int partA = bugger.get(indexA);
			
			for (int indexB = indexA + 1; indexB < N; indexB++) {
				int partB = bugger.get(indexB);
				
				if ((graph.get(partA) & (1 << partB)) != 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private void print() throws IOException {
		// 13. answer 값을 화면에 출력한다.
		writer.write("#" + testCase);
		writer.write(" " + answer);
		writer.write("\n");
		writer.flush();
	}
	
	// --------------------------------------------------------
	
	private static void getLine() throws IOException {
		input = new StringTokenizer(reader.readLine().trim());
	}
}
