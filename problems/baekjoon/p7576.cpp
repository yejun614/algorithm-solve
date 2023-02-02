/*
 * (230201) 토마토
 * https://www.acmicpc.net/problem/7576
 *
 * 풀이는 p7569.cpp 에 적혀있다.
 */

#include <cstdio>
#include <queue>

#define MAX_SIZE 1001
#define FOR2(code) \
  for (int y = 0; y < Height; ++y) { \
    for (int x = 0; x < Width; ++x) { \
      code \
  } }

const short DIR_X[] = { -1,  1,  0,  0 };
const short DIR_Y[] = {  0,  0, -1,  1 };

typedef struct POS {
  int x;
  int y;
} POS;

int Width, Height;
char Board[MAX_SIZE][MAX_SIZE];
std::queue<POS> Nodes;

int NextStep() {
  int diff = 0, count = Nodes.size();
  POS node, next;

  while (count--) {
    node = Nodes.front();
    Nodes.pop();

    for (int i = 0; i < 4; ++i) {
      next.x = node.x + DIR_X[i];
      next.y = node.y + DIR_Y[i];

      if (next.x < 0 || next.x >= Width  ||
          next.y < 0 || next.y >= Height ||
          Board[next.y][next.x] != '0'
      ) {
        continue;
      }

      Board[next.y][next.x] = '1';
      Nodes.push(next);
      ++diff;
    }
  }

  return diff;
}

bool CheckBoard() {
  FOR2(
    if (Board[y][x] == '0') return false;
  )

  return true;
}

int main() {
  scanf("%d %d", &Width, &Height);

  FOR2(
    scanf("%s", &Board[y][x]);
    if (Board[y][x] == '1') Nodes.push({x, y});
  )

  int answer = 0;
  while (NextStep()) ++answer;
  printf("%d\n", CheckBoard() ? answer : -1);

  return 0;
}

