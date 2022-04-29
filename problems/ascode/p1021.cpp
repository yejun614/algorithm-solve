/*
 * (220429) 암호화 알고리즘 #2
 * http://www.ascode.org/problem.php?id=1021
 */

#include <cstdio>
#include <cstring>

void setKey(char *key, int len) {
  int keyLen = strlen(key), i, current = 0;

  for (i = keyLen; i <= len; i ++) {
    key[i] = key[current ++];
  }

  key[i] = '\0';
}

void encrypt(char *target, const char *key) {
  int index = 0;

  while (target[index] != '\0') {
    target[index] ^= key[index];
    index ++;
  }
}

int main() {
  int T;
  scanf("%d", &T);

  char target[1025], key[513];

  while (T --) {
    scanf("%s", target);
    scanf("%s", key);

    setKey(key, strlen(target));
    encrypt(target, key);

    printf("%s\n", target);
  }

  return 0;
}
