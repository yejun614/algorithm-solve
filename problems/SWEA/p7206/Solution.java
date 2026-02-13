/*
 * (7206) 숫자 게임
 * https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AWlGyBQqaEgDFASG&categoryId=AWlGyBQqaEgDFASG&categoryType=CODE
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 7206. 숫자 게임
 * @author YeJun, Jung
 * 
 * [주요 변수]
 *   - 주어진 시작 숫자 = startNum
 *   - 숫자의 각 자리의 중간(자르는 부분)의 길이 = numLen
 * 
 * [탐색]
 *   - 숫자의 어디를 자를 건지, 부분집합으로 결정
 *     - 숫자의 최대길이가 5(99999) 이므로 BitMask(int, 32bit)로 충분히 처리 가능
 *     - BitMask를 사용해서 부분집합 생성
 *   - 숫자를 잘라서 곱한 후, 다음 탐색으로 이동
 *     - 재귀함수를 사용
 *     - 가장 turnCount가 값을 선택한다.
 * 
 * [최적화]
 *   - 메모이제이션 방법을 사용
 *     - solve() 함수의 파라미터와 리턴값을 기록해 두었다가, 활용
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    final int testCount = Integer.parseInt(reader.readLine().trim());

    for (int testCase = 1; testCase <= testCount; testCase++) {
      new Solution(testCase).run();
    }
  }

  // ----------------------------------------------------------

  static int[] cache = new int[100000];

  int testCase;
  int startNum;
  int answer;
  
  public Solution(int testCase) {
    this.testCase = testCase;
  }

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  public void input() throws IOException {
    startNum = Integer.parseInt(reader.readLine().trim());
  }

  public void solve() {
    answer = search(startNum, 0);
  }

  private int search(int num, int turnCount) {
    if (num < 10) return 0;
    if (cache[num] != 0) return cache[num];

    final int numLen = getNumLen(num) - 1;
    final int last = 1 << numLen;

    int bestTurnCount = 0;

    for (int mask = 1; mask != last; mask++) {
      int target = num;
      int nextNum = 1;

      for (int index = 0; index < numLen; index++) {
        if ((mask & (1 << index)) == 0) continue;

        int[] split = splitNum(target, numLen - index);
        nextNum *= split[0];
        target = split[1];
      }
      if (target > 0) nextNum *= target;

      bestTurnCount = Math.max(bestTurnCount, 1 + searchRecrusive(nextNum, turnCount + 1));
    }

    cache[num] = bestTurnCount;
    return bestTurnCount;
  }

  private int getNumLen(int num) {
    int result = 0;
    for (; num > 0; result++) num /= 10;
    return result;
  }

  private int[] splitNum(int num, int index) {
    int cutter = 10;
    for (int cnt = 1; cnt < index; cnt++) cutter *= 10;
    return new int[] { num / cutter, num % cutter };
  }

  public void print() throws IOException {
    writer.write("#" + testCase);
    writer.write(" " + answer);
    writer.write("\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
