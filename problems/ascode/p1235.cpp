/*
 * (220517) 소수 구하기 III
 * http://www.ascode.org/problem.php?id=1235
 *
 * [풀이]
 * 에라토스테네스의 체를 이용한 소수 판정 알고리즘
 */

#include <cstdio>
#define MAX_VAL 100001
#define LIMIT 316.2293471517152f  // sqrt(MAX_VAL)

bool *Primes;
void find_primes(int max_value) {
  Primes[0] = false;
  Primes[1] = false;
  for (int i = 2; i < max_value; i ++) Primes[i] = true;

  double limit = LIMIT;

  for (int i = 2; i <= limit; i ++) {
    if (!Primes[i]) continue;
    for (int j = i * i; j < max_value; j += i) Primes[j] = false;
  }
}

void swap(int *a, int *b) {
  int temp = *a;
  *a = *b;
  *b = temp;
}

int main() {
  Primes = new bool[MAX_VAL];
  find_primes(MAX_VAL);

  int T;
  scanf("%d", &T);

  while (T--) {
    int a, b;
    scanf("%d %d", &a, &b);
    
    if (a > b) swap(&a, &b);

    int count = 0;

    for (int i = a; i <= b; i ++) {
      if (Primes[i]) count ++;
    }

    printf("%d\n", count);
  }

  delete [] Primes;
  return 0;
}
