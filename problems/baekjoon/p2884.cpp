/**
 * (211001) 알람 시계
 * https://www.acmicpc.net/problem/2884
 */

#include <cstdio>
#define TIME 45

int main() {
  int H, M;
  scanf("%d %d", &H, &M);

  M -= TIME;

  if (M < 0) {
    H --;
    M = 60 + M;
  }

  if (H < 0) {
    H = 23;
  }

  printf("%d %d\n", H, M);

  return 0;
}
