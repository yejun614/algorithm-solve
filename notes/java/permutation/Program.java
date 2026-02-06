package practice.temp;

import java.io.*;
import java.util.*;

public class Program {
	public static void main(String[] args) {
		numbers = new int[]{1, 2, 3};
		isSelected = new boolean[3];
		
		permutation(0);
	}
	
	static int[] numbers;
	static boolean[] isSelected;
	
	static void permutation(int cnt) {
		for (int n = 0; n < cnt; n++) System.out.print("\t");
		System.out.printf("permutation(cnt: %d)\n", cnt);
		for (int n = 0; n < cnt; n++) System.out.print("\t");
		System.out.println(Arrays.toString(numbers));
		for (int n = 0; n < cnt; n++) System.out.print("\t");
		System.out.println(Arrays.toString(isSelected));
		
		if (cnt == 3) {
			// 순열 생성 완료
			for (int n = 0; n < cnt; n++) System.out.print("\t");
			System.out.print("순열 생성 완료 = ");
			System.out.println(Arrays.toString(numbers));
			System.out.println();
			
			return;
		}
		System.out.println();
		
		for (int i = 0; i < 3; i++) {
			if (isSelected[i]) continue;
			
			numbers[cnt] = i;
			isSelected[i] = true;
			
			permutation(cnt + 1);
			
			isSelected[i] = false;
		}
	}
}
