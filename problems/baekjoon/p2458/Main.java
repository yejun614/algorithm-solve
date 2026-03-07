/*
 * (2458) 키 순서
 * https://www.acmicpc.net/problem/2458
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 키 순서
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 학생은 1~N까지 번호가 매겨져 있으며 학생의 키는 모두 다르다.
 *   - 문제 예시에서 힌트를 얻을 수 있었다.
 *     - "4번 학생은 자기보다 작은 학생이 3명이 있고, 자기보다 큰 학생이 2명이 있게 되어 자신의 키가 몇 번째인지 정확히 알 수 있다."
 *     - 특정 학생을 기준으로 학생보다 (작은 학생 수) + (큰 학생 수) + 1 == (전체 학생 수) 조건을 만족한다면
 *       - 해당 학생은 자신의 키가 몇 번째인지 정확히 알 수 있다.
 * 
 * [전략]
 *   - 학생들의 키 정보를 인접리스트로 저장한다.
 *     - 자신보다 큰 학생의 번호는 그대로 저장하고, 자신보다 작은 학생의 번호는 (-번호)로 저장한다.
 *   - 1~N 학생들에 대해서...
 *     - 자신보다 큰 학생들의 수를 DFS로 카운트하고
 *     - 자신보다 작은 학생들의 수를 DFS로 카운트 하여 더한다.
 *     - 또한 자기자신에 대해서 +1 하여 더한다.
 *     - 구한 값이 (전체 학생 수)와 같으면 해당 학생의 키 순서는 정확히 알 수 있다.
 *       - 정답 변수를 업데이트 한다.
 */
public class Main {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder output = new StringBuilder();
  static StringTokenizer input;
  
  // --------------------------------------------------------
  
  public static void main(String[] args) throws IOException {
    new Main().run();
    System.out.print(output.toString());
  }
  
  // --------------------------------------------------------
  
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
    
    graph = new ArrayList<>(nodeLen + 1);
    for (int idx = 0; idx <= nodeLen; idx++) {
      graph.add(new ArrayList<>());
    }
    
    for (int idx = 0; idx < edgeLen; idx++) {
      a = nextInt();
      b = nextInt();
      
      graph.get(a).add(b);  // a < b
      graph.get(b).add(-a); // a < b
    }
  }
  
  private void solve() {
    answer = 0;
    
    // 각 노드에서 출발하는 DFS 탐색 하면서...
    // 자신의 (뒤에 있는 노드) + (앞에 있는 노드) + 1 == nodeLen 판별
    for (int node = 1; node <= nodeLen; node++) {
      visited = new boolean[nodeLen + 1];
      visited[node] = true;
      
      int cnt = 1;
      cnt += dfs_forward(node);
      cnt += dfs_backward(node);
      
      if (cnt == nodeLen) answer++;
    }
  }
  
  private int dfs_forward(int node) {
    int result = 0;
    
    for (int nnode : graph.get(node)) {
      if (nnode < 0 || visited[nnode]) continue;
      
      visited[nnode] = true;
      result += 1 + dfs_forward(nnode);
    }
    
    return result;
  }
  
  private int dfs_backward(int node) {
    int result = 0;
    
    for (int nnode : graph.get(node)) {
      if (nnode > 0 || visited[-nnode]) continue;
      
      visited[-nnode] = true;
      result += 1 + dfs_backward(-nnode);
    }
    
    return result;
  }
  
  private void print() {
    output
      .append(answer)
      .append('\n');
  }
  
  // --------------------------------------------------------
  
  static String next() throws IOException {
    if (input == null || !input.hasMoreTokens())
      input = new StringTokenizer(reader.readLine().trim());
    return input.nextToken();
  }
  
  static int nextInt() throws IOException {
    return Integer.parseInt(next());
  }
}
