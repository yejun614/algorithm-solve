/*
 * (14510) 나무 높이
 * https://swexpertacademy.com/main/code/userProblem/userProblemSolver.do?contestProbId=AYFofW8qpXYDFAR4
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 14510. 나무 높이
 * @author YeJun, Jung
 * 
 * @see #main(String[] args)
 * 1. 테스트케이스를 입력받는다.
 * 2. 솔루션을 실행한다.
 * 
 * @see #input()
 * 3. 나무의 개수와 나무의 높이를 입력받는다.
 * 
 * @see #solve()
 * 4. 입력받은 나무의 높이들 중에서 최대값을 찾는다.
 * 5. 홀수날, 짝수날이 각각 몇개 필요한지 저장할 변수를 선언한다.
 * 6. 각 나무 높이에 대해서...
 *   6-1. 나무가 얼마나 성장해야 하는지 계산한다.
 *   6-2. 가능한 짝수날에서 나무를 최대한 성장시키고,
 *        남은 높이는 홀수날에 성장시킨다.
 * 7. 각 나무 높이에 대해서 합산된 홀수날, 짝수날을...
 *   7-1. 짝수날을 홀수날 2개로 쪼개어 홀,짝,홀,짝 빈틈없이 채운다.
 * 8. 홀수날이 짝수날 보다 많다면 -> 첫번째 날(홀수) 1과 그외 홀수날들에 x2한 값을 더한다.
 *    짝수날이 홀수날 보다 많다면 -> 짝수날에 x2 한다.
 *    홀수날과 짝수날이 같다면    -> 홀수날의 합과 짝수날의 합을 더해준다.
 * 
 * @see #print()
 * 9. bestDays를 화면에 출력한다.
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    // 1. 테스트케이스를 입력받는다.
    final int testCount = Integer.parseInt(reader.readLine().trim());

    for (int testCase = 1; testCase <= testCount; testCase++) {
      // 2. 솔루션을 실행한다.
      new Solution(testCase).run();
    }
  }

  // ----------------------------------------------------------

  int testCase;
  int bestDays;

  int treeLen;
  int[] treeArr;

  public Solution(int testCase) {
    this.testCase = testCase;
  }

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  public void input() throws IOException {
    // 3. 나무의 개수와 나무의 높이를 입력받는다.

    treeLen = Integer.parseInt(reader.readLine().trim());
    treeArr = new int[treeLen];

    getLine();
    for (int index = 0; index < treeLen; index++) {
      treeArr[index] = Integer.parseInt(input.nextToken());
    }
  }

  public void solve() {
    // 4. 입력받은 나무의 높이들 중에서 최대값을 찾는다.
    int maxH = 0;
    for (int h : treeArr) maxH = Math.max(maxH, h);

    // 5. 홀수날, 짝수날이 각각 몇개 필요한지 저장할 변수를 선언한다.
    int odd = 0, even = 0;

    // 6. 각 나무 높이에 대해서...
    for (int i = 0; i < treeLen; i++) {
        // 6-1. 나무가 얼마나 성장해야 하는지 계산한다.
        int diff = maxH - treeArr[i];

        // 최대높이인 나무는 제외
        if (diff == 0) continue;

        // 6-2. 가능한 짝수날에서 나무를 최대한 성장시키고,
        //      남은 높이는 홀수날에 성장시킨다.
        even += diff / 2;
        odd += diff % 2;
    }

    if (even > odd) {
        // 7. 각 나무 높이에 대해서 합산된 홀수날, 짝수날을...
        while (Math.abs(even - odd) > 1) {
            // 7-1. 짝수날을 홀수날 2개로 쪼개어 홀,짝,홀,짝 빈틈없이 채운다.
            even--;
            odd += 2;
        }
    }

    // 8. 홀수날이 짝수날 보다 많다면 -> 첫번째 날(홀수) 1과 그외 홀수날들에 x2한 값을 더한다.
    //    짝수날이 홀수날 보다 많다면 -> 짝수날에 x2 한다.
    //    홀수날과 짝수날이 같다면    -> 홀수날의 합과 짝수날의 합을 더해준다.
    if (odd > even) {
        bestDays = odd * 2 - 1;
    } else if (even > odd) {
        bestDays = even * 2;
    } else {
        bestDays = odd + even;
    }
  }

  public void print() throws IOException {
    // 9. bestDays를 화면에 출력한다.
    writer.write("#" + testCase);
    writer.write(" " + bestDays);
    writer.write("\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
