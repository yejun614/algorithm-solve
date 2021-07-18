/*
 * (210718) 문자열 반복
 * https://www.acmicpc.net/problem/2675
 */

#include <cstdio>
#include <cstring>

#define MAX_LEN 21

int main() {
	int T, R;
	scanf("%d", &T);

	char str[MAX_LEN];
	strcpy(str, "");

	while (T--) {
		scanf("%d %s", &R, str);

		int i = 0;
		
		while (str[i] != '\0') {
			for (int n=0; n<R; n++) printf("%c", str[i]);
			i ++;
		}
		printf("\n");
	}

	return 0;
}
