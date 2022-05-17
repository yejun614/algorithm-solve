/*
 * (220429) 소수 구하기 I
 * http://www.ascode.org/problem.php?id=1233
 *
 * [풀이]
 * N이 소수인지 판정하기 위해서 3부터 sqrt(N) 까지 검증하는 알고리즘
 *
 * [실수]
 * (+) 소수 판정 조건이 잘못되서 수정함. (220517)
 */

#include <cstdio>
#include <cmath>

bool isPrime(int num) {
  if (num == 2) return true;
  if (num < 2 || num % 2 == 0) return false;

  int limit = sqrt(num);

  for (int i = 3; i <= limit; i += 3) {
    if (num % i == 0) return false;
  }

  return true;
}

int main() {
  int T, num;
  scanf("%d", &T);

  while (T--) {
    scanf("%d", &num);
    
    if (isPrime(num)) {
      printf("Prime\n");
      
    } else {
      printf("Not Prime\n");
    }
  }

  return 0;
}
