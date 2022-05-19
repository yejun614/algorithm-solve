/*
 * (220519) Goldbach's conjecture
 * http://www.ascode.org/problem.php?id=1256
 *
 * [풀이]
 * 골드바흐의 추측은 2보다 큰 모든 짝수는 두 소수의 합으로 나타낼 수 있다는 이론이다.
 * 증명은 아직이지만 반례가 존재하지 않아 거의 자명하다.
 *
 * 문제 조건중에 두 소수의 차이가 가장 큰 수를 출력하라고 했음으로
 * N이 입력되면 3에서 부터 N/2까지 탐색하면서 두 소수를 합했을 때 N이 되는 소수를 찾는다.
 *
 * 유사한 문제: 백준 9020
 */

#include <cstdio>
#include <cmath>
#define MAX_VAL 1000001

bool *Primes;
void find_primes(int max_value) {
  Primes[0] = false;
  Primes[1] = false;
  for (int i = 2; i < max_value; i ++) Primes[i] = true;
 
  int limit = sqrt(max_value);
 
  for (int i = 2; i <= limit; i ++) {
    if (!Primes[i]) continue;
    for (int j = i * i; j < max_value; j += i) Primes[j] = false;
  }
}
 
int main() {
  Primes = new bool[MAX_VAL];
  find_primes(MAX_VAL);
 
  int num;
 
  while (true) {
    scanf("%d", &num);
    if (num == 0) break;
 
    for (int i = 3; i <= (num/2); i += 2) {
      if (Primes[i] && Primes[num - i]) {
        printf("%d = %d + %d\n", num, i, num-i);
        break;
      }
    }
  }
 
  delete [] Primes;
  return 0;
}
