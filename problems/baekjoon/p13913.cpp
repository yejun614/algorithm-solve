/*
 * (221117) 숨바꼭질 4
 * https://www.acmicpc.net/problem/13913
 *
 * [풀이]
 * 숨바꼭질(p1697) 문제에서 이어지는 내용이다.
 * 수빈이가 동생에게 최소시간으로 가는 과정을 출력하는 게 핵심이다.
 *
 * 단순히 Memory가 갱신될 때 마다 해당 index의 PrevPosition에 현재
 * 위치를 기록하고, 탐색이 종료된 후 동생의 위치에서 수빈이의 위치까지
 * PrevPosition를 따라서 리스트를 만들어 마지막에 출력한다.
 */

#include <cstdio>
#include <queue>
#include <list>
using namespace std;

#define SIZE 200001
int Memory[SIZE];
int PrevPosition[SIZE];

int main() {
  int N, K;
  scanf("%d %d", &N, &K);

  // Reset memory
  for (int i = 0; i < SIZE; i++) {
    Memory[i] = SIZE; // assign infinite
    PrevPosition[i] = -1;
  }

  // Start position is zero
  Memory[N] = 0;

  queue<int> positions;
  positions.push(N);

  while (Memory[K] == SIZE) {
    int size = positions.size();

    for (int i = 0; i < size; i++) {
      // Get current position
      int position = positions.front();
      positions.pop();

      // EXCEPTION!
      if (position < 0 || position >= SIZE) continue;

      // Next positions
      if (position < K && Memory[position*2] > Memory[position]+1) {
        Memory[position*2] = Memory[position]+1;
        positions.push(position*2);
        PrevPosition[position*2] = position;
      }

      if (Memory[position-1] > Memory[position]+1) {
        Memory[position-1] = Memory[position]+1;
        positions.push(position-1);
        PrevPosition[position-1] = position;
      }

      if (position < K && Memory[position+1] > Memory[position]+1) {
        Memory[position+1] = Memory[position]+1;
        positions.push(position+1);
        PrevPosition[position+1] = position;
      }
    }
  }

  // Print answer
  printf("%d\n", Memory[K]);

  // Get route of the way to brother
  list<int> route;

  int current = K;
  route.push_front(K);

  while (current != N) {
    current = PrevPosition[current];
    route.push_front(current);
  }

  // Print route
  for (int position : route) {
    printf("%d ", position);
  }
  printf("\n");

  return 0;
}
