/*
 * (10816) 숫자 카드 2
 * https://www.acmicpc.net/problem/10816
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 숫자 카드 2
 * @author YeJun, Jung
 * 
 * [전략]
 *   - HashMap에 카드에 적힌 숫자를 Key, 개수를 Value로 저장
 *   - 주어진 M개의 수개에 대해서 숫자 카드의 개수를 출력
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

  int[] answer;
  int cardLen;
  Map<Integer, Integer> cardMap;

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    cardLen = Integer.parseInt(reader.readLine().trim());
    cardMap = new HashMap<Integer, Integer>();

    getLine();
    for (int index = 0; index < cardLen; index++) {
      int num = Integer.parseInt(input.nextToken());

      if (!cardMap.containsKey(num)) {
        cardMap.put(num, 0);
      }

      cardMap.put(num, cardMap.get(num) + 1);
    }
  }

  private void solve() throws IOException {
    int N = Integer.parseInt(reader.readLine().trim());
    answer = new int[N];

    getLine();
    for (int index = 0; index < N; index++) {
      int target = Integer.parseInt(input.nextToken());

      Integer count = cardMap.get(target);
      answer[index] = count == null ? 0 : count;
    }
  }

  private void print() throws IOException {
    for (int num : answer) {
      writer.write(num + " ");
    }
    writer.write("\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
