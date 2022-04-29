/*
 * (220429) 등차수열
 * http://www.ascode.org/problem.php?id=1042
 */

#include <cstdio>

int main() {
  short num;
  scanf("%hd", &num);

  // A_n = A_1 + (n - 1) * d
  // A_1 = 3
  // d = 4
  // 연립방정식 사용
  const int answer = 3 + (num - 1) * 4;

  printf("%d\n", answer);

  return 0;
}
