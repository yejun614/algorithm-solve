/*
 * (1767) [SW Test 샘플문제] 프로세서 연결하기
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf&categoryId=AV4suNtaXFEDFAUf&categoryType=CODE&problemTitle=1767&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */
package swea.p1767;

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [SW Test 샘플문제] 프로세서 연결하기
 * @author YeJun, Jung
 * 
 * - DFS, 백트래킹 적용
 * 	- DFS: 전원 공급이 안되는 코어를 상하좌우 전선 연결 시도
 * 	- 백트래킹: 전선이 다른 전선 및 코어에 부딪치면 탐색 중단
 * - 상하좌우 전선 연결시에는 델타 배열을 사용
 * - DFS 탐색을 위해서 Deque 사용
 * 	- DFS 탐색 중 값을 저장하기 위해 Context 클래스 사용
 * - Pos 클래스를 사용해서 좌표를 보관
 */
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
	
	final static int[] DIR_X = { 0, -1,  1,  0,  0 };
	final static int[] DIR_Y = { 0,  0,  0, -1,  1 };
	final static int DIR_LEN = 5;
	
	int testCase;
	int cost;
	int connectedCores;
	
	int boardSize;
	char[][] board;
	
	List<Pos> corePosList;
	
	public Solution(int testCase) {
		this.testCase = testCase;
	}
	
	public void run() throws IOException {
		input();
		solve();
		print();
	}
	
	private void input() throws IOException {
		boardSize = Integer.parseInt(reader.readLine().trim());
		board = new char[boardSize][boardSize];
		
		corePosList = new ArrayList<>();
		
		for (int y = 0; y < boardSize; y++) {
			String line = reader.readLine().trim();
			for (int x = 0; x < boardSize; x++) {
				char ch = line.charAt(x * 2);
				board[y][x] = ch;
				
				// 가장자리에 있어 이미 전원이 공급되는 코어는 제외한다.
				if (ch == '1' && !isSidePos(x, y)) corePosList.add(new Pos(x, y));
			}
		}
	}
	
	private boolean isSidePos(int x, int y) {
		return x == 0 || x == boardSize - 1 || y == 0 || y == boardSize - 1;
	}
	
	private void solve() {
		cost = Integer.MAX_VALUE;
		connectedCores = -1;
		
		// DFS 탐색 시작
		searchBoard();
	}
	
	private void searchBoard() {
		final int corePosLen = corePosList.size();
		
		Deque<Context> q = new ArrayDeque<>();
		q.push(new Context(board.clone(), 0, 0, 0));
		
		while (!q.isEmpty()) {
			Context top = q.pop();
			
			// 기저 조건
			if (top.coreIndex >= corePosLen) {
				// 연결된 코어수가 더 많거나 사용한 전선 길이가 작은 경우를 찾는다
				if (top.connectedCores == connectedCores) {
					if (top.cost < cost) {
						cost = top.cost;
					}
				} else if (top.connectedCores > connectedCores) {
					cost = top.cost;
					connectedCores = top.connectedCores;
				}
				
				// 기저조건 도달시 탐색 중단
				continue;
			}
			
			Pos corePos = corePosList.get(top.coreIndex);
			for (int dir = 0; dir < DIR_LEN; dir++) {
				Context nextCtx = top.nextCtx();
				
				// 백트래킹
				if (nextCtx.drawLine(corePos.x, corePos.y, DIR_X[dir], DIR_Y[dir])) {
					q.push(nextCtx);
				}
			}
		}
	}
	
	private void print() throws IOException {
		writer.write("#" + testCase);
		writer.write(" " + cost);
		writer.write("\n");
		writer.flush();
	}

	// --------------------------------------------------------
	
	private class Context {
		final int boardSize;
		char[][] board;
		int coreIndex;
		int cost;
		int connectedCores;
		
		public Context(char[][] board, int coreIndex, int cost, int connectedCores) {
			this.boardSize = board.length;
			this.board = board;
			this.coreIndex = coreIndex;
			this.cost = cost;
			this.connectedCores = connectedCores;
		}
		
		public Context nextCtx() {
			char[][] nextBoard = new char[boardSize][boardSize];
			
			for (int y = 0; y < boardSize; y++) {
				nextBoard[y] = board[y].clone();
			}
			
			return new Context(nextBoard, coreIndex + 1, cost, connectedCores);
		}
		
		public boolean drawLine(int coreX, int coreY, int deltaX, int deltaY) {
			if (deltaX == 0 && deltaY == 0) return true;
			
			while (true) {
				coreX += deltaX;
				coreY += deltaY;
				
				if (!isInsideBoard(coreX, coreY)) break;
				if (board[coreY][coreX] != '0') return false;
				
				board[coreY][coreX] = '#';
				cost++;
			}
			
			connectedCores++;
			return true;
		}
		
		private boolean isInsideBoard(int x, int y) {
			if (x < 0 || x >= boardSize || y < 0 || y >= boardSize) {
				return false;
			}
			return true;
		}
	}
	
	// --------------------------------------------------------
	
	private class Pos {
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// --------------------------------------------------------
}
