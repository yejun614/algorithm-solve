/*
 * (2117) [모의 SW 역량테스트] 홈 방범 서비스
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V61LqAf8DFAWu&categoryId=AV5V61LqAf8DFAWu&categoryType=CODE&problemTitle=2117&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [모의 SW 역량테스트] 홈 방범 서비스
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 마름모 꼴의 영역안에서 손해 없이 얼마나 많은 집들에 서비스를 제공할 수 있는지 구해야 한다.
 *   - 운영 비용은 (K * K) + ((K-1) * (K-1))로 계산된다.
 *   - 마름모의 각 위치에서 중심좌표 사이의 거리는 K-1 이하여야 한다.
 * 
 * [전략]
 *   - 접근법: 완전탐색
 *   - 지도의 각 좌표를 중심으로 영역을 1에서 모든 집을 덮을때 까지 키워가면서 운영 비용을 계산한다.
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

  int budget;
  int boardSize;
  char[][] board;
  int houseLen;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    boardSize = nextInt();
    budget = nextInt();

    board = new char[boardSize][boardSize];
    houseLen = 0;

    for (int y = 0; y < boardSize; y++) {
      for (int x = 0; x < boardSize; x++) {
        board[y][x] = next().charAt(0);

        if (board[y][x] == '1') houseLen++;
      }
    }
  }

  private void solve() {
    answer = 0;
    searchBest();
  }

  private void searchBest() {
    // 지도의 각 좌표마다...
    for (int cy = 0; cy < boardSize; cy++) {
      for (int cx = 0; cx < boardSize; cx++) {

        // 서비스 범위를 키워가면서...
        for (int area = 1, cnt = 0; cnt != houseLen; area++) {

          // 서비스 비용 계산
          cnt = 0;
          final int side = area - 1;
          int benefit = -1 * ((area * area) + ((area - 1) * (area - 1)));

          // 서비스 범위 안의 집 개수 확인
          for (int y = cy - side; y <= cy + side; y++) {
            for (int x = cx - side; x <= cx + side; x++) {
              if (!isInsideBoard(x, y) ||
                  getDistance(cx, cy, x, y) > side ||
                  board[y][x] == '0'
              ) {
                continue;
              }

              cnt++;
            }
          }

          // 운영비용에 손해가 없는 경우 정답 변수 업데이트
          benefit += budget * cnt;
          if (benefit >= 0) answer = Math.max(answer, cnt);
        }
      }
    }
  }

  private int getDistance(int x0, int y0, int x1, int y1) {
    return Math.abs(x0 - x1) + Math.abs(y0 - y1);
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

  static String next() throws IOException {
    if (input == null || !input.hasMoreTokens())
      input = new StringTokenizer(reader.readLine().trim());
    return input.nextToken();
  }

  static int nextInt() throws IOException {
    return Integer.parseInt(next());
  }
}
