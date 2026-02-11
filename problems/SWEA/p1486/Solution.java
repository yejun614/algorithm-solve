/*
 * (1486) 장훈이의 높은 선반
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV2b7Yf6ABcBBASw&categoryId=AV2b7Yf6ABcBBASw&categoryType=CODE&problemTitle=1486&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 1486. 장훈이의 높은 선반
 * @author YeJun, Jung
 * 
 * @see #main(String[] args)
 * 1. 테스트케이스를 입력받는다.
 * 2. 솔루션을 실행한다.
 * 
 * @see #input()
 * 3. 직원 명 수와 목표 높이를 입력받는다.
 * 4. 직원의 키와 키의 합을 저장할 변수를 초기화 한다.
 * 5. 직원들의 키를 입력받아 저장하면서 전체 직원 키의 합을 계산한다.
 * 
 * @see #solve()
 * 6. 멤버변수를 초기화한다.
 * 7. 직원의 부분집합으로 가능한 조합을 시도해본다.
 * 
 * @see #search()
 * 8. 최대 직원수가 20명까지 이므로 int 타입의 비트마스크를 사용한다.
 * 9. 직원의 부분집합을 생성하면서...
 *   9-1. 현재 조합이 목표 높이보다 크면서, 지금까지의 최적값보다 작으면
 *        현재 값을 최적값으로 업데이트 한다.
 * 
 * @see #print()
 * 10. 최적값과 목표값을 뺀 값을 화면에 출력한다.
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    // 1. 테스트케이스를 입력받는다.
    final int testCount = Integer.parseInt(reader.readLine().trim());

    for (int testCase = 1; testCase <= testCount; testCase++) {
      // 2. 솔루션을 실행한다.
      new Solution(testCase).run();
    }
  }

  // ----------------------------------------------------------

  int testCase;
  int bestHeight;

  int goalHeight;
  int peopleLen;
  int heightSum;
  int[] people;

  public Solution(int testCase) {
    this.testCase = testCase;
  }

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  public void input() throws IOException {
    // 3. 직원 명 수와 목표 높이를 입력받는다.
    getLine();
    peopleLen = Integer.parseInt(input.nextToken());
    goalHeight = Integer.parseInt(input.nextToken());

    // 4. 직원의 키와 키의 합을 저장할 변수를 초기화 한다.
    heightSum = 0;
    people = new int[peopleLen];

    // 5. 직원들의 키를 입력받아 저장하면서 전체 직원 키의 합을 계산한다.
    getLine();
    for (int index = 0; index < peopleLen; index++) {
      people[index] = Integer.parseInt(input.nextToken());
      heightSum += people[index];
    }
  }

  public void solve() {
    // 6. 멤버변수를 초기화한다.
    bestHeight = heightSum;

    // 7. 직원의 부분집합으로 가능한 조합을 시도해본다.
    search();
  }

  private void search() {
    // 8. 최대 직원수가 20명까지 이므로 int 타입의 비트마스크를 사용한다.
    int mask = 1;
    final int last = 1 << peopleLen;

    // 9. 직원의 부분집합을 생성하면서...
    for (; mask != last; mask++) {
      int current = 0;

      for (int index = 0; index < peopleLen; index++) {
        if ((mask & (1 << index)) == 0) continue;
        current += people[index];
      }

      // 9-1. 현재 조합이 목표 높이보다 크면서, 지금까지의 최적값보다 작으면
      //      현재 값을 최적값으로 업데이트 한다.
      if (current >= goalHeight && current < bestHeight) {
        bestHeight = current;
      }
    }
  }

  public void print() throws IOException {
    // 10. 최적값과 목표값을 뺀 값을 화면에 출력한다.
    
    int answer = bestHeight - goalHeight;

    writer.write("#" + testCase);
    writer.write(" " + answer);
    writer.write("\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
