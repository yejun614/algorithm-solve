/*
 * (1251) [S/W 문제해결 응용] 4일차 - 하나로
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD&categoryId=AV15StKqAQkCFAYD&categoryType=CODE&problemTitle=%ED%95%98%EB%82%98%EB%A1%9C&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [S/W 문제해결 응용] 4일차 - 하나로
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 모든 섬을 연결해야 하는데, 반드시 두 섬을 하나의 선분으로 연결해야만 한다.
 *   - 다리의 길이는 유클리드 거리(피타고라스 정리)로 계산된다.
 *   - 다리 건설 비용은 E * L^2로 계산된다.
 * 
 * [전략]
 *   - 크루스칼 알고리즘을 사용해서 최소 스패닝트리를 구성한다.
 *     - 각 노드에서 다른 노드들로 가는 간선들을 만들어 배열에 저장한다.
 *     - 간선이 저장된 배열을 가중치를 기준으로 오름차순 정렬한다.
 *     - 유니온 파인드 알고리즘으로 트리의 사이클이 생기는지 확인하면서 간선을 차례로 선택한다.
 *   - 비용을 계산할때 E * L^2 공식으로 계산한다.
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
  double[] nodeArrX;
  double[] nodeArrY;
  double extraCostRate;

  int[] parentArr;
  Edge[] edgeArr;
  int edgeLen;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    nodeLen = nextInt();
    nodeArrX = new double[nodeLen];
    nodeArrY = new double[nodeLen];

    for (int idx = 0; idx < nodeLen; idx++) nodeArrX[idx] = nextDouble();
    for (int idx = 0; idx < nodeLen; idx++) nodeArrY[idx] = nextDouble();

    extraCostRate = nextDouble();
  }

  private void solve() {
    makeEdgeArr();
    makeParentArr();

    answer = kruskal();
  }

  private void makeEdgeArr() {
    edgeLen = (nodeLen * nodeLen) - nodeLen;
    int idx = 0;
    edgeArr = new Edge[edgeLen];

    for (int a = 0; a < nodeLen; a++) {
      for (int b = 0; b < nodeLen; b++) {
        if (a == b) continue;

        edgeArr[idx++] = new Edge(a, b, getDistance(a, b));
      }
    }
  }

  private void makeParentArr() {
    parentArr = new int[nodeLen];
    Arrays.fill(parentArr, 0, nodeLen, -1);
  }

  private double kruskal() {
    int cnt = 0;
    double cost = 0;

    Arrays.sort(edgeArr);

    for (int idx = 0; idx < edgeLen && cnt < nodeLen - 1; idx++) {
      Edge edge = edgeArr[idx];

      if (unionParent(edge.v0, edge.v1)) {
        cost += costCalc(edge.w);
        cnt++;
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

  private double costCalc(double n) {
    return extraCostRate * (n * n);
  }

  private double getDistance(int a, int b) {
    return Math.sqrt(
      Math.pow(nodeArrX[a] - nodeArrX[b], 2) + Math.pow(nodeArrY[a] - nodeArrY[b], 2)
    );
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
