/*
 * (7465) 창용 마을 무리의 개수
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU&categoryId=AWngfZVa9XwDFAQU&categoryType=CODE&problemTitle=7465&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 창용 마을 무리의 개수
 * @author YeJun, Jung
 * 
 * [전략]
 *   - Union-Find 알고리즘 적용
 *   - Path compression 최적화 사용
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
  int edgeLen;
  int[] parentArr;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    int a, b;

    nodeLen = nextInt();
    edgeLen = nextInt();

    makeParentArr();

    for (int edge = 0; edge < edgeLen; edge++) {
      a = nextInt();
      b = nextInt();

      unionParent(a, b);
    }
  }

  private void makeParentArr() {
    parentArr = new int[nodeLen + 1];
    for (int idx = 1; idx <= nodeLen; idx++) {
      parentArr[idx] = idx;
    }
  }

  private void unionParent(int a, int b) {
    int pa = findParent(a);
    int pb = findParent(b);

    if (pa != pb) parentArr[pb] = pa;
  }

  private int findParent(int x) {
    if (parentArr[x] == x) return x;
    return parentArr[x] = findParent(parentArr[x]);
  }

  private void solve() {
    answer = cntGroup();
  }

  private int cntGroup() {
    Set<Integer> set = new HashSet<>();

    for (int idx = 1; idx <= nodeLen; idx++) {
      set.add(findParent(idx));
    }

    return set.size();
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
