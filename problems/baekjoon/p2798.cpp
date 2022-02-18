/*
 * (220218) 블랙잭
 * https://www.acmicpc.net/problem/2798
 *
 * [풀이]
 * Brute force 방식으로 풀이 가능하며 중복되지 않는 숫자 3개를 뽑아서 합한 다음
 * 주어진 수와 가장 비슷한 수를 찾아내는 문제 입니다.
 */

#include <cstdio>
#include <cstdlib>
#include <algorithm>

int main() {
  int N, M;
  scanf("%d %d", &N, &M);

  int* cards = (int*)malloc(sizeof(int) * N);

  for (int i = 0; i < N; i ++)
    scanf("%d", cards + i);
  std::sort(cards, cards + N);

  int answer = -1, current;
  int a, b, c;

  for (a = 0; a < N - 2; a ++) {
    for (b = a + 1; b < N - 1; b ++) {
      for (c = b + 1; c < N; c ++) {
        current = cards[a] + cards[b] + cards[c];

        if (M - current >= 0 && M - current < M - answer)
          answer = current;
      }
    }
  }

  printf("%d\n", answer);

  free(cards);
  return 0;
}
