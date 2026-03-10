/*
 * (3124) 최소 스패닝 트리
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV_mSnmKUckDFAWb&categoryId=AV_mSnmKUckDFAWb&categoryType=CODE&problemTitle=3124&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 최소 스패닝 트리
 * @author YeJun, Jung
 * 
 * [전략]
 *   - MST을 만들기 위해 크루스칼 알고리즘을 적용하였습니다.
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
  long answer;
  int nodeLen;
  int edgeLen;
  Edge[] edgeArr;
  int[] parentArr;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    nodeLen = nextInt();
    edgeLen = nextInt();
    edgeArr = new Edge[edgeLen];

    for (int idx = 0; idx < edgeLen; idx++) {
      edgeArr[idx] = Edge.fromReader();
    }
  }

  private void solve() {
    makeParentArr();

    answer = kruskal();
  }

  private void makeParentArr() {
    parentArr = new int[nodeLen];
    Arrays.fill(parentArr, 0, nodeLen, -1);
  }

  private long kruskal() {
    int cnt = 0;
    long cost = 0;

    Arrays.sort(edgeArr);

    for (int idx = 0; idx < edgeLen && cnt < nodeLen - 1; idx++) {
      Edge edge = edgeArr[idx];

      if (unionParent(edge.v0, edge.v1)) {
        cnt++;
        cost += edge.w;
      }
    }

    return cost;
  }

  private boolean unionParent(int a, int b) {
    int pa = findParent(a);
    int pb = findParent(b);

    if (pa == pb) return false;

    parentArr[pa] += parentArr[pb];
    parentArr[pb] = pa;
    return true;
  }

  private int findParent(int x) {
    if (parentArr[x] < 0) return x;
    return parentArr[x] = findParent(parentArr[x]);
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

  static class Edge implements Comparable<Edge> {
    int v0, v1;
    long w;

    public Edge(int v0, int v1, long w) {
      this.v0 = v0;
      this.v1 = v1;
      this.w = w;
    }

    public static Edge fromReader() throws IOException {
      int v0 = nextInt() - 1;
      int v1 = nextInt() - 1;
      long w = nextLong();

      return new Edge(v0, v1, w);
    }

    @Override
    public int compareTo(Edge another) {
      return Long.compare(this.w, another.w);
    }
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

  static long nextLong() throws IOException {
    return Long.parseLong(next());
  }
}
