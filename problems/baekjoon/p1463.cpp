/*
 * (210714) 1로만들기
 * https://www.acmicpc.net/problem/1463
 *
 * [풀이]
 * 동적계획법 알고리즘이 활용되는 문제입니다.
 * 문제에서 제시된 세가지 연산법 부분문제로 가지고 캐시에 연산결과를 저장하고
 * 다음 부분문제 진행시 기존 연산결과들을 바탕으로 더 빠르게 계산할 수 있습니다.
 * 
 * make_caches 함수는 Bottom-up(상향식)으로 설계 되었습니다.
 * 메모이제이션으로 불리는 Top-down(하양식)으로 설계할 경우 재귀함수 스택제한
 * 으로 인해 실행도중 스택오버플로우가 발생할 수 있으니 주의해야 합니다.
 */

#include <cstdio>
#include <algorithm>
#define MAX 1000002

int DP[MAX];

inline void DP_reset() {
	for (int i=0; i<MAX; i++) {
		::DP[i] = MAX;
	}
}

inline int min(int A, int B) {
	return A < B ? A : B;
}

void make_caches(int N) {
	::DP[1] = 0;
	::DP[2] = 1;
	::DP[3] = 1;

	int i = 0;
	while (i <= N) {
		if (::DP[i] != MAX) {
			if (i*3 <= MAX) ::DP[i*3] = min(::DP[i]+1, ::DP[i*3]);
			if (i*2 <= MAX) ::DP[i*2] = min(::DP[i]+1, ::DP[i*2]);
			if (i+1 <= MAX) ::DP[i+1] = min(::DP[i]+1, ::DP[i+1]);
		}

		i ++;
	}
}

int main() {
	int N;
	scanf("%d", &N);

	DP_reset();
	make_caches(N);

	printf("%d\n", ::DP[N]);

	return 0;
}
