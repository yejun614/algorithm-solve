/*
 * (221229) 미로 탐색
 * https://www.acmicpc.net/problem/2178
 *
 * [풀이]
 * 미로를 탈출하기 위한 최소의 이동거리를 구하는 문제이다.
 * 문제에서 입력되는 미로의 크기가 가로, 세로 100을 넘지 않기 때문에
 * 시간 복잡도가 O(100^2)으로 계산된다. 따라서 완전탐색 하더라도 시간 초가 되지 않는다.
 * 때문에 너비우선탐색(BFS)를 사용하여 해결 가능하며 탐색도중 목적지에 처음 도달하는
 * 경로가 최단거리가 된다.
 *
 * BFS 구현 방법은 아래 코드를 참고
 *
 * [실수]
 * (+) 처음에 A* 알고리즘을 사용해서 문제를 풀려고 했으나 해당 알고리즘은 내부적으로
 *     휴리스틱 알고리즘이 적용되는 탓에 항상 최단거리를 보장하지 못한다.
 *     반면, BFS 알고리즘은 항상 최단 거리를 보장함으로 이 문제에서는 BFS가 사용되어야 한다.
 */

#include <cstdio>
#include <queue>
using namespace std;

#define SIZE 101

typedef struct POS {
	int x;
	int y;
} POS;

short DIR_X[] = {  0, -1,  1,  0 };
short DIR_Y[] = { -1,  0,  0,  1 };

int Height = 0, Width = 0;

char Board[SIZE][SIZE];
short Prev[SIZE][SIZE];
bool Visited[SIZE][SIZE];

int main() {
	// INPUT
	scanf("%d %d", &Height, &Width);

	for (int y = 0; y < Height; ++y) {
		scanf("%s", Board[y]);
	}

	// Reset visited array
	for (int y = 0; y < Height; ++y) {
		for (int x = 0; x < Width; ++x) {
			Visited[y][x] = false;
		}
	}

	// Set start and end positions
	POS start = { 0, 0 };
	POS end = { Width - 1, Height - 1 };

	// Set queue
	queue<POS> nodes;
	nodes.push(start);

	// Set visited at start position
	Visited[0][0] = true;

	// Loop
	while (!nodes.empty()) {
		POS current = nodes.front();
		nodes.pop();

		if (current.y == (Height - 1) && current.x == (Width - 1)) {
			break;
		}

		for (int i = 0; i < 4; ++i) {
			POS next = current;
			next.x += DIR_X[i];
			next.y += DIR_Y[i];

			if (next.x < 0 || next.x >= Width || next.y < 0 || next.y >= Height) {
				continue;
			} else if (Visited[next.y][next.x]) {
				continue;
			} else if (Board[next.y][next.x] == '0') {
				continue;
			} else {
				nodes.push(next);

				Visited[next.y][next.x] = true;
				Prev[next.y][next.x] = i;
			}
		}
	}

	// Find shortest path
	int answer = 0;
	POS current = end;

	while (current.x != start.x || current.y != start.y) {
		++answer;

		int dir = Prev[current.y][current.x];
		current.x -= DIR_X[dir];
		current.y -= DIR_Y[dir];
	}

	// OUTPUT
	printf("%d\n", ++answer);

	// EXIT
	return 0;
}

