/*
 * (230217) 사파리월드
 * https://www.acmicpc.net/problem/2420
 */

#include <cstdio>

int main() {
  long long A, B;
  scanf("%lld %lld", &A, &B);

  A -= B;
  if (A < 0) A *= -1;

  printf("%lld\n", A);

  return 0;
}

