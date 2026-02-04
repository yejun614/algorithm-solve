/*
 * (6808) 규영이와 인영이의 카드게임
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWgv9va6HnkDFAW0&categoryId=AWgv9va6HnkDFAW0&categoryType=CODE&problemTitle=6808&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

package swea.p6808;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * SW Expert Academy - 6808. 규영이와 인영이의 카드게임
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
 * 6. 규영이가 내는 카드의 순서(cardOrder)를 입력받는다.
 *
 * @see #solve()
 * 7. 규영이의 카드를 제외한 나머지 카드로 인영이의 카드(myCards)를 구성한다.
 * 8. 인영이가 카드를 내는 모든 순열 조합을 생성하기 위해 인덱스 배열(order)을 준비한다.
 * 9. nextPermutation을 이용하여 인영이의 카드 순서를 바꾼다.
 * 10. 각 게임 라운드마다 점수를 계산한다.
 *  10-1. 인영이의 카드 숫자가 크면 인영이의 점수를 합산한다.
 *  10-2. 규영이의 카드 숫자가 크면 규영이의 점수를 합산한다(score 변수 차감).
 * 11. 최종 점수가 양수면 인영이의 승리(winCount), 음수면 인영이의 패배(loseCount)를 카운트한다.
 *
 * @see #setupMyCards()
 * 12. 비트마스크를 활용하여 1~18번 카드 중 규영이가 사용하지 않은 카드를 myCards에 저장한다.
 *
 * @see #nextPermutation(int[])
 * 13. 현재 배열의 상태를 사전순으로 나열했을 때의 다음 순열로 변환한다.
 *
 * @see #print()
 * 14. 규영이의 패배 횟수(인영의 승리)와 승리 횟수(인영의 패배)를 출력한다.
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
      // 3. 각 테스트 케이스에 대해 솔루션을 실행한다.
      new Solution(testCase).run();
    }
  }

  // ----------------------------------------------------------

  private static final int CARD_LEN = 9;

  private int testCase;
  private int[] cardOrder; // 규영이의 카드 순서
  private int[] myCards;   // 인영이가 가진 카드 목록

  private int loseCount;   // 인영이가 지는 횟수 (규영이가 이기는 횟수)
  private int winCount;    // 인영이가 이기는 횟수 (규영이가 지는 횟수)

  public Solution(int testCase) {
    // 4. 현재 테스트 케이스 번호를 멤버 변수에 저장한다.
    this.testCase = testCase;
  }

  public void run() throws IOException {
    // 5. 입력, 해결, 출력 순서로 실행한다.
    input();
    solve();
    print();
  }

  public void input() throws IOException {
    // 6. 규영이가 내는 카드의 순서를 입력받는다.
    cardOrder = new int[CARD_LEN];

    getLine();
    for (int index = 0; index < CARD_LEN; index++) {
      cardOrder[index] = Integer.parseInt(line.nextToken());
    }
  }

  public void solve() {
    winCount = 0;
    loseCount = 0;

    // 7. 인영이의 카드 목록을 설정한다.
    setupMyCards();

    // 8. 모든 순열을 탐색하기 위한 인덱스 배열 준비
    int[] order = IntStream.rangeClosed(0, CARD_LEN - 1).toArray();

    // 9. nextPermutation을 사용하여 모든 가능한 인영이의 카드 제출 순서를 확인한다.
    do {
      int score = 0;
      int currentSum;

      // 10. 한 게임(9라운드) 진행
      for (int index = 0; index < CARD_LEN; index++) {
        currentSum = cardOrder[index] + myCards[order[index]];

        if (cardOrder[index] < myCards[order[index]]) {
          // 10-1. 인영이의 카드 숫자가 더 큰 경우 점수 합산
          score += currentSum;
        } else if (cardOrder[index] > myCards[order[index]]) {
          // 10-2. 규영이의 카드 숫자가 더 큰 경우 (음수 처리로 규영이 점수 관리)
          score -= currentSum;
        }
      }

      // 11. 게임 결과 카운트
      if (score > 0) {
        winCount++; // 인영 승
      } else if (score < 0) {
        loseCount++; // 인영 패 (규영 승)
      }
    } while (nextPermutation(order));
  }

  private void setupMyCards() {
    // 12. 비트마스크를 활용하여 인영이의 카드를 할당한다.
    myCards = new int[CARD_LEN];

    int mask = 0;
    for (int card : cardOrder) mask |= (1 << card);

    for (int count = 1, index = 0; index < CARD_LEN; count++) {
      if ((mask & (1 << count)) != 0) continue;
      myCards[index++] = count;
    }
  }

  public void print() throws IOException {
    // 14. 규영이의 승리 횟수와 패배 횟수를 형식에 맞춰 출력한다.
    // 주의: 문제 요구사항에 따라 규영이 기준(loseCount, winCount 순서)으로 출력
    writer.write("#" + testCase + " " + loseCount + " " + winCount + "\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    line = new StringTokenizer(reader.readLine().trim());
  }

  /**
   * 13. 다음 순열을 찾는 알고리즘 (Next Permutation)
   */
  private static boolean nextPermutation(int[] x) {
    int n = x.length - 1, a = n, b = n;
    // 1. 뒤에서부터 x[i-1] < x[i]인 지점(a)을 찾는다.
    while (a > 0 && x[a - 1] >= x[a]) a--;
    if (a == 0) return false;
    
    // 2. 다시 뒤에서부터 x[a-1]보다 큰 x[b]를 찾는다.
    while (x[a - 1] >= x[b]) b--;
    
    // 3. x[a-1]과 x[b]를 교환하고 a 이후의 배열을 뒤집는다.
    swap(x, a - 1, b); 
    reverse(x, a, n);
    return true;
  }

  private static void reverse(int[] x, int a, int b) {
    while (a < b) swap(x, a++, b--);
  }

  private static void swap(int[] x, int a, int b) {
    int t = x[a]; x[a] = x[b]; x[b] = t;
  }
}