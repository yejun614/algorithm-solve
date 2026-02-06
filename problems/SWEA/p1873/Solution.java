/*
 * (1873) 상호의 배틀필드
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LyE7KD2ADFAXc&categoryId=AV5LyE7KD2ADFAXc&categoryType=CODE&problemTitle=1873&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

package swea.p1873;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * SW Expert Academy - 1873. 상호의 배틀필드
 * @author YeJun, Jung
 *
 * @see #main(String[])
 * 1. 입출력을 초기화한다.
 * 2. 테스트 케이스 개수를 입력받는다.
 * 3. 각 테스트 케이스에 대해 솔루션을 실행한다.
 * 
 * @see #Solution(int)
 * 4. 멤버 변수를 초기화한다.
 * 
 * @see #run()
 * 5. 입력, 해결, 출력 순서로 실행한다.
 * 
 * @see #input()
 * 6. 맵의 높이(H)와 너비(W)를 입력받는다.
 * 7. 맵 정보를 입력받으며 플레이어의 초기 위치를 저장한다.
 * 8. 명령어 배열을 입력받는다.
 * 
 * @see #solve()
 * 9. 입력받은 명령어를 순차적으로 수행한다.
 * 
 * @see #doShoot()
 * 10. 현재 바라보는 방향으로 포탄을 발사한다.
 *  10-1. 벽돌 벽(*)을 만나면 평지로 만들고 소멸한다.
 *  10-2. 강철 벽(#)을 만나면 아무 일 없이 소멸한다.
 * 
 * @see #doMove(char)
 * 11. 명령에 따라 방향을 바꾸고 이동 가능 시 이동한다.
 * 
 * @see #print()
 * 12. 최종 상태의 맵을 출력한다.
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;
  
  public static void main(String[] args) throws IOException {
    // 1. 입출력을 초기화한다.
    
    // 2. 테스트 케이스 개수를 입력받는다.
    final int testCount = Integer.parseInt(reader.readLine().trim());
    
    for (int testCase = 1; testCase <= testCount; testCase++) {
      // 3. 각 테스트 케이스에 대해 솔루션을 실행한다.
      new Solution(testCase).run();
    }
  }
  
  // --------------------------------------------------------
  
  private int testCase;
  
  private int height;
  private int width;
  private char[][] board;
  private char[] cmds;
  
  private int playerX;
  private int playerY;
  
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
    // 6. 맵의 높이(H)와 너비(W)를 입력받는다.
    getLine();
    height = Integer.parseInt(input.nextToken());
    width = Integer.parseInt(input.nextToken());
    
    // 7. 맵 정보를 입력받으며 플레이어의 초기 위치를 저장한다.
    board = new char[height][width];
    for (int y = 0; y < height; y++) {
      board[y] = reader.readLine().trim().toCharArray();
      
      for (int x = 0; x < width; x++) {
        if (board[y][x] < '<') continue; // 아스키 코드상 .*#- 는 ^v<> 보다 작다
        
        playerX = x;
        playerY = y;
      }
    }
    
    // 8. 명령어 배열을 입력받는다.
    reader.readLine(); // 명령어의 개수(해당 라인의 입력은 무시한다)
    cmds = reader.readLine().trim().toCharArray();
  }
  
  private void solve() {
    // 9. 입력받은 명령어를 순차적으로 수행한다.
    for (char cmd : cmds) {
      if (cmd == 'S') doShoot();
      else doMove(cmd);
    }
  }
  
  private void doShoot() {
    // 10. 현재 바라보는 방향으로 포탄을 발사한다.
    int bulletX = playerX;
    int bulletY = playerY;
    
    int deltaX = 0;
    int deltaY = 0;
    
    switch (board[playerY][playerX]) {
    case '^': deltaY--; break;
    case 'v': deltaY++; break;
    case '<': deltaX--; break;
    case '>': deltaX++; break;
    }
    
    boolean isContinue = true;
    while (isContinue && isInsideBoard(bulletX + deltaX, bulletY + deltaY)) {
      bulletX += deltaX;
      bulletY += deltaY;
      
      switch (board[bulletY][bulletX]) {
      case '*': // 10-1. 벽돌 벽(*)을 만나면 평지로 만들고 소멸한다.
        board[bulletY][bulletX] = '.';
      case '#': // 10-2. 강철 벽(#)을 만나면 아무 일 없이 소멸한다.
        isContinue = false;
        break;
      }
    }
  }
  
  private void doMove(char dir) {
    // 11. 명령에 따라 방향을 바꾸고 이동 가능 시 이동한다.
    int nextX = playerX;
    int nextY = playerY;
    char icon = board[playerY][playerX];
    
    switch (dir) {
    case 'U': nextY--; icon = '^'; break;
    case 'D': nextY++; icon = 'v'; break;
    case 'L': nextX--; icon = '<'; break;
    case 'R': nextX++; icon = '>'; break;
    }
    
    board[playerY][playerX] = icon;
    
    if (isInsideBoard(nextX, nextY) && isMovable(nextX, nextY)) {
      board[nextY][nextX] = board[playerY][playerX];
      board[playerY][playerX] = '.';
      
      playerX = nextX;
      playerY = nextY;
    }
  }
  
  private boolean isInsideBoard(int x, int y) {
    return x >= 0 && x < width && y >= 0 && y < height;
  }
  
  private boolean isMovable(int x, int y) {
    return board[y][x] == '.';
  }
  
  private void print() throws IOException {
    // 12. 최종 상태의 맵을 출력한다.
    writer.write("#" + testCase + " ");
    
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        writer.write(board[y][x]);
      }
      writer.write("\n");
    }
    
    writer.flush();
  }
  
  // --------------------------------------------------------
  
  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}