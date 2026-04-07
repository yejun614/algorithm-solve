/*
 * (1010) 다리 놓기
 * https://www.acmicpc.net/problem/1010
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 다리 놓기
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 강 서쪽에서 동쪽으로 다리를 연결하는데 다리들이 서로 겹쳐선 안된다.
 *   - 다리를 건설할 수 있는 경우의 수를 구해야 한다.
 * 
 * [전략]
 *   - 다리들이 서로 겹처선 안되기 때문에 동쪽 사이트들을 기준으로
 *     이미 설치된 장소와 앞으로 설치해야할 사이트들을 남겨두고 가운데에 다리를 설치한다.
 *   - 서쪽의 모든 사이트가 다리로 연결되면 경우의 수를 +1 카운트 한다.
 *   - 경우의 수를 탐색하는 search(idx, lastPos) 함수에 메모이제이션을 적용하여 최적화한다.
 */
public class Main {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder output = new StringBuilder();
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    final int testCount = nextInt();

    for (int testCase = 1; testCase <= testCount; testCase++) {
      new Main(testCase).run();
    }

    System.out.print(output.toString());
  }

  // ----------------------------------------------------------

  int testCase;
  int answer;

  int[] site;
  int[][] cache;

  public Main(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    site = new int[2];

    site[0] = nextInt();
    site[1] = nextInt();
  }

  private void solve() {
    cache = new int[site[0]][site[1]];
    for (int idx = 0; idx < site[0]; idx++) Arrays.fill(cache[idx], -1);

    answer = search(0, 0);
  }

  private int search(int idx, int lastPos) {
    if (idx == site[0]) return 1;
    if (cache[idx][lastPos] >= 0) return cache[idx][lastPos];

    int result = 0;

    for (int pos = lastPos; pos < site[1] - (site[0] - idx - 1); pos++) {
      result += search(idx + 1, pos + 1);
    }

    return cache[idx][lastPos] = result;
  }

  private void print() {
    output.append(answer).append('\n');
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
