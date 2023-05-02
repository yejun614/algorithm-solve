/*
 * (230502) 구간 합 구하기 4
 * https://www.acmicpc.net/problem/11659
 *
 * [풀이]
 * 구간 합 문제로 필승법이 존재한다. 바로 배열을 입력받은 후에 현재 요소에
 * 이전 요소를 더해서 배열을 구성하는 것이다. 자세한 내용은 아래 풀이 참고.
 *
 * [실수]
 * arr 자료형을 short으로 했었는데, 계산과정에서 오버플로우가 발생해서
 * 문제를 틀렸다. int형으로 맞춰주니 통과.
 */

#include <stdio.h>

int arr[100001];

int main() {
  int N, M;
  scanf("%d %d", &N, &M);

  for (int i = 1; i <= N; ++i) {
    scanf("%d", arr + i);
    
    if (i > 0) {
      arr[i] += arr[i - 1];
    }
  }

  int i, j;
  while (M--) {
    scanf("%d %d", &i, &j);
    printf("%d\n", arr[j] - arr[i - 1]);
  }

  return 0;
}
