/*
 * (1267) [S/W 문제해결 응용] 10일차 - 작업순서
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV18TrIqIwUCFAZN&categoryId=AV18TrIqIwUCFAZN&categoryType=CODE&problemTitle=1267&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 1267. [S/W 문제해결 응용] 10일차 - 작업순서
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 각 작업에는 선행작업이 있을 수 있다.
 *   - 간선이 없는 노드가 있을 수 있다.(즉 선행작업이 없는 노드가 존재할 수 있음)
 * 
 * [전략]
 *   - 위상정렬을 사용해서 선행작업이 먼저 수행되도록 순서를 만든다.
 *   - 선행작업이 없는 노드의 작업 순서는 중요하지 않다.
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

  int testCase;
  int[] workOrder;

  int nodeLen;
  int edgeLen;
  List<List<Integer>> graph;
  int[] inorder;

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
    edgeLen = Integer.parseInt(input.nextToken());

    graph = new ArrayList<>(nodeLen + 1);
    for (int node = 0; node <= nodeLen; node++) {
      graph.add(new ArrayList<>());
    }

    int nodeA, nodeB;
    inorder = new int[nodeLen + 1];
    getLine();
    while (input.hasMoreTokens()) {
      nodeA = Integer.parseInt(input.nextToken());
      nodeB = Integer.parseInt(input.nextToken());

      graph.get(nodeA).add(nodeB);
      inorder[nodeB]++;
    }
  }

  private void solve() {
    workOrder = new int[nodeLen];

    topologicalSort();
  }

  private void topologicalSort() {
    int size = 0;
    Queue<Integer> q = new ArrayDeque<>();

    for (int node = 1; node <= nodeLen; node++) {
      if (inorder[node] == 0) q.offer(node);
    }

    while (!q.isEmpty()) {
      int peek = q.poll();
      workOrder[size++] = peek;

      for (int child : graph.get(peek)) {
        if (--inorder[child] == 0) q.offer(child);
      }
    }
  }

  private void print() throws IOException {
    writer.write("#" + testCase + " ");

    for (int node : workOrder) {
      writer.write(node + " ");
    }

    writer.write("\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
