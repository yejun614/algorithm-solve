/**
 * (210904) 행렬
 * https://www.acmicpc.net/problem/1080
 */

#include <cstdio>
#include <cstring>

#define SIZE 51

int height, width;

char matrix[SIZE][SIZE];
bool check[SIZE][SIZE];

const short DIR_Y[] = {0, 0, 0, 1, 1, 1, 2, 2, 2};
const short DIR_X[] = {0, 1, 2, 0, 1, 2, 0, 1, 2};

void reverse(int ypos, int xpos) {
  for (int i=0; i<9; i++) {
    const short y = ypos + DIR_Y[i];
    const short x = xpos + DIR_X[i];

    check[y][x] = !check[y][x];
  }
}

int main() {
  scanf("%d %d", &height, &width);

  for (int y=0; y<height; y++)
    scanf("%s", &matrix[y]);

  char buffer[SIZE];

  for (int y=0; y<height; y++) {
    scanf("%s", &buffer);

    for (int x=0; x<width; x++)
      check[y][x] = (buffer[x] == matrix[y][x]);
  }

  int answer = 0;

  for (int y=0; y<height-2; y++) {
    for (int x=0; x<width-2; x++) {
      if (!check[y][x]) {
        reverse(y, x);

        answer ++;
      }
    }
  }

  for (int y=0; y<height; y++)
    for (int x=0; x<width; x++)
      if (!check[y][x]) answer = -1;

  printf("%d\n", answer);

  return 0;
}
