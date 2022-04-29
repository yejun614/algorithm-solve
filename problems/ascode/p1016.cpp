/*
 * (220429) 자동차 이동 거리 구하기 (기초) #6
 * http://www.ascode.org/problem.php?id=1016
 */

#include <cstdio>

int main() {
  double speed, hour;
  scanf("%lf %lf", &speed, &hour);

  printf("%.3lf %.3lf %.2lf\n", speed, hour, speed * hour);

  return 0;
}
