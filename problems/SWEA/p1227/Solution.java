/*
 * (1227) [S/W 문제해결 기본] 7일차 - 미로2
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14wL9KAGkCFAYD&categoryId=AV14wL9KAGkCFAYD&categoryType=CODE&problemTitle=1227&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [S/W 문제해결 기본] 7일차 - 미로2
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 100x100 미로와, 출발지, 도착지가 주어졌을때 탈출가능 여부를 파악해야 한다.
 *   - 미로에는 벽, 길 두가지가 있으며 길로만 다닐 수 있다.
 * 
 * [전략]
 *   - 미로의 출발지에서 DFS를 시작한다.
 *   - 상하좌우로 이동하면서 목적지에 도달하거나, 모든 장소를 방문할때가지 반복한다.
 *   - 목적지를 찾았다면 '1' 아니면 '0'을 화면에 출력한다.
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    final int testCount = 10;

    for (int testCase = 1; testCase <= testCount; testCase++) {
      reader.readLine(); // 테스트케이스 입력

      new Solution(testCase).run();
    }
  }

  // ----------------------------------------------------------

  static final int BOARD_SIZE = 100;
  static final char ROAD = '0';
  static final char WALL = '1';
  static final char START = '2';
  static final char GOAL = '3';

  static final int DIR_LEN = 4;
  static final int[] DIR_X = {  0,  0, -1,  1 };
  static final int[] DIR_Y = { -1,  1,  0,  0 };

  static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

  int testCase;
  int answer;
  Pos start;
  boolean[][] visited;

  public Solution(int testCase) {
    this.testCase = testCase;
  }

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    char[] input;
    for (int y = 0; y < BOARD_SIZE; y++) {
      input = reader.readLine().trim().toCharArray();

      for (int x = 0; x < BOARD_SIZE; x++) {
        board[y][x] = input[x];

        if (board[y][x] == START) {
          start = new Pos(x, y);
        }
      }
    }
  }

  private void solve() {
    visited = new boolean[BOARD_SIZE][BOARD_SIZE];
    visited[start.y][start.x] = true;

    answer = dfs(start.x, start.y) ? 1 : 0;
  }

  private boolean dfs(int x, int y) {
    if (board[y][x] == GOAL) return true;

    for (int dir = 0; dir < DIR_LEN; dir++) {
      int nx = x + DIR_X[dir];
      int ny = y + DIR_Y[dir];

      if (!isInsideBoard(nx, ny) ||
          board[ny][nx] == WALL ||
          visited[ny][nx]
      ) {
        continue;
      }

      visited[ny][nx] = true;
      if (dfs(nx, ny)) return true;
    }

    return false;
  }

  private boolean isInsideBoard(int x, int y) {
    return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
  }

  private void print() throws IOException {
    writer.write("#" + testCase);
    writer.write(" " + answer);
    writer.write("\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static class Pos {
    int x;
    int y;

    public Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  // ----------------------------------------------------------
}
