/*
 * (5215) 햄버거 다이어트 - 부분집합 풀이
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT&categoryId=AWT-lPB6dHUDFAVT&categoryType=CODE&problemTitle=5215&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

package swea.p5215v2;

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 5215. 햄버거 다이어트
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
 * 6. 재료의 수(partCount)와 제한 칼로리(limit)를 입력받는다.
 * 7. 각 재료의 점수와 칼로리 정보를 배열에 저장한다.
 * 
 * @see #solve()
 * 8. 최적 점수(bestScore)를 초기화한다.
 * 9. 비트 마스크를 이용하여 현재 선택 개수(count)에 대한 모든 부분 집합을 탐색한다.
 * 10. 선택할 재료의 개수를 1개부터 N개까지 늘려가며 부분 집합을 생성한다.
 *  10-1. 선택된 재료들의 칼로리 합과 점수 합을 계산한다.
 *  10-2. 제한 칼로리 미만이며 기존 최고 점수보다 높을 경우 갱신한다.
 * 
 * @see #print()
 * 11. 최종적으로 계산된 최대 점수를 출력한다.
 */
public class Solution {
  // 1. 입출력을 초기화한다.
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;

  public static void main(String[] args) throws IOException {
    // 2. 테스트 케이스 개수를 입력받는다.
    final int testCount = Integer.parseInt(reader.readLine().trim());

    for (int testCase = 1; testCase <= testCount; testCase++) {
      // 3. 각 테스트 케이스에 대해 솔루션을 실행한다.
      new Solution(testCase).run();
    }
  }

  // ----------------------------------------------------------

  private int testCase;
  private int bestScore;

  private int partCount;
  private int limit;
  private int[] parts;
  private int[] scores;

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
    // 6. 재료의 수(partCount)와 제한 칼로리(limit)를 입력받는다.
    getLine();
    partCount = Integer.parseInt(input.nextToken());
    limit = Integer.parseInt(input.nextToken());

    // 7. 각 재료의 점수와 칼로리 정보를 배열에 저장한다.
    scores = new int[partCount];
    parts = new int[partCount];

    for (int index = 0; index < partCount; index++) {
      getLine();
      scores[index] = Integer.parseInt(input.nextToken());
      parts[index] = Integer.parseInt(input.nextToken());
    }
  }

  private void solve() {
    // 8. 최적 점수(bestScore)를 초기화한다.
    bestScore = 0;
    
    // 9. 비트 마스크를 이용하여 현재 선택 개수(count)에 대한 모든 부분 집합을 탐색한다.
    int selects = 0;
    final int last = 1 << partCount;

    // 10. 선택할 재료의 개수를 1개부터 N개까지 늘려가며 부분 집합을 생성한다.
    for (; selects != last; selects++) {
		int currentValue = 0;
	    int currentScore = 0;
	
	    // 10-1. 선택된 재료들의 칼로리 합과 점수 합을 계산한다.
	    for (int index = 0; index < partCount; index++) {
	      if ((selects & (1 << index)) == 0) continue;
	      currentValue += parts[index];
	      currentScore += scores[index];
	    }
	
	    // 10-2. 제한 칼로리 미만이며 기존 최고 점수보다 높을 경우 갱신한다.
	    if (currentValue < limit && currentScore > bestScore) {
	      bestScore = currentScore;
	    }
    }
  }

  private void print() throws IOException {
    // 11. 최종적으로 계산된 최대 점수를 출력한다.
    writer.write("#" + testCase);
    writer.write(" " + bestScore);
    writer.write("\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}