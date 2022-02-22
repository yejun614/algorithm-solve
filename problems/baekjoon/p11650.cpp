/*
 * (220220) 좌표 정렬하기
 * https://www.acmicpc.net/problem/11650
 *
 * [풀이]
 * (x, y) 점을 여러개 입력 받고 문제에서 제시하는 대로 정렬해서 출력하는 문제 입니다.
 * x좌표가 증가하는 순으로, x좌표가 같으면 y좌표가 증가하는 순으로 정렬합니다.
 *
 * std::sort에 직접 구현한 compare 비교 함수를 이용해서 해결 가능합니다.
 * 문제에서 제시된 규칙을 compare안에 적용하여 정렬합니다.
 */

#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

typedef struct POS {
  int x;
  int y;
} POS;

bool compare(POS A, POS B) {
  if (A.x == B.x) return A.y < B.y;
  return A.x < B.x;
}

int main() {
  int N;
  scanf("%d", &N);

  vector<POS> positions(N);
  
  for (int i = 0; i < N; i ++)
    scanf("%d %d", &positions[i].x, &positions[i].y);

  sort(positions.begin(), positions.end(), compare);

  for (auto position : positions)
    printf("%d %d\n", position.x, position.y);

  return 0;
}
