/*
 * (5215) 햄버거 다이어트
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT&categoryId=AWT-lPB6dHUDFAVT&categoryType=CODE&problemTitle=5215&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

package swea.p5215;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * SW Expert Academy - 5215. 햄버거 다이어트
 * @author YeJun, Jung
 *
 * @see #main(String[])
 * 1. 입출력을 초기화한다.
 * 2. 테스트 케이스 개수를 입력받는다.
 * 3. 각 테스트 케이스에 대해 솔루션을 실행한다.
 * 
 * @see #Solution(int)
 * 4. 멤버 변수를 초기화한다.
 * 
 * @see #run()
 * 5. 입력, 해결, 출력 순서로 실행한다.
 * 
 * @see #input()
 * 6. 재료의 수(partCount)와 제한 칼로리(limit)를 입력받는다.
 * 7. 각 재료의 점수와 칼로리 정보를 배열에 저장한다.
 * 
 * @see #solve()
 * 8. 최적 점수(bestScore)를 초기화한다.
 * 9. 선택할 재료의 개수를 1개부터 N개까지 늘려가며 조합을 생성한다.
 * 10. nextPermutation을 이용하여 현재 선택 개수(count)에 대한 모든 조합을 탐색한다.
 *  10-1. 선택된 재료들의 칼로리 합과 점수 합을 계산한다.
 *  10-2. 제한 칼로리 미만이며 기존 최고 점수보다 높을 경우 갱신한다.
 * 11. 다음 개수의 조합 생성을 위해 선택 배열(selects)을 초기화(reverse)한다.
 * 
 * @see #print()
 * 12. 최종적으로 계산된 최대 점수를 출력한다.
 */
public class Solution {
  // 1. 입출력을 초기화한다.
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;

  public static void main(String[] args) throws IOException {
    // 2. 테스트 케이스 개수를 입력받는다.
    final int testCount = Integer.parseInt(reader.readLine().trim());

    for (int testCase = 1; testCase <= testCount; testCase++) {
      // 3. 각 테스트 케이스에 대해 솔루션을 실행한다.
      new Solution(testCase).run();
    }
  }

  // ----------------------------------------------------------

  private int testCase;
  private int bestScore;

  private int partCount;
  private int limit;
  private int[] parts;
  private int[] scores;

  public Solution(int testCase) {
    // 4. 멤버 변수를 초기화한다.
    this.testCase = testCase;
  }

  public void run() throws IOException {
    // 5. 입력, 해결, 출력 순서로 실행한다.
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    // 6. 재료의 수(partCount)와 제한 칼로리(limit)를 입력받는다.
    getLine();
    partCount = Integer.parseInt(input.nextToken());
    limit = Integer.parseInt(input.nextToken());

    // 7. 각 재료의 점수와 칼로리 정보를 배열에 저장한다.
    scores = new int[partCount];
    parts = new int[partCount];

    for (int index = 0; index < partCount; index++) {
      getLine();
      scores[index] = Integer.parseInt(input.nextToken());
      parts[index] = Integer.parseInt(input.nextToken());
    }
  }

  private void solve() {
    // 8. 최적 점수(bestScore)를 초기화한다.
    bestScore = 0;
    int[] selects = new int[partCount]; 

    // 9. 선택할 재료의 개수를 1개부터 N개까지 늘려가며 조합을 생성한다.
    for (int count = 0; count < partCount; count++) {
      selects[count] = 1;

      // 10. nextPermutation을 이용하여 현재 선택 개수에 대한 모든 조합을 탐색한다.
      do {
        int currentValue = 0;
        int currentScore = 0;

        // 10-1. 선택된 재료들의 칼로리 합과 점수 합을 계산한다.
        for (int index = 0; index < partCount; index++) {
          if (selects[index] == 0) continue;
          currentValue += parts[index];
          currentScore += scores[index];
        }

        // 10-2. 제한 칼로리 미만이며 기존 최고 점수보다 높을 경우 갱신한다.
        if (currentValue < limit && currentScore > bestScore) {
          bestScore = currentScore;
        }
      } while (nextPermutation(selects));

      // 11. 다음 개수의 조합 생성을 위해 선택 배열(selects)을 초기화(reverse)한다.
      reverse(selects, 0, partCount - 1);
    }
  }

  private void print() throws IOException {
    // 12. 최종적으로 계산된 최대 점수를 출력한다.
    writer.write("#" + testCase);
    writer.write(" " + bestScore);
    writer.write("\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }

  private static boolean nextPermutation(int[] x) {
    int n = x.length - 1, a = n, b = n;
    while (a > 0 && x[a - 1] <= x[a]) a--;
    if (a == 0) return false;
    while (x[a - 1] <= x[b]) b--;
    swap(x, a - 1, b); reverse(x, a, n);
    return true;
  }

  private static void swap(int[] x, int a, int b) {
    int t = x[a]; x[a] = x[b]; x[b] = t;
  }

  private static void reverse(int[] x, int a, int b) {
    while (a < b) swap(x, a++, b--);
  }
}
