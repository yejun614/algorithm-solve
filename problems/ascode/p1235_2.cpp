/*
 * (220517) 소수 구하기 III
 * http://www.ascode.org/problem.php?id=1235
 *
 * [풀이]
 * N이 소수인지 판정하기 위해서 3부터 sqrt(N) 까지 검증하는 알고리즘
 */

#include <cstdio>
#include <cmath>

bool isPrime(int num) {
  if (num == 2) return true;
  if (num < 2 || num % 2 == 0) return false;

  int limit = sqrt(num);

  for (int i = 3; i <= limit; i += 2) {
    if (num % i == 0) return false;
  }

  return true;
}

void swap(int *a, int *b) {
  int temp = *a;
  *a = *b;
  *b = temp;
}

int main() {
  int T, num;
  scanf("%d", &T);

  while (T--) {
    int a, b;
    scanf("%d %d", &a, &b);

    if (a > b) swap(&a, &b);

    int count = 0;
    for (int i = a; i <= b; i ++) {
      if (isPrime(i)) count ++;
    }

    printf("%d\n", count);

  }

  return 0;
}
