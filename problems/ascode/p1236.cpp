/*
 * (220512) 잔혹한 물벼룩의 세계
 * http://www.ascode.org/problem.php?id=1236
 */

#include <cstdio>

int solve(int num) {
  int result = 0;

  while (num > 1) {
    num = (num % 2 == 0) ? (num / 2) : (3 * num + 1);
    result ++;
  }

  return result;
}

int main() {
  int T, num;
  scanf("%d", &T);

  while (T --) {
    scanf("%d", &num);
    printf("%d\n", solve(num));
  }

  return 0;
}
