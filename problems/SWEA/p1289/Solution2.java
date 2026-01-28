package p1289;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution2 {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		
		int testCount = Integer.parseInt(reader.readLine().trim());
		
		for (int testCase = 1; testCase <= testCount; testCase++) {
			int answer = 0;
			
			String goalMem = reader.readLine().trim();
			final int memSize = goalMem.length();
			char filled = '0';
			
			for (int cursor = 0; cursor < memSize; cursor++) {
				if (goalMem.charAt(cursor) != filled) {
					answer++;
					filled = filled == '0' ? '1' : '0';
				}
			}
			
			output.append(String.format("#%d %d\n", testCase, answer));
		}
		
		System.out.print(output.toString());
	}
}
