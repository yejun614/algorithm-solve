/*
 * (230112) 계단 오르기
 * https://www.acmicpc.net/problem/2579
 *
 * [풀이]
 * 우선 브루트포스 방식으로 풀어보는 게 좋다.
 * 문제에서 주어진 규칙을 바탕으로 재귀함수를 응용하여 완전탐색을 진행한다.
 * 이어서 Dynamic Programming 기법인 Memoization 방식으로 최적화하면 문제를 풀 수 있다.
 *
 * 마지막 계단은 항상 밟아야 함으로 뒤에서 부터 앞으로 진행한다
 *
 * 문제에서 주어진 규칙을 바탕으로 완전 탐색에서는
 * 다음 세가지 중 최대값이 나오는 방향으로 진행한다.
 *
 * 1. 바로 앞에 있는 계단을 밟는다.
 * 2. 계단 하나를 뛰어넘고 다음 계단을 밟는다.
 * 3. 바로 앞에 있는 두개의 계단을 차례대로 밟는다.
 *
 * 이동 후에 위치한 계단에서 한칸 앞으로 전진한 후 다음 이동을 진행한다
 *  -> 연속하여 세칸을 밟지 않도록 하기 위함이다.
 */

#include <cstdio>
#define MAX_COUNT 301
#define MAX(a, b) ((a) > (b) ? (a) : (b))

short Count;
short Stairs[MAX_COUNT];
int Cache[MAX_COUNT];

void CacheReset() {
	for (int i = 0; i < MAX_COUNT; ++i) {
		Cache[i] = -1;
	}
}

int Solve(short pos) {
	if (Cache[pos] > -1) return Cache[pos];

	int maxValue = 0;

	if (pos >= 1) maxValue = MAX(maxValue, Stairs[pos - 1] + Solve(pos - 2));
	if (pos >= 2) maxValue = MAX(maxValue, Stairs[pos - 2] + Solve(pos - 3));
	if (pos >= 2) maxValue = MAX(maxValue, Stairs[pos - 2] + Stairs[pos - 1] + Solve(pos - 3));

	Cache[pos] = maxValue;
	return maxValue;
}

int main() {
	scanf("%hd", &Count);

	for (int i = 0; i < Count; ++i) {
		scanf("%hd", Stairs + i);
	}

	CacheReset();
	printf("%d\n", Solve(Count));

	return 0;
}

