/*
 * (220512) 수 뒤집기 2
 * http://www.ascode.org/problem.php?id=1250
 */

#include <cstdio>

int numberReverse(int num) {
  int result = 0;

  while (num > 0) {
    result *= 10;
    result += num % 10;
    num /= 10;
  }

  return result;
}

int main() {
  int T, N;
  scanf("%d", &T);

  while (T --) {
    scanf("%d", &N);
    int num = N + numberReverse(N);
    int numReverse = numberReverse(num);

    printf("%s\n", num == numReverse ? "YES" : "NO");
  }

  return 0;
}
