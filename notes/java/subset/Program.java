package practice.temp;

import java.io.*;
import java.util.*;

public class Program {
	public static void main(String[] args) {
		input = new int[] {1, 2, 3};
		isSelected = new boolean[N];
		
		makeSubset(0);
	}
	
	static final int N = 3;
	static int[] input;
	static boolean[] isSelected;
	
	static void makeSubset(int cnt) {
		for (int n = 0; n < cnt; n++) System.out.print("\t");
		System.out.printf("makeSubset(cnt: %d)\n", cnt);
		for (int n = 0; n < cnt; n++) System.out.print("\t");
		System.out.println("input = " + Arrays.toString(input));
		for (int n = 0; n < cnt; n++) System.out.print("\t");
		System.out.println("isSelected = " + Arrays.toString(isSelected));
		
		if (cnt == N) {
			// 부분 집합 완성
			List<Integer> subset = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) subset.add(input[i]);
			}
			
			for (int n = 0; n < cnt; n++) System.out.print("\t");
			System.out.println("부분 집합 완성 = " + subset);
			System.out.println();
			return;
		}
		System.out.println();
		
		isSelected[cnt] = true;
		makeSubset(cnt + 1);
		
		isSelected[cnt] = false;
		makeSubset(cnt + 1);
	}
}
