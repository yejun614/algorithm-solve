/*
 * (220429) KTY의 학점
 * http://www.ascode.org/problem.php?id=1226
 */

#include <cstdio>

const char SCORES[] = "FFFFFFDCBAA";
const char SCORE_PLUS[] = "---    +++";

int main() {
  int score;
  scanf("%d", &score);

  if (score == 100) {
    printf("A++\n");
  } else if (score < 60) {
    printf("F\n");
  } else {
    printf("%c%c", 68 - (score / 10 - 6), SCORE_PLUS[score % 10]);
  }

  return 0;
}
