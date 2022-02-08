/*
 * (220208) 영화감독 숌
 * https://www.acmicpc.net/problem/1436
 *
 * [풀이]
 * 단순하게 무조건 666이라는 숫자가 들어가는 수를 탐색하는 문제이다.
 * Brute force 문제로 실행시간 또한 2초 이다.
 *
 * 0666, ... 5666, 6660, ..., 6669, 16660, ..., 16669, 26660, ..., 66600, 66601, ..., 66666, ..., 166699
 *
 * [실수]
 * 난 이 문제에서 규칙성을 찾아낼려고 한참동안 고민했는데, 사실 문제의 의도는
 * 단순 반복 프로그램을 구현하는 것 이었다. 너무 단순해서 답이 아니라고 생각한게 실수.
 */

#include <cstdio>

inline bool check(int num) {
  int count = 0;

  while (num > 0) {
    if (count == 3) {
      break;
    } else if (num % 10 == 6) {
      count ++;
    } else {
      count = 0;
    }

    num /= 10;
  }

  return count == 3;
}

int main() {
  int N;
  scanf("%d", &N);

  int answer = 665;

  while (N > 0) {
    answer ++;

    if (check(answer)) {
      N --;
    }
  }
  
  printf("%d\n", answer);

  return 0;
}
