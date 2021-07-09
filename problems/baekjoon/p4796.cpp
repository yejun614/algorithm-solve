/*
 * (210709) 캠핑
 * https://www.acmicpc.net/problem/4796
 *
 * [풀이]
 * 단순 공식으로 해결가능 합니다.
 *
 * Alpha = V % P (단, Alpha값은 최대 L값이다. 초과할 경우 L값 대입)
 * 정답) Alpha + (V/P * L)
 */

#include <cstdio>

int main() {
	int L, P, V;
	int answer, testcase=1;

	while (true) {
		scanf("%d %d %d", &L, &P, &V);
		if (L+P+V == 0) break;

		answer = V%P > L ? L : V%P;
		answer += L * (V/P);

		printf("Case %d: %d\n", testcase++, answer);
	}

	return 0;
}
 
