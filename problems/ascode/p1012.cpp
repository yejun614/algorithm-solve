/*
 * (220429) 시간이 얼마나 흘렀지?
 * http://www.ascode.org/problem.php?id=1012
 */

#include <cstdio>

void printTime(unsigned int seconds) {
  unsigned int day = 0, hour = 0, minutes = 0;

  minutes = seconds / 60;
  seconds %= 60;

  hour = minutes / 60;
  minutes %= 60;

  day = hour / 24;
  hour %= 24;

  printf("%u day : %u hour : %u min : %u sec\n", day, hour, minutes, seconds);
}

int main() {
  int T;
  unsigned int seconds;
  scanf("%d", &T);

  while (T --) {
    scanf("%u", &seconds);

    printTime(seconds);
  }

  return 0;
}
