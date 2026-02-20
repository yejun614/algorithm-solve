/*
 * (2252) 줄 세우기
 * https://www.acmicpc.net/problem/2252
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 줄 세우기
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 전체 학생이 아닌 일부 학생들만 비교를 하고 있다.
 *   - 정답이 여러개 존재할 수 있다.
 * 
 * [전략]
 *   - 학생들의 키에 대한 관계를 그래프로 나타낸다.
 *   - 위상정렬을 사용해서 각 학생 노드의 방문 순서가 키를 기준으로 오름차순되도록 한다.
 *   - 정렬된 노드의 방문 순서를 화면에 표시한다.
 */
public class Main {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    new Main().run();
  }

  // ----------------------------------------------------------

  int[] answer;
  int size;

  int nodeLen;
  int edgeLen;
  List<List<Integer>> graph;
  int[] inorder;

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    getLine();
    nodeLen = Integer.parseInt(input.nextToken());
    edgeLen = Integer.parseInt(input.nextToken());

    int nodeA, nodeB;
    inorder = new int[nodeLen + 1];
    graph = new ArrayList<>(nodeLen + 1);

    for (int index = 0; index <= nodeLen; index++) {
      graph.add(new ArrayList<>());
    }

    for (int cnt = 0; cnt < edgeLen; cnt++) {
      getLine();
      nodeA = Integer.parseInt(input.nextToken());
      nodeB = Integer.parseInt(input.nextToken());

      graph.get(nodeA).add(nodeB);
      inorder[nodeB]++;
    }
  }

  private void solve() {
    answer = new int[nodeLen];

    topologicalSort();
  }

  private void topologicalSort() {
    size = 0;
    Queue<Integer> q = new ArrayDeque<>();

    for (int node = 1; node <= nodeLen; node++) {
      if (inorder[node] != 0) continue;
      q.offer(node);
    }

    while (!q.isEmpty()) {
      int peek = q.poll();

      answer[size++] = peek;

      for (int child : graph.get(peek)) {
        if (--inorder[child] == 0) q.offer(child);
      }
    }
  }

  private void print() throws IOException {
    for (int index = 0; index < nodeLen; index++) {
      writer.write(answer[index] + " ");
    }
    writer.write("\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
