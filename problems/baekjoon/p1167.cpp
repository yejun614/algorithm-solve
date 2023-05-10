/*
 * (230510) 트리의 지름
 * https://www.acmicpc.net/problem/1167
 *
 * [풀이]
 * 문제에서 트리의 지름에 대해서 정의를 하고 있다. 트리의 지름이란, 임의의 두 점 사이의 거리 중
 * 가장 긴 것을 말한다. 트리의 지름을 구하는 프로그램을 작성하시오.
 *
 * 트리의 지름을 구하기 위해서 우선 트리를 그래프로 표현하듯이 인접리스트로 나타내고,
 * 다익스트라 알고리즘을 사용하여 최단거리를 구하는 것이 필요하다.
 *
 * 인접리스트로 표현된 트리의 임의의 한 정점(e.g. 0번)에서 가장 거리가 먼 정점 A를 탐색한다.
 * 이후 정점 A에서 가장 거리가 먼 정점 B를 찾아내어 둘 사이의 거리를 구한다. 이때 정점 A와 B사이의
 * 거리가 바로 문제에서 요구하는 트리의 지름이다.
 */

#include <cstdio>
#include <queue>
#include <vector>
#include <utility>
#include <functional>

using namespace std;

int V;
vector< vector< pair<int, int> > > Graph;

vector<int> Solve(int start) {
  vector<int> distances(V, 0);

  priority_queue< pair<int, int>, vector< pair<int, int> >, greater< pair<int, int> > > stack;
  stack.push(make_pair(0, start));

  while (!stack.empty()) {
    pair<int, int> current = stack.top();
    stack.pop();

    for (auto it = Graph[current.second].begin(); it != Graph[current.second].end(); ++it) {
      pair<int, int> next = *it;
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
  scanf("%d", &V);
  Graph.resize(V);

  int index, start, distance;

  for (int i = 0; i < V; ++i) {
    scanf("%d", &index);
    --index;

    while (true) {
      scanf("%d", &start);
      if (start == -1) break;
      scanf("%d", &distance);

      Graph[index].push_back(make_pair(distance, --start));
    }
  }

  vector<int> distances = Solve(0);
  pair<int, int> vertexA = make_pair(0, 0);

  for (int i = 0; i < V; ++i) {
    if (distances[i] > vertexA.first) {
      vertexA.first = distances[i];
      vertexA.second = i;
    }
  }

  distances = Solve(vertexA.second);

  int answer = 0;

  for (int i = 0; i < V; ++i) {
    if (distances[i] > answer) {
      answer = distances[i];
    }
  }

  printf("%d\n", answer);

  return 0;
}
