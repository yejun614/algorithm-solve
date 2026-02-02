/*
 * (70129) 이진 변환 반복하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/70129
 */

import java.util.Arrays;

public class Solution {
  public int[] solution(String s) {
    int stepCount = 0; // 이진 변환을 한 횟수
    int zeroRemoveCount = 0; // 0을 삭제한 횟수

    // 1의 개수를 num에 저장한다
    int num = 0;

    // s 문자열에서 1의 개수와 0의 개수를 카운트
    for (int index = 0; index < s.length(); index++) {
      if (s.charAt(index) == '1') {
        num++;
      } else {
        zeroRemoveCount++;
      }
    }
    stepCount++;

    // 최종적으로 이진 변환 결과 1이 될때까지 반복한다
    for (; num != 1; stepCount++) {
      int y = 0;
      int i = 32; // int의 크기는 32비트

      // 앞에있는 0은 버린다
      for (i = 31; i >= 0 && ((num & (1 << i)) == 0); i--);

      // 1과 0의 개수를 카운트
      for (; i >= 0; i--) {
        if ((num & (1 << i)) != 0) {
          y++;          
        } else {
          zeroRemoveCount++;
        }
      }

      // 다음 이진 탐색을 위해 변수를 업데이트 한다
      num = y;
    }

    // 결과 반환
    return new int[]{stepCount, zeroRemoveCount};
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(new Solution().solution("0111010"))); // [2, 5]
    System.out.println(Arrays.toString(new Solution().solution("110010101001"))); // [3, 8]
    System.out.println(Arrays.toString(new Solution().solution("01110"))); // [3, 3]
    System.out.println(Arrays.toString(new Solution().solution("1111111"))); // [4, 1]
  }
}
