/**
 * (210705) 전자레인지
 * https://www.acmicpc.net/problem/10162
 *
 * [풀이]
 * 거스름돈 동전의 최소개수를 출력하는 문제와 크게 다를게 없다.
 * 각 버튼의 시간을 나누고 나머지를 저장하여 풀 수 있다.
 */

#include <cstdio>

const int BTN[] = {300, 60, 10};

int main() {
	int T;
	scanf("%d", &T);

	int answer[] = {0, 0, 0};

	for (int i=0; i<3; i++) {
		answer[i] = T / BTN[i];
		T %= BTN[i];
	}

	// result
	if (T > 0) {
		printf("-1\n");
	} else {
		printf("%d %d %d\n", answer[0], answer[1], answer[2]);
	}

	return 0;
}

