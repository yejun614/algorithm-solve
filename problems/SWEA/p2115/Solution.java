/*
 * (2115) [모의 SW 역량테스트] 벌꿀채취
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu&categoryId=AV5V4A46AdIDFAWu&categoryType=CODE&problemTitle=2115&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

package swea.p2115;

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 2115. [모의 SW 역량테스트] 벌꿀채취
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
 * 6. 벌통 크기(N), 선택 벌통 수(M), 꿀 채취 제한량(C)을 입력받는다.
 * 7. 벌통의 꿀 정보를 board 배열에 저장한다.
 * 
 * @see #solve()
 * 8. 두 명의 일꾼(Cursor)을 생성하여 각각 최적의 위치를 찾는다.
 * 
 * @see #searchBest(int)
 * 9. 현재 일꾼이 다른 일꾼과 겹치지 않는 위치 중 최대 이익을 낼 수 있는 곳을 탐색한다.
 * 10. Cursor를 이동시키며 이익을 계산한다.
 * 11. 탐색된 최적 위치를 일꾼 객체에 저장하고 전체 정답에 합산한다.
 * 
 * @see #getBestFromCursor(Cursor)
 * 12. 선택된 벌통 범위 내에서 꿀의 합이 C를 넘지 않는 부분 집합의 최대 이익을 계산한다.
 *  12-1. 부분 집합 구성을 위해 정렬 및 그리디/이진 탐색 기법을 활용한다.
 * 13. 선택된 벌통들의 제곱의 합(이익)을 계산하여 반환한다.
 * 
 * @see #print()
 * 14. 계산된 최대 이익(answer)을 출력한다.
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;
  
  public static void main(String[] args) throws IOException {
    // 1. 입출력을 초기화한다.
    
    // 2. 테스트 케이스 개수를 입력받는다.
    int testCount = Integer.parseInt(reader.readLine().trim());
    
    for (int testCase = 1; testCase <= testCount; testCase++) {
      // 3. 각 테스트 케이스에 대해 솔루션을 실행한다.
      new Solution(testCase).run();
    }
  }
  
  // --------------------------------------------------------
  
  private int testCase;
  private int answer;
  
  private int boardSize;
  private int cursorSize;
  private int maxValue;
  
  private int[][] board;
  private Cursor[] cursors;
  
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
    // 6. 벌통 크기(N), 선택 벌통 수(M), 꿀 채취 제한량(C)을 입력받는다.
    getLine();
    boardSize = Integer.parseInt(input.nextToken());
    cursorSize = Integer.parseInt(input.nextToken());
    maxValue = Integer.parseInt(input.nextToken());
    
    // 7. 벌통의 꿀 정보를 board 배열에 저장한다.
    board = new int[boardSize][boardSize];
    for (int y = 0; y < boardSize; y++) {
      getLine();
      for (int x = 0; x < boardSize; x++) {
        board[y][x] = Integer.parseInt(input.nextToken());
      }
    }
  }
  
  private void solve() {
    answer = 0;
    
    // 8. 두 명의 일꾼(Cursor)을 생성하여 각각 최적의 위치를 찾는다.
    cursors = new Cursor[2];
    cursors[0] = new Cursor();
    cursors[1] = new Cursor();
    
    for (int index = 0; index < cursors.length; index++) {
      searchBest(index);
    }
  }
  
  private void searchBest(int cursorIndex) {
    // 9. 현재 일꾼이 다른 일꾼과 겹치지 않는 위치 중 최대 이익을 낼 수 있는 곳을 탐색한다.
    Cursor myCursor = cursors[cursorIndex];
    Cursor anotherCursor = cursors[(cursorIndex + 1) % 2];
    
    int bestValue = 0;
    
    for (int y = 0; y < boardSize; y++) {
      // 10. Cursor를 이동시키며 이익을 계산한다.
      Cursor cursor = new Cursor(y, 0, cursorSize - 1);
      
      for (int x = 0; x <= boardSize - cursorSize; x++) {
        if (!cursor.isOverlap(anotherCursor)) {
          int current = getBestFromCursor(cursor);
          
          if (current > bestValue) {
            bestValue = current;
            // 11. 탐색된 최적 위치를 일꾼 객체에 저장하고 전체 정답에 합산한다.
            myCursor.set(cursor);
          }
        }
        cursor.moveRight();
      }
    }
    
    answer += bestValue;
  }
  
  private int getBestFromCursor(Cursor cursor) {
    // 12. 선택된 벌통 범위 내에서 꿀의 합이 C를 넘지 않는 부분 집합의 최대 이익을 계산한다.
    final int N = cursor.xEnd() - cursor.xBegin() + 1;
    
    int[] buffer = new int[N];
    System.arraycopy(board[cursor.y()], cursor.xBegin(), buffer, 0, N);
    Arrays.sort(buffer);
    
    int best = 0;
    
    // 12-1. 부분 집합 구성을 위해 정렬 및 그리디/이진 탐색 기법을 활용한다.
    for (int index = N - 1; index >= 0; index--) {
      List<Integer> candidates = new ArrayList<>();
      candidates.add(buffer[index]);
      
      int value = buffer[index];
      int begin = 0, end = index;
      
      while (end > 0 && candidates.size() < N && value < maxValue) {
        int nextIndex = binarySearchOrLower(buffer, begin, end, maxValue - value);
        int nextValue = buffer[nextIndex];
        
        if (value + nextValue > maxValue) break;

        value += nextValue;
        candidates.add(nextValue);
        
        end = nextIndex;
      }
      
      // 13. 선택된 벌통들의 제곱의 합(이익)을 계산하여 반환한다.
      int currentBenefit = calcBenefit(candidates);
      if (currentBenefit > best) {
        best = currentBenefit;
      }
    }
    
    return best;
  }
  
  private int calcBenefit(List<Integer> values) {
    int benefit = 0;
    for (int value : values) benefit += value * value;
    return benefit;
  }
  
  private void print() throws IOException {
    // 14. 계산된 최대 이익(answer)을 출력한다.
    writer.write("#" + testCase + " " + answer + "\n");
    writer.flush();
  }
  
  // --------------------------------------------------------
  
  private static class Cursor {
    private int y;
    private int xBegin;
    private int xEnd;
    
    public Cursor() { this(-1, -1, -1); }
    public Cursor(Cursor c) { this(c.y(), c.xBegin(), c.xEnd()); }
    public Cursor(int y, int xBegin, int xEnd) {
      this.y = y;
      this.xBegin = xBegin;
      this.xEnd = xEnd;
    }
    
    public int y() { return y; }
    public int xBegin() { return xBegin; }
    public int xEnd() { return xEnd; }
    
    public boolean isOverlap(Cursor c) {
      if (this.y != c.y()) return false;
      
      boolean result = false;
      result |= (this.xBegin > c.xBegin() && this.xBegin > c.xEnd());
      result |= (this.xEnd < c.xBegin() && this.xEnd < c.xEnd());
      return !result;
    }
    
    public void moveRight() {
      xBegin++; xEnd++;
    }
    
    public void set(Cursor c) {
      this.y = c.y();
      this.xBegin = c.xBegin();
      this.xEnd = c.xEnd();
    }
    
    @Override
    public String toString() {
      return String.format("[y=%d, xBegin=%d, xEnd=%d]", y, xBegin, xEnd);
    }
  }
  
  // --------------------------------------------------------
  
  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
  
  private static int binarySearchOrLower(int[] x, int begin, int end, int target) {
    int index = 0, value = 0;
    
    // binary search
    while(begin < end) {
      index = (begin + end) / 2;
      value = x[index];
      
      if (value < target) {
        begin = index + 1;
      } else if (value > target) {
        end = index - 1;
      } else {
        break;
      }
    }
    
    // or lower
    if (value > target && index > 0) index--;
    
    return index;
  }
}