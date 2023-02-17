/*
 * (230218) 행렬 덧셈
 * https://www.acmicpc.net/problem/2738
 */

#include <cstdio>
#define MAX_SIZE 101

short Width, Height;
short Mat[MAX_SIZE][MAX_SIZE];

int main() {
  scanf("%hd %hd", &Height, &Width);

  for (short y = 0; y < Height; ++y) {
    for (short x = 0; x < Width; ++x) {
      scanf("%hd", &Mat[y][x]);
    }
  }

  short num;
  for (short y = 0; y < Height; ++y) {
    for (short x = 0; x < Width; ++x) {
      scanf("%hd", &num);
      printf("%hd ", (short)(Mat[y][x] + num));
    }
    printf("\n");
  }

  return 0;
}

