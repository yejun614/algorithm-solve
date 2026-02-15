/*
 * (6782) 현주가 좋아하는 제곱근 놀이
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWgqsAlKr9sDFAW0&categoryId=AWgqsAlKr9sDFAW0&categoryType=CODE&problemTitle=6782&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 6782. 현주가 좋아하는 제곱근 놀이
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 주어진 N을 N+1 하면서 sqrt(N) 결과 (정수)가 되도록 만들어야 한다.
 *   - sqrt(N)은 비용이 낮지만, N+1은 너무 많이 반복해야 한다.
 *   - 주어지는 숫자가 크기 때문에(10¹²) Long 타입을 사용한다.
 * 
 * [전략]
 *   - 사전에 2...1,000,000 까지 제곱해서 저장해두고 활용한다.(백만번 반복)
 *     - sqrt(N) 결과 정수가 아니어서 N+1 해야할 때 사전에 계산해둔 제곱근 결과를 활용한다.
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    final int testCount = Integer.parseInt(reader.readLine().trim());

    for (int testCase = 1; testCase <= testCount; testCase++) {
      new Solution(testCase).run();
    }
  }

  // ----------------------------------------------------------

  static final int MAX_POW = 1000001;
  static long[] powArr = new long[MAX_POW];

  static {
    // 사전에 2...1,000,000 까지 제곱해서 저장해두고 활용한다.(백만번 반복)
    for (long num = 2; num < MAX_POW; num++) {
      powArr[(int)(num)] = num * num;
    }
  }

  int testCase;
  long startNum;
  int answer;

  public Solution(int testCase) {
    this.testCase = testCase;
  }

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    startNum = Long.parseLong(reader.readLine().trim());
  }

  private void solve() {
    answer = countUntilTwo(startNum);
  }

  private int countUntilTwo(long num) {
    // 기저 조건
    if (num == 2) return 0;

    // 일단 sqrt(num) 한다.
    int sqrt = (int)Math.sqrt(num);

    // sqrt(num) 결과가 정수라면, 바로 sqrt(num) 결과를 적용한다.
    if (powArr[sqrt] == num) {
      return 1 + countUntilTwo(sqrt);
    }

    // sqrt(num) 결과가 정수가 아니라면, 정수가 되도록 num을 조정한다.
    sqrt++;
    return (int)(powArr[sqrt] - num) + countUntilTwo(powArr[sqrt]);
  }

  private void print() throws IOException {
    writer.write("#" + testCase);
    writer.write(" " + answer);
    writer.write("\n");
    writer.flush();
  }

  // ----------------------------------------------------------
}
