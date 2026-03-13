/*
 * (7793) 오! 나의 여신님
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWsBQpPqMNMDFARG&categoryId=AWsBQpPqMNMDFARG&categoryType=CODE&problemTitle=7793&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 오! 나의 여신님
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 악마의 손아귀는 1초마다 상하좌우로 퍼진다.
 *   - 수연이는 퍼지는 악마의 손아귀를 피하면서 여신님이 있는 곳 까지 최단거리로 도달해야 한다.
 * 
 * [전략]
 *   - 악마의 손아귀의 초기 위치를 gasQ에 삽입한다.
 *   - gasQ에 저장된 악마의 손아귀를 차례로 방문하면서 DFS를 활용 상하좌우로 퍼뜨린다.
 *     - 악마의 손아귀가 해당 칸에 몇 초만에 도달하는지 gasBoard[y][x]에 기록한다.
 *   - 이제 수연이 위치에서 여신님까지 최단거리로 도달할 수 있도록 다익스트라 알고리즘을 사용한다.
 *     - 이때 수연이가 이동한 시간과 gasBoard[y][x]에 저장된 값을 비교하면서 악마의 손아귀에 들어가지 않도록 한다.
 *     - 다익스트라 알고리즘은 우선순위 큐를 사용해서 구현했다.
 *   - 다익스트라 결과 목적지까지 도달하는데 필요한 시간이 MAX_VALUE라면 GAME OVER이며 이외의 값은 그래로 정답으로 출력한다.
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

  int height;
  int width;
  char[][] board;
  int[][] gasBoard;

  Pos start;
  Pos goal;
  Queue<Pos> gasQ;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    gasQ = new ArrayDeque<>();

    height = nextInt();
    width = nextInt();
    board = new char[height][width];

    for (int y = 0; y < height; y++) {
      board[y] = next().toCharArray();

      for (int x = 0; x < width; x++) {
        switch (board[y][x]) {
        case 'S':
          start = new Pos(x, y);
          break;
        case 'D':
          goal = new Pos(x, y);
          break;
        case '*':
          gasQ.offer(new Pos(x, y, 0));
          break;
        }
      }
    }
  }

  private void solve() {
    makeGasBoard();
    answer = dijkstra();
  }

  private void makeGasBoard() {
    boolean[][] visited = new boolean[height][width];

    gasBoard = new int[height][width];
    for (int y = 0; y < height; y++) {
      Arrays.fill(gasBoard[y], -1);
    }

    for (Pos pos : gasQ) {
      gasBoard[pos.y][pos.x] = pos.t;
      visited[pos.y][pos.x] = true;
    }

    while (!gasQ.isEmpty()) {
      Pos pos = gasQ.poll();

      for (int dir = 0; dir < DIR_LEN; dir++) {
        int nx = pos.x + DIR_X[dir];
        int ny = pos.y + DIR_Y[dir];

        if ((!isInsideBoard(nx, ny)) ||
            (visited[ny][nx])        ||
            (board[ny][nx] == 'X')   ||
            (board[ny][nx] == 'D')
        ) {
          continue;
        }

        visited[ny][nx] = true;
        gasBoard[ny][nx] = pos.t + 1;
        gasQ.offer(new Pos(nx, ny, pos.t + 1));
      }
    }
  }

  private int dijkstra() {
    int[][] dist = new int[height][width];
    PriorityQueue<Pos> pq = new PriorityQueue<>();

    for (int y = 0; y < height; y++) {
      Arrays.fill(dist[y], Integer.MAX_VALUE);
    }

    dist[start.y][start.x] = 0;
    pq.offer(start);

    while (!pq.isEmpty()) {
      Pos it = pq.poll();

      if (it.x == goal.x && it.y == goal.y) {
        dist[goal.y][goal.x] = it.w;
        break;
      }

      if (dist[it.y][it.x] < it.w) continue;

      for (int dir = 0; dir < DIR_LEN; dir++) {
        int nx = it.x + DIR_X[dir];
        int ny = it.y + DIR_Y[dir];
        int cost = it.w + 1;

        if ((!isInsideBoard(nx, ny)) ||
            (dist[ny][nx] <= cost)   ||
            (board[ny][nx] == 'X')   ||
            (board[ny][nx] == '*')   ||
            (gasBoard[ny][nx] >= 0 && gasBoard[ny][nx] <= it.t + 1)
        ) {
          continue;
        }

        dist[ny][nx] = cost;
        pq.offer(new Pos(nx, ny, it.t + 1, cost));
      }
    }

    return dist[goal.y][goal.x];
  }

  private boolean isInsideBoard(int x, int y) {
    return x >= 0 && x < width && y >= 0 && y < height;
  }

  private void print() {
    output
      .append('#')
      .append(testCase)
      .append(' ')
      .append(answer == Integer.MAX_VALUE ? "GAME OVER" : answer)
      .append('\n');
  }

  // ----------------------------------------------------------

  static class Pos implements Comparable<Pos> {
    int x, y;
    int t, w;

    public Pos(int x, int y, int t, int w) {
      this.x = x;
      this.y = y;
      this.t = t;
      this.w = w;
    }

    public Pos(int x, int y, int t) {
      this(x, y, t, 0);
    }

    public Pos(int x, int y) {
      this(x, y, 0, 0);
    }

    public int compareTo(Pos another) {
      return Integer.compare(this.t, another.t);
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
