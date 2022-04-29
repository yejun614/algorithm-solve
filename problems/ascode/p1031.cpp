/*
 * (220429) 위대한 차차 범위 측정기
 * http://www.ascode.org/problem.php?id=1031
 */

#include <cstdio>

typedef unsigned long long llu;

int solve(const llu *num) {
  int result = 0;
  llu current = 1;

  while (*num > current) {
    current *= 2;
    result ++;
  }

  return result;
}

int main() {
  int T;
  llu N;

  scanf("%d", &T);

  while (T --) {
    scanf("%llu", &N);

    const int answer = solve(&N);
    printf("%d\n", answer);
  }
  return 0;
}
