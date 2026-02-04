/*
 * (4130) [모의 SW 역량테스트] 특이한 자석
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWJfhgr6DTQDFAXc&categoryId=AWJfhgr6DTQDFAXc&categoryType=CODE&problemTitle=4130&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

package swea.p4130;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * SW Expert Academy - 4130. [모의 SW 역량테스트] 특이한 자석
 * @author YeJun. Jung
 *
 * @see #main(String[])
 * 1. 입출력을 초기화한다.
 * 2. 전체 테스트 케이스 개수를 입력받는다.
 * 3. 각 테스트 케이스에 대해 솔루션을 인스턴스화하고 실행한다.
 *
 * @see #Solution(int)
 * 4. 현재 테스트 케이스 번호를 멤버 변수에 저장한다.
 *
 * @see #run()
 * 5. 입력(input), 해결(solve), 출력(print) 로직을 순차적으로 수행한다.
 *
 * @see #input()
 * 6. 회전 명령 횟수를 commandCount에 저장한다.
 * 7. 4개 자석의 8개 톱날 정보를 입력받아 gears 배열에 저장한다 (0:N, 1:S).
 *
 * @see #solve()
 * 8. 점수와 기어 회전 상태를 초기화한다.
 * 9. 입력된 명령 횟수만큼 반복하며 회전 시뮬레이션을 수행한다.
 *  9-1. 회전할 기어 번호와 방향을 입력받아 rotateGears를 호출한다.
 * 10. 모든 회전이 끝난 후 최종 점수를 계산한다.
 *
 * @see #rotateGears(int, char)
 * 11. 현재 명령으로 인해 영향을 받는 모든 기어의 회전 방향을 결정한다.
 *  11-1. 기준 기어로부터 왼쪽 방향으로 연쇄 회전 여부를 체크한다.
 *  11-2. 기준 기어로부터 오른쪽 방향으로 연쇄 회전 여부를 체크한다.
 *  11-3. 맞닿은 극이 다를 경우 인접 기어는 반대 방향으로 회전한다.
 * 12. 결정된 회전 방향에 따라 각 기어의 12시 방향 인덱스(gearRotates)를 갱신한다.
 *
 * @see #getDeltaGear(int, int)
 * 13. 현재 12시 방향 인덱스를 기준으로 상대적 위치(delta)에 있는 톱날의 극 정보를 반환한다.
 *
 * @see #updateScore()
 * 14. 각 기어의 12시 방향(gearRotates)이 S극인 경우 비트 연산을 통해 점수를 누적한다.
 */
public class Solution {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer line;

    public static void main(String[] args) throws IOException {
        // 1. 입출력을 초기화한다.
        // 2. 전체 테스트 케이스 개수를 입력받는다.
        int testCount = Integer.parseInt(reader.readLine().trim());

        for (int testCase = 1; testCase <= testCount; testCase++) {
            // 3. 각 테스트 케이스에 대해 솔루션을 인스턴스화하고 실행한다.
            new Solution(testCase).run();
        }
    }

    private static void getLine() throws IOException {
        line = new StringTokenizer(reader.readLine().trim());
    }

    // --------------------------------------------------------

    private int testCase;
    private int score;
    private int commandCount;
    private char[][] gears; // 4개 기어에 대해서 N혹은 S값이 저장된다
    private int[] gearRotates; // 현재 빨간색 화살표(12시)가 가리키는 기어의 톱날 Index

    public Solution(int testCase) {
        // 4. 현재 테스트 케이스 번호를 멤버 변수에 저장한다.
        this.testCase = testCase;
    }

    public void run() throws IOException {
        // 5. 입력(input), 해결(solve), 출력(print) 로직을 순차적으로 수행한다.
        input();
        solve();
        print();
    }

    private void input() throws IOException {
        // 6. 회전 명령 횟수를 commandCount에 저장한다.
        commandCount = Integer.parseInt(reader.readLine().trim());

        // 7. 4개 자석의 8개 톱날 정보를 입력받아 gears 배열에 저장한다.
        gears = new char[4][8]; // 4개 기어, 8개 톱날

        for (int index = 0; index < 4; index++) {
            getLine();
            for (int count = 0; count < 8; count++) {
                gears[index][count] = line.nextToken().charAt(0) == '0' ? 'N' : 'S';
            }
        }
    }

    private void solve() throws IOException {
        // 8. 점수와 기어 회전 상태를 초기화한다.
        score = 0;
        gearRotates = new int[4];

        // 9. 입력된 명령 횟수만큼 반복하며 회전 시뮬레이션을 수행한다.
        for (int command = 0; command < commandCount; command++) {
            getLine();
            // 9-1. 회전할 기어 번호(0-3)와 방향(1:시계, -1:반시계)을 입력받는다.
            int gearIndex = Integer.parseInt(line.nextToken()) - 1;
            char rotateDir = line.nextToken().charAt(0) == '1' ? '+' : '-';

            rotateGears(gearIndex, rotateDir);
        }

        // 10. 모든 회전이 끝난 후 최종 점수를 계산한다.
        updateScore();
    }

    private void rotateGears(int gearIndex, char rotateDir) {
        // 11. 현재 명령으로 인해 영향을 받는 모든 기어의 회전 방향을 결정한다.
        char[] currentRotates = new char[4]; // 시계(+), 반시계(-), 중립(\0)
        currentRotates[gearIndex] = rotateDir;

        // 11-1. 기준 기어로부터 왼쪽 방향으로 연쇄 회전 여부를 체크한다.
        for (int gear = gearIndex - 1; gear >= 0; gear--) {
            char rightRotateDir = currentRotates[gear + 1];
            if (rightRotateDir == '\0') break;

            char leftSideOfRightGear = getDeltaGear(gear + 1, -2); // 오른쪽 기어의 9시 방향
            char rightSideOfLeftGear = getDeltaGear(gear, 2);      // 왼쪽 기어의 3시 방향

            if (leftSideOfRightGear != rightSideOfLeftGear) {
                currentRotates[gear] = rightRotateDir == '+' ? '-' : '+';
            } else break;
        }

        // 11-2. 기준 기어로부터 오른쪽 방향으로 연쇄 회전 여부를 체크한다.
        for (int gear = gearIndex + 1; gear < 4; gear++) {
            char leftRotateDir = currentRotates[gear - 1];
            if (leftRotateDir == '\0') break;

            char rightSideOfLeftGear = getDeltaGear(gear - 1, 2);  // 왼쪽 기어의 3시 방향
            char leftSideOfRightGear = getDeltaGear(gear, -2);     // 오른쪽 기어의 9시 방향

            if (rightSideOfLeftGear != leftSideOfRightGear) {
                currentRotates[gear] = leftRotateDir == '+' ? '-' : '+';
            } else break;
        }

        // 12. 결정된 회전 방향에 따라 각 기어의 12시 방향 인덱스를 갱신한다.
        for (int gear = 0; gear < 4; gear++) {
            if (currentRotates[gear] != '\0') {
                gearRotates[gear] = getRotationIndex(gear, currentRotates[gear]);
            }
        }
    }

    private char getDeltaGear(int gearIndex, int delta) {
        // 13. 현재 12시 방향 인덱스를 기준으로 상대적 위치(delta)에 있는 톱날 극 반환
        int x = gearRotates[gearIndex] + delta;

        // 인덱스 보정 (0~7 범위를 벗어날 경우)
        if (x < 0) x += 8;
        x %= 8;

        return gears[gearIndex][x];
    }

    private int getRotationIndex(int gearIndex, char rotateDir) {
        int result = gearRotates[gearIndex];

        switch (rotateDir) {
            case '-': // 반시계 회전: 12시 방향 인덱스가 시계 방향으로 이동
                result = (result + 1) % 8;
                break;
            case '+': // 시계 회전: 12시 방향 인덱스가 반시계 방향으로 이동
                if (--result < 0) result = 7;
                break;
        }

        return result;
    }

    private void updateScore() {
        // 14. 각 기어의 12시 방향이 S극인 경우 점수를 누적한다 (1, 2, 4, 8점).
        for (int gear = 0; gear < 4; gear++) {
            if (gears[gear][gearRotates[gear]] == 'S') {
                score += (1 << gear);
            }
        }
    }

    private void print() throws IOException {
        writer.write("#" + testCase + " " + score + "\n");
        writer.flush();
    }
}
