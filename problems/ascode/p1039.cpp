/*
 * (220429) 마법의 수 찾아내기
 * http://www.ascode.org/problem.php?id=1039
 */

#include <cstdio>

int main() {
  int num;
  scanf("%d", &num);

  printf("%d\n", (num % 2 != 0 && num % 7 == 0) ? 1 : 0);

  return 0;
}
