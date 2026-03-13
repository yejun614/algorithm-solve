/*
 * (17472) 다리 만들기 2
 * https://www.acmicpc.net/problem/17472
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 다리 만들기 2
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 섬은 상하좌우로 인접하며 꼭 직사각형 인 것은 아니다.
 *   - 다리는 일직선으로 연결되어야 하며 중간에 방향을 바꾸면 안된다.
 *   - 다리끼리는 서로 교차할 수 있으며 서로에게 영향을 끼치지 않는다.
 * 
 * [전략]
 *   - 이중 반복문으로 board에 있는 섬들을 찾는다.
 *     - 섬을 발견하면 DFS로 상하좌우로 인접한 섬들을 방문한다.
 *     - 섬을 방문하면서 고유 ID로 채운다.
 *     - 섬 외곽선에 도달하면 해당 좌표를 landOutlineQ에 방향과 함께 삽입한다.
 *   - 섬과 섬을 연결하는 간선들을 만든다.
 *     - landOutlineQ에서 외곽선 정보를 차례대로 꺼낸다.
 *     - 외곽선이 바로보는 방향으로 이동하면서 다른 섬을 만나거나 board바깥으로 나간다.
 *       - 다른 섬을 만난다면 두 섬을 연결하는 간선을 리스트에 추가한다.
 *   - 외곽선 리스트를 바탕으로 최소 스패닝 트리를 구성한다.
 *     - 유니온 파인드 알고리즘이 적용된 DisjointSet 만들어서 클래스를 사용한다.
 *     - 간선을 가중치를 기준으로 오름차순 정렬한다.
 *     - 간선을 차례대로 선택하면서 싸이클이 생기지 않는다면 Union 연산한다.
 *   - 스패닝 트리를 만드는 과정에서 모든 섬들을 연결하는 다리의 최소 길이를 알 수 있다.
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

  static final int[] DIR_X = { -1,  1,  0,  0 };
  static final int[] DIR_Y = {  0,  0, -1,  1 };
  static final int DIR_LEN = 4;

  int answer;
  int height;
  int width;
  int[][] board;

  int nodeLen;
  int edgeLen;
  Queue<landOutline> landOutlineQ;
  List<Edge> edgeList;

  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    height = nextInt();
    width = nextInt();
    board = new int[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        board[y][x] = -1 * nextInt();
      }
    }
  }

  private void solve() {
    findLand();
    makeEdgeList();

    answer = kruskal();
  }

  private void findLand() {
    nodeLen = 0;
    landOutlineQ = new ArrayDeque<>();

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        if (board[y][x] == -1) fillLand(x, y, ++nodeLen, -1);
      }
    }
  }

  private void fillLand(int x, int y, int id, int fromDir) {
    if (board[y][x] == 0) landOutlineQ.offer(new landOutline(x, y, id, fromDir));
    if (board[y][x] != -1) return;

    board[y][x] = id;

    for (int dir = 0; dir < DIR_LEN; dir++) {
      int nx = x + DIR_X[dir];
      int ny = y + DIR_Y[dir];

      if (!isInsideBoard(nx, ny)) continue;

      fillLand(nx, ny, id, dir);
    }
  }

  private void makeEdgeList() {
    edgeList = new ArrayList<>();

    while (!landOutlineQ.isEmpty()) {
      landOutline land = landOutlineQ.poll();
      int nodeA = land.id;

      int nx = land.x + DIR_X[land.dir];
      int ny = land.y + DIR_Y[land.dir];
      int weight = 1;

      for (; isInsideBoard(nx, ny) && board[ny][nx] == 0; weight++) {
        nx += DIR_X[land.dir];
        ny += DIR_Y[land.dir];
      }

      if (!isInsideBoard(nx, ny)) continue;

      int nodeB = board[ny][nx];
      if (nodeB == 0 || nodeA == nodeB || weight < 2) continue;

      edgeList.add(new Edge(nodeA, nodeB, weight));
    }

    edgeLen = edgeList.size();
  }

  private int kruskal() {
    int cnt = 0, cost = 0;
    DisjointSet set = new DisjointSet(nodeLen + 1);

    Collections.sort(edgeList);

    for (int idx = 0; idx < edgeLen && cnt < nodeLen - 1; idx++) {
      Edge edge = edgeList.get(idx);

      if (set.union(edge.v0, edge.v1)) {
        cnt++;
        cost += edge.w;
      }
    }

    if (cnt != nodeLen - 1) return -1;
    return cost;
  }

  private boolean isInsideBoard(int x, int y) {
    return x >= 0 && x < width && y >= 0 && y < height;
  }

  private void print() {
    output.append(answer).append('\n');
  }

  // ----------------------------------------------------------

  static class landOutline {
    int x, y, id, dir;

    public landOutline(int x, int y, int id, int dir) {
      this.x = x;
      this.y = y;
      this.id = id;
      this.dir = dir;
    }
  }

  // ----------------------------------------------------------

  static class Edge implements Comparable<Edge> {
    int v0, v1, w;

    public Edge(int v0, int v1, int w) {
      this.v0 = v0;
      this.v1 = v1;
      this.w = w;
    }

    @Override
    public int compareTo(Edge another) {
      return Integer.compare(this.w, another.w);
    }
  }

  // ----------------------------------------------------------

  static class DisjointSet {
    int nodeLen;
    int[] parentArr;

    public DisjointSet(int nodeLen) {
      this.nodeLen = nodeLen;

      parentArr = new int[nodeLen];
      Arrays.fill(parentArr, -1);
    }

    public boolean union(int a, int b) {
      int pa = find(a);
      int pb = find(b);

      if (pa == pb) return false;

      parentArr[pa] += parentArr[pb];
      parentArr[pb] = pa;
      return true;
    }

    public int find(int x) {
      if (parentArr[x] < 0) return x;
      return parentArr[x] = find(parentArr[x]);
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
