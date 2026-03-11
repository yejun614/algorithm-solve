/*
 * (2382) [모의 SW 역량테스트] 미생물 격리
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV597vbqAH0DFAVl&categoryId=AV597vbqAH0DFAVl&categoryType=CODE&problemTitle=2382&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [모의 SW 역량테스트] 미생물 격리
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 미생물은 바라보고 있는 방향으로 이동한다.
 *     - 가장자리에 도달하면 방향이 반전되고 미생물이 1/2이 된다. (소수점 버림)
 *     - 이동 결과 충돌하는 미생물 군집들은 하나로 합쳐진다.
 *       - 미생물 수는 전부 합한다.
 *       - 미생물 수가 가장 많았던 군집의 방향을 따른다.
 * 
 * [전략]
 *   - 주어진 시간 만큼 runOnce() 함수를 통해 시뮬레이션 한다.
 *   - 미생물 군집을 이동시킨 다음 좌표를 HashMap에 저장한다.
 *     - 이때 미생물 수가 0인 군집은 무시한다.
 *     - Map의 Key는 (y * boarSize) + x 공식을 통해 계산된다.
 *   - 모든 군집의 이동이 끝난 후 HashMap에 저장된 데이터를 통해 충돌한 군집을 처리한다.
 *     - 충돌이 일어난 경우 미생물 수를 전부 합하고, 미생물 수가 가장 많았던 군집만 남긴다.
 *       - 자연스럽게 미생물 수가 가장 많았던 군집의 방향을 따르게 된다.
 *     - 그 외의 군집들은 미생물 수가 0이 되어 비활성화 된다.
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

  static final int[] DIR_X = {  0,  0, -1,  1 };
  static final int[] DIR_Y = { -1,  1,  0,  0 };
  static final int[] REVERSE = { 1, 0, 3, 2 };
  static final int DIR_LEN = 4;

  int testCase;
  int answer;
  int boardSize;
  int maxTime;
  int nodeLen;
  Node[] nodeArr;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    boardSize = nextInt();
    maxTime = nextInt();
    nodeLen = nextInt();

    nodeArr = new Node[nodeLen];
    for (int idx = 0; idx < nodeLen; idx++) {
      nodeArr[idx] = Node.fromReader();
    }
  }

  private void solve() {
    for (int time = 0; time < maxTime; time++) {
      runOnce();
    }

    answer = cntNodeSize();
  }

  private void runOnce() {
    // 이동 후 좌표 저장
    Map<Integer, List<Integer>> boardMap = new HashMap<>();

    // 각 미생물 군집 이동
    for (int idx = 0; idx < nodeLen; idx++) {
      Node node = nodeArr[idx];
      if (node.size == 0) continue;

      int nx = node.x + DIR_X[node.dir];
      int ny = node.y + DIR_Y[node.dir];
      node.setPos(nx, ny);

      int key = getKey(nx, ny);
      if (!boardMap.containsKey(key)) boardMap.put(key, new ArrayList<>());
      boardMap.get(key).add(idx);

      if (isOutlineBoard(nx, ny)) {
        node.dir = REVERSE[node.dir];
        node.size /= 2;
      }
    }

    // 이동 후 충돌 처리
    for (int key : boardMap.keySet()) {
      List<Integer> value = boardMap.get(key);
      if (value.size() == 1) continue;

      Node root = findMaxSizeNode(value);

      for (int idx : value) {
        Node node = nodeArr[idx];

        if (node == root) continue;

        root.size += node.size;
        node.size = 0;
      }
    }
  }

  private int getKey(int x, int y) {
    return (y * boardSize) + x;
  }

  private boolean isOutlineBoard(int x, int y) {
    final int N = boardSize - 1;
    return x == 0 || x == N || y == 0 || y == N;
  }

  private Node findMaxSizeNode(List<Integer> list) {
    Node best = nodeArr[list.get(0)];

    for (int idx : list) {
      Node node = nodeArr[idx];
      if (best.size < node.size) best = node;
    }

    return best;
  }

  private int cntNodeSize() {
    int cnt = 0;

    for (Node node : nodeArr) {
      cnt += node.size;
    }

    return cnt;
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

  static class Node {
    int x, y, size, dir;

    public Node(int x, int y, int size, int dir) {
      this.x = x;
      this.y = y;
      this.size = size;
      this.dir = dir;
    }

    public static Node fromReader() throws IOException {
      int y = nextInt();
      int x = nextInt();
      int size = nextInt();
      int dir = nextInt() - 1;

      return new Node(x, y, size, dir);
    }

    public void setPos(int x, int y) {
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
}
