/*
 * (1868) 파핑파핑 지뢰찾기
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LwsHaD1MDFAXc&categoryId=AV5LwsHaD1MDFAXc&categoryType=CODE&problemTitle=1868&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 파핑파핑 지뢰찾기
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 지뢰찾기 게임과 규칙은 동일하다.
 *   - 최소한의 클릭으로 지뢰가 없는 칸들을 모두 여는 방법을 찾아야 한다.
 * 
 * [전략]
 *   - 각 칸은 ID를 가지고 있다.
 *   - 각 ID에 대해서 부모 노드의 ID를 보관하는 배열 parentArr가 있다.
 *     - 처음에 parentArr는 자기자신을 가르키도록 초기화 한다.
 *   - 각 칸(ID)에 대해서 DFS으로 칸을 열어본다.
 *     - 열어본 칸에 대해서는 ID로 기록을 한다.
 *     - 칸을 열때 다른 ID가 기록되어 있다면 해당 칸의 ID와 현재 ID를 Union 한다.
 *   - parentArr에 있는 ID 종류의 개수가 정답이 된다.
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

  static final int[] DIR_X = { -1,  1,  0,  0,  1,  1, -1, -1 };
  static final int[] DIR_Y = {  0,  0, -1,  1,  1, -1,  1, -1 };
  static final int DIR_LEN = 8;

  int testCase;
  int answer;
  int boardSize;
  int[][] board;

  int[] parentArr;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    char[] buf;

    boardSize = nextInt();
    board = new int[boardSize][boardSize];

    for (int y = 0; y < boardSize; y++) {
      buf = next().toCharArray();

      for (int x = 0; x < boardSize; x++) {
        if (buf[x] == '.') continue;

        board[y][x] = -1;
      }
    }
  }

  private void solve() {
    answer = 0;

    p1();
  }

  private void p1() {
    makeParentArr();

    // System.out.printf("[%d]\n", testCase);

    for (int y = 0; y < boardSize; y++) {
      for (int x = 0; x < boardSize; x++) {
        int id = (y * boardSize) + x + 1;

        if (board[y][x] != 0) {
          parentArr[id] = 0;
          continue;
        }

        board[y][x] = id;
        openTile(x, y, id);
      }
    }

    // System.out.println("------------------------------");
    // for (int y = 0; y < boardSize; y++) {
    //   for (int x = 0; x < boardSize; x++) {
    //     System.out.printf("%3d ", board[y][x]);
    //   }
    //   System.out.println();
    // }
    // System.out.println(Arrays.toString(parentArr));

    updateAnswer();
  }

  private void makeParentArr() {
    final int N = boardSize * boardSize + 1;
    parentArr = new int[N];

    for (int idx = 0; idx < N; idx++) {
      parentArr[idx] = idx;
    }
  }

  private int findParent(int idx) {
    if (parentArr[idx] == idx) return idx;
    return findParent(parentArr[idx]);
  }

  private void unionParent(int a, int b) {
    int pa = findParent(a);
    int pb = findParent(b);
    if (pa != pb) parentArr[pb] = pa;
  }

  private void openTile(int x, int y, int id) {
    int cnt = 0;

    for (int dir = 0; dir < DIR_LEN; dir++) {
      int nx = x + DIR_X[dir];
      int ny = y + DIR_Y[dir];

      if (!isInsideBoard(nx, ny)) continue;
      if (board[ny][nx] == -1) cnt++;
    }

    // System.out.printf("%d, %d : %d (%d)\n", x, y, cnt, id);
    if (cnt != 0) return;

    for (int dir = 0; dir < DIR_LEN; dir++) {
      int nx = x + DIR_X[dir];
      int ny = y + DIR_Y[dir];

      if (!isInsideBoard(nx, ny) || board[ny][nx] == -1) continue;

      if (board[ny][nx] != 0) {
        unionParent(id, board[ny][nx]);
        continue;
      }

      board[ny][nx] = id;
      openTile(nx, ny, id);
    }
  }

  private boolean isInsideBoard(int x, int y) {
    return x >= 0 && x < boardSize && y >= 0 && y < boardSize;
  }

  private void updateAnswer() {
    Set<Integer> set = new HashSet<>();
    for (int idx = 1; idx < parentArr.length; idx++) {
      if (parentArr[idx] == 0) continue;
      set.add(parentArr[idx]);
    }

    answer = set.size();
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
