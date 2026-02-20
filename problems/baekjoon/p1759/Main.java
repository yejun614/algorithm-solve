/*
 * (1759) 암호 만들기
 * https://www.acmicpc.net/problem/1759
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 암호 만들기
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 주어진 알파벳으로 조건을 만족하는 모든 조합을 만들어야 한다.
 *   - 정답을 출력할때는 사전순으로 정렬되어야 한다.
 * 
 * [전략]
 *   - 입력된 알파벳 배열을 사전순으로 정렬하여, 조합 과정에서 사전순으로 암호문이 생성되도록 한다.
 *   - prevPermutation을 응용하여 조합을 생성한다.
 *   - switch ~ case 구문으로 모음 개수와 자음 개수를 카운트 하여 암호문 조건을 확인한다.
 */
public class Main {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    new Main().run();
  }

  // ----------------------------------------------------------

  int strLen;
  int charLen;
  String[] charArr;
  int[] select;

  public void run() throws IOException {
    input();
    solve();

    writer.flush();
  }

  private void input() throws IOException {
    getLine();
    strLen = Integer.parseInt(input.nextToken());
    charLen = Integer.parseInt(input.nextToken());

    getLine();
    charArr = new String[charLen];
    for (int index = 0; index < charLen; index++) {
      charArr[index] = String.valueOf(input.nextToken().charAt(0));
    }
  }

  private void solve() throws IOException {
    select = new int[charLen];
    Arrays.fill(select, 0, strLen, 1);

    Arrays.sort(charArr);

    makeCombination();
  }

  private void makeCombination() throws IOException {
    StringBuilder str;

    do {
      str = new StringBuilder();

      for (int index = 0; index < charLen; index++) {
        if (select[index] == 0) continue;
        str.append(charArr[index]);
      }

      if (isCorrect(str)) {
        print(str);
      }
    } while (prevPermutation(select));
  }

  private boolean isCorrect(StringBuilder str) {
    int c0 = 0; // 모음(a, i, u, e, o)
    int c1 = 0; // 자음
    int length = str.length();

    for (int index = 0; index < length; index++) {
      switch (str.charAt(index)) {
      case 'a':
      case 'i':
      case 'u':
      case 'e':
      case 'o':
        c0++;
        break;
      default:
        c1++;
      }
    }

    return c0 >= 1 && c1 >= 2;
  }

  private void print(StringBuilder str) throws IOException {
    str.append("\n");
    writer.write(str.toString());
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
