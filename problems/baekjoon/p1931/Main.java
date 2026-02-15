/*
 * (1931) 회의실 배정
 * https://www.acmicpc.net/problem/1931
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - 회의실 배정
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 회의 시간이 겹치지 않도록 최대한 많은 회의에 참가한다.
 * 
 * [전략]
 *   - 우선순위 큐를 사용해서 회의가 빨리 끝나는 순서로 참가한다.
 *     - 우선순위 큐 시간 복잡도 push, pop - O(logN)
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

  int answer;
  int meetLen;
  PriorityQueue<Meet> meetQ;
  int meetEndTime;

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    meetLen = Integer.parseInt(reader.readLine().trim());

    meetQ = new PriorityQueue<>((Meet a, Meet b) -> {
      int c1 = Integer.compare(a.end, b.end);
      if (c1 != 0) return c1;

      int c2 = Integer.compare(a.begin, b.begin);
      return c2;
    });

    for (int index = 0; index < meetLen; index++) {
      meetQ.offer(Meet.fromReader());
    }
  }

  private void solve() {
    meetEndTime = 0;

    while (!meetQ.isEmpty()) {
      Meet peek = meetQ.poll();

      if (meetEndTime <= peek.begin) {
        meetEndTime = peek.end;
        answer++;
      }
    }
  }

  private void print() throws IOException {
    writer.write(answer + "\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static class Meet {
    int begin;
    int end;

    public Meet(int begin, int end) {
      this.begin = begin;
      this.end = end;
    }

    public static Meet fromReader() throws IOException {
      getLine();

      int begin = Integer.parseInt(input.nextToken());
      int end = Integer.parseInt(input.nextToken());

      return new Meet(begin, end);
    }
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
