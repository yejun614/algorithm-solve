/*
 * (210706) 주유소
 * https://www.acmicpc.net/problem/13305
 *
 * [풀이]
 * 이 문제의 핵심은 최소 비용으로 최대거리를 이동하는 것이다.
 * (1) 각 도시를 순회하면서 첫 도시보다 비용이 저렴한 도시까지 이동한다.
 * (2) 첫 도시에 비해 비용이 저렴한 도시까지의 거리에 첫 도시의 가격을 곱해서
 *     정답 변수에 저장한다.
 * (3) 비용이 저렴한 도시를 기준으로 더 비용이 저렴한 도시까지 이동한다.
 *     (혹은 제일 마지막 도시에 도착할때까지 이동)
 * (4) 다시 비용시 더욱 저렴한 도시까지의 거리에 기준 도시의 가격을 곱하고
 *     정답변수에 더해서 저장한다.
 * (5) 3번~4번 반복 후 순회가 끝나면 정답 변수를 출력한다.
 */

#include <cstdio>
#include <cstdlib>

typedef unsigned long int lu;
typedef unsigned long long int llu;

int main() {
	int N;
	scanf("%d", &N);

	lu* roads = (lu*)malloc(sizeof(lu)*N);
	lu* cities = (lu*)malloc(sizeof(lu)*N);

	roads[0] = 0;
	for (int i=1; i<N; i++) scanf("%lu", roads+i);
	for (int i=0; i<N; i++) scanf("%lu", cities+i);

	int current = 0, city = 0;
	llu answer = 0;

	for (int i=0; i<N; i++) {
		current += roads[i];

		if (cities[i] < cities[city] || i == N-1) {
			answer += current * cities[city];

			current = 0;
			city = i;
		}
	}

	printf("%llu\n", answer);

	free(roads);
	free(cities);
	return 0;
}

