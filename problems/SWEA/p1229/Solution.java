/*
 * (1229) [S/W 문제해결 기본] 8일차 - 암호문2
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14yIsqAHYCFAYD&categoryId=AV14yIsqAHYCFAYD&categoryType=CODE&problemTitle=1229&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

package swea.p1229;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SW Expert Academy - 1229. [S/W 문제해결 기본] 8일차 - 암호문2
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
 * 6. 원본 암호문의 길이를 originalSize 변수에 저장한다.
 * 7. 원본 암호문 내용을 original 배열에 저장한다.
 * 
 * @see #solve()
 * 8. 시뮬레이션을 진행할 연결 리스트 memory 객체를 준비한다.
 * 9. 원본 암호문 내용을 memory에 올린다.
 * 10. 명령어의 개수를 commandLength 변수에 저장한다.
 * 11. 한 줄을 입력받아 띄어쓰기를 기준으로 분리한다.
 * 12. 명령어에 따라 시뮬레이션을 시작한다.
 * 	12-1. 명령어를 opcode, index, count로 분리한다.
 * 	12-2. opcode가 I라면 버퍼를 입력받아 memory객체의 index에 삽입한다.
 * 	12-3. opcode가 D라면 memory 객체의 index부터 count 개수 만큼 삭제한다.
 * 13. memory 내용을 iterator로 변환한다.
 * 14. 정답으로 출력할 내용의 개수를 계산한다.
 * 15. 정답 배열에 memory의 내용을 최대 10개까지 저장한다.
 * 
 * @see #print()
 * 16. 정답 배열 내용을 화면에 출력한다.
 */
public class Solution {
	static BufferedReader reader;
	static BufferedWriter writer;
	
	public static void main(String[] args) throws IOException {
		// 1. 입출력을 초기화한다.
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		final int testCount = 10;
		
		// 2. 테스트 케이스를 입력받는다.
		for (int testCase = 1; testCase <= testCount; testCase++) {
			// 3. 솔루션을 실행한다.
			Solution solution = new Solution(testCase);
			solution.run();
		}
	}
	
	// --------------------------------------------------------
	
	int testCase;
	String[] answer;
	
	int originalSize;
	String[] original;
	
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
		// 6. 원본 암호문의 길이를 originalSize 변수에 저장한다.
		originalSize = Integer.parseInt(reader.readLine().trim());
		
		// 7. 원본 암호문 내용을 original 배열에 저장한다.
		original = new String[originalSize];
		
		StringTokenizer codeTokenizer = new StringTokenizer(reader.readLine().trim());
		for (int index = 0; index < originalSize; index++) {
			original[index] = codeTokenizer.nextToken();
		}
	}
	
	private void solve() throws IOException {
		// 8. 시뮬레이션을 진행할 연결 리스트 memory 객체를 준비한다.
		LinkedList<String> memory = new LinkedList<>();
		
		// 9. 원본 암호문 내용을 memory에 올린다.
		for (String code : original) memory.add(code);
		
		// 10. 명령어의 개수를 commandLength 변수에 저장한다.
		int commandLength = Integer.parseInt(reader.readLine().trim());
		// 11. 한 줄을 입력받아 띄어쓰기를 기준으로 분리한다.
		StringTokenizer commandTokenizer = new StringTokenizer(reader.readLine().trim());
		
		// 12. 명령어에 따라 시뮬레이션을 시작한다.
		for (int commandIndex = 0; commandIndex < commandLength; commandIndex++) {
			// 12-1. 명령어를 opcode, index, count로 분리한다.
			String opcode = commandTokenizer.nextToken();
			int index = Integer.parseInt(commandTokenizer.nextToken());
			int count = Integer.parseInt(commandTokenizer.nextToken());
			
			if (opcode.equals("I")) {
				// 12-2. opcode가 I라면 버퍼를 입력받아 memory객체의 index에 삽입한다.
				List<String> buffer = new ArrayList<>();
				for (int current = 0; current < count; current++) {
					buffer.add(commandTokenizer.nextToken());
				}
				memory.addAll(index, buffer);
			} else if (opcode.equals("D")) {
				// 12-3. opcode가 D라면 memory 객체의 index부터 count 개수 만큼 삭제한다.
				for (int current = 0; current < count; current++) {
					memory.remove(index);
				}
			}
		}
		
		// 13. memory 내용을 iterator로 변환한다.
		Iterator<String> iter = memory.iterator();
		
		// 14. 정답으로 출력할 내용의 개수를 계산한다.
		int answerCount = Math.min(10, memory.size());
		answer = new String[answerCount];
		
		// 15. 정답 배열에 memory의 내용을 최대 10개까지 저장한다.
		for (int index = 0; index < answerCount; index++) {
			answer[index] = iter.next();
		}
	}
	
	private void print() throws IOException {
		// 16. 정답 배열 내용을 화면에 출력한다.
		writer.write("#" + testCase);
		for (String code : answer) writer.write(" " + code);
		writer.write("\n");
		writer.flush();
	}
}
