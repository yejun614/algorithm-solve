/*
 * (4008) [모의 SW 역량테스트] 숫자 만들기
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeRZV6kBUDFAVH&categoryId=AWIeRZV6kBUDFAVH&categoryType=CODE&problemTitle=4008&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */
package swea.p4008;

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [모의 SW 역량테스트] 숫자 만들기
 * @author YeJun, Jung
 *
 * 연산자 카드 순서를 순열을 만들어서 최대값 및 최소값을 찾는다.
 *   - 카드 순서를 순열로 만들때는 nextPermutation 함수를 사용한다.
 *   - 가능한 모든 연산자 카드 순서를 만들어 최대값과 최소값을 찾는다.
 */
public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer input;
	
	// --------------------------------------------------------
	
	public static void main(String[] args) throws IOException {
		int testCount = Integer.parseInt(reader.readLine().trim());
		
		for (int testCase = 1; testCase <= testCount; testCase++) {
			new Solution(testCase).run();
		}
	}
	
	// --------------------------------------------------------
	
	final static int OP_TYPE_LEN = 4;
	
	int testCase;
	int answer;
	
	int maxValue;
	int minValue;
	
	int numArrLen;
	int totalOpLen;
	
	int[] numArr;
	int[] opCountArr;
	int[] select;
	
	public Solution(int testCase) {
		this.testCase = testCase;
	}
	
	public void run() throws IOException {
		input();
		solve();
		print();
	}
	
	private void input() throws IOException {
		numArrLen = Integer.parseInt(reader.readLine().trim());
		
		numArr = new int[numArrLen];
		opCountArr = new int[OP_TYPE_LEN];
		totalOpLen = 0;
		
		getLine();
		for (int index = 0; index < OP_TYPE_LEN; index++) {
			opCountArr[index] = Integer.parseInt(input.nextToken());
			totalOpLen += opCountArr[index]; 
		}
		
		getLine();
		for (int index = 0; index < numArrLen; index++) {
			numArr[index] = Integer.parseInt(input.nextToken());
		}
	}
	
	private void solve() {
		maxValue = Integer.MIN_VALUE;
		minValue = Integer.MAX_VALUE;
		
		makeSelectArr();
		searchOpCardOrder();
		
		answer = maxValue - minValue;
	}
	
	private void makeSelectArr() {
		select = new int[totalOpLen];
		
		int index = 0;
		for (int opIndex = 0; opIndex < OP_TYPE_LEN; opIndex++) {
			for (int count = 0; count < opCountArr[opIndex]; count++) {
				select[index++] = opIndex;
			}
		}
	}
	
	private void searchOpCardOrder() {
		do {
			int current = calcurate(select);
			
			if (current > maxValue) maxValue = current;
			if (current < minValue) minValue = current;
		} while (nextPermutation(select));
	}
	
	private int calcurate(int[] opArr) {
		int result = numArr[0];
		
		for (int index = 1; index < numArrLen; index++) {
			int oprand = numArr[index];
			
			switch (opArr[index - 1]) {
			case 0:
				result += oprand;
				break;
			case 1:
				result -= oprand;
				break;
			case 2:
				result *= oprand;
				break;
			case 3:
				result /= oprand;
				break;
			}
		}
		
		return result;
	}
	
	private void print() throws IOException {
		writer.write("#" + testCase);
		writer.write(" " + answer);
		writer.write("\n");
		writer.flush();
	}
	
	// --------------------------------------------------------
	
	private void getLine() throws IOException {
		input = new StringTokenizer(reader.readLine().trim());
	}
	
	private boolean nextPermutation(int[] x) {
		int n = x.length - 1, a = n, b = n;
		while (a > 0 && x[a - 1] >= x[a]) a--;
		if (a == 0) return false;
		while (x[a - 1] >= x[b]) b--;
		swap(x, a - 1, b); reverse(x, a, n);
		return true;
	}
	
	private void swap(int[] x, int a, int b) {
		int t = x[a]; x[a] = x[b]; x[b] = t;
	}
	
	private void reverse(int[] x, int a, int b) {
		while (a < b) swap(x, a++, b--);
	}
}













