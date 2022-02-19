/*
 * (220219) 달팽이는 올라가고 싶다
 * https://www.acmicpc.net/problem/2869
 *
 * [풀이]
 * https://www.acmicpc.net/board/view/22313
 * https://www.acmicpc.net/board/view/74600
 *
 * (1 <= B < A <= V <= 1,000,000,000)
 *
 * 매일 (A - B) 미터 올라가는 데, 정상에 올라간 후에는 미끄러지지 않으므로,
 * (V - B - 1) 미터 까지 올라간 후 다음 날에는 V 미터 이상 올라갈수 있게 됩니다.
 * 따라서 목적지 까지 올라가는데 걸리는 일수는 (V - B - 1) / (A - B) + 1 이 됩니다.
 *
 * "이 문제 너무 어렵다..."
 */

#include <cstdio>

int main() {
  int A, B, V;
  scanf("%d %d %d", &A, &B, &V);

  int answer = (V - B - 1) / (A - B) + 1;
  printf("%d\n", answer);

  return 0;
}
