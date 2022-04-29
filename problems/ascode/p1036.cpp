/*
 * (220429) 사다리꼴 면적 구하기
 * http://www.ascode.org/problem.php?id=1036
 */

#include <cstdio>

int main() {
  int a, b, h;
  scanf("dside : %d, uside : %d, height : %d", &a, &b, &h);

  const float answer = ((float)a + (float)b) / 2.0f * (float)h;
  printf("%.1f\n", answer);

  return 0;
}
