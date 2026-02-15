/*
 * (1654) 랜선 자르기
 * https://www.acmicpc.net/problem/1654
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 랜선 자르기
 * @author YeJun, Jung
 * 
 * [전략]
 *   - 입력받은 랜선 중 최대값 ~ 0까지 이분탐색하면서 최적의 값을 찾아나간다.
 *   - 이분탐색을 통해 순차탐색보다 시간복잡도 O(logN)으로 최적의 길이를 찾을 수 있다.
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

  long answer;
  int lineCount;
  long needsLen;
  long[] lineArr;

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    getLine();
    lineCount = Integer.parseInt(input.nextToken());
    needsLen = Long.parseLong(input.nextToken());

    lineArr = new long[lineCount];

    for (int index = 0; index < lineCount; index++) {
      lineArr[index] = Long.parseLong(reader.readLine().trim());
    }
  }

  private void solve() {
    answer = binarySearch();
  }

  private long binarySearch() {
    long low = 1;
    long mid = 0;
    long high = lineArr[0];

    for (int index = 1; index < lineCount; index++) {
      if (lineArr[index] > high) high = lineArr[index];
    }

    while (low <= high) {
      mid = (low + high) / 2;

      if (countSplitLine(mid) >= needsLen) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return high;
  }

  private long countSplitLine(long value) {
    long result = 0;

    for (long item : lineArr) {
      result += item / value;
    }

    return result;
  }

  private void print() throws IOException {
    writer.write(answer + "\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
