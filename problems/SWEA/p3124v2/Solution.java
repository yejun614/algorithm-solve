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
 *   - 프림 알고리즘과 우선순위 큐를 사용해서 MST를 구성했습니다.
 * 
 * [오답을 받았던 이유]
 *   - 무향 그래프인걸 잊고 입력받은 간선을 그대로 사용해서 유향 그래프가 되어서 오답을 받았습니다.
 *   - 무향 그래프로 수정한 이후 (출발 노드, 도착 노드)를 뒤집어 저장하여 오답을 받았습니다.
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
  List<List<Edge>> graph;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    nodeLen = nextInt();
    edgeLen = nextInt();

    graph = new ArrayList<>(nodeLen);
    for (int node = 0; node < nodeLen; node++) {
      graph.add(new ArrayList<>());
    }

    for (int idx = 0; idx < edgeLen; idx++) {
      int v0 = nextInt() - 1;
      int v1 = nextInt() - 1;
      long w = nextLong();

      if (v0 == v1) continue;

      graph.get(v0).add(new Edge(v0, v1, w));
      graph.get(v1).add(new Edge(v1, v0, w));
    }
  }

  private void solve() {
    int start = 0;
    for (; start < nodeLen; start++) {
      if (graph.get(start).size() > 0) break;
    }

    answer = prim(start);
  }

  private long prim(int start) {
    int cnt = 0;
    long cost = 0;

    boolean[] visited = new boolean[nodeLen];
    PriorityQueue<Edge> pq = new PriorityQueue<>();

    visited[start] = true;
    for (Edge edge : graph.get(start)) pq.offer(edge);

    while (!pq.isEmpty() && cnt < nodeLen - 1) {
      Edge edge = pq.poll();

      if (visited[edge.v1]) continue;
      visited[edge.v1] = true;

      cnt++;
      cost += edge.w;

      for (Edge nextEdge : graph.get(edge.v1)) {
        if (visited[nextEdge.v1]) continue;
        pq.offer(nextEdge);
      }
    }

    return cost;
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
