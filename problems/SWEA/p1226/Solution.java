/*
 * (1226) [S/W 문제해결 기본] 7일차 - 미로1
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14vXUqAGMCFAYD
 */

package swea.p1226;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

/**
 * SW Export Academy - 1226. [S/W 문제해결 기본] 7일차 - 미로1
 * @author YeJun, Jung
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
 * 6. 미로를 저장할 board 배열을 초기화한다.
 * 7. 미로를 입력받아 board 배열에 저장한다.
 * 	7-1. 출발지와 도착지를 발견하면 저장해둔다.
 * 
 * @see #solve()
 * 8. 정답 변수 answer를 초기화 한다.
 * 9. 좌표를 보관하는 큐를 선언한다.
 * 	9-1. 큐에 시작 좌표를 추가한다.
 * 10. BFS 방식으로 미로를 탐사한다.
 * 	10-1. 큐의 가장 앞에 있는 요소를 꺼낸다.
 * 	10-2. 상하좌우 4방향으로 미로를 이동하면서 탐사한다.
 * 	10-3. 목적지에 도착했다면 정답을 기록하고 탐사를 중단한다.
 * 	10-4. 길이 존재한다면 큐에 넣는다.
 * 	10-5. 큐에 넣은 길은 방문 처리 한다.
 * 
 * @see #print()
 * 11. 정답을 출력한다.
 */
public class Solution {
	static BufferedReader reader;
	static BufferedWriter writer;
	
	public static void main(String[] args) throws IOException {
		// 1. 입출력을 초기화한다.
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		final int testCount = 10;
		
		for (int count = 1; count <= testCount; count++) {
			// 2. 테스트 케이스를 입력받는다.
			int testCase = Integer.parseInt(reader.readLine().trim());
			
			// 3. 솔루션을 실행한다.
			Solution solution = new Solution(testCase);
			solution.run();
		}
	}
	
	// ----------------------------------------------------------------
	
	static final int BOARD_SIZE = 16;
	
	static final int[] DIR_X = { -1,  1,  0,  0 };
	static final int[] DIR_Y = {  0,  0, -1,  1 };
	static final int DIR_LEN = 4;
	
	private int testCase;
	private int answer;
	
	private char[][] board;
	
	private Pos current;
	private Pos goal;
	
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
		// 6. 미로를 저장할 board 배열을 초기화한다.
		board = new char[BOARD_SIZE][0];
		
		for (int y = 0; y < BOARD_SIZE; y++) {
			// 7. 미로를 입력받아 board 배열에 저장한다.
			board[y] = reader.readLine().trim().toCharArray();
			
			// 7-1. 출발지와 도착지를 발견하면 저장해둔다.
			for (int x = 0; x < BOARD_SIZE; x++) {
				if (board[y][x] == '2') {
					current = new Pos(x, y);
				} else if (board[y][x] == '3') {
					goal = new Pos(x, y);
				}
			}
		}
	}
	
	private void solve() {
		// 8. 정답 변수 answer를 초기화 한다.
		answer = 0;
		
		// 9. 좌표를 보관하는 큐를 선언한다.
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(current); // 9-1. 큐에 시작 좌표를 추가한다.
		
		// 10. BFS 방식으로 미로를 탐사한다.
		while (!q.isEmpty()) {
			// 10-1. 큐의 가장 앞에 있는 요소를 꺼낸다.
			Pos peek = q.poll();
			
			// 10-2. 상하좌우 4방향으로 미로를 이동하면서 탐사한다.
			for (int index = 0; index < DIR_LEN; index++) {
				Pos next = new Pos(
						peek.x + DIR_X[index],
						peek.y + DIR_Y[index]
				);
				
				if (next.equals(goal)) {
					// 10-3. 목적지에 도착했다면 정답을 기록하고 탐사를 중단한다.
					answer = 1;
					return;
				} else if (board[next.y][next.x] == '0') {
					// 10-4. 길이 존재한다면 큐에 넣는다.
					q.offer(next);
					
					// 10-5. 큐에 넣은 길은 방문 처리 한다.
					board[next.y][next.x] = '1';
				}
			}
		}
	}
	
	private void print() throws IOException {
		// 11. 정답을 출력한다.
		writer.write("#" + testCase + " " + answer + "\n");
		writer.flush();
	}
}

/**
 * Pos
 * @author YeJun, Jung
 *
 * 2차원 좌표를 저장하기 위한 클래스, 객체간의 비교가 가능하다.
 */
class Pos {
	public int x;
	public int y;
	
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		Pos another = (Pos)obj;
		return x == another.x && y == another.y;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
