/*
 * (210718) 음계
 * https://www.acmicpc.net/problem/2920
 */

#include <cstdio>

int main() {
	int prev, note;
	bool up = false, down = false;

	scanf("%d", &prev);

	for (int i=0; i<7; i++) {
		scanf("%d", &note);

		if (note > prev) {
			up = true;
		} else {
			down = true;
		}

		prev = note;
	}

	if (up && down) {
		printf("mixed\n");
	} else if (up) {
		printf("ascending\n");
	} else {
		printf("descending\n");
	}

	return 0;
}
