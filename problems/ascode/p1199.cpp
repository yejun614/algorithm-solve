/*
 * (220429) 계산기 만들기 - Entry
 * http://www.ascode.org/problem.php?id=1199
 */

#include <cstdio>
#include <cmath>

int main() {
  int T;
  scanf("%d", &T);

  while (T--) {
    int numA, numB;
    char op;

    scanf("%d %c %d", &numA, &op, &numB);

    if (op == '+') {
      printf("%d\n", numA + numB);

    } else if (op == '-') {
      printf("%d\n", numA - numB);

    } else if (op == 'x') {
      printf("%d\n", numA * numB);

    } else if (op == '/' && numB != 0) {
      double result = (double)numA / (double)numB;

      result *= 100000;
      result = round(result);
      result /= 100000;

      printf("%.5lf\n", result);
    } else if (op == '%' && numB != 0) {
      printf("%d\n", numA % numB);
    } else {
      printf("Mission Impossible\n");
    }
  }

  return 0;
}
