/*
 * (210718) 숫자의 합
 * https://www.acmicpc.net/problem/11720
 */

#include <cstdio>

int main() {
	int N;
	scanf("%d", &N);

	char str[100] = "";
	scanf("%s", str);

	int i = 0, answer = 0;
	while (str[i] != '\0') {
		answer += str[i] - '0';
		i ++;
	}

	printf("%d\n", answer);

	return 0;
}
