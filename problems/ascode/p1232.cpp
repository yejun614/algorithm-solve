/*
 * (220429) 수소(emirp)
 * http://www.ascode.org/problem.php?id=1232
 */

#include <cstdio>
#include <cmath>
#include <vector>

using namespace std;

constexpr int MAX_VAL = 100000;
vector<bool> prime(MAX_VAL, true);

void eratos() {
  const int limit = sqrt(MAX_VAL);

  prime[0] = false;
  prime[1] = false;

  for (int i = 2; i <= limit; i ++) {
    if (!prime[i]) continue;
    
    for (int j = i * 2; j <= MAX_VAL; j += i)
      prime[j] = false;
  }
}

bool isEmirp(int num) {
  if (!prime[num]) return false;

  int reverse = 0, origin = num;

  while (num > 0) {
    reverse *= 10;
    reverse += num % 10;
    num /= 10;
  }

  return (origin != reverse) && prime[reverse];
}

int main() {
  eratos();

  int T;
  scanf("%d", &T);

  while (T--) {
    int M, N;
    scanf("%d %d", &M, &N);

    if (M > N) {
      int temp = M;
      M = N;
      N = temp;
    }

    int min_value = 0, max_value = 0, counter = 0;

    for (int i = M; i <= N; i ++) {
      if (!isEmirp(i)) continue;
      
      counter++;

      if (min_value == 0) min_value = i;
      max_value = i;
    }

    printf("%d %d %d\n", max_value, min_value, counter);
  }

  return 0;
}
