/*
 * (5215) 햄버거 다이어트
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT&categoryId=AWT-lPB6dHUDFAVT&categoryType=CODE&problemTitle=5215&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 햄버거 다이어트
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 각 자료마다 맛점수와 칼로리가 주어졌을때 제한 칼로리 안에서 점수가 가장 높은 조합을 찾아야 한다.
 * 
 * [전략]
 *   - DFS를 사용해서 제한 칼로리 안에서 재료들을 선택한다.
 *   - 점수가 높은 재료를 선택한다.
 *   - 현재 시도중인 재료와 현재 칼로리를 메모이제이션 하여 최적화 한다.
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

  int partLen;
  int costLimit;
  int[] partScoreArr;
  int[] partCostArr;
  int[][] cache;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    partLen = nextInt();
    costLimit = nextInt();

    partScoreArr = new int[partLen];
    partCostArr = new int[partLen];

    for (int idx = 0; idx < partLen; idx++) {
      partScoreArr[idx] = nextInt();
      partCostArr[idx] = nextInt();
    }
  }

  private void solve() {
    cache = new int[partLen][10001];
    for (int idx = 0; idx < partLen; idx++) Arrays.fill(cache[idx], -1);

    answer = search(0, 0);
  }

  private int search(int partIdx, int cost) {
    if (partIdx == partLen) return 0;
    if (cache[partIdx][cost] >= 0) return cache[partIdx][cost];

    int result = 0;

    // 1. 현재 재료를 선택하지 않은 경우
    result = Math.max(result, search(partIdx + 1, cost));

    // 2. 현재 재료를 선택한 경우
    if (cost + partCostArr[partIdx] <= costLimit) {
      result = Math.max(result, partScoreArr[partIdx] + search(partIdx + 1, cost + partCostArr[partIdx]));
    }

    return cache[partIdx][cost] = result;
    // return result;
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
