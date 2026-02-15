/*
 * (2839) 설탕 배달
 * https://www.acmicpc.net/problem/2839
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 설탕 배달
 * @author YeJun, Jung
 * 
 * [분석]
 *   - N kg의 설탕을 3kg, 5kg 봉지에 나누어 담을 때, 봉지의 개수를 최소화해야 한다.
 *   - 정확하게 N kg을 만들 수 없다면 -1을 출력한다.
 * 
 * [전략]
 *   - 5kg 봉지의 개수(kg5)를 0부터 최대 가능한 수(num / 5)까지 순회하며 완전 탐색한다.
 *   - 남은 무게(num - 5 * kg5)가 3으로 나누어떨어지는지 확인한다.
 *   - 조건을 만족하는 경우 중 총 봉지 수(kg5 + kg3)의 최솟값을 answer에 갱신한다.
 */
public class Main {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    new Main().run();
  }

  // ----------------------------------------------------------

  int answer;
  int num;

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    num = Integer.parseInt(reader.readLine().trim());
  }

  private void solve() {
    answer = -1;
    calc();
  }

  private void calc() {
    int last = num / 5;

    for (int kg5 = 0; kg5 <= last; kg5++) {
      int kg3 = num - 5 * kg5;
      if (kg3 % 3 != 0) continue;

      int len = kg5 + (kg3 / 3);
      if (answer < 0 || answer > len) answer = len;
    }
  }

  private void print() throws IOException {
    writer.write(answer + "\n");
    writer.flush();
  }

  // ----------------------------------------------------------
}
