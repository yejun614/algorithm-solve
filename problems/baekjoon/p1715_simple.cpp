/*
 * (210708) 카드 정렬하기
 * https://www.acmicpc.net/problem/1715
 *
 * [풀이]
 * 카드의 비교횟수를 최소화하기 위해서는 비교 하려는
 * 카드 묶음간의 카드 개수 차이가 적어야 한다.
 *
 * 우선순위 큐를 이용하면 카드묶음 중 가장 개수가 적은
 * 카드 묶음을 얻을 수 있다. 이를 통해서 가장 적은 카드를 가지는
 * 카드묶음끼리 합쳐서 비교횟수를 최소화 할 수 있다.
 */

#include <cstdio>
#include <queue>
#include <functional>

using namespace std;

int main() {
	int N;
	scanf("%d", &N);

	priority_queue<int, vector<int>, greater<int> > cards;

	int buf;
	for (int i=0; i<N; i++) {
		scanf("%d", &buf);
		cards.push(buf);
	}

	unsigned long int answer = 0;

	while (cards.size() > 1) {
		buf = cards.top(); cards.pop();
		buf += cards.top(); cards.pop();

		answer += buf;
		cards.push(buf);
	}

	printf("%lu\n", answer);

	return 0;
}

