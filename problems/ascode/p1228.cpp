/*
 * (220429) 숫자의 합 구하기 1
 * http://www.ascode.org/problem.php?id=1228
 */

#include <cstdio>

int main() {
  int T;
  scanf("%d", &T);

  while (T--) {
    int n, m;
    scanf("%d %d", &n, &m);

    long long sum = 0;

    for (int i = n; i <= m; i ++) {
      sum += i;
    }

    printf("%lld\n", sum);
  }

  return 0;
}
