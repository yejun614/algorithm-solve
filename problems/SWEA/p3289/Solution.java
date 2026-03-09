/*
 * (3289) 서로소 집합
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJKA6qr2oDFAWr&categoryId=AWBJKA6qr2oDFAWr&categoryType=CODE&problemTitle=3289&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 서로소 집합
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 런타임에 합집합 연산과 같은 집합에 속해있는지 확인하는 연산을 빠르게 수행해야 하는 문제이다.
 * 
 * [전략]
 *   - Union-Find 알고리즘 적용
 *     - Path compression 최적화 사용
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
  int nodeLen;
  int cmdLen;
  int[] parentArr;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    nodeLen = nextInt();
    cmdLen = nextInt();
  }

  private void solve() throws IOException {
    output
      .append('#')
      .append(testCase)
      .append(' ');

    parentArr = new int[nodeLen + 1];
    for (int idx = 1; idx <= nodeLen; idx++) {
      parentArr[idx] = idx;
    }

    int cmd, arg0, arg1;

    for (int cnt = 0; cnt < cmdLen; cnt++) {
      cmd = nextInt();
      arg0 = nextInt();
      arg1 = nextInt();

      if (cmd == 0) {
        // union
        unionParent(arg0, arg1);
      } else {
        // find
        output.append(checkParent(arg0, arg1) ? '1' : '0');
      }
    }
  }

  private boolean checkParent(int a, int b) {
    return findParent(a) == findParent(b);
  }

  private void unionParent(int a, int b) {
    int pa = findParent(a);
    int pb = findParent(b);

    if (pa != pb) parentArr[pb] = pa;
  }

  private int findParent(int node) {
    if (parentArr[node] == node) return node;
    return parentArr[node] = findParent(parentArr[node]);
  }

  private void print() {
    output.append('\n');
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
