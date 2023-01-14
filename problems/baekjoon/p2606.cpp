/*
 * (230115) 바이러스
 * https://www.acmicpc.net/problem/2606
 *
 * [풀이]
 * 그래프 자료구조가 입력으로 들어오고 1번 노드와 연결된 노드들의 개수를
 * 세는 문제이다. 너비우선탐색 혹은 깊이우선탐색을 통해서 해결가능하며
 * 노드의 개수는 100개 이하 임으로 깊이우선탐색을 구현할 때 재귀함수를 사용해도
 * 무관하다.
 *
 * 문제에서 1번 컴퓨터를 통해 감염된 컴퓨터의 수를 출력하라고 하였음으로
 * 1번 컴퓨터 그 자체는 제외해야 한다. (main 함수에서 "solve(1) - 1" 하는 이유)
 */

#include <cstdio>
#define MAX_COUNT 101

short Count;
short Pair;
bool Graph[MAX_COUNT][MAX_COUNT];
bool Visited[MAX_COUNT];

void ResetArr() {
	for (int y = 0; y < MAX_COUNT; ++y) {
		for (int x = 0; x < MAX_COUNT; ++x) {
			Graph[y][x] = false;
		}

		Visited[y] = false;
	}
}

int solve(short index) {
	if (Visited[index]) return 0;
	Visited[index] = true;

	int result = 1;

	for (short child = 0; child < MAX_COUNT; ++child) {
		if (!Graph[index][child]) continue;
		result += solve(child);
	}

	return result;
}

int main() {
	ResetArr();

	scanf("%hd", &Count);
	scanf("%hd", &Pair);

	short comA, comB;

	for (int i = 0; i < Pair; ++i) {
		scanf("%hd %hd", &comA, &comB);

		Graph[comA][comB] = true;
		Graph[comB][comA] = true;
	}

	printf("%d\n", solve(1) - 1);

	return 0;
}

