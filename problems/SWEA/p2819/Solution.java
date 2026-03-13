/*
 * (2819) 격자판의 숫자 이어 붙이기
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7I5fgqEogDFAXB&categoryId=AV7I5fgqEogDFAXB&categoryType=CODE&problemTitle=2819&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 격자판의 숫자 이어 붙이기
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 격자판은 항상 4x4 이다.
 *   - 임의의 정점에서 출발해서 상하좌우 이동이 가능하다.
 *   - 조합할 수 있는 글자의 개수는 항상 7로 고정이다.
 * 
 * [전략]
 *   - 격자판의 각 칸에 대해서 DFS 하면서...
 *     - StringBuilder 객체인 str에 차례대로 칸의 내용을 입력한다.
 *     - 델타배열을 사용한 상하좌우 이동으로 다음 탐색을 이어나간다.
 *       - 격자판 밖으로 나가지 않도록 체크한다.
 *     - 7글자를 완성하면 HashSet에 저장한다.
 *       - Set 자료구조는 중복을 허락하지 않기 때문에 가능한 모든 조합을 중복없이 저장하는게 가능하다.
 *   - HashSet 객체인 set에 저장된 글자의 개수가 정답이 된다.
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

  static final int BOARD_SIZE = 4;
  static final int MAX_LEN = 7;

  static final int[] DIR_X = { -1,  1,  0,  0 };
  static final int[] DIR_Y = {  0,  0, -1,  1 };
  static final int DIR_LEN = 4;

  int testCase;
  int answer;
  char[][] board;
  Set<String> set;
  StringBuilder str;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    board = new char[BOARD_SIZE][BOARD_SIZE];

    for (int y = 0; y < BOARD_SIZE; y++) {
      for (int x = 0; x < BOARD_SIZE; x++) {
        board[y][x] = next().charAt(0);
      }
    }
  }

  private void solve() {
    set = new HashSet<>();
    str = new StringBuilder("0000000");

    for (int y = 0; y < BOARD_SIZE; y++) {
      for (int x = 0; x < BOARD_SIZE; x++) {
        dfs(x, y, 0);
      }
    }

    answer = set.size();
  }

  private void dfs(int x, int y, int len) {
    if (len == MAX_LEN) {
      set.add(str.toString());
      return;
    }

    str.setCharAt(len, board[y][x]);

    for (int dir = 0; dir < DIR_LEN; dir++) {
      int nx = x + DIR_X[dir];
      int ny = y + DIR_Y[dir];

      if (!isInsideBoard(nx, ny)) continue;

      dfs(nx, ny, len + 1);
    }
  }

  private boolean isInsideBoard(int x, int y) {
    return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
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
