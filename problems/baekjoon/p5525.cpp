/*
 * (230126) IOIOI
 * https://www.acmicpc.net/problem/5525
 *
 * [풀이]
 * 이 문제는 주어진 문자열안에 규칙이 있는 부분 문자열이 (IOI...)
 * 몇개나 포함될 수 있는지 알아내는 문제이다.
 *
 * 이 문제의 부분 문자열은 IOI... 가 반복되는 규칙이 있어
 * 문제어서 주어진 문자열에서 규칙적으로 반복되는 (IOI...) 부분 문자열을 찾아내고
 * 부분 문자열에 포함된 I개수에 -1을 하면 해당 부분 문자열에 P(1)이 몇개
 * 포함될 수 있는지 알 수 있다.
 *
 * (예시)
 * OOIOIOIOIIOII
 *   |-----|
 *   IOIOIOI
 *
 * IOIOIOI 에는 I가 4개 포함되어 있고 I-1 을 하면 3이 된다.
 * P(1) = IOI 가 부분 문자열인 IOIOIOI에 3번 포함될 수 있다는 사실을 알 수 있다.
 *                             |-|-|-|
 *
 * 여기서 조금 더 확장하여 부분 문자열에 P(N)이 몇번 포함되었는지 어떻게 알 수 있을까?
 * I개수 - 1 한 것에서 (1 - N)을 더해주면 알 수 있다.
 *
 * (I개수 - 1) + (1 - N) => P(N)이 부분 문자열에 몇번 포함될 수 있는지 개수
 * 
 * 실제로 해당 공식을 사용하기 위한 조건들과 이를 C 방식으로 구현하는 법은
 * 아래 소스코드의 calc 함수를 참고하자.
 *
 * 해당 문제를 풀이하는 방법은 주어진 문자열에서 규칙이 있는 부분 문자열들을 찾아내어
 * 각 부분 문자열에 P(N)이 몇개 포함될 수 있는지 계산하여 이를 모두 합한 값이 정답이 된다.
 */

#include <cstdio>

#define MAX_SIZE 1000001
#define ODD(n) ((n) & 1)
#define POSITIVE(n) ((n) < 0 ? 0 : (n))

char Str[MAX_SIZE];

int calc(int m, int n) {
	if (m < 3) return 0;
	if (!ODD(m)) --m;
	return POSITIVE((m / 2) + (1 - n));
}

int main() {
	int N, M;

	scanf("%d", &N);
	scanf("%d", &M);
	scanf("%s", Str);

	int answer = 0, count = 0;

	for (char *ch = Str; *ch != '\0'; ++ch) {
		if (*ch == 'I') {
			// ch == 'I'

			if (!ODD(count)) {
				++count;
			} else {
				answer += calc(count, N);
				count = 1;
			}

		} else {
			// ch == 'O'
			
			if (count == 0) {
				continue;
			} else if (ODD(count)) {
				++count;
			} else {
				answer += calc(count, N);
				count = 0;
			}
		}
	}

	if (count > 0) answer += calc(count, N);
	printf("%d\n", answer);

	return 0;
}

