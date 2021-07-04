/**
 * (210704) 거스름돈
 * https://www.acmicpc.net/problem/5585
 *
 * [풀이]
 * 거스름돈 문제는 각 동전을 내림차순으로 나누면 몇개가
 * 필요한지 알 수 있다. 각 단계에서 나눗셈과 모듈러연산을
 * 통해 구현 가능하다.
 */

#include <cstdio>

const int coin[] = {500, 100, 50, 10, 5, 1};

int main() {
	int money, answer=0;

	scanf("%d", &money);
	money = 1000 - money;

	for (int i=0; i<6; i++) {
		if (money <= 0) break;

		answer += money / coin[i];
		money %= coin[i];
	}

	printf("%d\n", answer);

	return 0;
}

