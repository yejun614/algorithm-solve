/*
 * (1251) [S/W 문제해결 응용] 4일차 - 하나로
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD&categoryId=AV15StKqAQkCFAYD&categoryType=CODE&problemTitle=1251&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [S/W 문제해결 응용] 4일차 - 하나로 (Prim 알고리즘 버전)
 * @author YeJun, Jung
 * 
 * [전략]
 *   - Prim 알고리즘을 사용하여 MST를 구성
 *   - PriorityQeueu를 사용하여 최소 가중치 가져오기
 *   - 정답 변수 및 실수형 처리를 위해 double 자료구조 활용
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
  double answer;
  int nodeLen;
  int[] nodeArrX;
  int[] nodeArrY;
  double extraCostRate;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    nodeLen = nextInt();
    nodeArrX = new int[nodeLen];
    nodeArrY = new int[nodeLen];

    for (int idx = 0; idx < nodeLen; idx++) nodeArrX[idx] = nextInt();
    for (int idx = 0; idx < nodeLen; idx++) nodeArrY[idx] = nextInt();

    extraCostRate = nextDouble();
  }

  private void solve() {
    answer = prim();
  }

  private double prim() {
    int cnt = 0;
    double cost = 0;
    boolean[] visited = new boolean[nodeLen];
    PriorityQueue<Edge> pq = new PriorityQueue<>();

    visited[0] = true; // start point
    for (int idx = 1; idx < nodeLen; idx++) {
      pq.offer(new Edge(0, idx, getDistance(0, idx)));
    }

    while (!pq.isEmpty() && cnt < nodeLen - 1) {
      Edge edge = pq.poll();

      if (visited[edge.v1]) continue;
      visited[edge.v1] = true;

      cnt++;
      cost += getCost(edge.w);

      for (int idx = 0; idx < nodeLen; idx++) {
        if (visited[idx]) continue;

        pq.offer(new Edge(edge.v1, idx, getDistance(edge.v1, idx)));
      }
    }

    return cost;
  }

  private double getDistance(int a, int b) {
    int x0 = nodeArrX[a], y0 = nodeArrY[a];
    int x1 = nodeArrX[b], y1 = nodeArrY[b];

    return Math.sqrt(Math.pow(x0 - x1, 2) + Math.pow(y0 - y1, 2));
  }

  private double getCost(double n) {
    return n * n * extraCostRate;
  }

  private void print() {
    output
      .append('#')
      .append(testCase)
      .append(' ')
      .append(Math.round(answer))
      .append('\n');
  }

  // ----------------------------------------------------------

  static class Edge implements Comparable<Edge> {
    int v0, v1;
    double w;

    public Edge(int v0, int v1, double w) {
      this.v0 = v0;
      this.v1 = v1;
      this.w = w;
    }

    @Override
    public int compareTo(Edge another) {
      return Double.compare(this.w, another.w);
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

  static double nextDouble() throws IOException {
    return Double.parseDouble(next());
  }
}
