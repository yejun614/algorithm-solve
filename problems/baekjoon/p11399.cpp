/**
 * (210703) ATM
 * https://www.acmicpc.net/problem/11399
 */

#include <cstdio>
#include <cstdlib>

#include <algorithm>

using namespace std;

int main() {
	int N;
	scanf("%d", &N);

	int* people = (int*)malloc(sizeof(int)*N);
	for (int i=0; i<N; i++) {
		scanf("%d", people+i);
	}
	sort(people, people+N);

	int answer=0, buf=0;
	for (int i=0; i<N; i++) {
		buf += people[i];
		answer += buf;
	}

	printf("%d\n", answer);
	
	free(people);	
	return 0;
}

