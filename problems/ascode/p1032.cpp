/*
 * (220429) 대소문자 변경 #2
 * http://www.ascode.org/problem.php?id=1032
 */

#include <cstdio>
#include <cstdlib>

#define MAX_LEN 20001
#define DIFF ('a' - 'A')

void convert(char *str) {
  int index = 0;

  while (str[index] != '\0') {
    if (str[index] >= 'a') str[index] -= DIFF;
    else str[index] += DIFF;
  
    index ++;
  }
}

int main() {
  int T;
  scanf("%d", &T);

  char *input = (char*)malloc(sizeof(char) * MAX_LEN);

  while (T --) {
    scanf("%s", input);

    convert(input);
    printf("%s\n", input);
  }

  free(input);
  return 0;
}
