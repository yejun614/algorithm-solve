/*
 * (87390) n^2 배열 자르기
 * https://school.programmers.co.kr/learn/courses/30/lessons/87390
 */

import java.util.Arrays; // for debugging

public class Solution {
  public int[] solution(int n, long left, long right) {
    int[] answer = new int[(int)(right - left) + 1]; // right - left < 100000
    int index = 0;
    
    for (long x = left; x <= right; x++) {
      answer[index++] = 1 + Math.max((int)(x / (long)n), (int)(x % (long)n));
    }

    return answer;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(new Solution().solution(3, 2, 5))); // [3, 2, 2, 3]
    System.out.println(Arrays.toString(new Solution().solution(4, 7, 14))); // [4, 3, 3, 3, 4, 4, 4, 4]
  }
}
