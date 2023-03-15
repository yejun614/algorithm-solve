/*
 * (230315) 연결 요소의 개수
 * https://www.acmicpc.net/problem/11724
 * 
 * [풀이]
 * 무방향 그래프가 주어졌을 때 연결요소(Connected Component)의 개수를 구하는
 * 문제이다. 연결요소란 그래프 내부에 서로 독립되어 있는 하위 그래프들을 의미
 * 한다. 즉, 서로 연결되어 있지 않은 그래프들이 하나의 그래프로 표현되어 있는
 * 상황이다.
 *
 * 연결요소의 개수를 구하기 위해서는 BFS, DFS 중 하나의 방식으로 그래프를 탐색
 * 하면 간단하게 해결 가능하다.
 *
 * 이번에는 DFS를 Stack을 사용해서 구현했는데 노드 방문 여부를 기억하는 배열을
 * 준비하고 DFS를 계속 수행하여 수행된 횟수가 연결요소의 개수가 된다.
 */

#include <cstdio>
#include <stack>
using namespace std;

#define MAX_SIZE 1001

int N, M;
bool Graph[MAX_SIZE][MAX_SIZE];
bool Visited[MAX_SIZE];

void ExploreGraph(int start) {
  stack<int> nodes;
  nodes.push(start);

  while (!nodes.empty()) {
    int current = nodes.top();
    nodes.pop();

    for (int next = 1; next <= N; ++next) {
      if (Graph[current][next] && !Visited[next]) {
        nodes.push(next);
        Visited[next] = true;
      }
    }
  }
}

int main() {
  for (int y = 0; y < MAX_SIZE; ++y) {
    for (int x = 0; x < MAX_SIZE; ++x) {
      Graph[y][x] = false;
    }

    Visited[y] = false;
  }

  scanf("%d %d", &N, &M);

  int start, end;
  for (int edge = 0; edge < M; ++edge) {
    scanf("%d %d", &start, &end);

    Graph[start][end] = true;
    Graph[end][start] = true;
  }

  int answer = 0;

  for (int i = 1; i <= N; ++i) {
    if (Visited[i]) continue;

    ExploreGraph(i);
    ++answer;
  }

  printf("%d\n", answer);

  return 0;
}
