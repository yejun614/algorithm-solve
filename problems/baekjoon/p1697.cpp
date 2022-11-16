#include <cstdio>
#include <queue>

using namespace std;

#define SIZE 200001

int Memory[SIZE];

void ResetMemory() {
  for (int i = 0; i < SIZE; i++) {
    Memory[i] = SIZE; // assign infinite
  }
}

int main() {
  int N, K;
  scanf("%d %d", &N, &K);

  // N < K 조건을 만족 하도록 함
  // if (K < N) {
  //   // swap
  //   int temp = N;
  //   N = K;
  //   K = temp;
  // }

  // N의 위치를 0으로 고정
  // K -= N;

  ResetMemory();
  Memory[N] = 0;

  queue<int> positions;
  positions.push(N);

  while (Memory[K] == SIZE) {
  // for (int n = 0; n < SIZE; n++) {
    int size = positions.size();

    for (int i = 0; i < size; i++) {
      int position = positions.front();
      positions.pop();
      if (position < 0 || position >= SIZE) continue;  // EXCEPTION!

      // printf("%d\n", position);

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

    // printf("[] ");
    // for (int i = 0; i < 2*K; i++) {
    //   printf("%6d ", Memory[i]);
    // }
    // printf("\n");
  }

  printf("%d\n", Memory[K]);

  return 0;
}
