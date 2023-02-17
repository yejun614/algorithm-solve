/*
 * (230218) 그대로 출력하기
 * https://www.acmicpc.net/problem/11718
 */

#include <stdio.h>

int main() {
  char str[101];
  while (gets(str) != NULL) {
    printf("%s\n", str);
  }

  return 0;
}

