/*
 * (4038) [Professional] 단어가 등장하는 횟수
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIoEJzarUwDFAWN&categoryId=AWIoEJzarUwDFAWN&categoryType=CODE&problemTitle=4038&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [Professional] 단어가 등장하는 횟수
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 책의 내용에서 특정 단어가 몇 번 등장하는지 카운트해야 한다.
 * 
 * [전략]
 *   - 2중 반복문은 시간복잡도가 높기 때문에 전략적으로 KMP 알고리즘을 사용해야 한다.
 *   - makePi() 함수를 사용해서 찾고자 하는 단어의 prefix, postfix를 카운트해서 각 index에 저장한다.
 *   - kmp 알고리즘을 사용해서 특정 단어가 몇 번 등장하는지 카운트한다.
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

  String book;
  String target;
  int bookLen;
  int targetLen;

  int[] pi;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    book = next();
    target = next();

    bookLen = book.length();
    targetLen = target.length();
  }

  private void solve() {
    answer = 0;

    makePi();
    kmp();
  }

  private void makePi() {
    pi = new int[targetLen];

    for (int p0 = 0, p1 = 1; p1 < targetLen; p1++) {
      while (p0 > 0 && target.charAt(p0) != target.charAt(p1)) {
        p0 = pi[p0 - 1];
      }

      if (target.charAt(p0) == target.charAt(p1)) {
        pi[p1] = ++p0;
      }
    }
  }

  private void kmp() {
    for (int p0 = 0, p1 = 0; p0 < bookLen; p0++) {
      while (p1 > 0 && book.charAt(p0) != target.charAt(p1)) {
        p1 = pi[p1 - 1];
      }

      if (book.charAt(p0) == target.charAt(p1)) {
        if (p1 == targetLen - 1) {
          answer++;
          p1 = pi[p1];
        } else {
          p1++;
        }
      }
    }
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
