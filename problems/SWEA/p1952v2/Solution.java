/*
 * (1952) [모의 SW 역량테스트] 수영장
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq&categoryId=AV5PpFQaAQMDFAUq&categoryType=CODE&problemTitle=1952&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [모의 SW 역량테스트] 수영장
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 수영장 이용권은 1일/1달/3달/1년 으로 구분된다.
 *   - 12개월의 수영장 이용계획이 입력으로 주어진다.
 *   - 수영장 이용 비용을 최소화 해야 한다.
 * 
 * [전략]
 *   - 각 월에 대해서 4가지 이용권을 시도해보면서 가능한 모든 조합과 비용을 만든다.
 *   - 현재 시도중인 월에 대해 이전에 계산해둔 cache가 존재하면 해당 값을 사용한다.
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder output = new StringBuilder();
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    final int testCount = nextInt();

    for (int testCase = 1; testCase <= testCount; testCase++) {
      new Solution(testCase).run();
    }

    System.out.print(output.toString());
  }

  // ----------------------------------------------------------

  int testCase;
  int answer;

  int[] costArr;
  int[] monthPlanArr;
  int[] cache;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    costArr = new int[4];
    for (int idx = 0; idx < 4; idx++) {
      costArr[idx] = nextInt();
    }

    monthPlanArr = new int[12];
    for (int idx = 0; idx < 12; idx++) {
      monthPlanArr[idx] = nextInt();
    }
  }

  private void solve() {
    cache = new int[12];
    Arrays.fill(cache, -1);

    answer = search(0);
  }

  private int search(int month) {
    if (month >= 12) return 0;
    if (cache[month] >= 0) return cache[month];

    int result = costArr[3];

    result = Math.min(result, (costArr[0] * monthPlanArr[month]) + search(month + 1));
    result = Math.min(result, (costArr[1]) + search(month + 1));
    result = Math.min(result, (costArr[2]) + search(month + 3));

    return cache[month] = result;
  }

  private void print() {
    output
      .append('#')
      .append(testCase)
      .append(' ')
      .append(answer)
      .append('\n');
  }

  // ----------------------------------------------------------

  static String next() throws IOException {
    if (input == null || !input.hasMoreTokens())
      input = new StringTokenizer(reader.readLine().trim());
    return input.nextToken();
  }

  static int nextInt() throws IOException {
    return Integer.parseInt(next());
  }
}
