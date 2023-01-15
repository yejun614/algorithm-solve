/*
 * (230115) 색종이 만들기
 * https://www.acmicpc.net/problem/2630
 *
 * [풀이]
 * 전형적인 분할정복 문제로 입력되는 행렬 크기가 작기 때문에
 * 재귀함수로 충분히 해결 가능하다.
 */

#include <cstdio>
#define MAX_SIZE 200

short Size;
short WhiteCount = 0;
short BlueCount = 0;
char Board[MAX_SIZE][MAX_SIZE];

bool CheckBoard(short ypos, short xpos, short size) {
	char check = Board[ypos][xpos];

	for (short y = ypos; y < ypos + size; ++y) {
		for (short x = xpos; x < xpos + size; ++x) {
			if (Board[y][x] != check) {
				return false;
			}
		}
	}

	return true;
}

void Solve(short ypos, short xpos, short size) {
	if (size <= 0) {
		return;

	} else if (size == 1 || CheckBoard(ypos, xpos, size)) {
		if (Board[ypos][xpos] == '0') {
			++WhiteCount;
		} else {
			++BlueCount;
		}

		return;
	}

	size /= 2;

	Solve(ypos, xpos, size);               // top, left
	Solve(ypos, xpos + size, size);        // top, right
	Solve(ypos + size, xpos, size);        // bottom, left
	Solve(ypos + size, xpos + size, size); // bottom, right
}

int main() {
	scanf("%hd", &Size);

	for (short y = 0; y < Size; ++y) {
		for (short x = 0; x < Size; ++x) {
			scanf("%*c%c", &Board[y][x]);  // ignore the straight ahead a character
			                               // (likes white spaces or new line)
		}
	}

	Solve(0, 0, Size);

	printf("%hd\n", WhiteCount);
	printf("%hd\n", BlueCount);

	return 0;
}

