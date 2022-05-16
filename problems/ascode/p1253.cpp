/*
 * (220517) IP Address 2
 * http://www.ascode.org/problem.php?id=1253
 *
 * [풀이]
 * C언어에서는 scanf 함수안에 입력받을 포맷을 정할 수 있어서 편리하다.
 * IP 주소를 입력받을 때도 문자열로 입력받는 것이 아니라,
 * 각 부분을 정수로 입력받을 수 있다.
 *
 * 입력받은 IP주소는 배열안에 정수로 저장을 하고,
 * 저장된 숫자를 2진수로 변환해서 출력하면 된다.
 *
 * 비트 연산자 AND를 활용해서 간단하게 해결할 수 있다.
 */

#include <cstdio>
const int STEP[] = { 128, 64, 32, 16, 8, 4, 2, 1 };

int main() {
  int T;
  scanf("%d", &T);

  while (T--) {
    int nums[4];
    scanf("%d.%d.%d.%d", &nums[0], &nums[1], &nums[2], &nums[3]);

    for (int i = 0; i < 32; i ++)
      printf("%c", nums[i/8] & STEP[i%8] ? '1' : '0');
    printf("\n");
  }

  return 0;
}
