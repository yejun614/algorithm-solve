/*
 * (221111) N번째 큰 수
 * https://www.acmicpc.net/problem/2075
 *
 * [풀이]
 * 간단하게 C++언어의 sort 함수로 해결 가능하다.
 */

#include <cstdio>
#include <algorithm>

#define SIZE 1500

int Board[SIZE * SIZE];

int main() {
  int N, M;
  scanf("%d", &N);
  M = N * N;

  for (int i = 0; i < M; i++)
    scanf("%d", Board + i);
  std::sort(Board, Board + M);

  printf("%d\n", Board[M-N]);

  return 0;
}
