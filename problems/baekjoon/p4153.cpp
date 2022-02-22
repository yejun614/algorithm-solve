/*
 * (220219) 직각삼각형
 * https://www.acmicpc.net/problem/4153
 *
 * [풀이]
 * 세 변의 길이가 주어지면, 직각 삼각형인지 아닌지 판단하는 문제입니다.
 * 피타고라스의 정리를 이용하면 쉽게 풀이 가능합니다.
 *
 * A, B, C를 세변으로 하는 직각삼각형의 경우 (A <= B < C)
 * A^2 = B^2 + C^2
 */

#include <cstdio>
#define POW(A) (A * A)

inline void swap(int *A, int *B) {
  int temp = *A;
  *A = *B;
  *B = temp;
}

int main() {
  int a, b, c;

  while (true) {
    scanf("%d %d %d", &a, &b, &c);
    if (a + b + c == 0) break;

    if (a > c) swap(&a, &c);
    if (b > c) swap(&b, &c);

    if (POW(c) == POW(a) + POW(b)) {
      printf("right\n");
    } else {
      printf("wrong\n");      
    }
  }

  return 0;
}
