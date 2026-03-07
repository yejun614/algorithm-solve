/*
 * (1767) [SW Test 샘플문제] 프로세서 연결하기
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf&categoryId=AV4suNtaXFEDFAUf&categoryType=CODE&problemTitle=1767&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */
import java.io.*;
import java.util.*;

public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder output = new StringBuilder();
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    final int testCount =  nextInt();

    for (int testCase = 1; testCase <= testCount; testCase++) {
      new Solution(testCase).run();
    }

    System.out.print(output.toString());
  }

  // ----------------------------------------------------------

  static final int[] DIR_X = { -1,  1,  0,  0 };
  static final int[] DIR_Y = {  0,  0, -1,  1 };
  static final int[] REVERSE = { 1, 0, 3, 2 };
  static final int DIR_LEN = 4;

  int testCase;
  int answer;
  int boardSize;
  int[][] board;
  List<Pos> coreList;
  int coreLen;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    coreList = new ArrayList<>();
    boardSize = nextInt();
    board = new int[boardSize][boardSize];

    for (int y = 0; y < boardSize; y++) {
      for (int x = 0; x < boardSize; x++) {
        board[y][x] = nextInt();

        if (board[y][x] == 1 &&
            x != 0 && x != boardSize - 1 &&
            y != 0 && y != boardSize - 1
        ) {
          coreList.add(new Pos(x, y));
        }
      }
    }

    coreLen = coreList.size();
  }

  private void solve() {
    answer = dfs(0, 0, 0).len;
  }

  private Result dfs(int core, int cnt, int len) {
    if (core == coreLen) return new Result(cnt, len);

    Pos pos = coreList.get(core);

    // 수정된 버전: 속도가 더 빠르다.
    // 4방향 탐색 성공 여부와 관계 없이 무조건 해당 코어를 배제한 경우도 탐색해야 함
    Result result = dfs(core + 1, cnt, len);  

    for (int dir = 0; dir < DIR_LEN; dir++) {
      int nx = pos.x + DIR_X[dir];
      int ny = pos.y + DIR_Y[dir];

      int current = 0;
      while (isInsideBoard(nx, ny) && board[ny][nx] == 0) {
        board[ny][nx] = 2;

        current++;

        nx += DIR_X[dir];
        ny += DIR_Y[dir];
      }

      if (!isInsideBoard(nx, ny) && current != 0) {
        result = Result.selectBest(result, dfs(core + 1, cnt + 1, len + current));
      }

      undoBoard(nx, ny, dir, current);
    }

    return result;
  }

  private void undoBoard(int x, int y, int dir, int len) {
    int rdir = REVERSE[dir];

    for (int cnt = 0; cnt < len; cnt++) {
      x += DIR_X[rdir];
      y += DIR_Y[rdir];

      board[y][x] = 0;
    }
  }

  private boolean isInsideBoard(int x, int y) {
    return x >= 0 && x < boardSize && y >= 0 && y < boardSize;
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

  static class Pos {
    int x, y;

    public Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  // ----------------------------------------------------------

  static class Result {
    int cnt, len;

    public Result(int cnt, int len) {
      this.cnt = cnt;
      this.len = len;
    }

    public Result() {
      this(0, Integer.MAX_VALUE);
    }

    public static Result selectBest(Result a, Result b) {
      if (a.cnt == b.cnt) {
        if (a.len < b.len) {
          return a;
        } else {
          return b;
        }
      } else if (a.cnt > b.cnt) {
        return a;
      } else {
        return b;
      }
    }
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





/*

[예시 입력]
9
7
0 0 1 0 0 0 0
0 0 1 0 0 0 0
0 0 0 0 0 1 0
0 0 0 0 0 0 0
1 1 0 1 0 0 0
0 1 0 0 0 0 0
0 0 0 0 0 0 0
9
0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 1
1 0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0 0
0 0 0 1 0 0 0 0 0
0 0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0 1
11
0 0 1 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 1
0 0 0 1 0 0 0 0 1 0 0
0 1 0 1 1 0 0 0 1 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 1 0 0
0 0 0 0 0 0 1 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
7
1 1 1 1 1 1 1
1 0 0 1 0 0 1
1 0 0 0 0 0 1
1 1 0 0 0 0 0
1 0 0 0 0 0 1
0 0 0 0 0 1 1
1 1 1 0 1 1 1
9
0 0 0 1 0 1 1 1 0
1 0 0 1 0 0 0 0 0
1 0 0 1 0 0 0 0 0
1 0 0 0 0 0 1 1 1
0 0 0 0 1 0 0 0 0
1 1 1 0 0 0 0 0 1
0 0 0 0 0 1 0 0 1
0 0 0 0 0 1 0 0 1
0 1 1 1 0 1 0 0 0
5
0 0 0 0 0
0 0 1 0 0
0 1 1 1 0
0 0 1 0 0
0 0 0 0 0
7
1 1 1 1 1 1 1
1 0 0 1 0 0 1
1 0 0 0 0 0 1
1 1 0 0 0 0 1
1 0 0 0 0 0 1
1 0 0 0 0 1 1
1 1 1 1 1 1 1
7
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 1 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
7
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 1 1 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0


[예시 출력]
#1 12
#2 10
#3 24
#4 10
#5 40
#6 4
#7 0
#8 3
#9 5


 */