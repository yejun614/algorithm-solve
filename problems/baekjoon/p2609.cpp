/*
 * (220211) 최대공약수와 최소공배수
 * https://www.acmicpc.net/problem/2609
 *
 * [풀이]
 * 수학문제로 공식을 외우는게 좋다.
 * 
 * 최대공약수는 유클리드 호제법에 의하여 해결가능하다.
 * A, B 두 수의 최대공약수를 구한다고 했을 때,
 * A, B의 최대공약수는 B, A % B 의 최대 공약수와 같다. 따라서
 *
 * GCD(A, B) = GCD(B, A % B)
 * if A % B = 0 -> GCD = B
 * else GCD(B, A % B)
 *
 * 이러한 형태로 풀이한다.
 *
 * 또한 A, B 두 수의 최소공배수의 경우
 * (A * B) / 최대공배수
 * 임으로 쉽게 계산할 수 있다.
 *
 * [실수]
 * (+) 처음에는 short 자료형을 사용했는데, 계속 실패가 떠서 int 변경 후 성공했다.
 *     최소공배수 계산과정에서 A * B 하는데, 최대 10,000이 입력될 수 있음으로
 *     10,000 * 10,000 하면 오버플로우가 발생했던 것으로 추정된다.
 */

#include <cstdio>

int main() {
  int A, B;
  scanf("%d %d", &A, &B);

  int GCD, LCM = A * B, temp;

  while (A % B != 0) {
    temp = A % B;
    A = B;
    B = temp;
  }

  GCD = B;
  LCM /= GCD;

  printf("%d\n", GCD);
  printf("%d", LCM);

  return 0;
}
