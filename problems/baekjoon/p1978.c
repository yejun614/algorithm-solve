/*
 * (220209) 소수 찾기
 * https://www.acmicpc.net/problem/1978
 *
 * [풀이]
 * 소수를 빠르게 찾아내는 문제이다.
 * N이 소수인지 아닌지 알고 싶다면, 2부터 루트 N까지만
 * 반복해서 나누어 떨어지는 수가 있다면 소수가 아니다.
 */

#include <stdio.h>
#include <math.h>

#define MAX 100

char isPrime(int num) {
  if (num < 2) return 0;

  const int check_limit = sqrt(num);
  
  for (int i = 2; i <= check_limit; i ++) {
    if (num % i == 0) return 0;
  }

  return 1;
}

int main() {
  short N, number, count = 0;
  scanf("%hd", &N);

  for (int i = 0; i < N; i ++) {
    scanf("%hd", &number);

    if (isPrime(number)) count ++;
  }

  printf("%d\n", count);

  return 0;
}
