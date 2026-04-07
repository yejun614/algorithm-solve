/*
 * (1149) RGB거리
 * https://www.acmicpc.net/problem/1149
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - RGB거리
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 각 집을 R,G,B 색상 중 하나로 칠하는데 필요한 비용이 서로 다르다.
 *   - 이웃한 집의 색상은 서로 달라야 한다.
 *   - 각 집을 칠하는 최소 비용을 구해야 한다.
 * 
 * [전략]
 *   - DFS를 통해서 왼쪽의 집과 다른 색상으로 집을 칠하는 경우의 수를 구한다.
 *     - 이때 이전에 이미 계산해둔 비용이 있다면 다시 탐색하지 않는다.(DP)
 *   - 모든 집을 색칠하는데 필요한 최소 비용을 찾는다.
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

  int answer;
  int houseLen;
  int[][] houseCostMat;
  int[] houseColorArr;
  int[][] cache;

  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    houseLen = nextInt();

    houseCostMat = new int[houseLen + 1][3];

    for (int idx = 1; idx <= houseLen; idx++) {
      houseCostMat[idx][0] = nextInt();
      houseCostMat[idx][1] = nextInt();
      houseCostMat[idx][2] = nextInt();
    }
  }

  private void solve() {
    houseColorArr = new int[houseLen + 1];
    houseColorArr[0] = -1;

    cache = new int[houseLen + 1][];
    for (int idx = 0; idx <= houseLen; idx++) cache[idx] = new int[]{-1, -1, -1};

    answer = search(1);
  }

  private int search(int houseIdx) {
    if (houseIdx == houseLen + 1) return 0;

    int result = Integer.MAX_VALUE;

    for (int color = 0; color < 3; color++) {
      if (color == houseColorArr[houseIdx - 1]) continue;

      if (cache[houseIdx][color] < 0) {
        houseColorArr[houseIdx] = color;
        cache[houseIdx][color] = houseCostMat[houseIdx][color] + search(houseIdx + 1);
      }

      result = Math.min(result, cache[houseIdx][color]);
    }

    return result;
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
