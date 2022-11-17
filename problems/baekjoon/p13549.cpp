/*
 * (221117) 숨바꼭질 3
 * https://www.acmicpc.net/problem/13549
 *
 * [풀이]
 * 숨바꼭질(p1697) 문제에서 이어지는 내용이다.

 * 다른 내용은 모두 동일한데,
 * "순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다."
 * 이 부분만 다르다. 순간이동할 때 1초 대신 0초인 것만 기억하면 된다.
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

  // Reset memory
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
        Memory[position*2] = Memory[position];   // !!!!!!!!!!!!!!
                                                 // 이번 문제에서는 순간이동에 0초가 걸린다.
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
