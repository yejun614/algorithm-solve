/*
 * (220524) Jolly Jumpers
 * http://www.ascode.org/problem.php?id=1263
 *
 * [풀이]
 * 입력 받은 수열에서 이웃한 숫자간의 차이들의 집합들이 1에서 N-1 까지 정수들의 집합이
 * 되는 수열을 Jolly Jumpers 라고 한다.
 * 수열을 입력받고 해당 수열이 Jolly Jumpers 인지, 아닌지 판단하는 문제이다.
 * 
 * 수열을 입력받으면서 이전 수와 현재수의 차이(절대값)를 구해서 모든 차이를 더한 값이
 * 1부터 N-1까지의 정수 합과 같다면 Jolly Jumpers 이다.
 * 이때 중요한 부분이 이전 수와 현재수의 차이를 구할 때 결과가 0이 나온다면 해당 수열은
 * Jolly Jumpers가 아니다.
 */

#include <cstdio>
#define ABS(x) (x) < 0 ? -(x) : (x)

int main() {
  int count;

  while (~scanf("%d", &count)) {
    bool isRight = true;
    int current, diff = 0;
    int check = (count - 1) * count / 2;

    scanf("%d", &current);
    int prev = current;
    count--;

    while (count--) {
      scanf("%d", &current);
      if (!isRight) continue;

      const int num = ABS(prev - current);
      if (num < 1) isRight = false;

      diff += num;
      prev = current;
    }

    if (isRight && diff == check) printf("Jolly\n");
    else printf("Not jolly\n");
  }

  return 0;
}
