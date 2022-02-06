/*
 * (220206) 직사각형에서 탈출
 * https://www.acmicpc.net/problem/1085
 *
 * [풀이]
 * 1사분면의 2차원 좌표평면에서 한 정점의 좌표와 직사각형의 크기가 주어질 때
 * 주어진 정점에서 직사각형의 모서리(변)과의 최소 거리를 구하는 문제이다.
 *
 * 주어진 정점을 기준으로 상, 하, 좌, 우 4방향만 확인하면 됨으로
 * 정점에서 4방향으로 모서리와의 거리를 구하고, 그 중 최소값을 출력한다.
 */

#include <cstdio>

int main() {
  int x, y, w, h;
  scanf("%d %d %d %d", &x, &y, &w, &h);

  int top, left, right, bottom;
  top = h - y;
  left = x;
  right = w - x;
  bottom = y;

  int answer = top;
  if (left < answer) answer = left;
  if (right < answer) answer = right;
  if (bottom < answer) answer = bottom;

  printf("%d\n", answer);

  return 0;
}
