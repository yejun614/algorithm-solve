/*
 * (1247) [S/W 문제해결 응용] 3일차 - 최적 경로
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD&categoryId=AV15OZ4qAPICFAYD&categoryType=CODE&problemTitle=%EC%B5%9C%EC%A0%81+%EA%B2%BD%EB%A1%9C&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [S/W 문제해결 응용] 3일차 - 최적 경로
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 외판원 문제(TSP)로 출발지, 도착지가 정해져 있고 모든 노드를 방문하는 최소 거리를 구해야 합니다.
 * 
 * [전략]
 *   - DFS를 사용한 완전 탐색을 사용합니다.
 *   - 방문처리를 위해 Bitmask를 사용했습니다.
 *   - cache 변수를 통해 메모이제이션 최적화를 적용했습니다. (어떤 노드를 방문했는지를 캐시에 기록해야 합니다)
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

  Pos start;
  Pos goal;
  Pos[] customerArr;
  int customerLen;
  int visited;
  int[][] cache;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    customerLen = nextInt() + 1;
    customerArr = new Pos[customerLen];

    goal = Pos.fromReader();
    start = customerArr[0] = Pos.fromReader();

    for (int idx = 1; idx < customerLen; idx++) {
      customerArr[idx] = Pos.fromReader();
    }
  }

  private void solve() {
    visited = 1;
    cache = new int[customerLen][1 << customerLen];
    for (int idx = 0; idx < customerLen; idx++) Arrays.fill(cache[idx], -1);

    answer = searchBest(0, 1);
  }

  private int searchBest(int idx, int cnt) {
    if (cache[idx][visited] >= 0) return cache[idx][visited];

    Pos pos = customerArr[idx];
    int result = Integer.MAX_VALUE;
    if (cnt == customerLen) return getDistance(pos, goal);

    for (int next_idx = 1; next_idx < customerLen; next_idx++) {
      if ((visited & (1 << next_idx)) != 0) continue;
      Pos nextPos = customerArr[next_idx];

      visited |= 1 << next_idx;
      result = Math.min(result, getDistance(pos, nextPos) + searchBest(next_idx, cnt + 1));
      visited ^= 1 << next_idx;
    }

    return cache[idx][visited] = result;
  }

  private int getDistance(Pos a, Pos b) {
    return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
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

  static class Pos {
    int x, y;

    public Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public static Pos fromReader() throws IOException {
      int x = nextInt();
      int y = nextInt();
      return new Pos(x, y);
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
