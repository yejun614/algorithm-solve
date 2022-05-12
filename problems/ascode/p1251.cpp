/*
 * (220512) Binary Conversion
 * http://www.ascode.org/problem.php?id=1251
 */

#include <cstdio>
#include <cmath>
#include <cstring>

#define MAX_LEN 21

int main() {
  int T;
  scanf("%d", &T);

  char binary[MAX_LEN];

  while (T --) {
    scanf("%s", binary);
    const int size = strlen(binary);

    unsigned long long int result = 0;

    for (int i = 0; i < size; i ++) {
      if (binary[size - i - 1] == '0') continue;
      result += pow(2, i);
    }

    printf("%llu\n", result);
  }

  return 0;
}
