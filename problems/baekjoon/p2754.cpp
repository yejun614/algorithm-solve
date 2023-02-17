/*
 * (230218) 학점계산
 * https://www.acmicpc.net/problem/2754
 */

#include <cstdio>

constexpr float SCORE_TABLE[] = {
  4.3, 4.0, 3.7,
  3.3, 3.0, 2.7,
  2.3, 2.0, 1.7,
  1.3, 1.0, 0.7,
  0,   0,   0,
  0,
};

int main() {
  char score[10];
  scanf("%s", score);

  int index = (score[0] - 'A') * 3;

  if (score[1] == '+') index += 1;
  else if (score[1] == '0') index += 2;
  else if (score[1] == '-') index += 3;

  printf("%.1f\n", SCORE_TABLE[--index]);

  return 0;
}

