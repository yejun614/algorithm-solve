/*
 * (220217) 부녀회장이 될테야
 * https://www.acmicpc.net/problem/2775
 *
 * [풀이]
 * 1. A층의 B호에는 A-1 층의 1호부터 B호까지 지내는 사람들의 수의 합만큼 사람들이 있다.
 * 2. 아파트에는 0층부터 있고 각층에는 1호부터 있으며 0층의 i호에는 i명이 산다.
 *
 * 두 가지 조건을 조합하여 배열안에 입력받은 층수만큼 몇 명의 사람들이 사는지
 * 카운트하면 정답을 구할 수 있다.
 * (0층부터 K층까지 세면서 N호 부터 1호 까지 이중 반복문으로 탐색한다.)
 */

#include <cstdio>
#define MAX 14

int main() {
  int T;
  scanf("%d", &T);

  int rooms[MAX], k, n;

  while (T --) {
    scanf("%d %d", &k, &n);

    for (int i = 0; i < n; i ++)
      rooms[i] = i + 1;
    k --;

    while (k >= 0) {
      for (int i = n - 1; i >= 0; i --) {
        for (int j = i - 1; j >= 0; j --) {
          rooms[i] += rooms[j];
        }
      }

      k --;
    }

    printf("%d\n", rooms[n-1]);
  }

  return 0;
}
