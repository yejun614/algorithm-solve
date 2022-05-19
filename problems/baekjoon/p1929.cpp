/*
 * (220519) 소수 구하기
 * https://www.acmicpc.net/problem/1929
 *
 * [풀이]
 * 에라토스테네스의 체를 이용해서 소수를 판별한다.
 */

#include <cstdio>
#include <cmath>
#define MAX_VAL 1000001

bool *Primes;
void find_primes(int max_value) {
  Primes[0] = false;
  Primes[1] = false;
  for (int i = 2; i < max_value; i ++) Primes[i] = true;

  int limit = sqrt(max_value);

  for (int i = 2; i <= limit; i ++) {
    if (!Primes[i]) continue;
    for (int j = i * i; j < max_value; j += i) Primes[j] = false;
  }
}

int main() {
  Primes = new bool[MAX_VAL];
  find_primes(MAX_VAL);

  int a, b;
  scanf("%d %d", &a, &b);

  for (int i = a; i <= b; i ++)
    if (Primes[i]) printf("%d\n", i);

  delete [] Primes;
  return 0;
}
