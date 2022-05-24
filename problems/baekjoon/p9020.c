/*
 * (220519) 골드바흐의 추측
 * https://www.acmicpc.net/problem/9020
 *
 * [풀이]
 * 골드바흐의 추측은 2보다 큰 모든 짝수는 두 소수의 합으로 나타낼 수 있다는 이론이다.
 * 증명은 아직이지만 반례가 존재하지 않아 거의 자명하다.
 *
 * 에라토스테네스의 체를 이용해서 제한 범위까지 소수를 구한 후 저장한다.
 * N이 입력되었을 때 N/2 부터 3까지 반복하면서 두 소수의 합으로 나타낼 수 있는
 * 수를 찾는다.
 *
 * 이 때 문제 조건에 따라서 출력시 작은 수가 앞에 출력되어야 함에 주의한다.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define MAX_VAL 10001
#define MIN(a, b) (a) < (b) ? (a) : (b)
#define MAX(a, b) (a) > (b) ? (a) : (b)

char *Primes;
void find_primes(int max_values) {
  memset(Primes, 1, max_values);
  Primes[0] = 0;
  Primes[1] = 0;

  int limit = sqrt(max_values);

  for (int i = 2; i <= limit; i ++) {
    if (!Primes[i]) continue;
    for (int j = i * i; j < max_values; j += i) Primes[j] = 0;
  }
}

int main() {
  Primes = (char*)malloc(sizeof(char) * MAX_VAL);
  find_primes(MAX_VAL);

  int T, num;
  scanf("%d", &T);

  while (T--) {
    scanf("%d", &num);

    for (int i = (num/2+1); i >= 0; i --) {
      if (Primes[i] && Primes[num-i]) {
        printf("%d %d\n", MIN(i, num-i), MAX(i, num-i));
        break;
      }
    }
  }

  free(Primes);
  return 0;
}
