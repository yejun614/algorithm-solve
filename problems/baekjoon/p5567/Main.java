/*
 * (5567) 결혼식
 * https://www.acmicpc.net/problem/5567
 */

package baekjoon.p5567;

import java.io.*;
import java.util.*;

/**
 * 백준 - 5567. 결혼식
 * @author YeJun, Jung
 *
 * @see #main(String[])
 * 1. 메인 실행부에서 Main 인스턴스를 생성하고 run을 호출한다.
 *
 * @see #run()
 * 2. 입력(input), 해결(solve), 출력(print) 로직을 순차적으로 수행한다.
 *
 * @see #input()
 * 3. 동기의 수(nodeCount)와 리스트의 길이(inputLines)를 입력받는다.
 * 4. 인접 리스트 구조의 그래프를 초기화한다.
 * 5. 친구 관계를 입력받아 양방향 그래프로 저장한다 (a-b는 서로 친구).
 *
 * @see #solve()
 * 6. 상근이(1번 노드)의 직접적인 친구와 친구의 친구를 찾기 위한 탐색을 시작한다.
 * 7. 방문 여부를 체크할 checked 배열을 초기화하고 상근이 본인(1번)을 방문 처리한다.
 * 8. 상근이의 인접 리스트를 순회하며 직접적인 친구(Depth 1)를 friends 리스트에 담는다.
 * 9. 찾은 직접적인 친구들의 인접 리스트를 다시 순회하며 '친구의 친구'(Depth 2)를 탐색한다.
 * 10. 이미 방문한 노드(상근이 본인 또는 중복된 친구)를 제외하고 새로운 사람을 발견할 때마다 answer를 증가시킨다.
 *
 * @see #print()
 * 11. 최종적으로 계산된 초대 인원(answer)을 출력한다.
 */
public class Main {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer line;

  public static void main(String[] args) throws IOException {
    // 1. 인스턴스 생성 및 실행
    new Main().run();
  }

  // ----------------------------------------------------------

  private int answer;
  private int nodeCount;
  private List<List<Integer>> graph;

  public Main() { }

  public void run() throws IOException {
    // 2. 전체 프로세스 제어
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    // 3. 동기의 수와 관계의 수 입력
    nodeCount = Integer.parseInt(reader.readLine().trim());
    int inputLines = Integer.parseInt(reader.readLine().trim());

    // 4. 인접 리스트 초기화
    graph = new ArrayList<>();
    for (int count = 0; count < nodeCount + 1; count++) graph.add(new ArrayList<>());

    // 5. 양방향 친구 관계 구축
    for (int lineCount = 0; lineCount < inputLines; lineCount++) {
      getLine();
      int a = Integer.parseInt(line.nextToken());
      int b = Integer.parseInt(line.nextToken());

      graph.get(a).add(b);
      graph.get(b).add(a);
    }
  }

  private void solve() {
    List<Integer> friends = new ArrayList<>();
    boolean[] checked = new boolean[501]; // 최대 동기 수 500명
    
    // 7. 상근이 본인(1번)은 초대 인원에서 제외하기 위해 미리 방문 처리
    checked[1] = true;

    // 8. 상근이의 직접적인 친구(Depth 1) 찾기
    for (Integer node : graph.get(1)) {
      if (checked[node]) continue;
      checked[node] = true;

      friends.add(node);
    }

    // 직접적인 친구 수만큼 정답 초기화
    answer = friends.size();

    // 9. 친구의 친구(Depth 2) 탐색
    for (Integer friend : friends) {
      for (Integer node : graph.get(friend)) {
        // 10. 아직 초대 리스트에 없는 사람만 추가
        if (checked[node]) continue;
        checked[node] = true;
        answer++;
      }
    }
  }
  
  private void print() throws IOException {
    // 11. 최종 결과 출력
    writer.write(answer + "\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    line = new StringTokenizer(reader.readLine().trim());
  }
}