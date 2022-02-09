/*
 * (220209) 스택 수열
 * https://www.acmicpc.net/problem/1874
 *
 * [풀이]
 * 스택이 있고 워하는 만큼 1부터 N까지 차례대로 PUSH 하거나 원하는 만큼 POP 할 수 있다고
 * 가정한다. 이러한 규칙 속에서 스택 POP으로 주어진 수열을 완성할 수 있는지 확인하는 문제이다.
 *
 * (1). 수열을 입력받아 큐에 저장한다. (inputs)
 * (2). 스택 nums와 PUSH, POP 순서를 기록할 queue로 order를 선언한다.
 * (3). 1부터 N까지 차례대로 PUSH 해야 함으로, 정수 변수(current)를 선언하고 1로 초기화 한다.
 * (4). 입력받은 수열의 원소를 하나씩 꺼내서 반복문을 진행한다.
 * (5). 해당 원소값이 current보다 작거나 같다면, PUSH를 진행한다. (PUSH 기록)
 * (6). 해당 원소값이 current와 다르다면, POP을 진행한다. (POP 기록)
 * (7). 만약 POP을 진행하다 더 이상 POP을 할 원소가 없다면 "NO"를 출력하고 종료한다.
 * (8). 반복문이 끝났다면 기록해둔 order 변수를 차례대로 출력한다.
 */

#include <cstdio>
#include <vector>
#include <queue>
#include <stack>

using namespace std;

bool solve(queue<int>& inputs) {
  stack<int> nums;
  queue<bool> order;

  int current, count = 1, check;

  while (!inputs.empty()) {
    current = inputs.front();
    inputs.pop();

    // PUSH
    while (current >= count) {
      nums.push(count ++);
      order.push(true);
    }

    // POP
    check = -1;
    while (check != current) {
      if (nums.empty()) return false;

      check = nums.top();
      nums.pop();
      order.push(false);
    }
  }

  // PRINT
  while (!order.empty()) {
    printf("%c\n", order.front() ? '+' : '-');
    order.pop();
  }

  return true;
}

int main() {
  int N;
  scanf("%d", &N);

  int input;
  queue<int> inputs;

  for (int i = 0; i < N; i ++) {
    scanf("%d", &input);
    inputs.push(input);
  }

  if (!solve(inputs)) {
    printf("NO\n");
  }

  return 0;
}
