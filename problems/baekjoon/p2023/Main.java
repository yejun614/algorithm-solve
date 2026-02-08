/*
 * (2023) 신기한 소수
 * https://www.acmicpc.net/problem/2023
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 신기한 소수
 * @author YeJun, Jung
 * 
 * @see main(String[] args)
 * 1. N을 입력받는다.
 * 2. 1~10자리수를 가진 숫자중 가장 작은 숫자들을 사전 생성한다.
 *    (1, 10, 100, 1000, 10000, ...)
 * 3. 탐색 범위를 begin, end 변수에 저장한다.
 * 4. 신기한 소수의 왼쪽 1자리에는 반드시 소수인 (2, 3, 5, 7) 중에 하나가 와야 한다.
 * 5. 만약 N이 4라면 2000~2999, 3000~3999, ... 식으로 탐색을 진행한다.
 * 6. 만약 현재 탐색중인 수가 신비한 소수라면 화면에 출력한다.
 * 
 * @see makeSizedNum()
 * 7. sizedNum 배열의 0번째 자리에는 1을 넣어둔다.
 * 8. 1, 10, 100, 1000, 10000, ... 식으로 숫자를 만들어 sizedNum 배열에 저장한다.
 * 
 * @see isSpecialPrimeNum(int num, int pos, int size)
 * 9. 기저조건을 확인한다.
 * 10. 현재 자리수(pos)에 해당하는 숫자까지 추출한다.
 * 11. 지금 탐색중인 수가 소수에 해당하지 않는다면 신비한 소수가 될 수 없다.
 * 12. 다음 자리수로 탐색을 진행한다.
 * 
 * @see isPrimeNum(int num)
 * 13. 1이하의 숫자는 소수가 아니다.
 * 14. 2, 3은 소수이다.
 * 15. 2의 배수는 소수가 아니다.
 * 16. 3부터 2씩 늘려가면서 홀수값들을 sqrt(num)까지 탐색한다.
 * 17. 1과 자기자신 이외의 수와 나누어 떨어진다면 해당 수는 소수가 아니다.
 */
public class Main {
  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

  private static final int[] START_NUM = { 2, 3, 5, 7 };
  private static final int MAX_N = 10;
  private static int[] sizedNum;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    // 1. N을 입력받는다.
    int N = Integer.parseInt(reader.readLine().trim());

    // 2. 1~10자리수를 가진 숫자중 가장 작은 숫자들을 사전 생성한다.
    //   (1, 10, 100, 1000, 10000, ...)
    makeSizedNum();

    // 3. 탐색 범위를 begin, end 변수에 저장한다.
    int begin = sizedNum[N];
    int end = sizedNum[N + 1] - 1;

    // 4. 신기한 소수의 왼쪽 1자리에는 반드시 소수인 (2, 3, 5, 7) 중에 하나가 와야 한다.
    for (int start : START_NUM) {
      // 5. 만약 N이 4라면 2000~2999, 3000~3999, ... 식으로 탐색을 진행한다.
      int num = begin * start;

      for (int count = 0; count < sizedNum[N]; count++) {
        // 6. 만약 현재 탐색중인 수가 신비한 소수라면 화면에 출력한다.
        if (isSpecialPrimeNum(num, 2, N)) writer.write(num + "\n");

        num++;
      }
    }
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void makeSizedNum() {
    sizedNum = new int[MAX_N];

    // 7. sizedNum 배열의 0번째 자리에는 1을 넣어둔다.
    sizedNum[0] = sizedNum[1] = 1;

    // 8. 1, 10, 100, 1000, 10000, ... 식으로 숫자를 만들어 sizedNum 배열에 저장한다.
    for (int index = 2; index < MAX_N; index++) {
      sizedNum[index] = sizedNum[index - 1] * 10;
    }
  }

  private static boolean isSpecialPrimeNum(int num, int pos, int size) {
    // 9. 기저조건을 확인한다.
    if (pos > size) return true;

    // 10. 현재 자리수(pos)에 해당하는 숫자까지 추출한다.
    int current = num / sizedNum[size - pos + 1];

    // 11. 지금 탐색중인 수가 소수에 해당하지 않는다면 신비한 소수가 될 수 없다.
    if (!isPrimeNum(current)) return false;
    // 12. 다음 자리수로 탐색을 진행한다.
    return isSpecialPrimeNum(num, pos + 1, size);
  }

  private static boolean isPrimeNum(int num) {
    if (num <= 1) return false;      // 13. 1이하의 숫자는 소수가 아니다.
    if (num <= 3) return true;       // 14. 2, 3은 소수이다.
    if (num % 2 == 0) return false;  // 15. 2의 배수는 소수가 아니다.

    // 16. 3부터 2씩 늘려가면서 홀수값들을 sqrt(num)까지 탐색한다.
    final int last = (int)Math.sqrt(num);
    for (int x = 3; x <= last; x += 2) {
      // 17. 1과 자기자신 이외의 수와 나누어 떨어진다면 해당 수는 소수가 아니다.
      if (num % x == 0) return false;
    }
    return true;
  }
}
