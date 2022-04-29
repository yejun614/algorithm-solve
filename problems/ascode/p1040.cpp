/*
 * (220429) 2의n승 배 계산하기
 * http://www.ascode.org/problem.php?id=1040
 */

#include <cstdio>
#include <cmath>

int main() {
  int a, n;
  scanf("%d %d", &a, &n);

  const int answer = a * pow(2, n);
  printf("%d\n", answer);

  return 0;
}
