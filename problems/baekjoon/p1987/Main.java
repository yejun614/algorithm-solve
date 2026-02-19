/*
 * (1987) 알파벳
 * https://www.acmicpc.net/problem/1987
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 알파벳
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 보드를 상하좌우로 이동하면서 방문한다.
 *   - 방문한 곳에 있는 알파벳을 기억해 두었다가, 해당 알파벳이 적힌 다른 타일을 방문할 수 없다.
 *   - 초기위치(0, 0)에 있는 알파벳에도 동일하게 적용된다.
 * 
 * [전략]
 *   - DFS하면서 상하좌우 탐색한다.
 *   - visitied 배열에 알파벳을 키로 하여 방문여부를 저장한다.
 *   - 위치가 아닌 값을 이용해서 방문 처리하므로 DFS시 자식 노드 탐색 후 방문을 해제해 주어야 한다.
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

  static final int[] DIR_X = { -1,  1,  0,  0 };
  static final int[] DIR_Y = {  0,  0, -1,  1 };
  static final int DIR_LEN = 4;
  static final int ALPHABET_LEN = 'Z' - 'A' + 1;

  int answer;
  int boardWidth;
  int boardHeight;
  char[][] board;
  boolean[] visited;

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
    visited = new boolean[ALPHABET_LEN];

    visited[board[0][0] - 'A'] = true;
    answer = dfs(0, 0);
  }

  private int dfs(int x, int y) {
    int result = 0;

    for (int dir = 0; dir < DIR_LEN; dir++) {
      int nx = x + DIR_X[dir];
      int ny = y + DIR_Y[dir];

      if (!isInsideBoard(nx, ny)) continue;

      char ch = board[ny][nx];
      int key = ch - 'A';
      if (visited[key]) continue;

      visited[key] = true;
      result = Math.max(result, dfs(nx, ny));
      visited[key] = false;
    }

    return 1 + result;
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
