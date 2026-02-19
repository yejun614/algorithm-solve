/*
 * (1238) [S/W 문제해결 기본] 10일차 - Contact
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15B1cKAKwCFAYD&categoryId=AV15B1cKAKwCFAYD&categoryType=CODE&problemTitle=1238&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [S/W 문제해결 기본] 10일차 - Contact
 * @author YeJun, Jung
 * 
 * [분석]
 *   - BFS를 하는데, 마지막에 연락을 받는 노드들 중에서 가장 큰 숫자를 출력
 * 
 * [전략]
 *   - BFS 사용
 *   - visited 배열을 사용해서 방문 처리(양방향 통신이 가능한 경우를 처리하기 위함)
 *   - 마지막에 연락을 받는 노드들 중에서 가장 큰 숫자를 출력
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    final int testCount = 10;

    for (int testCase = 1; testCase <= testCount; testCase++) {
      new Solution(testCase).run();
    }
  }

  // ----------------------------------------------------------

  static final int MAX_NODE_LEN = 101;

  int testCase;
  int answer;
  int nodeLen;
  int startNode;
  List<List<Integer>> graph;
  boolean[] visited;

  public Solution(int testCase) {
    this.testCase = testCase;
  }

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    getLine();
    nodeLen = Integer.parseInt(input.nextToken());
    startNode = Integer.parseInt(input.nextToken());

    int fromNode, toNode;
    graph = new ArrayList<>(MAX_NODE_LEN);
    for (int cnt = 0; cnt < MAX_NODE_LEN; cnt++) {
      graph.add(new ArrayList<>());
    }

    getLine();
    while (input.hasMoreTokens()) {
      fromNode = Integer.parseInt(input.nextToken());
      toNode = Integer.parseInt(input.nextToken());

      graph.get(fromNode).add(toNode);
    }
  }

  private void solve() {
    visited = new boolean[MAX_NODE_LEN];
    Deque<Context> q = new ArrayDeque<>();
    answer = startNode;
    int time = 0;

    q.offer(new Context(startNode, time));
    visited[startNode] = true;

    while (!q.isEmpty()) {
      Context peek = q.poll();

      if (time == peek.time) {
        answer = Math.max(answer, peek.node);
      } else if (time < peek.time) {
        answer = peek.node;
        time = peek.time;
      }

      for (int node : graph.get(peek.node)) {
        if (visited[node]) continue;
        visited[node] = true;

        q.offer(new Context(node, peek.time + 1));
      }
    }
  }

  private void print() throws IOException {
    writer.write("#" + testCase);
    writer.write(" " + answer);
    writer.write("\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static class Context {
    int node;
    int time;

    public Context(int node, int time) {
      this.node = node;
      this.time = time;
    }
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
