/*
 * (220926) Sport Climbing Combined
 * https://www.acmicpc.net/problem/23246
 *
 * [풀이]
 * 정렬하는 문제이다. 문제에서 제시된 조건을 자세히 읽어보고,
 * 비교 함수를 작성한 후 sort 함수를 호출하여 정렬한 다음
 * 0, 1, 2번째 선수를 출력하면 통과할 수 있다.
 */

#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

typedef struct _Player {
	int mul;
	int sum;
	int num;
} Player;

bool compare(Player A, Player B) {
	if (A.mul == B.mul) {
		if (A.sum == B.sum) return A.num < B.num;
		return A.sum < B.sum;
	}
	return A.mul < B.mul;
}

int main() {
	int N;
	scanf("%d", &N);

	int b, p, q, r;
	vector<Player> players(N);
	
	for (int i = 0; i < N; i++) {
		scanf("%d %d %d %d", &b, &p, &q, &r);

		players[i].num = b;
		players[i].mul = p * q * r;
		players[i].sum = p + q + r;
	}

	sort(players.begin(), players.end(), compare);

	printf("%d %d %d\n", players[0].num, players[1].num, players[2].num);

	return 0;
}

