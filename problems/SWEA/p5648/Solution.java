package swea.p5648;

import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer input;
	
	// --------------------------------------------------------
	
	public static void main(String[] args) throws IOException {
		final int testCount = Integer.parseInt(reader.readLine().trim());
		
		for (int testCase = 1; testCase <= testCount; testCase++) {
			new Solution(testCase).run();
		}
	}
	
	// --------------------------------------------------------
	
	int testCase;
	int raisedEnergy;
	
	int atomCount;
	List<Atom> atomList;
	
	public Solution(int testCase) {
		this.testCase = testCase;
	}
	
	public void run() throws IOException {
		input();
		solve();
		print();
	}
	
	private void input() throws IOException {
		atomCount = Integer.parseInt(reader.readLine().trim());
		atomList = new ArrayList<>(atomCount);
		
		for (int count = 0; count < atomCount; count++) {
			atomList.add(Atom.fromReader(reader));
		}
	}
	
	private void solve() {
//		System.out.println("--------------------------------------");
		raisedEnergy = 0;
		
//		runSimutation();
		updateAllCollisions();
	}
	
	private void updateAllCollisions() {
		for (int A = 0; A < atomCount; A++) {
			
			Atom atomA = atomList.get(A);
			
			if (atomA.energy == 0) continue;
			
			List<Atom> checkList = new ArrayList<>(atomCount);
			
			for (int B = 0; B < atomCount; B++) {
				if (A == B) continue;
				
				Atom atomB = atomList.get(B);
				if (atomB.energy == 0) continue;
				
//				if (atomA.isPredictedCollision(atomB)) {
				if (getCollisionTimeIfPossible(atomA, atomB) != Double.MAX_VALUE) {
//					System.out.printf("%d : %d\n", A, B);
					checkList.add(atomB);
				}
			}
			
			if (checkList.size() == 0) continue;
			
			Collections.sort(checkList, (Atom sortA, Atom sortB) -> {
//				double time1 = atomA.getCollisionTime(sortA);
//				double time2 = atomA.getCollisionTime(sortB);
				
				double time1 = getCollisionTimeIfPossible(atomA, sortA);
				double time2 = getCollisionTimeIfPossible(atomA, sortB);
				
				return Double.compare(time1, time2);
			});
			
			raisedEnergy += atomA.destroy();
			raisedEnergy += checkList.get(0).destroy();
		}
	}
	
	private double getCollisionTimeIfPossible(Atom a, Atom b) {
	    int dx = b.x - a.x;
	    int dy = b.y - a.y;

	    // 1. 마주보는 경우 (정수 시간 또는 .5초 시간)
	    if (a.x == b.x) { // 세로선상
	        if (dy > 0 && a.dir == Atom.Direction.UP && b.dir == Atom.Direction.DOWN) return dy / 2.0;
	        if (dy < 0 && a.dir == Atom.Direction.DOWN && b.dir == Atom.Direction.UP) return Math.abs(dy) / 2.0;
	    }
	    if (a.y == b.y) { // 가로선상
	        if (dx > 0 && a.dir == Atom.Direction.RIGHT && b.dir == Atom.Direction.LEFT) return dx / 2.0;
	        if (dx < 0 && a.dir == Atom.Direction.LEFT && b.dir == Atom.Direction.RIGHT) return Math.abs(dx) / 2.0;
	    }

	    // 2. 직각 충돌 (반드시 dx == dy 여야 함)
	    if (Math.abs(dx) == Math.abs(dy)) {
	        double time = Math.abs(dx);
	        // A가 우측으로 갈 때
	        if (a.dir == Atom.Direction.RIGHT) {
	            if (dx > 0 && ((dy > 0 && b.dir == Atom.Direction.DOWN) || (dy < 0 && b.dir == Atom.Direction.UP))) return time;
	        }
	        // A가 좌측으로 갈 때
	        if (a.dir == Atom.Direction.LEFT) {
	            if (dx < 0 && ((dy > 0 && b.dir == Atom.Direction.DOWN) || (dy < 0 && b.dir == Atom.Direction.UP))) return time;
	        }
	        // A가 위로 갈 때
	        if (a.dir == Atom.Direction.UP) {
	            if (dy > 0 && ((dx > 0 && b.dir == Atom.Direction.LEFT) || (dx < 0 && b.dir == Atom.Direction.RIGHT))) return time;
	        }
	        // A가 아래로 갈 때
	        if (a.dir == Atom.Direction.DOWN) {
	            if (dy < 0 && ((dx > 0 && b.dir == Atom.Direction.LEFT) || (dx < 0 && b.dir == Atom.Direction.RIGHT))) return time;
	        }
	    }

	    return Double.MAX_VALUE; // 충돌 불가
	}
	
	private void runSimutation() {
		// 종료 조건: 원자들의 위치는 -1000 ~ 1000이기 때문에 2000번만 반복하고 종료하면 됨
		final int last = 4000;
		
		for (int count = 0; count < last; count++) {
			if (nextStep2() == 0) break;
		}
	}
	
	// -3000 ~ 3000 범위를 커버하기 위한 6001 크기의 배열
	static int[][] map = new int[6001][6001];
	private final int OFFSET = 3000; 

	private int nextStep2() {
	    int activeAtoms = 0;

	    // 1. 모든 원자 이동 및 맵 기록
	    for (Atom atom : atomList) {
	        if (atom.energy == 0) continue;

	        atom.moveNext2();
	        
	        // 이론적 최대 범위 (-3000 ~ 3000) 내에서 인덱싱
	        int mx = atom.x + OFFSET;
	        int my = atom.y + OFFSET;
	        
	        // 만약 이 범위를 벗어난다면 그 원자는 영원히 누구와도 만날 수 없음
	        if (mx < 0 || mx > 6000 || my < 0 || my > 6000) {
	            atom.energy = 0;
	            continue;
	        }

	        activeAtoms++;
	        map[mx][my] += atom.energy;
	    }

	    if (activeAtoms <= 1) {
	        clearMap();
	        return 0;
	    }

	    // 2. 충돌 판정
	    for (Atom atom : atomList) {
	        if (atom.energy == 0) continue;

	        int mx = atom.x + OFFSET;
	        int my = atom.y + OFFSET;

	        if (map[mx][my] > atom.energy) {
	            raisedEnergy += atom.energy;
	            atom.energy = 0;
	        }
	    }
	    
	    // 3. 맵 청소
	    clearMap();

	    return activeAtoms;
	}

	private void clearMap() {
	    for (Atom atom : atomList) {
	        int mx = atom.x + OFFSET;
	        int my = atom.y + OFFSET;
	        if (mx >= 0 && mx <= 6000 && my >= 0 && my <= 6000) {
	            map[mx][my] = 0;
	        }
	    }
	}
	
	private int nextStep() {
		// 각 원자들의 이동 벡터를 [시작, 종료]로 기록
		// 서로 다른 벡터들의 [시작, 종료]가 겹치면 해당 원소는 에너지를 모두 발산하고 소멸
		
		List<Vec> vecList = new ArrayList<>(atomCount);
		int vecLen = 0;
		
		for (int atomIndex = 0; atomIndex < atomCount; atomIndex++) {
			Atom atom = atomList.get(atomIndex);
			if (atom.energy == 0) continue;
			
			vecList.add(atom.moveNext());
//			System.out.println(atomIndex + " " + atom);
			vecLen++;
		}
		
		for (int A = 0; A < vecLen; A++) {
			for (int B = 0; B < vecLen; B++) {
				if (A == B) continue;
				
				Vec vecA = vecList.get(A);
				Vec vecB = vecList.get(B);
				
				if (vecA.isCollision(vecB) || vecA.isEqualPos(vecB)) {
					raisedEnergy += atomList.get(A).destroy();
					raisedEnergy += atomList.get(B).destroy();
//					System.out.printf("[collision] %d : %d -> %d\n", A, B, raisedEnergy);
				}
			}
		}
		
		return vecLen;
	}
	
	private void print() throws IOException {
		writer.write("#" + testCase);
		writer.write(" " + raisedEnergy);
		writer.write("\n");
		writer.flush();
	}
	
	// --------------------------------------------------------
	
	static class Atom {
		enum Direction {
			UP,
			DOWN,
			LEFT,
			RIGHT
		}
		
		static final int[] DIR_X = {  0,  0, -1,  1 };
		static final int[] DIR_Y = {  1, -1,  0,  0 };
		
		int x;
		int y;
		Direction dir;
		int energy;
		
		public Atom(int x, int y, Direction dir, int energy) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.energy = energy;
		}
		
		public static Atom fromReader(BufferedReader reader) throws IOException {
			getLine();
			int x = Integer.parseInt(input.nextToken()) * 2; // WOW
			int y = Integer.parseInt(input.nextToken()) * 2; // WOW
			int dir = Integer.parseInt(input.nextToken());
			int energy = Integer.parseInt(input.nextToken());
			
			Direction eDir = Direction.UP;
			switch (dir) {
			case 0:
				eDir = Direction.UP;
				break;
			case 1:
				eDir = Direction.DOWN;
				break;
			case 2:
				eDir = Direction.LEFT;
				break;
			case 3:
				eDir = Direction.RIGHT;
				break;
			}
			
			return new Atom(x, y, eDir, energy);
		}
		
		public Vec moveNext() {
			int nextX = x + DIR_X[dir.ordinal()];
			int nextY = y + DIR_Y[dir.ordinal()];
			
			Vec vec = new Vec(x, y, nextX, nextY);
			
			x = nextX;
			y = nextY;
			
			return vec;
		}
		
		public void moveNext2() {
			int nextX = x + DIR_X[dir.ordinal()];
			int nextY = y + DIR_Y[dir.ordinal()];
			
			x = nextX;
			y = nextY;
		}
		
		public Atom moveNextAtom() {
			int nextX = x + DIR_X[dir.ordinal()];
			int nextY = y + DIR_Y[dir.ordinal()];
			
			return new Atom(nextX, nextY, dir, energy);
		}
		
		public int destroy() {
			int returnEnergy = energy;
			energy = 0;
			
			return returnEnergy;
		}
		
		public double getCollisionTime(Atom another) {
			return getDistance(another);
		}
		
		public boolean isPredictedCollision(Atom another) {
			// x좌표가 같은데, 방향이 마주보는 경우
			// y좌표가 같은데, 방향이 마주보는 경우
			// abs(A.y - B.y) == abs(A.x - B.x) 인 경우
			
			if (this.x == another.x && isSameOriginDirectionVertical(another)) {
				return true;
			} else if (this.y == another.y && isSameOriginDirectionHorizontal(another)) {
				return true;
			} else if (Math.abs(this.x - another.x) == Math.abs(this.y - another.y) &&
					   isSameOriginDirectionCross(another)
		    ) {
				return true;
			}
			
			return false;
		}
		
		public boolean isSameOriginDirectionCross(Atom another) {
//			Atom next = moveNextAtom();
//			return next.getDistance(another) < this.getDistance(another);
			
			Atom atomA = this;
			Atom atomB = another;
			
			if (atomA.dir == Direction.UP || atomA.dir == Direction.DOWN) {
				Atom temp = atomA;
				atomA = atomB;
				atomB = temp;
			}
			
			if (atomA.y < atomB.y) {
				if (atomB.dir != Direction.DOWN) return false;
			} else if (atomB.y < atomA.y) {
				if (atomB.dir != Direction.UP) return false;
			}
			
			if (atomA.x < atomB.x) {
				if (atomA.dir != Direction.RIGHT) return false;
			} else if (atomB.x < atomA.x) {
				if (atomA.dir != Direction.LEFT) return false;
			}
			
			return true;
		}
		
		public boolean isSameOriginDirectionHorizontal(Atom another) {
			boolean result = false;
			
			result |= (this.dir == Direction.LEFT && another.dir == Direction.RIGHT);
			result |= (this.dir == Direction.RIGHT && another.dir == Direction.LEFT);
			
			return result;
		}
		
		public boolean isSameOriginDirectionVertical(Atom another) {
			boolean result = false;
			
			result |= (this.dir == Direction.UP && another.dir == Direction.DOWN); 
			result |= (this.dir == Direction.DOWN && another.dir == Direction.UP);
			
			return result;
		}
		
		public int getDistance(Atom another) {
			int result = 0;
			
			result += Math.max(this.x, another.x) - Math.min(this.x, another.x);
			result += Math.max(this.y, another.y) - Math.min(this.y, another.y);
			
			return result;
		}
		
		@Override
		public String toString() {
			return String.format("[x=%d, y=%d, dir=%d, energy=%d]", x, y, dir, energy);
		}
	}
	
	// --------------------------------------------------------
	
	static class Vec {
		int beginX;
		int beginY;
		int endX;
		int endY;
		
		public Vec(int beginX, int beginY, int endX, int endY) {
			this.beginX = beginX;
			this.beginY = beginY;
			this.endX = endX;
			this.endY = endY;
		}
		
		public boolean isCollision(Vec another) {
			// AABB Collision Detection Algorithm
			// (상하좌우로만 움직이기 때문에 AABB 활용 가능)
			
			boolean result = false;

			result |= another.beginX <= this.beginX && another.endX <= this.beginX;
			result |= this.endX <= another.beginX && this.endX <= another.endX;
			
			result |= another.beginY <= this.beginY && another.endY <= this.beginY;
			result |= this.endY <= another.beginY && this.endY <= another.endY;
			
//			System.out.printf("isCollision (%d:%d, %d:%d) - (%d:%d, %d:%d) %s\n", beginX, endX, beginY, endY, another.beginX, another.endX, another.beginY, another.endY, !result);
			
			return !result;
		}
		
		public boolean isEqualPos(Vec another) {
			return this.endX == another.endX && this.endY == another.endY;
		}
	}
	
	// --------------------------------------------------------
	
	static class Pos {
		double x;
		double y;
		
		public Pos(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object obj) {
			Pos another = (Pos)obj;
			return this.x == another.x && this.y == another.y;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
	
	// --------------------------------------------------------
	
	private static void getLine() throws IOException {
		input = new StringTokenizer(reader.readLine().trim());
	}
}
