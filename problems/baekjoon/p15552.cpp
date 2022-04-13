/*
 * (220414) 빠른 A+B
 * https://www.acmicpc.net/problem/15552
 *
 * [풀이]
 * 이 문제는 입출력을 빨리할 수 있는 코드를 짜는게 핵심이다.
 * C/C++은 scanf, printf 사용시 충분히 통과 가능하다.
 *
 * 다른 언어는 다음 링크를 참고한다.
 * https://www.acmicpc.net/board/view/22716
 */

#include <cstdio>

int main() {
  int T;
  scanf("%d", &T);

  int A, B;

  while (T --) {
    scanf("%d %d", &A, &B);
    printf("%d\n", A+B);
  }

  return 0;
}
