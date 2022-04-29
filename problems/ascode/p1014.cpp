/*
 * (220429) 평균 관객수 구하기 (기초) #5
 * http://www.ascode.org/problem.php?id=1014
 */

#include <cstdio>

int main() {
  int current, avg = 0;

  for (int i = 0; i < 7; i ++) {
    scanf("%d", &current);
    avg += current;
  }

  avg /= 7;

  printf("%d\n", avg);

  return 0;
}
