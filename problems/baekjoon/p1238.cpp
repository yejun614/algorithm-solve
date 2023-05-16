/*
 * (230510) 파티
 * https://www.acmicpc.net/problem/1238
 *
 * [풀이]
 * 단방향 그래프에서 각 정점에서 정해진 정점으로 오고, 가는 최단거리 비용을 측정하고,
 * 가장 큰 비용을 출력하는 문제이다. 최단거리 비용은 다익스트라 알고리즘으로 산출가능하며,
 * 이렇게 구한 최단거리 중에서 가장 큰 비용을 찾아 출력하는 방식으로 해결한다.
 *
 * [실수]
 * (+) 계산 과정에서 수가 커질 수 있으므로 자료형을 적절하게 지정해야 한다.
 */

#include <cstdio>
#include <queue>
#include <utility>
#include <functional>

using namespace std;

#define MAX_SIZE 1001
short N, M, X;
vector< vector< pair<int, short> > > Board(MAX_SIZE);

vector<int> Solve(short start) {
  vector<int> distances(MAX_SIZE, 0);

  priority_queue< pair<int, short>, vector< pair<int, short> >, greater< pair<int, short> > > stack;
  stack.push(make_pair(0, start));

  while (!stack.empty()) {
    pair<int, short> current = stack.top();
    stack.pop();

    for (auto it = Board[current.second].begin(); it != Board[current.second].end(); ++it) {
      pair<int, short> next = *it;
      next.first += current.first;

      if (next.second != start && (distances[next.second] == 0 || next.first < distances[next.second])) {
        stack.push(next);
        distances[next.second] = next.first;
      }
    }
  }

  return distances;
}

int main() {
  scanf("%hd %hd %hd", &N, &M, &X);

  short start, end, distance;

  for (int i = 0; i < M; ++i) {
    scanf("%hd %hd %hd", &start, &end, &distance);
    Board[start].push_back(make_pair(distance, end));
  }

  vector<int> distances = Solve(X);

  for (short i = 1; i < MAX_SIZE; ++i) {
    if (Board[i].size() == 0) continue;

    vector<int> result = Solve(i);
    distances[i] += result[X];
  }

  int answer = 0;

  for (int i = 1; i < MAX_SIZE; ++i) {
    if (distances[i] > answer) {
      answer = distances[i];
    }
  }

  printf("%d\n", answer);

  return 0;
}
