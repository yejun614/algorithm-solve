/*
 * (210719) 1, 2, 3 더하기
 * https://www.acmicpc.net/problem/9095
 *
 * [풀이]
 * 숫자를 입력 받으면 반복문을 이용해서 1, 2, 3을 빼고, 해당 값을
 * 재귀함수로 넘겨준다. 재귀함수에서 0을 입력받으면 1을 반환
 * 최초 호출한 곳에서 반환값을 합쳐서 정답 값으로 반환한다.
 * 
 * [추가]
 * 동적계획법 문제 풀이 팁 (풀이 순서)
 * (1). 문제에서 점화식, 알고리즘을 구현하고, 정확성을 확인(증명) 한다.
 * (2). 동적계획법 적용이 가능한 부분을 찾는다.
 * (3). Top-down 혹은 Bottom-up 을 적용하여 최적화 한다.
 */

#include <cstdio>
#define MAX_VAL 12

int DP[MAX_VAL];

int solve(int num) {
	if (DP[num] >= 0) return ::DP[num];
	if (num <= 0) return 1;

	int count = 0;
	
	for (int i=1; i<=3; i++) {
		if (num-i < 0) continue;

		count += solve(num-i);
	}

	::DP[num] = count;
	return count;
}

int main() {
	// Cache Reset
	for (int i=0; i<MAX_VAL; i++) ::DP[i] = -1;

	int T, N;
	scanf("%d", &T);

	while (T--) {
		scanf("%d", &N);

		printf("%d\n", solve(N));
	}

	return 0;
}
