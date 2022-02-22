/*
 * (220220) 요세푸스 문제 0
 * https://www.acmicpc.net/problem/11866
 *
 * [풀이]
 * 1번부터 N번 까지 차례대로 원을 이루어 앉아있고, 양의 정수 K(<=N)이 주어집니다.
 * 순서대로 K번째 사람을 제거한다. K번째 사람이 제거되면, 남은 사람들끼리 다시 순서대로 K번째 사람을 제거합니다.
 * 모든 사람이 제거될 때 까지 반복합니다.
 *
 * 예를 들어 N, K가 각자 7, 3이고 위의 규칙대로 진행했을 경우 제거된 사람은 순서대로
 * <3, 6, 2, 7, 5, 1, 4> 가 됩니다.
 *
 * N과 K가 주어졌을 때 제거되는 순서대로 번호를 출력하는 문제 입니다.
 *
 * 큐를 사용하면 간단하게 해결 가능합니다. 큐 안에 1부터 N까지 수를 push 하고,
 * 하나씩 pop하는데, pop을 몇번 했는지 카운트해서 K번째가 되면 출력하면 통과할 수 있습니다.
 */

#include <cstdio>
#include <queue>

int main() {
  int N, K;
  std::queue<int> circle;

  scanf("%d %d", &N, &K);

  for (int i = 1; i <= N; i ++)
    circle.push(i);

  printf("<");

  int count = 0, current;
  bool first = true;

  while (!circle.empty()) {
    count ++;
    current = circle.front();
    circle.pop();

    if (count >= K) {
      count = 0;

      if (first) first = false;
      else printf(", ");

      printf("%d", current);
      
    } else {
      circle.push(current);
    }
  }
  
  printf(">");

  return 0;
}
