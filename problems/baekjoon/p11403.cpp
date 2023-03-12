/*
 * (230308) 경로 찾기
 * https://www.acmicpc.net/problem/11403
 *
 * [풀이]
 * 방향 그래프가 인접행렬 형식으로 입력될 때, 모든 정점에서 또 다른 정점으로
 * 이동 가능한 간선이 존재하는지를 인접행렬 형식으로 출력하는 문제이다.
 *
 * 정점의 최대 개수가 100개로 크기 않음으로 깊이우선탐색 혹은 너비우선탐색으로
 * 모든 정점들에 대해 탐색을 진행하고 그 결과를 출력하면 된다.
 *
 * 시간 복잡도: O(N^2) = O(100 * 100)
 */

#include <cstdio>
#define MAX_LEN 100

int N;
bool Graph[MAX_LEN][MAX_LEN];
bool Visited[MAX_LEN];

void ResetVisited() {
  for (int i = 0; i < N; ++i) {
    Visited[i] = false;
  }
}

bool DepthFirstFind(int start, int end) {
  for (int i = 0; i < N; ++i) {
    if ((!Graph[start][i]) || (Visited[i])) {
      continue;

    } else if (i == end) {
      return true;

    } else {
      Visited[i] = true;

      if (DepthFirstFind(i, end)) {
        return true;
      }
    }
  }

  return false;
}

int main() {
  scanf("%d", &N);
  
  int input;

  for (int y = 0; y < N; ++y) {
    for (int x = 0; x < N; ++x) {
      scanf("%d", &input);
      Graph[y][x] = input;
    }
  }

  for (int start = 0; start < N; ++start) {
    for (int end = 0; end < N; ++end) {
      ResetVisited();
      printf("%d ", DepthFirstFind(start, end));
    }
    printf("\n");
  }

  return 0;
}
