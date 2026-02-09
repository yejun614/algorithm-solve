/*
 * (10972) 다음 순열
 * https://www.acmicpc.net/problem/10972
 */
package boj.p10972;

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 다음 순열
 * @author YeJun, Jung
 *
 * nextPermutation
 *   - 오름차순으로 정렬되어 있는 배열을 내림차순으로 변경하는 과정에서 순열을 생성하는 알고리즘
 *   - 이번 문제에서 말하는 사전순으로 다음에 오는 순열은 nextPermutation이 생성하는 순서와 일치한다.
 *   - nextPermutation 구현체는 C++의 표준 라이브러리 구현체를 참고했다.
 *   
 * 참고자료
 *   - https://en.cppreference.com/w/cpp/algorithm/next_permutation.html
 *   - https://charles098.tistory.com/8
 */
public class Main {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer input;
	
	// --------------------------------------------------------
	
	public static void main(String[] args) throws IOException {
		new Main().run();
	}
	
	// --------------------------------------------------------
	
	int arrLen;
	int[] arr;
	
	public void run() throws IOException {
		arrLen = Integer.parseInt(reader.readLine().trim());
		arr = new int[arrLen];
		
		getLine();
		for (int index = 0; index < arrLen; index++) {
			arr[index] = Integer.parseInt(input.nextToken());
		}
		
		if (nextPermutation(arr)) {
			for (int index = 0; index < arrLen; index++) {
				writer.write(arr[index] + " ");
			}
			writer.write("\n");
		} else {
			writer.write("-1\n");
		}
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
