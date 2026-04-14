/*
 * (5643) [Professional] 키 순서
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXQsLWKd5cDFAUo&categoryId=AWXQsLWKd5cDFAUo&categoryType=CODE&problemTitle=%ED%82%A4+%EC%88%9C%EC%84%9C&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [Professional] 키 순서
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 학생들의 키를 비교하여 그래프로 나타낸 데이터가 입력된다.
 *   - (키가 작은 학생)의 자식 노드에 (키가 큰 학생)이 저장된다.
 *   - 자신의 상대적 키의 위치를 정확히 알 수 있는 학생의 수를 찾아야 한다.
 *   - 그래프를 구성하고 (타겟 노드)의 (자식 노드의 개수) + (부모 노드의 개수) + (자기 자신, 1) == (전체 노드의 개수) 조건을 만족하면 자신의 위치를 정확히 알 수 있는 노드이다.
 *   - 각 노드에 대해서 위의 조건에 부합하는 노드들의 개수를 세어서 출력한다.
 * 
 * [전략]
 *   - 그래프를 저장하기 위해 인접리스트를 사용했는데, `ArrayList<List<Integer>>` 객체를 상속받은 Graph 클래스를 만들어 활용했다.
 *   - 자식/부모 노드의 개수를 세기 위해서 DFS를 사용했다.
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

  int studentLen;
  int edgeLen;
  int nodeLen;
  Graph parentGraph;
  Graph childGraph;

  boolean[] visited;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    int a, b;

    studentLen = nextInt();
    edgeLen = nextInt();

    nodeLen = studentLen + 1;
    parentGraph = new Graph(nodeLen);
    childGraph = new Graph(nodeLen);

    for (int cnt = 0; cnt < edgeLen; cnt++) {
      a = nextInt();
      b = nextInt();

      // add edges(a --> b)
      parentGraph.get(b).add(a);
      childGraph.get(a).add(b);
    }
  }

  private void solve() {
    answer = 0;

    for (int idx = 1; idx < nodeLen; idx++) {
      if (isFullOrdering(idx)) answer++;
    }
  }

  private boolean isFullOrdering(int idx) {
    visited = new boolean[nodeLen];
    int cnt0 = cntDfs(parentGraph, idx);

    visited = new boolean[nodeLen];
    int cnt1 = cntDfs(childGraph, idx);

    return (cnt0 + cnt1 + 1) == studentLen;
  }

  private int cntDfs(Graph graph, int idx) {
    visited[idx] = true;

    int result = 0;

    for (int nIdx : graph.get(idx)) {
      if (visited[nIdx]) continue;

      result += 1 + cntDfs(graph, nIdx);
    }

    return result;
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

  class Graph extends ArrayList<List<Integer>> {
    public Graph(int len) {
      super();

      for (int cnt = 0; cnt < len; cnt++)
        this.add(new ArrayList<>());
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
