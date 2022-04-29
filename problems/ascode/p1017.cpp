/*
 * (220429) 반복문 다루기 # 1 (입력값 그대로 출력하기)
 * http://www.ascode.org/problem.php?id=1017
 */

#include <cstdio>

int main() {
  int N, current;
  scanf("%d", &N);

  for (int i = 0; i < N; i ++) {
    scanf("%d", &current);
    printf("%d\n", current);
  }

  return 0;
}
