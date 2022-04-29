/*
 * (220429) 우리들의 학과는 무엇이지?
 * http://www.ascode.org/problem.php?id=1035
 */

#include <cstdio>

int main() {
  int num, major = 0;
  scanf("%d", &num);

  num /= 100;

  major += num % 10;
  num /= 10;
  major += (num % 10) * 10;

  printf("%d\n", major);

  return 0;
}
