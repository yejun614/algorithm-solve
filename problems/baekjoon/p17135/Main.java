/*
 * (17135) 캐슬 디펜스
 * https://www.acmicpc.net/problem/17135
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 캐슬 디펜스
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 궁수 3명의 x위치는 주어지지 않는다. (직접 적절히 배치해야 한다)
 *   - 궁수는 같은 거리의 적들이 있다면 제일 왼쪽에 있는 적을 노린다.
 *   - 적들은 매일 +1만큼 y좌표를 이동한다.
 * 
 * [전략]
 *   - 궁수 3명의 위치는 가능한 조합들을 모두 시도해 본다.
 *     - boardWidth 만큼의 칸에서 3개를 선택하는 것
 *     - prevPermutation과 mask 배열을 사용해서 구현한다.
 *   - 궁수들의 위치 조합들에 대해서 시뮬레이션을 통해 죽인 적의 개수를 센다.
 *     - 적들의 위치를 모두 Queue에 삽입하고 시뮬레이션을 시작한다.
 *     - boardHeight 만큼의 일수 혹은 모든 적들의 삭제 전까지 반복한면서...
 *       - 각 헌터의 위치별 최소 거리의 적을 식별한다.
 *         - 이때 거리제한으로 인해 맞출수 있는 적이 하나도 없을 수 도 있다.
 *       - 각 헌터가 맞춘 적은 HashSet에 보관해둔다.
 *       - 헌터가 맞춘 적과 이미 성까지 도달한 적을 제외한 모든 적을 y좌표 +1하고 Queue에 삽입한다.
 *     -  헌터가 맞춘 적들의 수를 합하여 최대값이 되는 조합을 찾는다.
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

  int answer;
  int boardHeight;
  int boardWidth;
  int attackDistance;
  char[][] board;
  int[] select;
  List<Pos> enemyList;
  Set<Pos> enemyKill;
  Queue<Pos> enemyQ;

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    getLine();
    boardHeight = Integer.parseInt(input.nextToken());
    boardWidth = Integer.parseInt(input.nextToken());
    attackDistance = Integer.parseInt(input.nextToken());

    board = new char[boardHeight][boardWidth];
    enemyList = new ArrayList<>(boardHeight * boardWidth); // Capacity Reservation

    for (int y = 0; y < boardHeight; y++) {
      getLine();

      for (int x = 0; x < boardWidth; x++) {
        board[y][x] = input.nextToken().charAt(0);
        if (board[y][x] == '1') enemyList.add(new Pos(x, y));
      }
    }
  }

  private void solve() {
    answer = 0;

    select = new int[boardWidth];
    Arrays.fill(select, 0, 3, 1);

    do {
      answer = Math.max(answer, runSimulation());
    } while (prevPermutation(select));
  }

  private int runSimulation() {
    int result = 0;
    enemyKill = new HashSet<>();
    enemyQ = new ArrayDeque<>(enemyList);

    for (int day = 0; day < boardHeight && !enemyQ.isEmpty(); day++) {
      for (int hunterX = 0; hunterX < boardWidth; hunterX++) {
        if (select[hunterX] == 0) continue;

        Pos pos = shootFrom(hunterX);
        if (pos != null) enemyKill.add(pos);
      }

      result += enemyKill.size();

      int qSize = enemyQ.size();
      for (int cnt = 0; cnt < qSize; cnt++) {
        Pos peek = enemyQ.poll();
        if (enemyKill.contains(peek) || peek.y + 1 >= boardHeight) continue;

        enemyQ.offer(new Pos(peek.x, peek.y + 1));
      }
      enemyKill.clear();
    }

    return result;
  }

  private Pos shootFrom(int hunterX) {
    Pos pos = null;
    int distance = Integer.MAX_VALUE;
    int current;

    for (Pos enemy : enemyQ) {
      current = distanceCalc(hunterX, boardHeight, enemy.x, enemy.y);

      if (current > attackDistance) continue;

      if ((current < distance) ||
          (current == distance && enemy.x < pos.x)
      ) {
        distance = current;
        pos = enemy;
      }
    }

    return pos;
  }

  private int distanceCalc(int x0, int y0, int x1, int y1) {
    return Math.abs(x0 - x1) + Math.abs(y0 - y1);
  }

  private void print() throws IOException {
    writer.write(answer + "\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static class Pos {
    int x;
    int y;

    public Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override public String toString() { return String.format("[%d, %d]", x, y); }
    @Override public int hashCode() { return Objects.hash(x, y); }
    @Override public boolean equals(Object obj) {
      Pos another = (Pos)obj;
      return this.x == another.x && this.y == another.y;
    }
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }

  private static boolean prevPermutation(int[] x) {
    int n = x.length - 1, a = n, b = n;
    while (a > 0 && x[a - 1] <= x[a]) a--;
    if (a == 0) return false;
    while (x[a - 1] <= x[b]) b--;
    swap(x, a - 1, b); reverse(x, a, n);
    return true;
  }

  private static void swap(int[] x, int a, int b) {
    int t = x[a]; x[a] = x[b]; x[b] = t;
  }

  private static void reverse(int[] x, int a, int b) {
    while (a < b) swap(x, a++, b--);
  }
}
