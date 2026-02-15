/*
 * (1074) Z
 * https://www.acmicpc.net/problem/1074
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - Z
 * @author YeJun, Jung
 * 
 * [전략]
 *   - 주어진 행렬의 크기를 1/4로 줄이면서 탐색한다.
 *     - 목표 좌표가 현재 부분행렬에서 몇 사분면에 있는지 파악한다.
 *     - 해당 사분면을 부분행렬로 하여 다음 탐색을 진행한다.
 * 
 * [TIP]
 *   - 2를 곱하거나 나눌때 비트 연산자를 사용하면 실행속도가 조금 빨라진다.
 *     - 5 * 2      ==  5 << 1
 *     - 5 * 2 * 2  ==  5 << 2
 *     - 5²         ==  5 << 2
 *     - 5 / 2      ==  5 >> 1
 *     - 5 / 2 / 2  ==  5 >> 2
 */
public class Main {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    new Main().run();
  }

  // ----------------------------------------------------------

  int boardSize;
  int goalX;
  int goalY;
  int answer;

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    getLine();

    boardSize = Integer.parseInt(input.nextToken());
    goalY = Integer.parseInt(input.nextToken());
    goalX = Integer.parseInt(input.nextToken());
  }

  private void solve() {
    answer = search(1 << boardSize, goalX, goalY);  // 1 << N == 2ⁿ (단, N ≧ 1)
  }

  private int search(int size, int x, int y) {
    int nSize = size >> 1;  // N >> 1 == N / 2
    int horizontal = x < nSize ? 0 : 1; // 왼쪽(0), 오른쪽(1)
    int vertical = y < nSize ? 0 : 1; // 위(0), 아래(1)
    int quad = horizontal + (2 * vertical); // 0, 1, 2, 3 사분면

    // 크가가 2이하가 되면 quad(사분면)을 반환하고 탐색을 종료한다.
    if (size <= 2) return quad;

    // 다음 탐색할 부분행렬 이전까지의 타일의 개수
    int tileNm = (nSize * nSize) * quad;

    // 다음 위치
    int nx = x - (nSize * horizontal);
    int ny = y - (nSize * vertical);

    // 재귀 호출
    return tileNm + search(nSize, nx, ny);
  }

  private void print() throws IOException {
    writer.write(answer + "\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
