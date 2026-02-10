package swea.p2806;

import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	
	// --------------------------------------------------------
	
	public static void main(String[] args) throws IOException {
		final int testCount = Integer.parseInt(reader.readLine().trim());
		
		for (int testCase = 1; testCase <= testCount; testCase++) {
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
		N = Integer.parseInt(reader.readLine().trim());
	}
	
	private void solve() {
		answer = 0;
		board = new int[N][N];
		
		search(N, 0);
	}
	
	private void search(int nQueen, int y) {
		// 기저조건
		if (nQueen == 0) {
			answer++;
			return;
		}
		
		for (int cy = y; cy < N; cy++) {
			for (int cx = 0; cx < N; cx++) {
				if (!putQueen(cx, cy)) continue;
				
				search(nQueen - 1, cy + 1);
				
				removeQueen(cx, cy);
			}
		}
	}
	
	private boolean putQueen(int x, int y) {
		if (board[y][x] != 0) return false;
		
		boolean available = true;
		int count = 0;
		
		for (int dir = 0; dir < QUEEN_DIR_LEN; dir++) {
			int cx = x + QUEEN_DIR_X[dir];
			int cy = y + QUEEN_DIR_Y[dir];
			
			while (isInsideBoard(cx, cy)) {
				if (board[cy][cx] < 0) {
					available = false;
					break;
				}
				
				board[cy][cx]++;
				count++;
				
				cx += QUEEN_DIR_X[dir];
				cy += QUEEN_DIR_Y[dir];
			}
		}
		
		// rollback
		if (!available) {
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
		}
		
		board[y][x] = -1;
		
		return true;
	}
	
	private void removeQueen(int x, int y) {
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
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	private void print() throws IOException {
		writer.write("#" + testCase);
		writer.write(" " + answer);
		writer.write("\n");
		writer.flush();
	}
	
	// --------------------------------------------------------
}
