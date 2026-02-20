/*
 * (2105) [모의 SW 역량테스트] 디저트 카페
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu&categoryId=AV5VwAr6APYDFAWu&categoryType=CODE&problemTitle=2105&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 2105. [모의 SW 역량테스트] 디저트 카페
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 보드의 각 요소에서 출발해서 대각선 방향으로 이동한다.
 *   - 카페 투어중에 같은 숫자를 만나선 안된다.
 *   - 특정 위치에서 우상향으로 width만큼, 우하향으로 height만큼 움직이면
 *     사각형 path가 만들어진다.
 * 
 * [전략]
 *   * search(): 보드의 각 요소를 방문하면서...
 *     - 일단 우상향 방향으로 간다.
 *     * pathUp(): 그 다음 부터 DFS 하면서 가능한 width를 만들어본다...
 *       (1-1) 계속 우상향으로 이동하거나(재귀 호출)
 *       (1-2) 꺽어서 우하양으로 전환하거나
 *         * pathDown(): 그 다음 부터 DFS 하면서 가능한 height를 만들어본다...
 *           (2-1) 계속 우하양으로 이동하거나(재귀호출)
 *           (2-2) 그만 진행하고 만들어진 path 값을 계산해보거나
 *             * countDesert(): 조합된 startX, startY, width, height를 바탕으로 
 *               path를 만들고 몇 개의 디저트를 먹을 수 있는지 카운트 한다.
 *               - 보드 밖으로 나가거나 디저트가 중복되면 (-1)을 반환한다.
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

  static final int MAX_DESSERT = 100; // 디저트 종류

  static final int RIGHT_UP = 0;
  static final int RIGHT_DOWN = 1;
  static final int LEFT_DOWN = 2;
  static final int LEFT_UP = 3;

  static final int[] DIR_X = {  1,  1, -1, -1 }; // RIGHT-UP, RIGHT-DOWN, LEFT-DOWN, LEFT-UP
  static final int[] DIR_Y = { -1,  1,  1, -1 }; // RIGHT-UP, RIGHT-DOWN, LEFT-DOWN, LEFT-UP

  int testCase;
  int answer;
  int boardSize;
  int[][] board;
  int startX; // 지금 탐색중인 path의 시작점 X
  int startY; // 지금 탐색중인 path의 시작점 Y

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
    answer = -1;
    
    search();
  }

  private void search() {
    for (int y = 1; y < boardSize; y++) {
      for (int x = 0; x < boardSize - 1; x++) {
        startX = x;
        startY = y;

        answer = Math.max(answer, makePath(x, y));
      }
    }
  }

  private int makePath(int x, int y) {
    int nx = x + DIR_X[RIGHT_UP];
    int ny = y + DIR_Y[RIGHT_UP];

    if (!isInsideBoard(nx, ny)) return -1;

    return pathUp(nx, ny, 1);
  }

  private int pathUp(int x, int y, int width) {
    int r1 = -1, r2 = -1;
    int nx = x + DIR_X[RIGHT_UP];
    int ny = y + DIR_Y[RIGHT_UP];

    if (isInsideBoard(nx, ny)) {
      r1 = pathUp(nx, ny, width + 1);
    }

    r2 = pathDown(x, y, width, 0);

    return Math.max(r1, r2);
  }

  private int pathDown(int x, int y, int width, int height) {
    int r1 = -1, r2 = -1;
    int nx = x + DIR_X[RIGHT_DOWN];
    int ny = y + DIR_Y[RIGHT_DOWN];

    if (isInsideBoard(nx, ny)) {
      r1 = pathDown(nx, ny, width, height + 1);
    }

    r2 = height > 0 ? countDessert(width, height) : -1;

    return Math.max(r1, r2);
  }

  private int countDessert(int width, int height) {
    int result = 0;
    int cx = startX, cy = startY;
    int dir = 0;
    boolean[] visited = new boolean[MAX_DESSERT + 1];

    for (int c0 = 0; c0 < 2; c0++) {
      for (int c1 = 0; c1 < width + height; c1++) {
        if (!isInsideBoard(cx, cy)) return -1;

        int dessert = board[cy][cx];

        if (visited[dessert]) return -1; // 만약 가계 중복되면 -1 반환

        visited[dessert] = true;
        result++;

        if (c1 == width) dir++;
        cx += DIR_X[dir];
        cy += DIR_Y[dir];
      }

      dir++;
    }

    return result;
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
