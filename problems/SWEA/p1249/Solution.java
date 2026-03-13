/*
 * (1249) [S/W 문제해결 응용] 4일차 - 보급로
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD&categoryId=AV15QRX6APsCFAYD&categoryType=CODE&problemTitle=%EB%B3%B4%EA%B8%89%EB%A1%9C&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [S/W 문제해결 응용] 4일차 - 보급로
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 행렬이 주어질때 시작점(0, 0)에서 도착점(N-1, N-1)까지 도달하는 최소비용을 구하라
 *   - 이때, 이동거리는 비용에 포함되지 않고 복구비용만 고려한다.
 * 
 * [전략]
 *   - 다익스트라 알고리즘 적용
 *     - 우선순위 큐를 사용하여 속도를 높였습니다.
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

  static final int[] DIR_X = { -1,  1,  0,  0 };
  static final int[] DIR_Y = {  0,  0, -1,  1 };
  static final int DIR_LEN = 4;

  int testCase;
  int answer;
  int boardSize;
  int[][] board;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    char[] buf;

    boardSize = nextInt();
    board = new int[boardSize][boardSize];

    for (int y = 0; y < boardSize; y++) {
      buf = next().toCharArray();

      for (int x = 0; x < boardSize; x++) {
        board[y][x] = buf[x] - '0';
      }
    }
  }

  private void solve() {
    answer = dijkstra();
  }

  private int dijkstra() {
    Pos start = new Pos(0, 0, 0);
    Pos goal = new Pos(boardSize - 1, boardSize - 1, 0);

    int[][] dist = new int[boardSize][boardSize];
    PriorityQueue<Pos> pq = new PriorityQueue<>();

    for (int y = 0; y < boardSize; y++) {
      Arrays.fill(dist[y], Integer.MAX_VALUE);
    }

    dist[start.y][start.x] = 0;
    pq.offer(start);

    while (!pq.isEmpty()) {
      Pos pos = pq.poll();

      if (dist[pos.y][pos.x] < pos.w) continue;

      for (int dir = 0; dir < DIR_LEN; dir++) {
        int nx = pos.x + DIR_X[dir];
        int ny = pos.y + DIR_Y[dir];

        if (!isInsideBoard(nx, ny)) continue;

        int cost = pos.w + board[ny][nx];
        if (dist[ny][nx] <= cost) continue;

        dist[ny][nx] = cost;
        pq.offer(new Pos(nx, ny, cost));
      }
    }

    return dist[goal.y][goal.x];
  }

  private boolean isInsideBoard(int x, int y) {
    return x >= 0 && x < boardSize && y >= 0 && y < boardSize;
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

  static class Pos implements Comparable<Pos> {
    int x, y, w;

    public Pos(int x, int y, int w) {
      this.x = x;
      this.y = y;
      this.w = w;
    }

    @Override
    public int compareTo(Pos another) {
      return Integer.compare(this.w, another.w);
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
