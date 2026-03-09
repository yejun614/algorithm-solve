/*
 * (13023) ABCDE
 * https://www.acmicpc.net/problem/13023
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - ABCDE
 * @author YeJun, Jung
 * 
 * [분석]
 *   - A -> B -> C -> D -> E 관계가 그래프 안에 존재하는지 파악하는 문제이다.
 * 
 * [전략]
 *   - 인접리스트로 그래프를 저장한다.
 *   - 모든 정점에서 출발하는 DFS를 통해서 깊이가 4이상 되는지 파악한다.
 *   - 깊이가 4가 되면 탐색을 조기 종료하여 최적화 한다.
 */
public class Main {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder output = new StringBuilder();
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    new Main().run();
    System.out.print(output.toString());
  }

  // ----------------------------------------------------------

  int answer;
  int nodeLen;
  int edgeLen;
  List<List<Integer>> graph;
  boolean[] visited;

  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    int a, b;

    nodeLen = nextInt();
    edgeLen = nextInt();

    graph = new ArrayList<>(nodeLen);
    for (int idx = 0; idx < nodeLen; idx++) {
      graph.add(new ArrayList<>());
    }

    for (int edge = 0; edge < edgeLen; edge++) {
      a = nextInt();
      b = nextInt();

      graph.get(a).add(b);
      graph.get(b).add(a);
    }
  }

  private void solve() {
    visited = new boolean[nodeLen];
    answer = 0;

    for (int node = 0; node < nodeLen; node++) {
      visited[node] = true;
      dfs(node, 0);
      visited[node] = false;

      if (answer == 1) return;
    }
  }

  private void dfs(int node, int cnt) {
    if (answer == 1) return;
    if (cnt == 4) {
      answer = 1;
      return;
    }

    for (int child : graph.get(node)) {
      if (visited[child]) continue;

      visited[child] = true;
      dfs(child, cnt + 1);
      visited[child] = false;

      if (answer == 1) return;
    }
  }

  private void print() {
    output
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
