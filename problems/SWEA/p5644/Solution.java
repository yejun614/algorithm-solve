/*
 * (5644) [모의 SW 역량테스트] 무선 충전
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRDL1aeugDFAUo&categoryId=AWXRDL1aeugDFAUo&categoryType=CODE&problemTitle=5644&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 5644. [모의 SW 역량테스트] 무선 충전
 * @author YeJun, Jung
 * 
 * [전략]
 *   - 문제에서 제시된 조건에 따라 시뮬레이션을 충실히 수행한다.
 *   - OOP를 적절히 사용하여 개발과정에서 실수를 줄인다.
 * 
 * [주요 코드]
 *   - input()
 *     - BC를 입력받아 towerArr에 저장한다.
 *       - 입력이 끝난후 towerArr 마지막에 더미 BC를 하나 추가한다.
 *       - 시뮬레이션 과정에서 사람이 BC 범위 밖에 있을때 더미 BC를 가르킨다.
 *   - solve()
 *     - 두 사람의 초기위치를 설정한 후, 시뮬레이션을 시작한다.
 *   - runSimuration()
 *     - 먼저 BC에서 충전한 에너지를 반영하고, 이동한다.
 *     - 시뮬레이션 종료 후 마지막 위치에서의 에너지도 반영한다.
 *   - updateEnergy()
 *     - 두 사람이 밟고 있는 BC는 towerCheck 배열에 true로 표시된다.
 *       - 밟고 있는 BC가 없으면 더미BC가 true 된다.
 *     - 2중 반복문을 통해 두 사람이 밟고 있는 BC 중에서 하나씩 선택한다.
 *       - 두 사람이 동일한 BC를 밟고 있다면 받는 에너지가 절반이 된다.
 *       - 가능한 최대 에너지를 받을 수 있는 BC가 선택된다.
 *   - moving(int t)
 *     - t 시간에 움직인 방향으로 사람들을 이동시킨다.
 *   - isInsideTower(int personIdx, int towerIdx)
 *     - 문제에서 제시된 거리 공식에 따라 파라미터로 입력된 사람이
 *       BC 범위에 있는지 검사한다.
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    final int testCount = Integer.parseInt(reader.readLine().trim());

    for (int testCase = 1; testCase <= testCount; testCase++) {
      new Solution(testCase).run();
    }
  }

  // ----------------------------------------------------------

  final static int[] DIR_X = {  0,  0,  1,  0, -1 };
  final static int[] DIR_Y = {  0, -1,  0,  1,  0 };
  final static int DIR_LEN = 5;

  final static int[] BC_AREA_X = {  0,  1,  1,  1,  0, -1, -1, -1 };
  final static int[] BC_AREA_Y = { -1, -1,  0,  1,  1,  1,  0, -1 };
  final static int BC_AREA_LEN = 8;

  final static int BOARD_SIZE = 10;
  final static int MAX_TOWER_COUNT = 8;
  final static int PERSON_COUNT = 2;

  int testCase;
  int energy; // answer
  int totalMovingTime;
  int towerCount;
  char[][] move;
  Tower[] towerArr;
  Pos[] people;

  public Solution(int testCase) {
    this.testCase = testCase;
  }

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  public void input() throws IOException {
    getLine();
    totalMovingTime = Integer.parseInt(input.nextToken());
    towerCount = Integer.parseInt(input.nextToken());

    move = new char[2][totalMovingTime];
    for (int person = 0; person < PERSON_COUNT; person++) {
      getLine();

      for (int t = 0; t < totalMovingTime; t++) {
        move[person][t] = input.nextToken().charAt(0);
      }
    }

    towerArr = new Tower[towerCount + 1];
    for (int index = 0; index < towerCount; index++) {
      towerArr[index] = Tower.fromReader();
    }
    towerArr[towerCount] = new Tower(-1, -1, 0, 0);
  }

  public void solve() {
    energy = 0;

    initPeople();
    runSimutation();
  }

  private void initPeople() {
    people = new Pos[2];

    people[0] = new Pos(0, 0);
    people[1] = new Pos(BOARD_SIZE - 1, BOARD_SIZE - 1);
  }

  private void runSimutation() {
    for (int t = 0; t < totalMovingTime; t++) {
      updateEnergy();
      moving(t);
    }
    updateEnergy();
  }

  private void updateEnergy() {
    int bestEnergy = 0;
    boolean[][] towerCheck = new boolean[PERSON_COUNT][towerCount + 1];

    for (int person = 0; person < PERSON_COUNT; person++) {
      int x = people[person].x;
      int y = people[person].y;
      boolean hasTower = false;

      for (int tower = 0; tower < towerCount; tower++) {
        if (!isInsideTower(person, tower)) continue;

        towerCheck[person][tower] = true;
        hasTower = true;
      }

      if (!hasTower) {
        towerCheck[person][towerCount] = true;
      }
    }

    for (int selectA = 0; selectA < towerCount + 1; selectA++) {
      if (!towerCheck[0][selectA]) continue;
      Tower towerA = towerArr[selectA];

      for (int selectB = 0; selectB < towerCount + 1; selectB++) {
        if (!towerCheck[1][selectB]) continue;
        Tower towerB = towerArr[selectB];

        int currentEnergy = towerA.power + towerB.power;
        if (selectA == selectB) currentEnergy /= 2;

        if (currentEnergy > bestEnergy) {
          bestEnergy = currentEnergy;
        }
      }
    }

    energy += bestEnergy;
  }

  private void moving(int t) {
    for (int person = 0; person < PERSON_COUNT; person++) {
      int dir = move[person][t] - '0';

      people[person].x += DIR_X[dir];
      people[person].y += DIR_Y[dir];
    }
  }

  private boolean isInsideTower(int personIdx, int towerIdx) {
    Pos person = people[personIdx];
    Tower tower = towerArr[towerIdx];

    int distance = Math.abs(person.x - tower.x) + Math.abs(person.y - tower.y);

    return distance <= tower.size;
  }

  public void print() throws IOException {
    writer.write("#" + testCase);
    writer.write(" " + energy);
    writer.write("\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static class Tower {
    int x;
    int y;
    int size;
    int power;

    public Tower(int x, int y, int size, int power) {
      this.x = x;
      this.y = y;
      this.size = size;
      this.power = power;
    }

    public static Tower fromReader() throws IOException {
      getLine();

      int x = Integer.parseInt(input.nextToken()) - 1;
      int y = Integer.parseInt(input.nextToken()) - 1;
      int size = Integer.parseInt(input.nextToken());
      int power = Integer.parseInt(input.nextToken());

      return new Tower(x, y, size, power);
    }
  }

  // ----------------------------------------------------------

  private static class Pos {
    int x;
    int y;

    public Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
