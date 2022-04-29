/*
 * (220429) 삼각형 면적 구하기 소수버전 (기초) #4
 * http://www.ascode.org/problem.php?id=1011
 */

#include <cstdio>

int main() {
  double X, Y, Z;
  scanf("%lf %lf", &X, &Y);

  Z = (X * Y) /2.0f;

  printf("%lf, %lf, %lf\n", X, Y, Z);

  return 0;
}
