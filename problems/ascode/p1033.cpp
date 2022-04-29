/*
 * (220429) 대소문자 변경 HARD
 * http://www.ascode.org/problem.php?id=1033
 */

#include <cstdio>
#include <cstdlib>

#define MAX_LEN 20001

int main() {
  int T, index;
  scanf("%d", &T);

  char *input = (char*)malloc(sizeof(char) * MAX_LEN);

  while (T --) {
    scanf("%s", input);

    index = 0;
    while (input[index] != '\0') {
      input[index] ^= 0x20;
      index ++;
    }

    printf("%s\n", input);
  }

  free(input);
  return 0;
}
