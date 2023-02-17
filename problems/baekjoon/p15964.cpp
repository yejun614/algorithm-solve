/*
 * (230218) 이상한 기호
 * https://www.acmicpc.net/problem/15964
 */

#include <cstdio>

int main() {
  long long A, B;
  scanf("%lld %lld", &A, &B);
  printf("%lld\n", (A + B) * (A - B));

  return 0;
}

