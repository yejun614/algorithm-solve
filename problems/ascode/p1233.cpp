/*
 * (220429) 소수 구하기
 * http://www.ascode.org/problem.php?id=1233
 */

#include <cstdio>
#include <cmath>

bool isPrime(int num) {
  if (num <= 3) return true;

  int limit = sqrt(num);

  for (int i = 2; i <= limit; i ++) {
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
