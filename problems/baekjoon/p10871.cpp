/**
 * (210929) X보다 작은 수
 * https://www.acmicpc.net/problem/10871
 */

#include <cstdio>

int main() {
  int N, X;
  scanf("%d %d", &N, &X);

  int buf;
  for (int i=0; i<N; i++) {
    scanf("%d",&buf);

    if (buf < X)
      printf("%d ", buf);
  }
  printf("\n");

  return 0;
}
