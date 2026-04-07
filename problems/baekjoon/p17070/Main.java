/*
 * (17070) 파이프 옮기기 1
 * https://www.acmicpc.net/problem/17070
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 파이프 옮기기 1
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 파이프는 2칸을 차지하며 [RIGHT, CROSS, DOWN] 3방향으로만 위치할 수 있다.
 *   - 파이프는 문제에서 첨부된 사진(가로, 세로, 대각선)의 패턴으로만 이동할 수 있다.
 *   - 대각선 이동을 위해 '_|' 형태로 이웃한 칸을 조사해야 함에 주의
 * 
 * [전략]
 *   simulation(x, y, dir) : DFS를 통해 가능한 모든 이동경로를 조사한다.
 *     - MOVING[dir][next_dir] : 현재 방향에 대해서 이동가능한 다음 방향을 제시한다.
 *     - CHECKING[dir][axis][check] : 이동하려는 위치에 대해서 이동 가능한지 이웃한 칸을 조사한다.
 *     - 함수 파라미터마다 결과 값을 cache 변수에 메모이제이션하여 반복된 연산이 발생되지 않도록 최적화한다.
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

  static final int DIR_RIGHT = 0;
  static final int DIR_CROSS = 1;
  static final int DIR_DOWN  = 2;

  static final int[] DIR_X = {  1,  1,  0  };
  static final int[] DIR_Y = {  0,  1,  1  };
  static final int DIR_LEN = 3;

  static final int[][][] CHECKING = {
    {  // RIGHT
      {  1  },
      {  0  },
    },
    { // CROSS
      {  1,  1,  0  },
      {  0,  1,  1  },
    },
    { // DOWN
      {  0  },
      {  1  },
    },
  };

  static final int[][] MOVING = {
    /* RIGHT  ->  */ {DIR_RIGHT, DIR_CROSS},
    /* CROSS  ->  */ {DIR_RIGHT, DIR_DOWN, DIR_CROSS},
    /* DOWN   ->  */ {DIR_DOWN, DIR_CROSS},
  };

  int answer;
  int boardSize;
  char[][] board;
  int[][][] cache;

  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    boardSize = nextInt();
    board = new char[boardSize][boardSize];

    for (int y = 0; y < boardSize; y++) {
      for (int x = 0; x < boardSize; x++) {
        board[y][x] = next().charAt(0);
      }
    }
  }

  private void solve() {
    initCache();

    answer = simulation(0, 0, DIR_RIGHT);
  }

  private void initCache() {
    cache = new int[boardSize][boardSize][DIR_LEN];

    for (int y = 0; y < boardSize; y++)
      for (int x = 0; x < boardSize; x++)
        Arrays.fill(cache[y][x], -1);
  }

  private int simulation(int x, int y, int dir) {
    if (cache[x][y][dir] >= 0) return cache[x][y][dir];

    int result = 0;
    int nx = x + DIR_X[dir];
    int ny = y + DIR_Y[dir];

    if (nx == boardSize - 1 && ny == boardSize - 1) return 1;

    for (int ndir : MOVING[dir]) {
      if (isMovable(nx, ny, ndir)) result += simulation(nx, ny, ndir);
    }

    return cache[x][y][dir] = result;
  }

  private boolean isMovable(int x0, int y0, int dir) {
    final int len = CHECKING[dir][0].length;

    for (int d = 0; d < len; d++) {
      int x1 = x0 + CHECKING[dir][0][d];
      int y1 = y0 + CHECKING[dir][1][d];
      if (!isInsideBoard(x1, y1) || board[y1][x1] == '1') return false;
    }

    return true;
  }

  private boolean isInsideBoard(int x, int y) {
    return (x >= 0 && x < boardSize) && (y >= 0 && y < boardSize);
  }

  private void print() {
    output.append(answer).append('\n');
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
