/*
 * (7733) 치즈 도둑
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWrDOdQqRCUDFARG&categoryId=AWrDOdQqRCUDFARG&categoryType=CODE&problemTitle=7733&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 치즈 도둑
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 0...100일이 지나는 동안 매일 오늘 날짜에 해당하는 치즈가 삭제된다.
 *   - 0일 최초의 치즈는 하나의 덩어리이다.
 *   - 남아있는 치즈들이 상하좌우로 이웃해 있으면 하나의 그룹으로 본다.
 * 
 * [전략]
 *   - 매일 해당 날짜의 치즈를 0으로 설정해 삭제 처리한다.
 *     - 삭제한 치즈의 개수를 카운트해서 프로그램의 조기 종료를 가능하게 한다.
 *   - 각 행렬 요소에 대해서 DFS를 하여 그룹의 개수를 센다.
 *     - DFS할때 상하좌우로 이웃한 요소로 가지를 펼친다.
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    final int testCount = Integer.parseInt(reader.readLine().trim());

    for (int testCase = 1; testCase <= testCount; testCase++) {
      new Solution(testCase).run();
    }
  }

  // ----------------------------------------------------------

  static final int[] DIR_X = {  0,  0, -1,  1 };
  static final int[] DIR_Y = { -1,  1,  0,  0 };
  static final int DIR_LEN = 4;

  int testCase;
  int answer;
  int boardSize;
  int[][] board;
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
    boardSize = Integer.parseInt(reader.readLine().trim());
    board = new int[boardSize][boardSize];

    for (int y = 0; y < boardSize; y++) {
      getLine();

      for (int x = 0; x < boardSize; x++) {
        board[y][x] = Integer.parseInt(input.nextToken());
      }
    }
  }

  private void solve() {
    answer = 1; // 최초에 치즈는 하나의 덩어리이다.
    int restBlocks = boardSize * boardSize;

    for (int day = 1; day <= 100 && restBlocks > 0; day++) {
      restBlocks -= eating(day);
      answer = Math.max(answer, countGroup());
    }
  }

  private int eating(int num) {
    int eatCount = 0;

    for (int y = 0; y < boardSize; y++) {
      for (int x = 0; x < boardSize; x++) {
        if (board[y][x] != num) continue;

        board[y][x] = 0;
        eatCount++;
      }
    }

    return eatCount;
  }

  private int countGroup() {
    visited = new boolean[boardSize][boardSize];
    int result = 0;

    for (int y = 0; y < boardSize; y++) {
      for (int x = 0; x < boardSize; x++) {
        if (dfs(x, y)) result++;
      }
    }

    return result;
  }

  private boolean dfs(int x, int y) {
    if (board[y][x] == 0 || visited[y][x]) return false;
    visited[y][x] = true;

    for (int dir = 0; dir < DIR_LEN; dir++) {
      int nx = x + DIR_X[dir];
      int ny = y + DIR_Y[dir];

      if (!isInsideBoard(nx, ny)) continue;

      dfs(nx, ny);
    }

    return true;
  }

  private boolean isInsideBoard(int x, int y) {
    return x >= 0 && x < boardSize && y >= 0 && y < boardSize;
  }

  private void print() throws IOException {
    writer.write("#" + testCase);
    writer.write(" " + answer);
    writer.write("\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
