/*
 * (220914) 벌집
 * https://www.acmicpc.net/problem/2292
 *
 * [풀이]
 * 벌집은 제일 가운데에서 시작해서 바깥쪽으로 갈수록 번호가 커지는데
 * 자세히 살펴보면 한 둘레마다 +6 씩 커지는 등차수열이 누적되는 규칙이 있다.
 *
 * W: 1, 6, 12, 18, 24, 30, ...
 * X: 1, 7, 19, 37, 61, 91, ...
 *
 * X의 값은 한 둘레의 최대값과 같다는 것을 알 수 있다.
 *
 * 반복횟수를 count라고 할 때,
 * W는 6 * count로 계산 가능하다.
 *
 * 이러한 규칙을 활용해서 중앙 1에서 부터 N까지 몇개의 방을
 * 지나야 하는지 확인 가능하다.
 */

#include <cstdio>

int main() {
	unsigned int count = 1;
	unsigned long int N, X = 1;

	scanf("%lu", &N);

	while (X < N) {
		X += 6 * count++;
	}

	printf("%u\n", count);

	return 0;
}

