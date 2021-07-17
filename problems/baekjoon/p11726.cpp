/*
 * (210717) 2xn 타일링
 * https://www.acmicpc.net/problem/11726
 *
 * [풀이]
 * 피보나치 점화식이랑 유사하게 i-1 값과 i-2값을 합하면 원하는 값을 구할 수 있다.
 * 하지만 1000까지 진행하면 오버플로우가 발생하기 때문에 문제에 명시된 것 처럼
 * 10007의 나머지를 이용함으로서 문제를 해결할 수 있다.
 */

#include <cstdio>
#define MAX 1001

int main() {
	int N;
	scanf("%d", &N);

	int DP[MAX];
	DP[0] = 0;
	DP[1] = 1;
	DP[2] = 2;

	for (int i=3; i<=N; i++) {
		DP[i] = (DP[i-1]+DP[i-2])%10007;
	}

	printf("%d\n", DP[N]);

	return 0;
}
