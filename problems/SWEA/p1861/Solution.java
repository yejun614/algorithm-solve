/*
 * (1861) 정사각형 방
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LtJYKDzsDFAXc&categoryId=AV5LtJYKDzsDFAXc&categoryType=CODE&problemTitle=1861&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 1861. 정사각형 방
 * @author YeJun, Jung
 * 
 * @see #main(String[] args)
 * 1. 테스트케이스 입력
 * 2. 솔루션 실행
 * 
 * @see #input()
 * 3. N을 입력받아 boardSize로 저장한다.
 * 4. 방위치를 저장할 board 배열을 초기화 한다.
 * 5. 우선 탐색할 위치를 보관할 배열 goodStartArr 배열을 초기화한다.
 * 6. 방위치 정보를 입력받아 저장한다.
 * 7. 우선 탐색할 위치를 찾아 저장한다.
 * 
 * @see #solve()
 * 8. 탐색을 시작한다.
 * 
 * @see #startPositioner()
 * 9. 우선 탐색 위치를 먼저 방문한다.
 * 10. 이후 전체 위치를 방문한다.
 * 
 * @see #search(Pos pos)
 * 11. 입력받은 pos을 기준으로 DFS 탐색 한다.
 *   11-1. 현재 방문중인 위치: peek
 *   11-2. 현재 방문중인 방번호가 입력받은 방번호보다 작다면 업데이트 한다.
 *   11-3. 이웃한 4방향 위치에 대해서...
 *     11-3-1. 방문 예정인 위치: nPos
 *     11-3-2. 현재 방문중인 방번호와 차이가 1인 경우 해당 위치를 스택에 삽입한다.
 *     11-3-3. 방문처리 및 방의 크기(distnace)를 +1한다.
 *   11-4. 방의 크기가 같은 공간 중에서 방번호가 제일 작은 값이 정답이다.
 * 
 * @see #getDistance(int roomNum)
 * 12. 입력받은 방번호보다 크면서 방문하지 않은 방번호들 중에서 가장 큰 값을 반환한다.
 * 
 * @see #print()
 * 13. 최적의 방번호와 그 방의 크기를 화면에 출력한다.
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    // 1. 테스트케이스 입력
    final int testCount = Integer.parseInt(reader.readLine().trim());

    for (int testCase = 1; testCase <= testCount; testCase++) {
      // 2. 솔루션 실행
      new Solution(testCase).run();
    }
  }

  // ----------------------------------------------------------

  static final int[] DIR_X = { -1,  1,  0,  0 };
  static final int[] DIR_Y = {  0,  0, -1,  1 };
  static final int DIR_LEN = 4;

  int testCase;

  int bestRoomNum;
  int bestDistance;

  int boardSize;
  int[][] board;

  Pos[] goodStartArr;
  boolean[] visited;
  int maxValue;

  public Solution(int testCase) {
    this.testCase = testCase;
  }

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  public void input() throws IOException {
    // 3. N을 입력받아 boardSize로 저장한다.
    boardSize = Integer.parseInt(reader.readLine().trim());

    // 4. 방위치를 저장할 board 배열을 초기화 한다.
    board = new int[boardSize][boardSize];

    // 5. 우선 탐색할 위치를 보관할 배열 goodStartArr 배열을 초기화한다.
    int mid = boardSize / 2;
    int mid1 = mid + 1;
    goodStartArr = new Pos[3];

    for (int y = 0; y < boardSize; y++) {
      getLine();
      for (int x = 0; x < boardSize; x++) {
        // 6. 방위치 정보를 입력받아 저장한다.
        board[y][x] = Integer.parseInt(input.nextToken());

        // 7. 우선 탐색할 위치를 찾아 저장한다.
        int index = 0;
        if (board[y][x] == 1) index = 1;
        else if (board[y][x] == mid) index = 0;
        else if (board[y][x] == mid1) index = 2;

        goodStartArr[index] = new Pos(x, y);
      }
    }
  }

  public void solve() {
    // 탐색순서:
    //   N/2 -> 1 ~ (N/2)-1 -> (N/2)+1 ~ N 순서로 탐색한다.
    // 
    // 백트래킹
    //   - value : 지금 방문하는 곳의 값
    //   - distance : 방문가능한 최대 방의 개수
    //     - value부터 다음 (이미 방문한 곳)까지의 거리
    //   - distance가 best보다 작으면 해당 위치를 방문하지 않고 PASS
    //
    // 요구시간
    //   - Java: 2sec
    //
    // 시간복잡도 분석
    //   - 1,000,000,000 (1억) -> 1초
    //     - 한번 방문했을때 백트래킹 안된다면 DFS탐색 실시(추가 자원 소모)
    //       - "여기서 남은 1초 안에 실행이 되는가?" 관건

    bestRoomNum = -1;
    bestDistance = 0;
    maxValue = boardSize * boardSize; // N²
    visited = new boolean[maxValue + 1]; // 1 ~ N², [false, ...]

    // 8. 탐색을 시작한다.
    startPositioner();
  }

  private void startPositioner() {
    // 9. 우선 탐색 위치를 먼저 방문한다.
    for (Pos pos : goodStartArr) {
      search(pos);
    }

    // 10. 이후 전체 위치를 방문한다.
    Pos pos = new Pos(0, 0);
    for (int y = 0; y < boardSize; y++) {
      for (int x = 0; x < boardSize; x++) {
        pos.x = x;
        pos.y = y;

        search(pos);
      }
    }
  }

  private void search(Pos pos) {
    int roomNum = board[pos.y][pos.x];
    int distance = getDistance(roomNum);

    // backtracking
    if (visited[roomNum]) return;
    if (distance < bestDistance) return;

    distance = 1;
    visited[roomNum] = true;

    // 11. 입력받은 pos을 기준으로 DFS 탐색 한다.
    Deque<Pos> stack = new ArrayDeque<>();
    stack.push(pos);

    while (!stack.isEmpty()) {
      // 11-1. 현재 방문중인 위치: peek
      Pos peek = stack.pop();
      int pRoomNum = board[peek.y][peek.x];

      // 11-2. 현재 방문중인 방번호가 입력받은 방번호보다 작다면 업데이트 한다.
      if (pRoomNum < roomNum) roomNum = pRoomNum;

      // 11-3. 이웃한 4방향 위치에 대해서...
      for (int dir = 0; dir < DIR_LEN; dir++) {
        // 11-3-1. 방문 예정인 위치: nPos
        Pos nPos = peek.moveDelta(DIR_X[dir], DIR_Y[dir]);
        if (!isInsideBoard(nPos)) continue;

        int nRoomNum = board[nPos.y][nPos.x];

        // 11-3-2. 현재 방문중인 방번호와 차이가 1인 경우 해당 위치를 스택에 삽입한다.
        if (Math.abs(nRoomNum - pRoomNum) != 1) continue;
        if (visited[nRoomNum]) continue;
        
        // 11-3-3. 방문처리 및 방의 크기(distnace)를 +1한다.
        visited[nRoomNum] = true;
        distance++;

        stack.push(nPos);
      }
    }

    if (distance == bestDistance) {
      // 11-4. 방의 크기가 같은 공간 중에서 방번호가 제일 작은 값이 정답이다.
      if (roomNum < bestRoomNum) {
        bestRoomNum = roomNum;
      }
    } else if (distance > bestDistance) {
      bestRoomNum = roomNum;
      bestDistance = distance;
    }
  }

  private int getDistance(int roomNum) {
    // 12. 입력받은 방번호보다 크면서 방문하지 않은 방번호들 중에서 가장 큰 값을 반환한다.
    int current = roomNum + 1;
    for (; current <= maxValue; current++) {
      if (visited[current]) break;
    }
    return current - roomNum;
  }

  private boolean isInsideBoard(Pos pos) {
    return pos.x >= 0 && pos.x < boardSize && pos.y >= 0 && pos.y < boardSize;
  }

  public void print() throws IOException {
    // 13. 최적의 방번호와 그 방의 크기를 화면에 출력한다.
    writer.write("#" + testCase);
    writer.write(" " + bestRoomNum);
    writer.write(" " + bestDistance);
    writer.write("\n");
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

    public Pos moveDelta(int dx, int dy) {
      return new Pos(x + dx, y + dy);
    }
  }

  // ----------------------------------------------------------

  private void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
