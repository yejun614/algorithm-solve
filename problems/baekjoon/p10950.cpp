/**
 * (210929) A+B - 3
 * https://www.acmicpc.net/problem/10950
 */

#include <cstdio>

int main() {
  int T, A, B;
  scanf("%d", &T);

  while (T--) {
    scanf("%d %d", &A, &B);
    printf("%d\n", A+B);
  }

  return 0;
}
