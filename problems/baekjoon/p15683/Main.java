/*
 * (15683) 감시
 * https://www.acmicpc.net/problem/15683
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 감시
 * @author YeJun, Jung
 * 
 * [분석]
 *   - CCTV는 5가지 종류가 있으며, 각각 감시할 수 있는 방향이 다르다.
 *   - CCTV는 90도 회전이 가능하다.
 *   - CCTV는 벽으로 가로막히지 않는한 바라보는 방향으로 끝까지 감시할 수 있다.
 * 
 * [전략]
 *   - 각 CCTV의 방향을 바꿔가면서 DFS 탐색한다.
 *     - 모든 CCTV의 방향이 결정되면 CCTV가 감시하지 않는 사각지대의 크기를 센다.
 *     - 사각지대가 가장 작아지는 CCTV 방향의 조합을 찾는다.
 */
public class Main {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder output = new StringBuilder();
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    new Main().run();
    System.out.print(output.toString());
  }

  // ----------------------------------------------------------

  static final int TILE_WALL = 6;

  static final int[] DIR_X = {  0,  1,  0, -1 }; // UP, RIGHT, DOWN, LEFT
  static final int[] DIR_Y = { -1,  0,  1,  0 }; // UP, RIGHT, DOWN, LEFT

  // 비트 마스크를 활용하여 델타배열(DIR_X, DIR_Y)의 인덱스를 기록한다.
  static final int[] CAM_DIR = {
    0b0000,
    0b0100, // 1번
    0b0101, // 2번
    0b1100, // 3번
    0b1101, // 4번
    0b1111, // 5번
  };

  int answer;
  int height;
  int width;
  int[][] board;
  List<Cam> camList;
  int camLen;

  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    int buf;
    camList = new ArrayList<>(8);

    height = nextInt();
    width = nextInt();
    board = new int[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        buf = board[y][x] = nextInt();

        // CCTV는 따로 모아 리스트에 추가한다.
        if (buf > 0 && buf < TILE_WALL) {
          camList.add(new Cam(x, y, buf));
        }
      }
    }

    camLen = camList.size();
  }

  private void solve() {
    // DFS 탐색 시작
    answer = dfs(0);
  }

  private int dfs(int idx) {
    if (idx == camLen) return cntTile();

    Cam cam = camList.get(idx);
    int key = (cam.y * height) + cam.x + 10;
    int result = Integer.MAX_VALUE;

    for (int rotate = 0; rotate < 4; rotate++) {
      fillCam(cam, rotate, key, 0);
      result = Math.min(result, dfs(idx + 1));
      fillCam(cam, rotate, 0, key);
    }

    return result;
  }

  private void fillCam(Cam cam, int rotate, int fg, int bg) {
    for (int vec = 0; vec < 4; vec++) {
      if ((CAM_DIR[cam.type] & (1 << (3 - vec))) == 0) continue;

      int dir = (vec + rotate) % 4;
      int nx = cam.x + DIR_X[dir];
      int ny = cam.y + DIR_Y[dir];

      while (isInsideBoard(nx, ny) && board[ny][nx] != TILE_WALL) {
        if (board[ny][nx] == bg) board[ny][nx] = fg;

        nx += DIR_X[dir];
        ny += DIR_Y[dir];
      }
    }
  }

  private int cntTile() {
    int cnt = 0;

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        if (board[y][x] == 0) cnt++;
      }
    }

    return cnt;
  }

  private boolean isInsideBoard(int x, int y) {
    return x >= 0 && x < width && y >= 0 && y < height;
  }

  private void print() {
    output.append(answer).append('\n');
  }

  // ----------------------------------------------------------

  static class Cam {
    int x, y, type;

    public Cam(int x, int y, int type) {
      this.x = x;
      this.y = y;
      this.type = type;
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
