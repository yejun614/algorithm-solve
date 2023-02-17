/*
 * (230218) 문자열
 * https://www.acmicpc.net/problem/9086
 */

#include <cstdio>
#include <cstring>

int main() {
  int T;
  scanf("%d", &T);

  char str[1001];

  while (T--) {
    scanf("%s", str);
    printf("%c%c\n", str[0], str[strlen(str) - 1]);
  }

  return 0;
}

