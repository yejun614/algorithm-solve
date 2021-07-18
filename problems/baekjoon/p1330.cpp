/*
 * (210718) 두 수 비교하기
 * https://www.acmicpc.net/problem/1330
 */

#include <cstdio>

int main() {
	int A, B;
	scanf("%d %d", &A, &B);

	if (A > B) {
		printf(">\n");
	} else if (A < B) {
		printf("<\n");
	} else {
		printf("==\n");
	}

	return 0;
}
