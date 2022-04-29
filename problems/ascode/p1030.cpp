/*
 * (220429) 나는 차차를 싫어해! EASY
 * http://www.ascode.org/problem.php?id=1030
 */

#include <cstdio>
#include <cstdlib>

#define MAX_LEN 1025

void remove(char *str, int start, int length) {
  char *copyStr = (char*)malloc(sizeof(char) * MAX_LEN);
  int index = 0, pos = start + length;

  while (str[pos + index] != '\0') {
    copyStr[index] = str[pos + index];
    index ++;
  }
  copyStr[index] = '\0';

  index = 0;
  while (copyStr[index] != '\0') {
    str[start ++] = copyStr[index ++];
  }
  str[start] = '\0';
  
  free(copyStr);
}

void replaceAll(char *str, const char *check, const int checkLen) {
  int index = 0;

  int subIndex = 0;

  while (str[index] != '\0') {
    if (subIndex == checkLen - 1) {
      subIndex = 0;
      index -= checkLen;
      
      remove(str, index + 1, checkLen);
      
    } else if (str[index] == check[subIndex]) {
      subIndex ++;
      
    } else {
      subIndex = 0;
    }
  
    index ++;
  }
}

int main() {
  int T;
  scanf("%d", &T);

  char *input = (char*)malloc(sizeof(char) * MAX_LEN);

  while (T --) {
    scanf("%s", input);
    
    replaceAll(input, "charchar", 8);
    printf("%s\n", input);
  }

  free(input);
  return 0;
}
