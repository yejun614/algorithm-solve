#include <cstdio>
#include <queue>
#include <list>

using namespace std;

#define SIZE 200001

int Memory[SIZE];
int PrevPosition[SIZE];

void ResetMemory() {
  for (int i = 0; i < SIZE; i++) {
    Memory[i] = SIZE; // assign infinite
    PrevPosition[i] = -1;
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

    // printf("[] ");
    // for (int i = 0; i < 2*K; i++) {
    //   printf("%6d ", Memory[i]);
    // }
    // printf("\n");
  }

  printf("%d\n", Memory[K]);


  // for (int i = 0; i < K*2; i++) {
  //   printf("%2d ", i);
  // }
  // printf("\n");
  // for (int i = 0; i < K*2; i++) {
  //   printf("%2d ", PrevPosition[i]);
  // }
  // printf("\n");

  list<int> route;

  int current = K;
  route.push_front(K);

  while (current != N) {
    current = PrevPosition[current];
    route.push_front(current);
  }

  for (int position : route) {
    printf("%d ", position);
  }
  printf("\n");

  return 0;
}
