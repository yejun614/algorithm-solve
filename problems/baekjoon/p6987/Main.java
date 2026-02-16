/*
 * (6987) 월드컵
 * https://www.acmicpc.net/problem/6987
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 월드컵
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 주어진 경기 결과를 보고, 해당 결과가 존재할 수 있는 경기 결과인지 판단한다.
 * 
 * [전략]
 *   - 사전 체크
 *     - 각 국가의 가로 합은 항상 5가 되어야 함
 *     - 모든 숫자를 더했을때 값은 항상 30이 되어야 함
 *     - 승이 15면 패가 15여야 함 (승의 합 == 패의 합)
 *       누군가 이기면, 누군가 지는것이기 때문
 *     - 30 - (승) - (패) == (무)의 합이여 함
 *   - DFS + Backtracking
 *     - 각 팀들과 경기했을 때 결과(승,무,패) 조합에 대해서 DFS한다.
 *     - 입력된 경기 결과를 벗어난 조합은 탐색할 필요없다. (Backtracking)
 */
public class Main {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    final int testCount = 4;

    for (int testCase = 1; testCase <= testCount; testCase++) {
      new Main().run();
    }

    writer.write("\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  static final int TEAM_LEN = 6;
  static final int WIN = 0;
  static final int DRAW = 1;
  static final int LOSE = 2;

  static final int WIDTH = 3;
  static final int HEIGHT = 6;
  static int[][] mat0 = new int[HEIGHT][WIDTH];

  char answer;
  int[][] mat1;

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    getLine();
    for (int y = 0; y < HEIGHT; y++) {
      for (int x = 0; x < WIDTH; x++) {
        mat0[y][x] = Integer.parseInt(input.nextToken());
      }
    }
  }

  int[][] matches = new int[15][2];

  private void solve() {
    answer = '0';
    mat1 = new int[HEIGHT][WIDTH];

    for (int index = 0, teamA = 0; teamA < TEAM_LEN; teamA++) {
      for (int teamB = teamA + 1; teamB < TEAM_LEN; teamB++) {
        matches[index][0] = teamA;
        matches[index][1] = teamB;
        index++;
      }
    }

    if (!precheck()) return;
    answer = search(0) ? '1' : '0';
  }

  private boolean precheck() {
    int sum = 0;
    int win = 0, lose = 0;
    int draw = 0;

    // 각 국가의 가로 합은 항상 5가 되어야 함
    for (int y = 0; y < HEIGHT; y++) {
      int row = 0;

      for (int x = 0; x < WIDTH; x++) {
        row += mat0[y][x];
      }

      win += mat0[y][0];
      draw += mat0[y][1];
      lose += mat0[y][2];

      if (row != 5) return false;
      sum += row;
    }

    // 모든 숫자를 더했을때 값은 항상 30이 되어야 함
    if (sum != 30) return false;

    // 승이 15면 패가 15여야 함 (승의 합 == 패의 합)
    // 누군가 이기면, 누군가 지는것이기 때문
    if (win != lose) return false;

    // 30 - (승) - (패) == (무)의 합이여 함
    if (30 - win - lose != draw) return false;

    return true;
  }

  private boolean search(int match) {
    // A - B : 승
      // A - C : 승
        // A - D : 승
        // A - D : 무
        // A - D : 패
      // A - C : 무
      // A - C : 패
    // A - B : 무    
    // A - B : 패

    if (match == 15) return true;

    int teamA = matches[match][0];
    int teamB = matches[match][1];

    if (mat1[teamA][WIN] + 1 <= mat0[teamA][WIN] && mat1[teamB][LOSE] + 1 <= mat0[teamB][LOSE]){
      mat1[teamA][WIN]++; mat1[teamB][LOSE]++;
      if (search(match + 1)) return true;
      mat1[teamA][WIN]--; mat1[teamB][LOSE]--;
    }

    if (mat1[teamA][DRAW] + 1 <= mat0[teamA][DRAW] && mat1[teamB][DRAW] + 1 <= mat0[teamB][DRAW]){
      mat1[teamA][DRAW]++; mat1[teamB][DRAW]++;
      if (search(match + 1)) return true;
      mat1[teamA][DRAW]--; mat1[teamB][DRAW]--;
    }

    if (mat1[teamA][LOSE] + 1 <= mat0[teamA][LOSE] && mat1[teamB][WIN] + 1 <= mat0[teamB][WIN]){
      mat1[teamA][LOSE]++; mat1[teamB][WIN]++;
      if (search(match + 1)) return true;
      mat1[teamA][LOSE]--; mat1[teamB][WIN]--;
    }

    return false;
  }

  private void print() throws IOException {
    writer.write(answer);
    writer.write(" ");
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
