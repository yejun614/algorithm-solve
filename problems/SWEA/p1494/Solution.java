/*
 * (1494) 사랑의 카운슬러
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV2b_WPaAEIBBASw&categoryId=AV2b_WPaAEIBBASw&categoryType=CODE&problemTitle=1494&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 사랑의 카운슬러
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 2차원 위에 있는 서로 다른 정점들을 짝지어 벡터들로 만든다.
 *   - 벡터들을 모두 더하여 크기를 구했을때 최솟값이 되도록 짝지어야 한다.
 *   - 이때, 벡터의 방향 (A --> B), (B --> A) 은 어느쪽을 선택해도 벡터의 크기는 동일하여 무관하다.
 * 
 * [결국은 수학문제]
 *   - (A --> B) 벡터는 (P𝘣 - P𝘢) 로 나타낼 수 있다. (점 b에서 점a를 뺀 좌표)
 *   - 또한 서로 짝지어진 벡터들의 합은 Σ(A𝒾 --> B𝒾) 로 나타날 수 있다.
 *   - V𝒾 = (A𝒾 --> B𝒾)
 *   - V₁ + V₂, ..., V𝑛
 *   - Σ(V𝒾) = Σ(A𝒾 --> B𝒾) = Σ(P𝒾{𝘣} - P𝒾{𝘢})
 *   - (P₁{𝘣} - P₁{𝘢}) + (P₂{𝘣} - P₂{𝘢}) + ... + (P𝑛{𝘣} - P𝑛{𝘢})
 *   - (P₁{𝘣} + P₂{𝘣} + P𝑛{𝘣}) - (P₁{𝘢} + P₂{𝘢} + P𝑛{𝘢})
 *   - (종점들의 합 좌표) - (시점들의 합 좌표) = Σ𝘣 - Σ𝘢
 *   - 결국, 짝지어진 벡터들의 합은 (Σ𝘣 - Σ𝘢) 로 표현되며 정답은 min(combination(Σ𝘣 - Σ𝘢)) 이 된다.
 * 
 * [전략]
 *   - 입력받은 좌표들을 그룹A와 그룹B로 절반씩 나누어야 한다.
 *   - 이때 어느 좌표가 어떤 그룹에 속할지 조합을 nextPermutation으로 생성한다.
 *   - 위에서 정리한대로 그룹A는 더하고 그룹B는 빼주어 "짝지어진 벡터들의 합" 벡터를 계산한다.
 *   - 계산한 벡터의 크기를 구하고 최솟값이면 정답변수를 업데이트 한다.
 * 
 * [구현 팁]
 *   - nextPermutaion 과정에서 순열 생성 범위를 [s, e]로 제한할 수 있게 하여 생성해야하는 조합의 개수를 줄였다.
 *   - 입력된 좌표들중 하나의 좌표는 그룹을 고정해도 무관하기 때문이다.
 *   - 그래서 [2, N] 범위 안에서 조합을 생성했다.
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
  long answer;

  int posLen;
  long[][] posArr;

  int[] select;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    posLen = nextInt();
    posArr = new long[posLen][2];

    for (int idx = 0; idx < posLen; idx++) {
      posArr[idx][0] = nextLong();
      posArr[idx][1] = nextLong();
    }
  }

  private void solve() {
    answer = Long.MAX_VALUE;
    comb();
  }

  private void comb() {
    select = new int[posLen];
    Arrays.fill(select, posLen / 2, posLen, 1);

    do {
      answer = Math.min(answer, getVecSize());
    } while (nextPermutation(select, 1, posLen - 1));
  }

  private long getVecSize() {
    long x = 0;
    long y = 0;

    for (int idx = 0; idx < posLen; idx++) {
      if (select[idx] == 0) {
        x -= posArr[idx][0];
        y -= posArr[idx][1];
      } else {
        x += posArr[idx][0];
        y += posArr[idx][1];
      }
    }

    return (x * x) + (y * y);
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

  static long nextLong() throws IOException {
    return Long.parseLong(next());
  }

  // ----------------------------------------------------------

  static boolean nextPermutation(int[] x, int s, int e) {
    int a = e, b = e;
    while (a > s && x[a - 1] >= x[a]) a--;
    if (a == s) return false;
    while (x[a - 1] >= x[b]) b--;
    swap(x, a - 1, b); reverse(x, a, e);
    return true;
  }

  static void swap(int[] x, int a, int b) {
    int t = x[a]; x[a] = x[b]; x[b] = t;
  }

  static void reverse(int[] x, int a, int b) {
    while (a < b) swap(x, a++, b--);
  }
}
