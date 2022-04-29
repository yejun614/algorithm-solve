/*
 * (220429) 시간 계산하기 #1
 * http://www.ascode.org/problem.php?id=1029
 */

#include <cstdio>

int main() {
  int seconds;
  scanf("%d", &seconds);

  int day = 0, hour = 0, minutes = 0;

  minutes = seconds / 60;
  seconds %= 60;

  hour = minutes / 60;
  minutes %= 60;

  day = hour / 24;
  hour %= 24;

  printf("%d %d %d %d\n", day, hour, minutes, seconds);

  return 0;
}
