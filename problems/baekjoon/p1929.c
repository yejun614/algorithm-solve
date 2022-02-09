/*
 * (220209) 소수 구하기
 * https://www.acmicpc.net/problem/1929
 *
 * [풀이]
 * 이번 문제는 범위 내에 있는 소수를 빠르게 찾아내는 문제이다.
 * 중요한 점은 실행 속도를 최대한 빠르게 끌어내는 것.
 *
 * 소수를 빠르게 구하는 방법의 핵심의 수학 루트를 이용하는 것이다.
 * 소수의 대략적인 정의는 1과 자기자신으로만 나누어 떨어지는 수 이다.
 * 따라서 N이 소수인지 확인하기 위해서는 1부터 N까지 나누어 봐야 하지만,
 * 실제로는 1부터 루트 N까지 나누어 보는 것으로 충분하다.
 *
 * 또 한가지 속도를 빠르게 하는 방법은 에라토스테네스의 체 원리 인데,
 * 이번 문제처럼 주어진 범위내의 모든 소스를 구하는 경우, 소수의 배수는
 * 소수가 아니라는 법칙을 이용하면 더욱 빠르게 계산할 수 있다.
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define MAX 1000000

int *cache, cache_index = 0;

void isPrime(int num) {
  const int check_limit = sqrt(num);

  for (int i = 0; i < cache_index; i ++) {
    if (cache[i] > check_limit) break;
    if (num % cache[i] == 0) return;
  }

  cache[cache_index ++] = num;
}

int main() {
  int M, N;
  scanf("%d %d", &M, &N);

  cache = (int*)malloc(sizeof(int) * MAX);

  for (int i = 2; i <= N; i ++)
    isPrime(i);

  for (int i = 0; i < cache_index; i ++) {
    if (cache[i] < M) continue;
    printf("%d\n", cache[i]);
  }
  
  free(cache);
  return 0;
}
