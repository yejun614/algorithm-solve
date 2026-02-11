/*
 * (2806) N-Queen
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GKs06AU0DFAXB&categoryId=AV7GKs06AU0DFAXB&categoryType=CODE&problemTitle=2806&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 2806. N-Queen
 * @author YeJun, Jung
 * 
 * @see #main(String[] args)
 * 1. 테스트케이스를 입력받는다.
 * 2. 솔루션을 실행한다.
 * 
 * @see #input()
 * 3. 보드의 크기이자 퀸의 개수인 N을 입력받는다.
 * 
 * @see #solve()
 * 4. 멤버변수를 초기화한다.
 * 5. 퀸을 배치할 수 있는 경우의 수에 대해서 탐색을 시작한다.
 * 
 * @see #search(int nQueen, int y)
 * 6. 기저조건을 검사한다.
 *   6-1. 기저조건을 달성하면 answer을 +1 한다.
 * 7. 현재 y위치에서 board의 마지막 위치까지 탐색한다.
 *   7-1. 지금 위치에 퀸을 배치한다.
 *     7-1-1. 지금 위치에 퀸을 배치할 수 없다면 다음 위치로 이동한다.
 *   7-2. 다음 퀸 배치를 위한 탐색을 재귀함수로 진행한다.
 *   7-3. 배치해 두었던 퀸을 삭제한다.
 * 
 * @see #putQueen(int x, int y)
 * 8. 주어진 위치(x, y)에 퀸을 배치한다.
 *   8-1. 주어진 위치에 이미 퀸이 존재한다면 false(불가능)을 반환한다.
 *   8-2. 변수를 초기화한다.
 *   8-3. 퀸의 이동범위에 대해서...
 *     8-3-1. 주어진 위치에 퀸을 배치했을때 이미 배치되어 있는 퀸을 잡을 수 있다면
 *       8-3-1-1. 퀸을 배치할 수 없다.
 *     8-3-2. 퀸이 이동가능한 위치를 표시한다.
 *     8-3-3. 계속해서 다음 퀸의 이동가능한 위치를 검토한다.
 *   8-4. 만약, 이전에 배치된 퀸을 잡을 수 있는 위치에 있다면...
 *     8-4-1. 지금까지 표시한 퀸의 이동가능한 위치들을 롤백한다.
 *     8-4-2. false(불가능)을 반환한다.
 *   8-5. 주어진 위치에 퀸을 배치한다.
 *   8-6. true(배치 성공함)을 반환한다.
 * 
 * @see #removeQueen(int x, int y)
 * 9. 주어진 위치에 있는 퀸을 제거하고, 해당 퀸의 이동가능한 범위를 board에서 삭제한다.
 * 
 * @see #isInsideBoard(int x, int y)
 * 10. 주어진 위치가 board 범위 안에 있는지 검사한다.
 * 
 * @see print()
 * 11. answer를 화면에 출력한다.
 */
public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	
	// --------------------------------------------------------
	
	public static void main(String[] args) throws IOException {
		// 1. 테스트케이스를 입력받는다.
		final int testCount = Integer.parseInt(reader.readLine().trim());
		
		for (int testCase = 1; testCase <= testCount; testCase++) {
			// 2. 솔루션을 실행한다.
			new Solution(testCase).run();
		}
	}
	
	// --------------------------------------------------------
	
	final static int[] QUEEN_DIR_X = {  0,  1,  1,  1,  0, -1, -1, -1 };
	final static int[] QUEEN_DIR_Y = { -1, -1,  0,  1,  1,  1,  0, -1 };
	final static int QUEEN_DIR_LEN = 8;
	
	int testCase;
	int answer;
	
	int N;
	int[][] board;
	
	public Solution(int testCase) {
		this.testCase = testCase;
	}
	
	public void run() throws IOException {
		input();
		solve();
		print();
	}
	
	private void input() throws IOException {
		// 3. 보드의 크기이자 퀸의 개수인 N을 입력받는다.
		N = Integer.parseInt(reader.readLine().trim());
	}
	
	private void solve() {
		// 4. 멤버변수를 초기화한다.
		answer = 0;
		board = new int[N][N];
		
		// 5. 퀸을 배치할 수 있는 경우의 수에 대해서 탐색을 시작한다.
		search(N, 0);
	}
	
	private void search(int nQueen, int y) {
		// 6. 기저조건을 검사한다.
		if (nQueen == 0) {
			// 6-1. 기저조건을 달성하면 answer을 +1 한다.
			answer++;

			return;
		}
		
		// 7. 현재 y위치에서 board의 마지막 위치까지 탐색한다.
		for (int cy = y; cy < N; cy++) {
			for (int cx = 0; cx < N; cx++) {
				// 7-1. 지금 위치에 퀸을 배치한다.
				// 7-1-1. 지금 위치에 퀸을 배치할 수 없다면 다음 위치로 이동한다.
				if (!putQueen(cx, cy)) continue;
				
				// 7-2. 다음 퀸 배치를 위한 탐색을 재귀함수로 진행한다.
				search(nQueen - 1, cy + 1);
				
				// 7-3. 배치해 두었던 퀸을 삭제한다.
				removeQueen(cx, cy);
			}
		}
	}
	
	private boolean putQueen(int x, int y) {
		// 8. 주어진 위치(x, y)에 퀸을 배치한다.

		// 8-1. 주어진 위치에 이미 퀸이 존재한다면 false(불가능)을 반환한다.
		if (board[y][x] != 0) return false;
		
		// 8-2. 변수를 초기화한다.
		boolean available = true;
		int count = 0;
		
		// 8-3. 퀸의 이동범위에 대해서...
		for (int dir = 0; dir < QUEEN_DIR_LEN; dir++) {
			int cx = x + QUEEN_DIR_X[dir];
			int cy = y + QUEEN_DIR_Y[dir];
			
			while (isInsideBoard(cx, cy)) {
				// 8-3-1. 주어진 위치에 퀸을 배치했을때 이미 배치되어 있는 퀸을 잡을 수 있다면
				if (board[cy][cx] < 0) {
					// 8-3-1-1. 퀸을 배치할 수 없다.
					available = false;
					break;
				}
				
				// 8-3-2. 퀸이 이동가능한 위치를 표시한다.
				board[cy][cx]++;
				count++;
				
				// 8-3-3. 계속해서 다음 퀸의 이동가능한 위치를 검토한다.
				cx += QUEEN_DIR_X[dir];
				cy += QUEEN_DIR_Y[dir];
			}
		}
		
		// 8-4. 만약, 이전에 배치된 퀸을 잡을 수 있는 위치에 있다면...
		if (!available) {
			// 8-4-1. 지금까지 표시한 퀸의 이동가능한 위치들을 롤백한다.
			for (int dir = 0; dir < QUEEN_DIR_LEN && count > 0; dir++) {
				int cx = x + QUEEN_DIR_X[dir];
				int cy = y + QUEEN_DIR_Y[dir];
				
				while (isInsideBoard(cx, cy)) {
					board[cy][cx]--;
					count--;
					
					cx += QUEEN_DIR_X[dir];
					cy += QUEEN_DIR_Y[dir];
				}
			}

			// 8-4-2. false(불가능)을 반환한다.
			return false;
		}
		
		// 8-5. 주어진 위치에 퀸을 배치한다.
		board[y][x] = -1;
		
		// 8-6. true(배치 성공함)을 반환한다.
		return true;
	}
	
	private void removeQueen(int x, int y) {
		// 9. 주어진 위치에 있는 퀸을 제거하고, 해당 퀸의 이동가능한 범위를 board에서 삭제한다.

		board[y][x] = 0;
		
		for (int dir = 0; dir < QUEEN_DIR_LEN; dir++) {
			int cx = x + QUEEN_DIR_X[dir];
			int cy = y + QUEEN_DIR_Y[dir];
			
			while (isInsideBoard(cx, cy)) {
				board[cy][cx]--;
				
				cx += QUEEN_DIR_X[dir];
				cy += QUEEN_DIR_Y[dir];
			}
		}
	}
	
	private boolean isInsideBoard(int x, int y) {
		// 10. 주어진 위치가 board 범위 안에 있는지 검사한다.
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	private void print() throws IOException {
		// 11. answer를 화면에 출력한다.
		writer.write("#" + testCase);
		writer.write(" " + answer);
		writer.write("\n");
		writer.flush();
	}
	
	// --------------------------------------------------------
}
