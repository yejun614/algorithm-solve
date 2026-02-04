/*
 * (1210) [S/W 문제해결 기본] 2일차 - Ladder1
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14ABYKADACFAYh&categoryId=AV14ABYKADACFAYh&categoryType=CODE&problemTitle=1210&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

package swea.p1210;

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 1210. [S/W 문제해결 기본] 2일차 - Ladder1
 * @author YeJun, Jung
 *
 * @see #main(String[])
 * 1. 입출력을 초기화한다.
 * 2. 총 10개의 테스트 케이스를 반복 처리한다.
 * 3. 각 테스트 케이스의 번호를 입력받고 솔루션 인스턴스를 생성하여 실행한다.
 *
 * @see #Solution(int)
 * 4. 현재 테스트 케이스 번호를 멤버 변수에 저장한다.
 *
 * @see #run()
 * 5. 입력(input), 해결(solve), 출력(print) 로직을 순차적으로 수행한다.
 *
 * @see #input()
 * 6. 100x100 크기의 사다리 판(board) 정보를 입력받는다.
 * 7. 입력 과정에서 값이 '2'인 도착 지점의 좌표(goalX, goalY)를 찾아 저장한다.
 *
 * @see #solve()
 * 8. 도착점(goalX, goalY)에서 시작하여 위쪽 방향으로 역추적 시뮬레이션을 시작한다.
 * 9. 현재 위치에서 좌/우에 가로축('1')이 있는지 확인한다.
 * 9-1. 왼쪽(cx - 1)에 길이 있다면, 왼쪽 길이 끝날 때까지 $x$ 좌표를 감소시킨다.
 * 9-2. 왼쪽에 길이 없고 오른쪽(cx + 1)에 길이 있다면, 오른쪽 길이 끝날 때까지 $x$ 좌표를 증가시킨다.
 * 10. 좌우 이동이 끝났거나 없다면 위쪽(cy - 1)으로 한 칸 이동한다.
 * 11. $y$ 좌표가 0에 도달했을 때의 $x$ 좌표가 시작점이므로 answer에 저장한다.
 *
 * @see #print()
 * 12. 계산된 시작점의 $x$ 좌표를 형식에 맞춰 출력한다.
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer line;

  public static void main(String[] args) throws IOException {
    // 1. 입출력을 초기화한다.
    final int testCount = 10;

    // 2. 총 10개의 테스트 케이스를 반복 처리한다.
    for (int testCase = 1; testCase <= testCount; testCase++) {
      // 3. 테스트 케이스 번호 읽기 및 솔루션 실행
      reader.readLine(); 
      new Solution(testCase).run();
    }
  }

  // ----------------------------------------------------------

  private final int BOARD_SIZE = 100;

  private int testCase;
  private int answer;

  private char[][] board;
  private int goalX;
  private int goalY;

  public Solution(int testCase) {
    // 4. 멤버 변수를 초기화한다.
    this.testCase = testCase;
  }

  public void run() throws IOException {
    // 5. 입력, 해결, 출력 순서로 실행한다.
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    // 6. 사다리 판 정보를 저장할 배열 생성
    board = new char[BOARD_SIZE][BOARD_SIZE];

    for (int y = 0; y < BOARD_SIZE; y++) {
      getLine();
      for (int x = 0; x < BOARD_SIZE; x++) {
        board[y][x] = line.nextToken().charAt(0);

        // 7. 도착 지점('2')의 좌표를 저장한다.
        if (board[y][x] == '2') {
          goalX = x;
          goalY = y;
        }
      }
    }
  }

  private void solve() {
    // 8. 도착점에서 역순으로 올라가기 위한 현재 좌표 설정
    int cx = goalX, cy = goalY;

    // 11. y가 0(최상단)에 도달할 때까지 반복
    while (cy > 0) {
      boolean check = true;

      // 9-1. 왼쪽 방향으로 이동 가능한지 확인하고 끝까지 이동
      for (; cx > 0 && board[cy][cx - 1] == '1'; cx--, check = false);
      
      // 9-2. 왼쪽으로 이동하지 않았을 경우에만 오른쪽 방향 확인 및 이동
      for (; check && cx < BOARD_SIZE - 1 && board[cy][cx + 1] == '1'; cx++);
      
      // 10. 한 층 위로 이동
      cy--;
    }

    // 최종 시작점 x 좌표 저장
    answer = cx;
  }
  
  private void print() throws IOException {
    // 12. 정답을 화면에 출력한다.
    writer.write("#" + testCase + " " + answer + "\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    line = new StringTokenizer(reader.readLine().trim());
  }
}