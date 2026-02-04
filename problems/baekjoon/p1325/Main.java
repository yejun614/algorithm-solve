/*
 * (1325) 효율적인 해킹
 * https://www.acmicpc.net/problem/1325
 */

package baekjoon.p1325;

import java.io.*;
import java.util.*;

/**
 * 백준 - 1325. 효율적인 해킹
 * @author YeJun, Jung
 *
 * @see #main(String[])
 * 1. 메인 실행부에서 Main 인스턴스를 생성하고 run을 호출한다.
 *
 * @see #run()
 * 2. 입력(input), 해결(solve), 출력(print) 로직을 순차적으로 수행한다.
 *
 * @see #input()
 * 3. 컴퓨터 개수(comCount)와 신뢰 관계 개수(inputLines)를 입력받는다.
 * 4. 인접 리스트 구조의 그래프를 초기화한다.
 * 5. 신뢰 관계(A가 B를 신뢰한다)를 입력받아 역방향(B -> A)으로 간선을 저장한다.
 *    (B를 해킹했을 때 전파되는 경로를 추적하기 위함)
 *
 * @see #solve()
 * 6. 모든 정점에 대해 각각 해킹 가능한 컴퓨터 수를 계산한다.
 * 7. graphMembers(BFS)를 호출하여 해당 정점에서 도달 가능한 노드 리스트를 반환받는다.
 * 8. 현재까지의 최대 방문 수(maxVisited)와 비교하여 정답 리스트(answer)를 관리한다.
 *  8-1. 더 큰 값이 나오면 기존 리스트를 비우고(clear) 갱신한다.
 *  8-2. 같은 값이 나오면 리스트에 추가한다.
 *
 * @see #graphMembers(int)
 * 9. BFS 알고리즘을 사용하여 특정 노드에서 접근 가능한 모든 노드를 탐색한다.
 * 10. 방문 여부를 체크(visited)하며 큐(Queue)에 자식 노드들을 삽입한다.
 * 11. 탐색이 완료된 모든 노드 정보를 리스트에 담아 반환한다.
 *
 * @see #print()
 * 12. 정답 리스트를 오름차순으로 정렬한 뒤 공백으로 구분하여 출력한다.
 */
public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer line;

    public static void main(String[] args) throws IOException {
        // 1. 메인 실행부 실행
        new Main().run();
    }

    // ----------------------------------------------------------

    private int comCount;
    private int inputLines;
    List<List<Integer>> graph;
    private List<Integer> answer;

    public Main() { }

    public void run() throws IOException {
        // 2. 실행 프로세스 제어
        input();
        solve();
        print();
    }

    private void input() throws IOException {
        // 3. 컴퓨터 수와 입력 라인 수 초기화
        getLine();
        comCount = Integer.parseInt(line.nextToken());
        inputLines = Integer.parseInt(line.nextToken());

        // 4. 인접 리스트 생성
        graph = new ArrayList<>();
        for (int count = 0; count < comCount + 1; count++) graph.add(new ArrayList<>());

        // 5. 신뢰 관계를 역방향 그래프로 저장 (전파 경로 추적 최적화)
        for (int lineCount = 0; lineCount < inputLines; lineCount++) {
            getLine();
            int com0 = Integer.parseInt(line.nextToken()); // A (신뢰하는 쪽)
            int com1 = Integer.parseInt(line.nextToken()); // B (신뢰받는 쪽)

            // 자기 자신을 신뢰하는 경우가 아니라면 B -> A 연결
            if (com0 != com1) graph.get(com1).add(com0);
        }
    }

    private void solve() {
        // 6. 결과 저장소 및 최대 방문 수 초기화
        answer = new ArrayList<>();
        int maxVisited = 0;

        for (int current = 1; current <= comCount; current++) {
            // 7. 각 컴퓨터를 시작점으로 BFS 수행
            List<Integer> result = graphMembers(current);
            final int size = result.size();

            // 8. 최대 해킹 가능 수 갱신 로직
            if (size < maxVisited) continue;
            if (size > maxVisited) {
                answer.clear();
                maxVisited = size;
            }
            answer.add(current);
        }
    }

    /**
     * 9. 너비 우선 탐색(BFS)을 통한 도달 가능 노드 탐색
     */
    private List<Integer> graphMembers(int nodeIndex) {
        List<Integer> result = new ArrayList<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[comCount + 1];

        // 10. 시작 노드 방문 처리
        queue.offer(nodeIndex);
        visited[nodeIndex] = true;

        while (!queue.isEmpty()) {
            Integer top = queue.poll();
            result.add(top);

            // 연결된 신뢰 관계를 따라 해킹 전파 확인
            for (int child : graph.get(top)) {
                if (visited[child]) continue;
                visited[child] = true;

                queue.offer(child);
            }
        }

        // 11. 도달 가능한 모든 컴퓨터 리스트 반환
        return result;
    }
    
    private void print() throws IOException {
        // 12. 문제 조건에 따라 오름차순 정렬 후 출력
        Collections.sort(answer);

        for (Integer child : answer) {
            writer.write(child + " ");
        }
        writer.write("\n");
        writer.flush();
    }

    // ----------------------------------------------------------

    private static void getLine() throws IOException {
        line = new StringTokenizer(reader.readLine().trim());
    }
}