/**
 * (210703) 회의실 배정
 * https://www.acmicpc.net/problem/1931
 */

#include <cstdio>
#include <set>
using namespace std;

int main() {
	int N;
	scanf("%d", &N);

	multiset<pair<int, int> > meets;
	int start, end;

	for (int i=0; i<N; i++) {
		scanf("%d %d", &start, &end);
		meets.insert(make_pair(end, start));
	}

	int answer=0, current=0;
	multiset<pair<int, int> >::iterator it=meets.begin();

	while (it != meets.end()) {
		for (it=meets.begin(); it!=meets.end(); it++) {
			if (it->second >= current) {
				current = it->first;
				answer++;

				meets.erase(it);
				break;
			}
		}
	}

	printf("%d\n", answer);

	return 0;
}

