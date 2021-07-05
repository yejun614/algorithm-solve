/**
 * (210705) 신입 사원
 * https://www.acmicpc.net/problem/1946
 *
 * [풀이]
 * (1) 등수를 set에 pair형으로 저장하여 오름차순 정렬한다.
 * (2) set을 순회하면서 기존 등급(A, B) 보다 높은 등급을 가지고 있으면
 *     등급을 업데이트하고 정답 변수를 카운트 한다.
 * (3) 마지막에 카운트한 정답 변수를 출력한다.
 */

#include <cstdio>
#include <set>
using namespace std;

typedef set<pair<int, int> > set_pair;

void solve() {
	int N;
	scanf("%d", &N);

	set_pair rank;
	int A, B;

	for (int i=0; i<N; i++) {
		scanf("%d %d", &A, &B);
		rank.insert(make_pair(A, B));
	}

	int answer = 0;
	bool check;

	A=N;
	B=N;

	for (set_pair::iterator it=rank.begin(); it!=rank.end(); it++) {
		check = false;

		if (it->first < A) {
			A = it->first;
			check = true;
		}

		if (it->second < B) {
			B = it->second;
			check = true;
		}

		if (check) {
			answer++;
		}
	}

	printf("%d\n", answer);
}

int main() {
	int T;
	scanf("%d", &T);

	while (T--) {
		solve();
	}

	return 0;
}

