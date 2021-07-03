/**
 * (210703) 동전 0
 * https://www.acmicpc.net/problem/11047
 */

#include <cstdio>
#include <cstdlib>

int main() {
	int N, K;
	scanf("%d %d", &N, &K);

	int* coins = (int*)malloc(sizeof(int)*N);

	for (int i=0; i<N; i++)
		scanf("%d", coins+i);

	int answer = 0;
	while (N--) {
		answer += K / coins[N];
		K %= coins[N];
	}

	printf("%d\n", answer);

	free(coins);
	return 0;
}

