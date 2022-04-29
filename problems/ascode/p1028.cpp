/*
 * (220429) 나머지 구하기 #1
 * http://www.ascode.org/problem.php?id=1028
 */

#include <cstdio>

int main() {
  int nums[4], count = 4;
  while (count --) scanf("%d", nums + count);

  const int answer = (nums[3] * nums[2]) % (nums[1] + nums[0]);

  printf("%d\n", answer);

  return 0;
}
