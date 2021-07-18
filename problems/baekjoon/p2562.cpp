/*
 * (210718) 최댓값
 * https://www.acmicpc.net/problem/2562
 */

#include <cstdio>

int main() {
	int num, max_value=0, index=-1;
	
	for (int i=0; i<9; i++) {
		scanf("%d", &num);

		if (num > max_value) {
			max_value = num;
			index = i;
		}
	}

	printf("%d\n", max_value);
	printf("%d\n", index+1);

	return 0;
}
