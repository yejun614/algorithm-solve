/*
 * (230308) 적록색약
 * https://www.acmicpc.net/problem/10026
 *
 * [풀이]
 * 2차원 행렬에서 주어진 조건에 맞는 영역의 개수를 출력하는 문제입니다.
 *
 * 문제에서 주어진 조건은 적록색약인 경우와 그렇지 아니한 경우 두가지가
 * 존재합니다.
 *
 * 행렬에서 영역의 개수를 확인할 때는 깊이우선탐색(DFS)가 좋습니다.
 * 이 때 주의해야 하는 점은 재귀함수로 구현할 경우 행렬의 크기가
 * 너무 클때 스택 오버플로우가 발생하기 쉽다는 점 입니다.
 *
 * 이런 경우에는 Stack 자료구조를 이용해서 구현하면 오버플로우 없이
 * DFS를 구현할 수 있습니다.
 */

#include <cstdio>
#define MAX_SIZE 101

int N;
char Image[MAX_SIZE][MAX_SIZE];
bool Check[MAX_SIZE][MAX_SIZE];

constexpr int DIR_X[] = { -1,  1,  0,  0 };
constexpr int DIR_Y[] = {  0,  0, -1,  1 };

void ResetCheck() {
  for (int y = 0; y < N; ++y) {
    for (int x = 0; x < N; ++x) {
      Check[y][x] = false;
    }
  }
}

void AreaCount(int y, int x, char pixel, bool isNormal) {
  if ((y < 0) || (y >= N) || (x < 0) || (x >= N)) return;
  if (Check[y][x]) return;
  if (isNormal && Image[y][x] != pixel) return;
  if (!isNormal) {
    if ((pixel == 'R' || pixel == 'G') && (Image[y][x] == 'B')) return;
    if ((pixel == 'B') && Image[y][x] != 'B') return;
  }

  Check[y][x] = true;

  for (int i = 0; i < 4; ++i) {
    AreaCount(y + DIR_Y[i], x + DIR_X[i], pixel, isNormal);
  }
}

int Solve(bool isNormal) {
  ResetCheck();

  int result = 0;

  for (int y = 0; y < N; ++y) {
    for (int x = 0; x < N; ++x) {
      if (Check[y][x]) continue;

      AreaCount(y, x, Image[y][x], isNormal);
      ++result;
    }
  }

  return result;
}

int main() {
  scanf("%d", &N);

  for (int y = 0; y < N; ++y) {
    scanf("%s", Image[y]);
  }

  printf("%d %d\n", Solve(true), Solve(false));

  return 0;
}
