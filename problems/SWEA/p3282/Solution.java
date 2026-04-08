/*
 * (3282) 0/1 Knapsack
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJAVpqrzQDFAWr&categoryId=AWBJAVpqrzQDFAWr&categoryType=CODE&problemTitle=3282&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 0/1 Knapsack
 * @author YeJun, Jung
 * 
 * [분석]
 *   - (냅색 유형) 가치와 무게가 다른 물건들을 조합하는데, 무게 제한을 넘기지 않으면서 가치가 최대가 되도록 해야한다.
 * 
 * [전략]
 *   - (가방 무게 제한) + 1 크기의 배열을 만들어 0으로 채운다.
 *   - 각 물건들을 차례로 고려하면서 (가방 무게 제한)kg ~ 0kg 까지의 가방에 해당 물건을 넣을건지, 넣지 않을건지 선택한다.
 *   - 각 물건을 고려할때마다 이전 결과를 참고하기 때문에 조합 과정에 필요한 연산을 최소화 할 수 있다.
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder output = new StringBuilder();
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    final int testCount = nextInt();

    for (int testCase = 1; testCase <= testCount; testCase++) {
      new Solution(testCase).run();
    }

    System.out.print(output.toString());
  }

  // ----------------------------------------------------------

  int testCase;
  int answer;

  int bagWeightLimit;
  int itemLen;
  int[] itemWeightArr;
  int[] itemValueArr;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    itemLen = nextInt();
    bagWeightLimit = nextInt();

    itemWeightArr = new int[itemLen];
    itemValueArr = new int[itemLen];

    for (int idx = 0; idx < itemLen; idx++) {
      itemWeightArr[idx] = nextInt();
      itemValueArr[idx] = nextInt();
    }
  }

  private void solve() {
    answer = knapsack();
  }

  private int knapsack() {
    // 초기화
    int[] mem = new int[bagWeightLimit + 1];

    // 조합 시작 (각 물건을 차례로 고려한다.)
    for (int itemIdx = 0; itemIdx < itemLen; itemIdx++) {
      // 현재 물건의 정보
      int itemWeight = itemWeightArr[itemIdx];
      int itemValue = itemValueArr[itemIdx];

      // 가방의 제한 무게를 1씩 줄이면서 현재 물건을 넣을 수 있는지 아니지 판단하여 선택한다.
      for (int bagWeight = bagWeightLimit; bagWeight >= itemWeight; bagWeight--) {
        mem[bagWeight] = Math.max(mem[bagWeight], mem[bagWeight - itemWeight] + itemValue);
      }
    }

    // 가방의 제한무게에서 최대 가치를 반환
    return mem[bagWeightLimit];
  }

  private void print() {
    output
      .append('#')
      .append(testCase)
      .append(' ')
      .append(answer)
      .append('\n');
  }

  // ----------------------------------------------------------

  static String next() throws IOException {
    if (input == null || !input.hasMoreTokens())
      input = new StringTokenizer(reader.readLine().trim());
    return input.nextToken();
  }

  static int nextInt() throws IOException {
    return Integer.parseInt(next());
  }
}
