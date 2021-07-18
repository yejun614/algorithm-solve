/*
 * (210717) 피보나치 함수
 * https://www.acmicpc.net/problem/1003
 *
 * [풀이]
 * 피보나치를 구하는 재귀함수 호출시 0과 1이 각각 몇번 출력되는지
 * 구하는 문제 입니다.
 *
 * 0과 1이 몇번 호출되는지 저장할 정수 배열을 만듭니다.
 * 동적계획법 Bottom-up 방식으로 진행하겠습니다.
 * 피보나치 점화식에 의해 원하는 답을 구할 수 있습니다. (소스코드 참고)
 */

#include <cstdio>
#define MAX 41

int main() {
	int DP0[MAX], DP1[MAX];

	DP0[0] = 1; DP1[0] = 0;
	DP0[1] = 0; DP1[1] = 1;
	DP0[2] = 1; DP1[2] = 1;

	for (int i=2; i<=MAX; i++) {
		DP0[i] = DP0[i-1] + DP0[i-2];
		DP1[i] = DP1[i-1] + DP1[i-2];
	}

	int T, N;
	scanf("%d", &T);

	while (T--) {
		scanf("%d", &N);

		printf("%d %d\n", DP0[N], DP1[N]);
	}

	return 0;
}
