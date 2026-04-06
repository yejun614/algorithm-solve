/*
 * (2383) [모의 SW 역량테스트] 점심 식사시간
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5-BEE6AK0DFAVl&categoryId=AV5-BEE6AK0DFAVl&categoryType=CODE&problemTitle=2383&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [모의 SW 역량테스트] 점심 식사시간
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 정사각형 보드에서 사람들의 위치와 계단의 위치가 주어진다.
 *   - 사람들이 계단으로 최단거리(맨허튼 거리)로 이동하여 계단 위에 서면, 1분 뒤에 계단을 내려갈 수 있다.
 *   - 이때 이미 계단을 내려가고 있는 사람이 3명 있다면 제일 아래에 있는 사람이 빠져나갈때 까지 계단을 내려갈 수 없다.
 *   - 계단은 항상 2개 존재한다.
 *   - 최소 시간으로 모든 사람들을 계단 밑으로 보내는 방법을 찾아야 한다.
 * 
 * [전략]
 *   - `selection(idx)`: 각 사람들을 어느 계단으로 보낼 것인지...
 *     - 사람들은 최대 10명이고 계단은 항상 2개로 2^{10} = 1024 충분히 완전탐색 가능하다.
 *     - BFS를 사용하여 가능한 모든 조합을 시도한다.
 *   - `simulation(gateIdx)`: 사람들이 각자 계단으로 이동할때 걸리는 시간 계산...
 *     - 각 사람들의 위치에서 계단까지의 거리는 맨허튼 거리 D로 계산한다.
 * 
 *     1. Setup
 *       - 사람들이 계단에 도달한 후 1분 뒤부터 내려갈 수 있으므로 D + 1 로 계산한다.
 *       - 각 사람들이 계단에 도달한 1분 뒤 시간을 배열에 넣고 오름차순으로 정렬한다.
 *           arr = [3, 3, 3, 4]
 *       - 계단을 통해 바닥에 도착했을때의 시간을 배열로 나타낸다.
 *           초기값은 st = [0, 0, 0]
 *     2. Simulation
 *       - 가장 왼쪽에 있는 0이 아닌 숫자를 dt에 더하고, 나머지 배열 요소를 뺀다.
 *       - st 배열 마지막에 계단 바닥에 도달했을때의 시간을 기록한다.
 *           $val = dt + gate_size + Math.max(0, st_{stLen - 3} - (val - gate_size))$
 *     3. Return Result
 *       - st 배열 가장 마지막 요소를 반환한다. (계단 바닥에 마지막으로 도달한 시간)
 * 
 *  [simulation 예시]
 *    gate_size = 2
 *    1) dt=0, arr=[3, 3, 3, 4], st=[0, 0, 0]
 *    2) dt=3, arr=[0, 0, 0, 1], st=[0, 0, 0, 5, 5, 5]
 *    3) dt=4, arr=[0, 0, 0, 0], st=[0, 0, 0, 5, 5, 5, 7]  ... result=7
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
  Pos[] posArr;
  Pos[] gateArr;
  int posLen;
  int gateLen;
  int[] selectArr;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    boardSize = nextInt();

    int buf;
    posArr = new Pos[10];
    gateArr = new Pos[2];
    posLen = 0;
    gateLen = 0;

    for (int y = 0; y < boardSize; y++) {
      for (int x = 0; x < boardSize; x++) {
        buf = nextInt();

        if (buf == 1) {
          posArr[posLen++] = new Pos(x, y);
        } else if (buf > 1) {
          gateArr[gateLen++] = new Pos(x, y, buf);
        }
      }
    }
  }

  private void solve() {
    answer = Integer.MAX_VALUE;
    selectArr = new int[posLen];

    selection(0);
  }

  private void selection(int idx) {
    if (idx == posLen) {
      int result = Math.max(simulation(0), simulation(1));
      answer = Math.min(answer, result);
      return;
    }

    selectArr[idx] = 0;
    selection(idx + 1);
   
    selectArr[idx] = 1;
    selection(idx + 1);
  }

  private int simulation(int gateIdx) {
    // 1. Setup
    Pos gate = gateArr[gateIdx];

    int arrLen = 0;
    int[] arr = new int[10];

    for (int idx = 0; idx < posLen; idx++) {
      if (selectArr[idx] != gateIdx) continue;
      arr[arrLen++] = getDistance(gate, posArr[idx]) + 1;
    }

    Arrays.sort(arr);

    // 2. Simulation
    int stLen = 3;
    int[] st = new int[13];

    int idx = 0;
    while (idx < 10 && arr[idx] == 0) idx++;

    int dt = 0;

    for (; idx < 10; idx++) {
      int it = arr[idx];
      int cnt = 0;

      for (int target = idx; target < 10; target++) {
        arr[target] -= it;
        if (arr[target] == 0) cnt++;
      }

      idx += cnt - 1;
      dt += it;

      for (int c = 0; c < cnt; c++) {
        int val = dt + gate.size;
        val += Math.max(0, st[stLen - 3] - (val - gate.size));

        st[stLen++] = val;
      }
    }

    // 3. Return Result
    return st[stLen - 1];
  }

  private int getDistance(Pos a, Pos b) {
    return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
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
    int x, y, size;

    public Pos(int x, int y, int size) {
      this.x = x;
      this.y = y;
      this.size = size;
    }

    public Pos(int x, int y) {
      this(x, y, 0);
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
