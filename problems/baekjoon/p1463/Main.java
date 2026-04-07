/*
 * (1463) 1로 만들기
 * https://www.acmicpc.net/problem/1463
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 1로 만들기
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 정수 X가 입력되었을때 3으로 나누기, 2로 나누기, 1을 빼기 3가지 동작의 조합으로 1을 만들어야 한다.
 *   - 이때 연산의 횟수가 최소가 되도록 해야한다.
 * 
 * [전략]
 *   - DFS을 통해서 각 연산을 실시하고 최소 연산의 횟수를 cache에 기록한다.
 *   - cache에 기록된 값이 있다면 연산하지 않고 바로 cache의 값을 사용한다.
 */
public class Main {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder output = new StringBuilder();
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    new Main().run();

    System.out.print(output.toString());
  }

  // ----------------------------------------------------------

  int x;
  int answer;
  int[] cache;

  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    x = nextInt();
  }

  private void solve() {
    cache = new int[1000001];
    Arrays.fill(cache, -1);

    answer = search(x);
  }

  private int search(int num) {
    if (num == 1) return 0;
    if (cache[num] >= 0) return cache[num];

    int result = Integer.MAX_VALUE;

    if (num % 3 == 0) result = Math.min(result, 1 + search(num / 3));
    if (num % 2 == 0) result = Math.min(result, 1 + search(num / 2));
    result = Math.min(result, 1 + search(num - 1));

    return cache[num] = result;
  }

  private void print() {
    output.append(answer).append('\n');
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
