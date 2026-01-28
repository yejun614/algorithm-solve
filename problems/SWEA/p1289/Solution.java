package p1289;

import java.util.Scanner;

public class Solution {
	static Scanner scanner;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		
		int testCount = scanner.nextInt();
		scanner.nextLine();
		
		for (int testCase = 1; testCase <= testCount; testCase++) {
			Solution solution = new Solution(testCase);
			solution.run();
		}
		
		scanner.close();
	}
	
	int testCase;
	int answer;
	String goalMem;
	
	public Solution(int testCase) {
		this.testCase = testCase;
	}
	
	public void run() {
		input();
		solve();
		print();
	}
	
	private void print() {
		System.out.printf("#%d %d\n", testCase, answer);
	}
	
	private void solve() {
		answer = 0;
		
		// 앞에서 부터 목표 값이랑 비교하면서 덮어씌운다
		// 이미 목표 값이랑 동일하면 설정안하고 그냥 다음으로 커서를 넘긴다.
		
		final int memSize = goalMem.length();
		char filled = '0';
		
		for (int cursor = 0; cursor < memSize; cursor++) {
			if (goalMem.charAt(cursor) != filled) {
				answer++;
				filled = filled == '0' ? '1' : '0';
			}
		}
	}
	
	private void input() {
		goalMem = scanner.nextLine().trim();
	}
}
