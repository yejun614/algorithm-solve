/*
 * (1992) 쿼드트리
 * https://www.acmicpc.net/problem/1992
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 쿼드트리
 * @author YeJun, Jung
 * 
 * [전략]
 *   - 압축 되어있는지 판다.
 *     - 주어진 범위의 문자가 모두 같으면 압축이 이미 되어 있는 것.
 *   - 주어진 범위를 사분면으로 쪼개서 차례대로 방문한다.
 *   - 압축 결과를 StringBuilder를 사용해서 합친다.
 */
public class Main {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    new Main().run();
  }

  // ----------------------------------------------------------

  int boardSize;
  char[][] board;
  String answer;

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    boardSize = Integer.parseInt(reader.readLine().trim());
    board = new char[boardSize][boardSize];

    for (int y = 0; y < boardSize; y++) {
      board[y] = reader.readLine().trim().toCharArray();
    }
  }

  private void solve() {
    answer = compress(0, 0, boardSize - 1, boardSize - 1).toString();
  }

  private StringBuilder compress(int sx, int sy, int ex, int ey) {
    int size = ex - sx + 1;

    if (size == 1 || isCompressed(sx, sy, ex, ey)) {
      return new StringBuilder(String.valueOf(board[sy][sx]));
    }

    int nSize = size / 2;
    StringBuilder result = new StringBuilder();

    result.append("(");
    result.append(compress(sx,         sy,         sx + nSize - 1, sy + nSize - 1));
    result.append(compress(sx + nSize, sy,         ex,             sy + nSize - 1));
    result.append(compress(sx,         sy + nSize, sx + nSize - 1, ey));
    result.append(compress(sx + nSize, sy + nSize, ex,             ey));
    result.append(")");

    return result;
  }

  private boolean isCompressed(int sx, int sy, int ex, int ey) {

    for (int cy = sy; cy <= ey; cy++) {
      for (int cx = sx; cx <= ex; cx++) {
        if (board[cy][cx] != board[sy][sx]) return false;
      }
    }
    return true;
  }

  private void print() throws IOException {
    writer.write(answer);
    writer.write("\n");
    writer.flush();
  }

  // ----------------------------------------------------------
}
