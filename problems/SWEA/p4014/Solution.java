/*
 * (4014) [모의 SW 역량테스트] 활주로 건설
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeW7FakkUDFAVH&categoryId=AWIeW7FakkUDFAVH&categoryType=CODE&problemTitle=4014&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [모의 SW 역량테스트] 활주로 건설
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 활주로는 가로/세로 방향 일직선만 놓을 수 있고 길이는 N으로 고정이다.
 *   - 경사로의 높이는 1이며, 너비는 입력값으로 결정된다.
 *   - 활주로 중간에 높이는 연속적이어야 하며 이웃한 칸끼리 차이가 1이상 나면 안된다.
 *     - 활주로 높이가 달라지는 부분에 경사로를 놓아야 하는데, 경사로가 활주로 밖으로 나가거나 경사로 중간에 높이가 달라지면 안된다.
 * 
 * [전략]
 *   - 가로/세로 차례로 활주로 가능성을 검사하는데...
 *     - 경사로 배치여부를 저장하는 배열 step[N]을 준비한다.
 *     - 이웃한 칸끼리 차이가 1이상 이면 해당 행/열에는 활주로를 놓을 수 없다.
 *     - 이웃한 칸끼리 차이가 1이면 경사로를 배치한다.
 *       - 경사로를 배치 하려는데 이미 경사로가 배치되어 있다면 해당 행/열에는 활주로를 놓을 수 없다.
 *       - 경사로가 활주로 범위 밖으로 나간다면 해당 행/열에 활주로를 놓을 수 없다.
 *     - 이웃한 칸끼리 차리가 0이면 다음 칸으로 이동한다.
 *   - 활주로의 개수를 화면에 출력한다.
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

  int testCase;
  int answer;
  int boardSize;
  int stepWidth;
  int[][] board;
  boolean[] step;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    boardSize = nextInt();
    stepWidth = nextInt();

    board = new int[boardSize][boardSize];

    for (int y = 0; y < boardSize; y++) {
      for (int x = 0; x < boardSize; x++) {
        board[y][x] = nextInt();
      }
    }
  }

  private void solve() {
    answer = 0;

    for (int y = 0; y < boardSize; y++) {
      if (checkRow(y)) answer++;
    }

    for (int x = 0; x < boardSize; x++) {
      if (checkCol(x)) answer++;
    }
  }

  private boolean checkRow(int y) {
    step = new boolean[boardSize];
    int prev = board[y][0];

    for (int x = 0; x < boardSize; x++) {
      int current = board[y][x];
      int diff = prev - current;

      if ((diff >  1)                                ||
          (diff < -1)                                ||
          (diff ==  1 && !fillStep(x,     y,  1, 0)) ||
          (diff == -1 && !fillStep(x - 1, y, -1, 0))
      ) {
        return false;
      }

      prev = current;
    }

    return true;
  }

  private boolean checkCol(int x) {
    step = new boolean[boardSize];
    int prev = board[0][x];

    for (int y = 0; y < boardSize; y++) {
      int current = board[y][x];
      int diff = prev - current;

      if ((diff >  1)                                ||
          (diff < -1)                                ||
          (diff ==  1 && !fillStep(x, y,     0,  1)) ||
          (diff == -1 && !fillStep(x, y - 1, 0, -1))
      ) {
        return false;
      }

      prev = current;
    }

    return true;
  }

  private boolean fillStep(int x, int y, int dx, int dy) {
    int dir = (dx == 0) ? dy : dx;
    int begin = (dx == 0) ? y : x;
    int end = begin + (dir * (stepWidth));

    if (!isInside(begin) || !isInside(end - dir)) return false;

    int nx = x, ny = y;
    int value = board[y][x];

    for (int idx = begin; idx != end; idx += dir) {
      if (step[idx] || board[ny][nx] != value) return false;

      step[idx] = true;

      nx += dx;
      ny += dy;
    }

    return true;
  }

  private boolean isInside(int i) {
    return i >= 0 && i < boardSize;
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
