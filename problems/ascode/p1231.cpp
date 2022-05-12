/*
 * (220511) 어떤 소수일까?
 * http://www.ascode.org/problem.php?id=1231
 *
 * [풀이]
 * 수학에서 유한소수와 무한소수를 정확하게 구분할 수 있는 방법이 있다.
 * 
 * (1). 분모, 분자를 최대공약수로 나누어 기약분수로 만든다.
 * (2). 분모의 소인수가 2와 5만으로 이루어진다면 해당 분수를 유한소수이다. (반대는 무한소수)
 *
 * 참고로 기약분수를 만들기 위한 최대공약수는 유클리드 호제법을 이용하면
 * 간단하게 구현가능 하다. (코드에 gcd 함수를 참고한다)
 */

#include <cstdio>

int gcd(int A, int B) {
  return B == 0 ? A : gcd(B, A % B);
}

int main() {
  int T;
  scanf("%d", &T);

  while (T --) {
    int A, B;
    scanf("%d %d", &A, &B);

    int C = gcd(A, B);
    A /= C;
    B /= C;

    while (B != 1) {
      if (B % 2 == 0) { B /= 2; }
      else if (B % 5 == 0) { B /= 5; }
      else { break; }
    }

    if (B == 1) {
      printf("Limited\n"); // 유한소수
    } else {
      printf("Unlimited\n"); // 무한소수
    }
  }

  return 0;
}
