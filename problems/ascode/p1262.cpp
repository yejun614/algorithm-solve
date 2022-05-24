/*
 * (220524) 중복된 숫자 지우기
 * http://www.ascode.org/problem.php?id=1262
 *
 * [풀이]
 * 수열을 입력받아서 중복되는 숫자를 삭제한 후 정렬하여 출력하는 문제이다.
 * 배열을 이용해서 수열에서 특정 숫자가 있는지 없는지 표시하고,
 * 배열을 앞에서 붙어 읽으면서 표시된 숫자만 출력하는 방법으로 해결할 수 있다.
 */

#include <cstdio>
#define MAX_LEN 1001

int main() {
  int T;
  scanf("%d", &T);

  while (T--) {
    int count, num;
    scanf("%d", &count);
    char check[MAX_LEN] = { 0, };

    while (count--) {
      scanf("%d", &num);

      check[num] = 1;
    }

    for (int i = 0; i < MAX_LEN; i ++)
      if (check[i]) printf("%d ", i);
    printf("\n");
  }

  return 0;
}
