/*
 * (230116) 토마토
 * https://www.acmicpc.net/problem/7569
 *
 * [풀이]
 * 3차원 배열을 입력받아 문제에 적혀 있는데로 토마토마 모두 익을 때 까지
 * 걸리는 시간을 출력하는 문제이다. (모두 익을 수 없다면 -1 출력)
 *
 * 원래 브루트 포스로 풀이하면 3중첩 반복문으로 해결 가능하나 시간 복잡도가
 * 최소 O(N^3) 이 되어 (최대 N = 100) 시간초과를 받게 된다.
 *
 * 이 때 큐(Queue)를 활용하여 반복횟수를 줄일 수 있는데,
 * 처음 배열을 입력받을 때 토마토(1로 표시)의 위치를 큐에 담아두고
 * 매번 큐를 돌면서 해당 토마토 주변 6면의 토마토를 익히고(0에서 1로 변경)
 * 해당 토마토를 다시 큐에 넣어 반복하는 방식을 사용한다.
 *
 * 큐를 사용하게 되면 전체를 반복할 때에 비해 시간을 크게 절약할 수 있어
 * 문제에서 요구하는 시간내에 실행 가능하다. (해당 소스코드의 백준 실행시간은 120ms 이다)
 */

#include <cstdio>
#include <queue>

#define MAT(x, y, z) (Matrix[(z) * Height * Width + (y) * Width + (x)])
#define FOR3(code) \
  for (short z = 0; z < Depth; ++z) { \
    for (short y = 0; y < Height; ++y) { \
      for (short x = 0; x < Width; ++x) { \
        code \
  } } }

const short X_DIR[] = {  0,  0,  1, -1,  0,  0 };
const short Y_DIR[] = {  0,  0,  0,  0,  1, -1 };
const short Z_DIR[] = {  1, -1,  0,  0,  0,  0 };

short Width, Height, Depth;
short *Matrix;

struct Pos { short x, y, z; };
std::queue<Pos> NextNodes;

int NextStep() {
  int diff = 0;
  int nodeCount = NextNodes.size();
  Pos pos, next;

  while (nodeCount--) {
    pos = NextNodes.front();
    NextNodes.pop();
    
    if (MAT(pos.x, pos.y, pos.z) != 1) continue;

    for (short i = 0; i < 6; ++i) {
      next.x = pos.x + X_DIR[i];
      next.y = pos.y + Y_DIR[i];
      next.z = pos.z + Z_DIR[i];

      if (
        next.z < 0 || next.z >= Depth  ||
        next.y < 0 || next.y >= Height ||
        next.x < 0 || next.x >= Width  ||
        MAT(next.x, next.y, next.z) != 0
      ) {
        continue;
      }

      NextNodes.push(next);
      MAT(next.x, next.y, next.z) = 1;
      ++diff;
    }
  }

  return diff;
}

bool CheckMatrix() {
  FOR3( if (MAT(x, y, z) == 0) return false; )
  return true;
}

int main() {
  scanf("%hd %hd %hd", &Width, &Height, &Depth);
  
  Matrix = new short[Width * Height * Depth];

  FOR3(
    scanf("%hd", &MAT(x, y, z));
    if (MAT(x, y, z) == 1) NextNodes.push({x, y, z});
  )

  int answer = 0;
  while (NextStep()) ++answer;
  printf("%d\n", CheckMatrix() ? answer : -1);

  delete [] Matrix;
  return 0;
}
