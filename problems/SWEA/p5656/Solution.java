/*
 * (5656) [모의 SW 역량테스트] 벽돌 깨기
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo&categoryId=AWXRQm6qfL0DFAUo&categoryType=CODE&problemTitle=5656&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [모의 SW 역량테스트] 벽돌 깨기
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 구슬은 위에서 아래로 떨어지며, 부딪친 블록은 삭제되고 해당 블록에 적힌 숫자만큼 연쇄 반응을 일으킨다.
 *   - 삭제된 블록들로 인해 빈공간이 생기면 그 위에 있는 블록들은 아래로 떨어진다.
 *   - 매 라운드마다 구슬을 어느 X좌표에서 놓을지는 프로그래머가 찾아내야 한다.
 *   - 최대한 만은 블록을 파괴하여 최소한의 블록만을 남기는게 목표이다.
 * 
 * [전략]
 *   - DFS로 매 라운드 구슬의 X좌표를 정한다.
 *   - 모든 라운드를 마치면 남아있는 블록의 개수를 바탕으로 정답변수를 업데이트 한다.
 *   - 각 시행마다 board의 상태를 독립적으로 유지시키기 위해서 깊은복사를 구현하였다. (copyMat 함수)
 *   - 블록 파괴시에는 델타배열을 활용하였으며 빈 공간을 매우기 위해서 blockDown 함수를 구현하였다.
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

  int shotCnt;
  int width;
  int height;
  int blockCnt;
  int[][] board;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    shotCnt = nextInt();
    width = nextInt();
    height = nextInt();

    board = new int[height][width];
    blockCnt = 0;

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        board[y][x] = nextInt();

        if (board[y][x] != 0) blockCnt++;
      }
    }
  }

  private void solve() {
    answer = blockCnt;
    searchBest(0, blockCnt, 0);
  }

  private void searchBest(int x, int score, int round) {
    if (round == shotCnt) {
      answer = Math.min(answer, score);
      return;
    }

    int[][] backup;

    // CASE 1: 현재 X 좌표에서 구술 발사
    backup = copyMat(board);
    searchBest(0, score - shot(x), round + 1);
    board = backup;

    if (x < width - 1) {
      // CASE 2: 다음 X 좌표 검토
      backup = copyMat(board);
      searchBest(x + 1, score, round);
      board = backup;
    }
  }

  private int[][] copyMat(int[][] mat) {
    int[][] result = new int[mat.length][];

    for (int y = 0; y < mat.length; y++) {
      result[y] = mat[y].clone();
    }

    return result;
  }

  private int shot(int x) {
    int y = 0;
    while (y < height - 1 && board[y][x] == 0) y++;

    int result = explosion(x, y);
    blockDown();
    return result;
  }

  private int explosion(int x, int y) {
    int block = board[y][x];
    if (block == 0) return 0;

    board[y][x] = 0;
    int result = 1;

    for (int dir = 0; dir < DIR_LEN; dir++) {
      int nx = x + DIR_X[dir];
      int ny = y + DIR_Y[dir];

      for (int cnt = 0; cnt < block - 1 && isInsideBoard(nx, ny); cnt++) {
        result += explosion(nx, ny);

        nx += DIR_X[dir];
        ny += DIR_Y[dir];
      }
    }

    return result;
  }

  private void blockDown() {
    for (int x = 0; x < width; x++) {
      int floor = height - 1;

      for (int y = height - 1; y >= 0; y--) {
        int block = board[y][x];
        if (block == 0) continue;

        board[y][x] = 0;
        board[floor--][x] = block;
      }
    }
  }

  private boolean isInsideBoard(int x, int y) {
    return x >= 0 && x < width && y >= 0 && y < height;
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

  static String next() throws IOException {
    if (input == null || !input.hasMoreTokens())
      input = new StringTokenizer(reader.readLine().trim());
    return input.nextToken();
  }

  static int nextInt() throws IOException {
    return Integer.parseInt(next());
  }
}
