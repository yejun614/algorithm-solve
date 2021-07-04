/**
 * (210704) 로프
 * https://www.acmicpc.net/problem/2217
 *
 * [풀이]
 * 로프문제는 각 로프를 선택해서 최대중량을 알아내는 문제입니다.
 * 여기서 최대중량은 선택한 로프 중 최소중량을 가진 로프의
 * 중량에 선택한 로프의 개수를 곱하면 얼마나 중량을 버틸수 있는지
 * 계산할 수 있습니다.
 *
 * 어떤 로프들을 사용해야 최대중량을 얻을 수 있는지 알고리즘은
 * 다음과 같습니다.
 *
 * 1) 입력받은 숫자들을 배열에 넣고 오름차순으로 정렬합니다.
 * 2) 차례대로 배열을 순회하면서 (로프의 개수)-(현재 index)
 *    를 곱하고 이를 저장합니다.
 * 3) 배열안에서 최대값을 정답으로 출력합니다.
 */

#include <cstdio>
#include <cstdlib>
#include <algorithm>
using namespace std;

int main() {
	int N;
	scanf("%d", &N);

	int* ropes = (int*)malloc(sizeof(int)*N);

	for (int i=0; i<N; i++) {
		scanf("%d", ropes+i);
	}
	sort(ropes, ropes+N);

	for (int i=0; i<N; i++) {
		ropes[i] *= N-i;
	}

	printf("%d\n", *max_element(ropes, ropes+N));

	free(ropes);
	return 0;
}

