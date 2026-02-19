/*
 * (2636) 치즈
 * https://www.acmicpc.net/problem/2636
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 치즈
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 주어진 보드에서 치즈 그룹의 가장자리가 1시간 마다 녹아 없어진다.
 *   - 치즈로 둘러쌓인 그룹 내부의 구멍의 가장자리는 녹지 않는다.
 * 
 * [전략]
 *   - 보드를 입력받으면서 치즈의 개수를 센다.
 *   - 1턴을 지나면 다음 일이 일어난다.
 *     - setOutline(): 치즈 그룹의 외곽선을 찾는다.
 *       - 상하좌우로 이동하면서 DFS 한다.
 *       - 치즈를 발견하면 외곽선을 표시하고 해당 위치에 대해서는 더 이상 탐색하지 않는다.
 *     - meltOutline(): 찾아놓은 외곽선을 녹이고 남은 치즈의 개수를 업데이트 한다.
 *       - 'C'로 표시된 외곽선을 모두 찾아 없앤다.
 *       - 삭제한 치즈의 개수를 반환한다.
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

  static final int[] DIR_X = {  0,  0, -1,  1 };
  static final int[] DIR_Y = { -1,  1,  0,  0 };
  static final int DIR_LEN = 4;

  int totalTime;
  int tileLen;
  int boardWidth;
  int boardHeight;
  char[][] board;
  boolean[][] visited;

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    getLine();
    boardHeight = Integer.parseInt(input.nextToken());
    boardWidth = Integer.parseInt(input.nextToken());

    tileLen = 0;
    board = new char[boardHeight][boardWidth];

    for (int y = 0; y < boardHeight; y++) {
      String line = reader.readLine().trim();

      for (int x = 0; x < boardWidth; x++) {
        board[y][x] = line.charAt(x * 2);

        if (board[y][x] == '1') ++tileLen;
      }
    }
  }

  private void solve() {
    totalTime = 0;
    for (int nextTileLen = tileLen; nextTileLen > 0; totalTime++) {
      tileLen = nextTileLen;

      setOutline();
      nextTileLen -= meltOutline();
    }
  }

  private void setOutline() {
    visited = new boolean[boardHeight][boardWidth];
    findOutline(0, 0);
  }

  private void findOutline(int x, int y) {
    for (int dir = 0; dir < DIR_LEN; dir++) {
      int nx = x + DIR_X[dir];
      int ny = y + DIR_Y[dir];

      if (!isInsideBoard(nx, ny) || visited[ny][nx]) continue;
      visited[ny][nx] = true;

      if (board[ny][nx] == '1') {
        board[ny][nx] = 'C';
      } else {
        findOutline(nx, ny);
      }
    }
  }

  private int meltOutline() {
    int result = 0;

    for (int y = 0; y < boardHeight; y++) {
      for (int x = 0; x < boardWidth; x++) {
        if (board[y][x] != 'C') continue;

        board[y][x] = '0';
        result++;
      }
    }

    return result;
  }

  private boolean isInsideBoard(int x, int y) {
    return x >= 0 && x < boardWidth && y >= 0 && y < boardHeight;
  }

  private void print() throws IOException {
    writer.write(totalTime + "\n");
    writer.write(tileLen + "\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
