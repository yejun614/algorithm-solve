/*
 * (220914) 손익분기점
 * https://www.acmicpc.net/problem/1712
 *
 * [풀이]
 * 문제 내용을 잘 읽고 부등식으로 만들어서 정리하면
 * 공식이 도출됩니다.
 *
 * 공식: n = (A / (C - B)) + 1
 * 조건: n > 0 && C != B
 */

#include <cstdio>

int main() {
	int A, B, C;
	scanf("%d %d %d", &A, &B, &C);

	if (B == C) {
		printf("-1\n");

	} else {
		int n = A / (C - B);

		printf("%d\n", n < 0 ? -1 : n + 1);
	}

	return 0;
}

