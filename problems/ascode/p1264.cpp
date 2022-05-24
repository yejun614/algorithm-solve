/*
 * (220524) Common Permutation
 * http://www.ascode.org/problem.php?id=1264
 *
 * [풀이]
 * 주어진 두 문자열에서 공통되는 문자들을 사전순으로 출력하는 문제이다.
 * 두 문자열을 정렬한 후 각 자리수를 비교하는 방식으로
 * 비교하다가 서로 같은 경우 출력하는 방식으로 해결할 수 있다.
 */

#include <cstdio>
#include <cstring>
#include <algorithm>
#define MAX_LEN 1001

int main() {
  int T;
  scanf("%d", &T);

  while (T--) {
    char str1[MAX_LEN], str2[MAX_LEN];
    scanf("%s", str1);
    scanf("%s", str2);

    int str1_len = strlen(str1);
    int str2_len = strlen(str2);

    std::sort(str1, str1 + str1_len);
    std::sort(str2, str2 + str2_len);

    int a = 0, b = 0;

    while (a < str1_len && b < str2_len) {
      if (str1[a] == str2[b]) {
        printf("%c", str1[a]);
        a++;
        b++;
      } else if (str1[a] < str2[b]) {
        a++;
      } else {
        b++;
      }
    }
    printf("\n");
  }

  return 0;
}
