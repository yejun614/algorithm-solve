/*
 * (210705) 수들의 합
 * https://www.acmicpc.net/problem/1789
 *
 * [풀이]
 * 1부터 N까지의 합 공식인 N*(N+1)/2 을 이용한다.
 * 입력받은 S에 루트를 씌운 값을 저장하고 이를 answer에 저장한다.
 * answer를 1씩 키워나가면서 1~answer 까지의 합이 S보다 커지는 순간에
 * 반복문을 멈추고 answer값을 출력한다.
 */

#include <cstdio>
#include <cmath>

int main() {
	unsigned long int S;
	scanf("%lu", &S);

	unsigned long int answer = sqrt(S);

	while ((answer+1)*(answer+2)/2 <= S) {
		answer++;
	}

	printf("%lu\n", answer);

	return 0;
}

