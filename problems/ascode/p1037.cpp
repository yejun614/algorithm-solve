/*
 * (220429) 시간 계산하기 #2
 * http://www.ascode.org/problem.php?id=1037
 */

#include <cstdio>

int main() {
  // READY
  int pivot[] = { 7, 24, 60, 60 };
  long time[5];
  
  for (int i = 0; i < 5; i ++) time[i] = 0;

  // INPUT
  scanf("%ld", &time[4]);

  // SOLVE
  for (int i = 3; i >= 0; i --) {
    time[i] = time[i+1] / pivot[i];
    time[i+1] %= pivot[i];
  }

  // OUTPUT
  for (int i = 0; i < 5; i ++) {
    printf("%ld ", time[i]);
  }
  printf("\n");
  
  return 0;
}
