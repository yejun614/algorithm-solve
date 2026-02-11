/*
 * (5648) [모의 SW 역량테스트] 원자 소멸 시뮬레이션
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRFInKex8DFAUo&categoryId=AWXRFInKex8DFAUo&categoryType=CODE&problemTitle=5648&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 5648. [모의 SW 역량테스트] 원자 소멸 시뮬레이션
 * @author YeJun, Jung
 * 
 * @see #main(String[] args)
 * 1. 테스트케이스를 입력받는다.
 * 2. 솔루션을 실행한다.
 * 
 * @see #input()
 * 3. 원자 개수를 입력받는다.
 * 4. 원자 목록을 보관할 리스트 atomList를 초기화한다.
 * 5. 원자들을 입력받아 atomList에 저장한다.
 * 
 * @see #solve()
 * 6. 멤버변수를 초기화한다.
 * 7. 시뮬레이션을 시작한다.
 * 
 * @see #runSimuration()
 * 8. 이론상 가능한 최대 횟수만큼 시뮬레이션을 실시하되, 모든 원자가 충돌하여
 *    시뮬레이션할 원자가 없는 경우에는 조기에 종료한다.
 * 
 * @see #nextStep()
 * 9. 리턴 변수를 초기화한다.
 * 10. 아직 충돌하지 않은 원자들을 이동시키고, 그 위치를 기록한다.
 *   10-1. 이론적으로 가능한 범위 안에서 원자 위치를 index로 변환한다.
 *   10-2. 범위를 벗어난다면 해당 원소를 소멸 시킨다.
 *   10-3. 이동 후 원자의 위치를 기록한다.
 * 11. 모든 원자가 충돌하여 소멸한 경우 시뮬레이션을 중단한다.
 * 12. 원자들 간의 충돌을 판단하여 원자를 소멸시키고, 발산한 에너지를 기록한다.
 * 13. board 배열을 초기화한다.
 *  
 * @see #print()
 * 
 */
public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer input;
	
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

	private final int OFFSET = 2000;
	private final int MAX_RANGE = OFFSET * 2;
	
	int testCase;
	int raisedEnergy; // answer
	
	int atomCount;
	List<Atom> atomList;

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
		// 3. 원자 개수를 입력받는다.
		atomCount = Integer.parseInt(reader.readLine().trim());
		// 4. 원자 목록을 보관할 리스트 atomList를 초기화한다.
		atomList = new ArrayList<>(atomCount);
		
		// 5. 원자들을 입력받아 atomList에 저장한다.
		for (int count = 0; count < atomCount; count++) {
			atomList.add(Atom.fromReader(reader));
		}
	}
	
	private void solve() {
		// 6. 멤버변수를 초기화한다.
		raisedEnergy = 0;
		board = new int[MAX_RANGE + 1][MAX_RANGE + 1];
		
		// 7. 시뮬레이션을 시작한다.
		runSimuration();
	}
	
	private void runSimuration() {
		// 원자들의 초기위치는 -1000 ~ 1000인데, 0.5이동 때문에 초기위치에 x2되어
		// -2000 ~ 2000가 초기위치가 된다.
		// 가장자리에 있는 원소들을 고려할때, 최대 4000번만 시뮬레이션하고 종료하면 된다.
		final int last = 4000;
		
		// 8. 이론상 가능한 최대 횟수만큼 시뮬레이션을 실시하되, 모든 원자가 충돌하여
		//    시뮬레이션할 원자가 없는 경우에는 조기에 종료한다.
		for (int count = 0; count < last; count++) {
			if (nextStep() == 0) break;
		}
	}

	private int nextStep() {
		// 9. 리턴 변수를 초기화한다.
		int activeAtoms = 0;

    // 10. 아직 충돌하지 않은 원자들을 이동시키고, 그 위치를 기록한다.
		for (Atom atom : atomList) {
			if (atom.energy == 0) continue;

			atom.moveNext();

      // 10-1. 이론적으로 가능한 범위 안에서 원자 위치를 index로 변환한다.
			int mx = atom.x + OFFSET;
			int my = atom.y + OFFSET;

      // 10-2. 범위를 벗어난다면 해당 원소를 소멸 시킨다.
			if (mx < 0 || mx > MAX_RANGE || my < 0 || my > MAX_RANGE) {
				atom.energy = 0;
				continue;
			}

			// 10-3. 이동 후 원자의 위치를 기록한다.
			board[mx][my] += atom.energy;
			activeAtoms++;
		}

		// 11. 모든 원자가 충돌하여 소멸한 경우 시뮬레이션을 중단한다.
		if (activeAtoms == 0) return 0;

    // 12. 원자들 간의 충돌을 판단하여 원자를 소멸시키고, 발산한 에너지를 기록한다.
		for (Atom atom : atomList) {
			if (atom.energy == 0) continue;

			int mx = atom.x + OFFSET;
			int my = atom.y + OFFSET;

			if (board[mx][my] > atom.energy) {
				raisedEnergy += atom.energy;
				atom.energy = 0;
			}
		}

    // 13. board 배열을 초기화한다.
		clearBoard();

		return activeAtoms;
	}

	private void clearBoard() {
		for (Atom atom : atomList) {
			int mx = atom.x + OFFSET;
			int my = atom.y + OFFSET;
			if (mx >= 0 && mx <= MAX_RANGE && my >= 0 && my <= MAX_RANGE) {
				board[mx][my] = 0;
			}
		}
	}
	
	private void print() throws IOException {
		writer.write("#" + testCase);
		writer.write(" " + raisedEnergy);
		writer.write("\n");
		writer.flush();
	}
	
	// --------------------------------------------------------
	
	static class Atom {
		static final int[] DIR_X = {  0,  0, -1,  1 };
		static final int[] DIR_Y = {  1, -1,  0,  0 };
		
		int x;
		int y;
		int dir;
		int energy;
		
		public Atom(int x, int y, int dir, int energy) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.energy = energy;
		}
		
		public static Atom fromReader(BufferedReader reader) throws IOException {
			getLine();
			int x = Integer.parseInt(input.nextToken()) * 2; // 0.5 시간에 충돌을 처리하기 위해서 x2 한다.
			int y = Integer.parseInt(input.nextToken()) * 2; // 0.5 시간에 충돌을 처리하기 위해서 x2 한다.
			int dir = Integer.parseInt(input.nextToken());
			int energy = Integer.parseInt(input.nextToken());
			
			return new Atom(x, y, dir, energy);
		}

		public void moveNext() {
			int nextX = x + DIR_X[dir];
			int nextY = y + DIR_Y[dir];
			
			x = nextX;
			y = nextY;
		}
	}
	
	// --------------------------------------------------------
	
	private static void getLine() throws IOException {
		input = new StringTokenizer(reader.readLine().trim());
	}
}
