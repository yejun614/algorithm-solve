/*
 * (220429) 짝수만 변환하기
 * http://www.ascode.org/problem.php?id=1041
 */

#include <cstdio>

int main() {
  short num;
  scanf("%hd", &num);

  num += num % 2 == 0 ? 1 : 0;
  
  printf("%hd\n", num);

  return 0;
}
