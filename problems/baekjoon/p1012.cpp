/*
 * (220205) 유기농 배추
 * https://www.acmicpc.net/problem/1012
 *
 * [풀이]
 * 2차원 좌표평면에 위치한 이웃한 배추들의 개수를 세는 문제 입니다.
 * (1). 우선 빈 좌표평면을 준비하고, 배추들의 위치를 기록합니다.
 * (2). 좌표평면을 순회하면서 배추가 있다면 answer 값을 하나 늘리고,
 * (3). 해당 배추와 인접한 배추들은 모두 좌표평면상에서 지워 줍니다.
 * (4). 좌표평면을 모두 탐색했다면 answer 값을 출력 합니다.
 *
 * [주의]
 * (+) 배추 위치를 모두 입력받은 후에 이웃한 배추의 개수를 파악해야 합니다.
 */

#include <cstdio>
#include <vector>

using namespace std;

const short dx[] = {  0 , -1 , +1,  0 };
const short dy[] = { -1 ,  0 ,  0, +1 };

bool neighborhood_tiles(vector<vector<bool> >& map, int x, int y, int width, int height) {
  if (!map[y][x]) return false;
  
  map[y][x] = false;

  for (int i = 0; i < 4; i ++) {
    const int cx = dx[i] + x;
    const int cy = dy[i] + y;

    if (cx < 0 || cx >= width || cy < 0 || cy >= height)
      continue;
    
    neighborhood_tiles(map, cx, cy, width, height);
  }

  return true;
}

int main() {
  int T;
  scanf("%d", &T);

  while (T --) {
    int answer = 0;
    int M, N, K;

    // Inputs
    scanf("%d %d %d", &M, &N, &K);
    vector<vector<bool> > map(N, vector<bool>(M, false) );

    int X, Y;
    
    while (K --) {
      scanf("%d %d", &X, &Y);
      map[Y][X] = true;
    }

    // Solve
    for (int y = 0; y < N; y ++) {
      for (int x = 0; x < M; x ++) {
        if (neighborhood_tiles(map, x, y, M, N))
          answer ++;
      }
    }

    // Print Answer
    printf("%d\n", answer);
  }

  return 0;
}
