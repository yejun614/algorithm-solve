/*
 * (230217) 과제 안 내신 분..?
 * https://www.acmicpc.net/problem/5597
 */

#include <cstdio>

bool Students[30];

int main() {
  for (int i = 0; i < 30; ++i) {
    Students[i] = false;
  }

  int num;
  for (int i = 0; i < 28; ++i) {
    scanf("%d", &num);
    Students[num - 1] = true;
  }

  for (int i = 0; i < 30; ++i) {
    if (!Students[i]) {
      printf("%d\n", i + 1);
    }
  }

  return 0;
}

