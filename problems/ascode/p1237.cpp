/*
 * (220512) The 3n+1 Problem
 * http://www.ascode.org/problem.php?id=1237
 */

#include <cstdio>

inline void swap(int *A, int *B) {
  int temp = *A;
  *A = *B;
  *B = temp;
}

int solve(int num) {
  int result = 1;

  while (num > 1) {
    num = (num % 2 == 0) ? (num / 2) : (3 * num + 1);
    result ++;
  }

  return result;
}

int main() {
  int A, B;
  while (true) {
    if (scanf("%d %d", &A, &B) == -1) break;
    printf("%d %d ", A, B);

    if (A > B) swap(&A, &B);

    int answer = 0, current;

    for (int i = A; i <= B; i ++) {
      current = solve(i);
      if (current > answer) answer = current;
    }

    printf("%d\n", answer);
  }

  return 0;
}
