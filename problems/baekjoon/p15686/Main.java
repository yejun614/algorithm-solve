/*
 * (15686) 치킨 배달
 * https://www.acmicpc.net/problem/15686
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 치킨 배달
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 각 집의 "치킨거리"는 집과 가장 가까운 치킨집까지의 거리로 정의한다.
 *     - 이때 거리는 "맨허튼 거리"를 사용한다.
 *   - "도시의 치킨 거리"는 모든 집의 "치킨거리"를 합한 것이다.
 *   - 주어진 치킨집들 중에서 M개를 고르는데 "도시의 치킨 거리"가 최소가 되도록 해야 한다.
 * 
 * [전략]
 *   - 각 집에서 모든 치킨집 사이의 거리를 사전에 계산하여 저장해 둔다.
 *   - 치킨집들 중에서 M개를 선택하는 조합
 *     - prevPermutation(arr) 함수와 플래그 배열을 사용해서 조합을 구현한다.
 *     - 선택된 치킨집들만 사용해서 "도시의 치킨 거리"를 계산하여 정답 변수를 업데이트 한다.
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

  static final char TILE_HOUSE = '1';
  static final char TILE_STORE = '2';

  int answer;
  int boardSize;
  int selectCnt;

  int[] select;
  List<Pos> storeList;
  List<Pos> houseList;
  int[][] distList;
  int storeLen;
  int houseLen;

  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    char buf;
    storeList = new ArrayList<>();
    houseList = new ArrayList<>();

    boardSize = nextInt();
    selectCnt = nextInt();

    for (int y = 0; y < boardSize; y++) {
      for (int x = 0; x < boardSize; x++) {
        buf = next().charAt(0);

        switch (buf) {
        case TILE_STORE:
          storeList.add(new Pos(x, y));
          break;
        case TILE_HOUSE:
          houseList.add(new Pos(x, y));
          break;
        }
      }
    }

    storeLen = storeList.size();
    houseLen = houseList.size();
  }

  private void solve() {
    makeDistList();
    answer = findBestSelect();
  }

  private void makeDistList() {
    distList = new int[houseLen][storeLen];

    for (int house = 0; house < houseLen; house++) {
      Pos housePos = houseList.get(house);
      for (int store = 0; store < storeLen; store++) {
        Pos storePos = storeList.get(store);

        distList[house][store] = getDistance(housePos, storePos);
      }
    }
  }

  private int findBestSelect() {
    int result = Integer.MAX_VALUE;

    select = new int[storeLen];
    Arrays.fill(select, 0, selectCnt, 1);

    do {
      int current = 0;
      for (int house = 0; house < houseLen; house++) {
        int dist = Integer.MAX_VALUE;
        for (int store = 0; store < storeLen; store++) {
          if (select[store] == 0) continue;

          dist = Math.min(dist, distList[house][store]);
        }
        current += dist;
      }

      result = Math.min(result, current);
    } while (prevPermutation(select));

    return result;
  }

  private int getDistance(Pos a, Pos b) {
    return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
  }

  private void print() {
    output.append(answer).append('\n');
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

  static String next() throws IOException {
    if (input == null || !input.hasMoreTokens())
      input = new StringTokenizer(reader.readLine().trim());
    return input.nextToken();
  }

  static int nextInt() throws IOException {
    return Integer.parseInt(next());
  }

  static boolean prevPermutation(int[] x) {
    int n = x.length - 1, a = n, b = n;
    while (a > 0 && x[a - 1] <= x[a]) a--;
    if (a == 0) return false;
    while (x[a - 1] <= x[b]) b--;
    swap(x, a - 1, b); reverse(x, a, n);
    return true;
  }

  static void swap(int[] x, int a, int b) {
    int t = x[a]; x[a] = x[b]; x[b] = t;
  }

  static void reverse(int[] x, int a, int b) {
    while (a < b) swap(x, a++, b--);
  }
}
