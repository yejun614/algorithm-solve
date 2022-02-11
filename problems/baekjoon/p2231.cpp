/*
 * (220211) 분해합
 * https://www.acmicpc.net/problem/2231
 *
 * [풀이]
 * 문제에서는 분해합이라는 개념을 소개한다. M의 분해합이 N인 경우, M을 N의 생성자
 * 라고 한다. N이 주어졌을 때 N의 생성자를 찾아내야 한다.
 *
 * 단순 Brute force 문제 임으로 1부터 N까지 생성자를 구하여 N과 동일하면
 * 해당 수를 정답으로 제출한다.
 *
 * [실수]
 * (+) 처음에 분해합가 아니라 생성자가 입력된 줄 알고 삽질했음.
 * (+) Brute force로 풀면 되는데 규칙 생각하다가 시간 날렸다.
 */

#include <cstdio>

int main() {
  int N, answer = 0;
  scanf("%d", &N);

  int num, current;

  for (int i = 1; i < N; i ++) {
    num = i;
    current = i;

    while (num > 0) {
      current += num % 10;
      num /= 10;
    }

    if (current == N) {
      answer = i;
      break;
    }
  }
  printf("%d\n", answer);
  
  return 0;
}
