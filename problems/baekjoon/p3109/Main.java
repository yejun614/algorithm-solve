/*
 * (3109) 빵집
 * https://www.acmicpc.net/problem/3109
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 빵집
 * @author YeJun, Jung
 * 
 * [전략]
 *   - DFS with DP + Greedy
 *     - 각 출발지점(열이 0인 모든 행)에 대해서 파이프 설치를 시도한다.
 *     - addPipe(int startY)
 *       - 마지막 열에 도착할때까지 DFS 하면서...
 *         - DP: 막힌 구간(대각선 및 오른쪽으로 이동 불가능) 혹은 이미 방문한 장소에 대해서
 *               기록하여 반복해서 해당 구역을 탐색하는 것을 방지한다.
 *         - Greedy: 대각선 위, 오른쪽, 대각선 아래 순서로 탐색하면서, 마지막 열에
 *                   도착하면 탐색을 중단한다.
 * 
 * [시간복잡도 분석]
 *   - DFS에 대해서 각 열에 대해서 3방향으로 이동 가능하기 때문에 30,000 만큼 시간이 걸린다.
 *   - 출발 지점이 높이 만큼 있으므로 500 * 30,000 = 15,000,000 만큼의 시간이 걸린다.
 *   - 즉, 최악의 경우 15,000,000만큼 반복문을 도는데, 1억번 반복에 1초 걸린다고 가정했을 때
 *     DP, Greedy로 인한 최적화도 있으므로 충분히 시간 제한안에 들어갈 것으로 예상된다.
 *   - O(R * C * 3)
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

  static final int[] DIR_X = {  1,  1,  1 };
  static final int[] DIR_Y = {  1,  0, -1 };
  static final int DIR_LEN = 3;

  static final int MAX_BOARD_WIDTH = 10000;
  static final int MAX_BOARD_HEIGHT = 500;
  static final int MAX_STACK_SIZE = MAX_BOARD_WIDTH * DIR_LEN + 100; // 100 is padding

  static int[] pipeX = new int[MAX_STACK_SIZE];
  static int[][] pipeY = new int[MAX_STACK_SIZE][MAX_BOARD_HEIGHT];

  int answer;
  int boardWidth;
  int boardHeight;
  char[][] board;

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    getLine();
    boardHeight = Integer.parseInt(input.nextToken());
    boardWidth = Integer.parseInt(input.nextToken());

    board = new char[boardHeight][];

    for (int y = 0; y < boardHeight; y++) {
      board[y] = reader.readLine().trim().toCharArray();
    }
  }

  private void solve() {
    answer = 0;

    for (int y = 0; y < boardHeight; y++) {
      if (addPipe(y)) ++answer;
    }
  }

  private boolean addPipe(int startY) {
    pipeX[0] = 0;
    pipeY[0][0] = startY;
    board[startY][0] = 'O';

    int stackSize = 1, peek = 0;
    int n = 0, nx = 0, ny = 0;
    int x = 0, y = 0;
    boolean hasChild = false;

    while (stackSize > 0) {
      peek = --stackSize;
      x = pipeX[peek];
      y = pipeY[peek][x];
      hasChild = false;

      if (x == boardWidth - 1) return true;

      // 대각선 아래, 오른쪽, 대각선 위 순으로 stack에 쌓여야만
      // 쌓은 반대인 대각선 위, 오른족, 대각선 아래 순서로 방문할 수 있다. (Greedy)
      for (int dir = 0; dir < DIR_LEN; dir++) {
        nx = x + DIR_X[dir];
        ny = y + DIR_Y[dir];

        if (!isInsideBoard(nx, ny) || board[ny][nx] != '.') {
          continue;
        }

        n = stackSize++;
        pipeX[n] = nx;
        pipeY[n][nx] = ny;
        board[y][x] = 'O';

        hasChild = true;
      }

      if (!hasChild) board[y][x] = 'N';
    }

    return false;
  }

  private boolean isInsideBoard(int x, int y) {
    return x >= 0 && x < boardWidth && y >= 0 && y < boardHeight;
  }

  private void print() throws IOException {
    writer.write(answer + "\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
