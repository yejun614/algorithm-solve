/*
 * (221117) 숨바꼭질
 * https://www.acmicpc.net/problem/1697
 *
 * [풀이]
 * 1차원 배열을 사용해서 출발지에서 목적지 까지 도달하기 위해
 * 필요한 횟수를 구한는 것이 문제의 목표이다.
 *
 * 현재 위치를 기준으로 이동가능한 모든 위치들에 최단 거리를 갱신하고,
 * 이동가능한 모든 위치들을 큐에 삽입하여 다음 루프에서는 큐에서 차례대로
 * 꺼내어 해당 위치를 기준으로 다시 이동가능한 모든 위치를 고려한다.
 */

#include <cstdio>
#include <queue>
using namespace std;

#define SIZE 200001
int Memory[SIZE];

int main() {
  // Input
  int N, K;
  scanf("%d %d", &N, &K);

  // Reset Memory  
  for (int i = 0; i < SIZE; i++) {
    Memory[i] = SIZE; // assign infinite
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
      }

      if (Memory[position-1] > Memory[position]+1) {
        Memory[position-1] = Memory[position]+1;
        positions.push(position-1);
      }

      if (position < K && Memory[position+1] > Memory[position]+1) {
        Memory[position+1] = Memory[position]+1;
        positions.push(position+1);
      }
    }
  }

  // Print answer
  printf("%d\n", Memory[K]);

  return 0;
}
