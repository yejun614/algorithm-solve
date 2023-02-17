/*
 * (230218) 대소문자 바꾸기
 * https://www.acmicpc.net/problem/2744
 */

#include <cstdio>
constexpr int DIFF = 'a' - 'A';

int main() {
  char str[101];
  scanf("%s", str);

  for (char *ch = str; *ch != '\0'; ++ch) {
    *ch += (*ch < 'a') ? DIFF : -DIFF;
  }

  printf("%s\n", str);

  return 0;
}

