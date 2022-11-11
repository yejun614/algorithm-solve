/*
 * (221111) 방 번호
 * https://www.acmicpc.net/problem/1475
 *
 * [풀이]
 * 입력받은 수에 각 자리수의 숫자가 몇번씩 등장하는지 세고,
 * 그 중 가장 많이 등장하는 숫자의 등장 횟수를 출력하면 정답을
 * 받을 수 있다.
 *
 * 있때 6과 9는 바꿔서 사용할 수 있다는 규칙이 있는데,
 * 때문에 6이 입력되었을 때 count[6] > count[9] 상황이면
 * count[9]++을 수행하며 9가 입력되었을 때도 비슷하게 처리한다.
 */

#include <cstdio>

#define NUM(x) ((x) - '0')

int main() {
  int num;
  char N[8];
  scanf("%s", N);

  char *ch = N;
  int count[10] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

  while (*ch) {
    num = NUM(*ch);

    if (num == 9 && count[9] > count[6]) {
      count[6]++;
    } else if (num == 6 && count[6] > count[9]) {
      count[9]++;
    } else {
      count[num]++;
    }

    ch++;
  }

  int answer = 0;
  for (int i = 0; i < 10; i++) {
    if (answer < count[i]) {
      answer = count[i];
    }
  }

  printf("%d\n", answer);

  return 0;
}
