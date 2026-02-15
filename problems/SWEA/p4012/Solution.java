/*
 * (4012) [모의 SW 역량테스트] 요리사
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeUtVakTMDFAVH&categoryId=AWIeUtVakTMDFAVH&categoryType=CODE&problemTitle=4012&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 4012. [모의 SW 역량테스트] 요리사
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 주어진 식재료 중에서 N/2를 선택해서 조합한다.
 *   - 식재료를 조합해서 시너지를 합친다.
 *   - 두 요리의 시너지 차이의 최솟값을 출력한다.
 * 
 * [전략]
 *   - 조합을 위해 prevPermutation 메서드를 사용한다.
 *   - getFoodScore 메서드에서 2중 반복문을 통해 사용한 식재료의 시너지 합을 계산한다.
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

  int testCase;
  int answer;
  int matSize;
  int[][] mat;
  int[] select;

  public Solution(int testCase) {
    this.testCase = testCase;
  }

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    matSize = Integer.parseInt(reader.readLine().trim());
    mat = new int[matSize][matSize];

    for (int y = 0; y < matSize; y++) {
      getLine();

      for (int x = 0; x < matSize; x++) {
        mat[y][x] = Integer.parseInt(input.nextToken());
      }
    }
  }

  private void solve() {
    answer = Integer.MAX_VALUE;
    select = new int[matSize];

    Arrays.fill(select, 0, matSize / 2, 1);

    do {
      answer = Math.min(answer, search());
    } while (prevPermutation(select));
  }

  private int search() {
    int foodA = getFoodScore(0);
    int foodB = getFoodScore(1);

    return Math.abs(foodA - foodB);
  }

  private int getFoodScore(int food) {
    int result = 0;

    for (int A = 0; A < matSize; A++) {
      if (select[A] != food) continue;
      for (int B = 0; B < matSize; B++) {
        if (select[B] != food) continue;

        result += mat[A][B];
      }
    }

    return result;
  }

  private void print() throws IOException {
    writer.write("#" + testCase);
    writer.write(" " + answer);
    writer.write("\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }

  private static boolean prevPermutation(int[] x) {
    int n = x.length - 1, a = n, b = n;
    while (a > 0 && x[a - 1] <= x[a]) a--;
    if (a == 0) return false;
    while (x[a - 1] <= x[b]) b--;
    swap(x, a - 1, b); reverse(x, a, n);
    return true;
  }

  private static void swap(int[] x, int a, int b) {
    int t = x[a]; x[a] = x[b]; x[b] = t;
  }

  private static void reverse(int[] x, int a, int b) {
    while (a < b) swap(x, a++, b--);
  }
}
