/*
 * (210211) 카드2
 * https://www.acmicpc.net/problem/2164
 *
 * [풀이]
 * Queue를 이용하면 간단하게 풀이 가능하다.
 * Queue에 1부터 N까지 차례대로 입력하고, 문제에서 제시한 규칙에 따라서
 * POP, PUSH를 반복하면 정답을 얻을 수 있다.
 */

#include <cstdio>
#include <queue>

using namespace std;

int main() {
  int N;
  scanf("%d", &N);

  queue<int> cards;

  for (int i = 1; i <= N; i ++) {
    cards.push(i);
  }

  int answer = -1;

  while (true) {
    answer = cards.front();
    
    cards.pop();
    if (cards.empty()) break;

    answer = cards.front();
    cards.pop();

    cards.push(answer);
  }

  printf("%d\n", answer);
  
  return 0;
}
