/*
 * (230224) 양팔저울
 * https://www.acmicpc.net/problem/25943
 *
 * [풀이]
 * 문제에서 제시된 규칙대로 저울 왼쪽과 오른쪽에 최종 무개를 구하고,
 * 왼쪽과 오른쪽 무개를 뺀 절대값에서 무개추를 무거운 순으로 나누어
 * 무개추의 개수를 구한다.
 *
 * 거스름돈을 최소화하는 문제와 비슷한 유형이다.
 */

#include <cstdio>
#include <cstdlib>

int main() {
  int N;
  scanf("%d", &N);

  int num;
  int left = 0, right = 0;

  while (N--) {
    scanf("%d", &num);

    if (left <= right) {
      left += num;
    } else {
      right += num;
    }
  }

  left = abs(left - right);

  int answer = 0;
  const int W[] = { 100, 50, 20, 10, 5, 2, 1 };

  for (int weight : W) {
    answer += left / weight;
    left %= weight;
  }

  printf("%d\n", answer);

  return 0;
}
