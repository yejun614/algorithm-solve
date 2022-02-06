/*
 * (220206) 체스판 다시 칠하기
 * https://www.acmicpc.net/problem/1018
 *
 * [풀이]
 * 체스판 처럼 격자무늬로 된 2차원 배열을 만드는데 필요한 최소한의 칠하는 횟수를
 * 구하는 것이 문제의 요지 이다.
 *
 * (1). 먼저 보드의 너비와 높이를 입력 받는다. (N, M)
 * (2). 격자무늬로 된 2차원 배열을 만드는 것이기 때문에, 흰색과 검은색을 번가라 가면서
 *      모범 정답 보드를 만들 수 있다.
 *      이 때, 상단 좌측 칸이 흰색인 보드와, 검은색인 보드 두가지 경우를 모두 생각한다.
 * (3). 이제 각 칸의 색을 차례대로 입력 받으면서 모범 정답 보드와 비교하여 서로 다른 색인
 *      경우 좌표를 기록해 둔다.
 * (4). 문제에서 요구된 보드의 크기가 8x8 이기 때문에, 가능한 최소한의 칠하는 수를 구하므로,
 *      (0, 0)에서 출발하여 (M-7, N-7)까지 탐색하여 최소값을 찾아낸다.
 * (5). 정답을 출력.
 *
 * [실수했던 부분]
 * (+) 코드를 보면 입력받은 보드 색들과 실제로 칠해져 있어야 하는 정답 보드 색을
 *     둘러보면서 비교해 서로 다른 값을 체크하는데, 문제에서 제시되어 있는대로
 *
 *     "각 칸이 검은색과 흰색중 하나로 색칠되어 있고 변을 공유하는 두 개의 사각형은
 *      다른 색으로 칠해져 있어야" 하기 때문에,
 *
 *     입력받은 보드와 정답 보드를 비교하는 과정에서 다음 줄(y ++)로 넘어갈 때,
 *     M 값(보드의 너비)가 짝수라면 이전 줄의 마지막 타일의 색과 다음 줄의 첫번째 타일의
 *     색이 동일해야 한다.
 *
 *     [M이 짝수]   [M이 홀수]
 *       XOXOXO       XOXOX
 *       OXOXOX       OXOXO
 *       XOXOXO       XOXOX
 *     
 */

#include <cstdio>
#include <vector>

#define MAX_VAL 50

using namespace std;

inline short counting(int x, int y, vector<vector<bool> >& map) {
  short count = 0;

  for (int cy = 0; cy < 8; cy ++) {
    for (int cx = 0; cx < 8; cx ++) {
      if (map[y + cy][x + cx]) count ++;
    }
  }

  return count;
}

int main() {
  short N, M;
  scanf("%hd %hd", &N, &M);

  vector<vector<bool> > map_A, map_B;
  map_A = vector<vector<bool> > (N, vector<bool>(M, false));
  map_B = vector<vector<bool> > (N, vector<bool>(M, false));

  char pattern = 'W', input[MAX_VAL + 1];
  
  for (int y = 0; y < N; y ++) {
    scanf("%s", input);

    for (int x = 0; x < M; x ++) {
      if (input[x] == pattern) {
        map_B[y][x] = true;
      } else {
        map_A[y][x] = true;
      }

      // Flip
      pattern = (pattern == 'W') ? 'B' : 'W';
    }

    // Flip
    if (M % 2 == 0)
      pattern = (pattern == 'W') ? 'B' : 'W';
  }

  short answer = (MAX_VAL * MAX_VAL), current;

  for (int y = 0; y < N-7; y ++) {
    for (int x = 0; x < M-7; x ++) {
      // A
      current = counting(x, y, map_A);
      if (current < answer) answer = current;

      // B
      current = counting(x, y, map_B);
      if (current < answer) answer = current;
    }
  }

  printf("%hd\n", answer);
  
  return 0;
}
