#include <cstdio>

int solve(int M, int N, int x, int y) {
  if (x == y) return x;

  int count = 1;
  while (M * count % N != 0) ++count;

  const int total = M * count;
  for (int i = x; i <= total; i += M) {
    if ((i - 1) % N == y - 1) return i;
  }

  return -1;
}

int main() {
  int T;
  scanf("%d", &T);

  int M, N, x, y;
  while (T--) {
    scanf("%d %d %d %d", &M, &N, &x, &y);
    printf("%d\n", solve(M, N, x, y));
  }

  return 0;
}

