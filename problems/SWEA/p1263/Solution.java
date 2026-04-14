/*
 * (1263) [S/W 문제해결 응용] 8일차 - 사람 네트워크2
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV18P2B6Iu8CFAZN&categoryId=AV18P2B6Iu8CFAZN&categoryType=CODE&problemTitle=1263&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [S/W 문제해결 응용] 8일차 - 사람 네트워크2
 * @author YeJun, Jung
 * 
 * [분석]
 *   - CC(i) = Σ{dist(i, j)} 로 정의된다. (단, dist(i, j)는 노드 i에서 노드 j까지의 최단거리)
 *   - minval(CC(1), CC(2), ..., CC(N)) 을 찾아서 출력한다.
 * 
 * [전략]
 *   - 우선 입력은 인접행렬로 들어온다. 0은 연결되지 않음, 1은 서로 연결됨을 의미한다.
 *   - floydWarshall() : 플루이드 와샬 구현체
 *     - dist 행렬을 초기화 한다. (자기자신은 0으로, 서로 연결되어 있다면 1, 아니면 0으로 초기화)
 *     - mid, start, end 세가지 변수에 대해서 3중 반복문으로 최단거리를 갱신한다.
 *   - 플루이드 와샬 결과로 얻은 최단거리를 이용해서 CC(i)를 계산한다.
 *     - dist 행렬을 가로방향 혹은 세로방향으로 더하고 최솟값을 구하여 출력한다.
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

  static final int INF = Integer.MAX_VALUE;

  int testCase;
  int answer;

  int nodeLen;
  boolean[][] graph;
  int[][] dist;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    char buf;

    nodeLen = nextInt();
    graph = new boolean[nodeLen][nodeLen];

    for (int y = 0; y < nodeLen; y++) {
      for (int x = 0; x < nodeLen; x++) {
        buf = next().charAt(0);

        if (buf == '1') graph[y][x] = true;
      }
    }
  }

  private void solve() {
    floydWarshall();

    int[] sumArr = new int[nodeLen];
    for (int y = 0; y < nodeLen; y++)
      for (int x = 0; x < nodeLen; x++)
        if (dist[y][x] != INF) sumArr[y] += dist[y][x];

    answer = INF;
    for (int idx = 0; idx < nodeLen; idx++)
      if (sumArr[idx] < answer) answer = sumArr[idx];
  }

  private void floydWarshall() {
    dist = new int[nodeLen][nodeLen];
    
    for (int y = 0; y < nodeLen; y++)
      for (int x = 0; x < nodeLen; x++)
        dist[y][x] = (x == y) ? (0) : (graph[y][x] ? 1 : INF);

    for (int m = 0; m < nodeLen; m++) {
      for (int s = 0; s < nodeLen; s++) {
        for (int e = 0; e < nodeLen; e++) {
          if (dist[s][m] == INF || dist[m][e] == INF) continue;

          dist[s][e] = Math.min(dist[s][e], dist[s][m] + dist[m][e]);
        }
      }
    }
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

  static String next() throws IOException {
    if (input == null || !input.hasMoreTokens())
      input = new StringTokenizer(reader.readLine().trim());
    return input.nextToken();
  }

  static int nextInt() throws IOException {
    return Integer.parseInt(next());
  }
}
