/*
 * (220429) 숫자의 합 구하기 2
 * http://www.ascode.org/problem.php?id=1229
 */

#include <cstdio>

int main() {
  int T;
  scanf("%d", &T);

  while (T--) {
    long long n, m;
    scanf("%lld %lld", &n, &m);

    long long sum = (m-n+1)*(n + m) / 2;
    printf("%lld\n", sum);
  }

  return 0;
}
