/**
 * (211001) 곱셈
 * https://www.acmicpc.net/problem/2588
 * 
 * 풀이
 * (1) 세 자리 수 정수 두개를 각각 변수 A, B에 대입한다.
 * (2) A * B의 값을 구해서 저장해 둔다.
 * (3) B를 뒤에서 부터 한 자리씩 가져와서 A와 곱하고 결과를 출력한다.
 * (4) 2번에서 구해놓은 값을 출력한다.
 */

#include <cstdio>

int main() {
  int A, B;
  scanf("%d", &A);
  scanf("%d", &B);

  int result = A * B;

  int current;
  while (B > 0) {
    current = B % 10;
    printf("%d\n", A * current);

    B /= 10;
  }

  printf("%d\n", result);

  return 0;
}
