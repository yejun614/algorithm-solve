/*
 * (220512) 수 뒤집기
 * http://www.ascode.org/problem.php?id=1249
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
  int T;
  scanf("%d", &T);

  while (T --) {
    int number, reversed;

    scanf("%d", &number);
    reversed = numberReverse(number);

    printf("%d\n", number > reversed ? number : reversed);
  }

  return 0;
}
