/*
 * (1247) [S/W 문제해결 응용] 3일차 - 최적 경로
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD&categoryId=AV15OZ4qAPICFAYD&categoryType=CODE&problemTitle=1247&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [S/W 문제해결 응용] 3일차 - 최적 경로
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 시작 지점과 도착 지점이 정해질때 주어진 중간 지점을 최소한의 비용으로 방문하는 방법을 찾아야 한다.
 *   - TSP(외판원 문제) 유형
 * 
 * [전략]
 *   - DFS + Backtracking 으로 해결
 *     - 시작/도착 지점은 고정이니 제외하고, 중간 지점의 조합을 탐색하면서 최적해를 찾아나간다.
 *     - 계산 과정에서 지금까지 계산된 최적해보다 비용이 커지면 해당 가지는 더 이상 탐색하지 않는다.
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

  int start;
  int goal;
  Pos[] nodeArr;
  boolean[] visited;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    nodeLen = nextInt() + 2;
    nodeArr = new Pos[nodeLen];

    start = 0;
    goal = 1;

    for (int idx = 0; idx < nodeLen; idx++) {
      nodeArr[idx] = Pos.fromReader();
    }
  }

  private void solve() {
    answer = Integer.MAX_VALUE;

    visited = new boolean[nodeLen];
    visited[start] = true;

    dfs(start, 1, 0);
  }

  private void dfs(int node, int cnt, int cost) {
    if (answer < cost) return;

    if (cnt == nodeLen - 1) {
      answer = Math.min(answer, cost + getDistance(node, goal));
      return;
    }

    for (int idx = 2; idx < nodeLen; idx++) {
      if (visited[idx]) continue;

      visited[idx] = true;
      dfs(idx, cnt + 1, cost + getDistance(node, idx));
      visited[idx] = false;
    }
  }

  private int getDistance(int a, int b) {
    Pos posA = nodeArr[a];
    Pos posB = nodeArr[b];

    return Math.abs(posA.x - posB.x) + Math.abs(posA.y - posB.y);
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

  static class Pos {
    int x, y;

    public Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public static Pos fromReader() throws IOException {
      int x = nextInt();
      int y = nextInt();

      return new Pos(x, y);
    }
  }

  // ----------------------------------------------------------

  static class Edge implements Comparable<Edge> {
    int v0, v1, w;

    public Edge(int v0, int v1, int w) {
      this.v0 = v0;
      this.v1 = v1;
      this.w = w;
    }

    @Override
    public int compareTo(Edge another) {
      return Integer.compare(this.w, another.w);
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
}
