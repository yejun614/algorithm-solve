/*
 * (221108) Z
 * https://www.acmicpc.net/problem/1074
 *
 * [풀이]
 * 분할 정복 문제로 가로, 세로 크기가 2^N인 정사각형를 4개의 부분 정사각형으로
 * 나누고, 계속 나누어서 가로, 세로 크기가 2인 정사각형이 될 때까지 문제를
 * 효율적으로 축소시키는게 필요하다.
 * 
 * 이를 위해서 정사각형을 부분 정사각형 4개로 나누어 방문순서 대로
 * 0, 1, 2, 3 사분면으로 생각한다.
 *
 * (1) 처음 N을 입력 받았을 때 크기를 size = 2^N 으로 저장한다.
 * (2) 지금 r행, c열이 어느 사분면에 위치하는지 확인한다.
 *     (quad = C + (2 * R) 을 통해서 알 수 있다)
 * (3) 현재 크기가 2인 경우 사분면 숫자만큼 방문한 순서가 됨으로 quad를 반환한다.
 * (4) 현재 크기가 2가 아닌 경우,
 *     전체 정사각형에서 부분 정사각형 4개로 나누고
 *     지금 r행, c열이 위치한 사분면 앞에까지 방문 횟수를 더하고 (size * size / 4 * quad)
 *     해당 사분면에 대해서 다시 solve 함수를 수행한다. (1) 번으로 회귀
 */

#include <cstdio>
#include <cmath>

int solve(int size, int r, int c) {
	int half = size / 2;
	char R = r < half ? 0 : 1;
	char C = c < half ? 0 : 1;
	int quad = C + (2 * R);

	if (size < 2) return 0;  // Exception
	if (size == 2) return quad;  // Most small case

	r -= half * R;
	c -= half * C;

	return (size * size / 4 * quad) + solve(half, r, c);
}

int main() {
	int N, r, c;

	scanf("%d %d %d", &N, &r, &c);

	printf("%d\n", solve(pow(2, N), r, c));

	return 0;
}
