package practice.temp;

import java.io.*;
import java.util.*;

public class Program {
	public static void main(String[] args) {
		input = new int[] {1, 2, 3};
		numbers = new int[r];
		
		combination(0, 0);
	}
	
	static final int r = 2;
	static final int n = 3;
	static int[] input;
	static int[] numbers;
	
	private static void combination(int cnt, int start) {
		for (int n = 0; n < cnt; n++) System.out.print("\t");
		System.out.printf("Combination(cnt: %d, start: %d)\n", cnt, start);
		for (int n = 0; n < cnt; n++) System.out.print("\t");
		System.out.println(Arrays.toString(input));
		for (int n = 0; n < cnt; n++) System.out.print("\t");
		System.out.println(Arrays.toString(numbers));
		
		if (cnt == r) {
			// 조합 생성 완료
			for (int n = 0; n < cnt; n++) System.out.print("\t");
			System.out.println("조합 생성 완료 = " + Arrays.toString(numbers));
			System.out.println();
			return;
		}
		System.out.println();
		
		for (int i = start; i <= n - 1; i++) {
			numbers[cnt] = input[i];
			combination(cnt + 1, i + 1);
		}
	}
}
