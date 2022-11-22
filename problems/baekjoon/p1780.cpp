/*
 * (221123) 종이의 개수
 * https://www.acmicpc.net/problem/1780
 *
 * [풀이]
 * 분할 정복 문제로 재귀함수을 이용해서 풀이 가능하다.
 * 문제에서 제시된 규칙대로 현재 부분 행렬의 모든 요소 값이 같다면
 * 해당 값을 카운트하고 요소가 하나라도 다르다면 9개의 부분 행렬로
 * 분할하여 재귀호출 한다.
 *
 * [실수]
 * Count 함수의 자료형을 unsigned short로 했다가 Wrong Answer 받았다.
 * (3^7) * (3^7) = 4,782,969 로 오버플로우가 발생하기 때문.
 * unsigned int로 자료형을 수정하닌깐 통과할 수 있었다.
 * 
 * (도움) https://www.acmicpc.net/board/view/67181
 */

#include <cstdio>
#define MAX_SIZE 2187

const unsigned short DIR_X[] = { 0, 1, 2, 0, 1, 2, 0, 1, 2 };
const unsigned short DIR_Y[] = { 0, 0, 0, 1, 1, 1, 2, 2, 2 };

char Matrix[MAX_SIZE][MAX_SIZE];
unsigned int Count[3] = { 0, 0, 0 };

bool IsSameMatrix(short sx, short sy, short size) {
  char check = Matrix[sy][sx];

  for (short y = sy; y < sy + size; y++) {
    for (short x = sx; x < sx + size; x++) {
      if (check != Matrix[y][x]) return false;
    }
  }

  return true;
}

void Solve(short sx, short sy, short size) {
  if (IsSameMatrix(sx, sy, size)) {
    Count[Matrix[sy][sx] + 1]++;

  } else {
    const short nextSize = size / 3;

    for (short i = 0; i < 9; i++) {
      Solve(sx + (nextSize * DIR_X[i]), sy + (nextSize * DIR_Y[i]), nextSize);
    }
  }
}

int main() {
  short size;
  scanf("%hd", &size);

  for (short y = 0; y < size; y++) {
    for (short x = 0; x < size; x++) {
      scanf("%hd", &(Matrix[y][x]));
    }
  }

  Solve(0, 0, size);

  printf("%d\n", Count[0]);
  printf("%d\n", Count[1]);
  printf("%d\n", Count[2]);

  return 0;
}
