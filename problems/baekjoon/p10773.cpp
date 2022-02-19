/*
 * (220219) 제로
 * https://www.acmicpc.net/problem/10773
 *
 * [풀이]
 * 숫자를 입력받는데, 0을 입력받으면 가장 최근에 입력받은 수를 하나 지웁니다.
 * 입력 받은 수를 모두 합한 값을 출력하는 문제입니다.
 *
 * 스택을 사용하면 쉽게 구현할 수 있습니다.
 */

#include <cstdio>
#include <stack>

int main() {
  int K, current;
  std::stack<int> nums;

  scanf("%d", &K);

  while (K --) {
    scanf("%d", &current);

    if (current == 0 && !nums.empty()) nums.pop();
    else nums.push(current);
  }

  int sum = 0;

  while (!nums.empty()) {
    sum += nums.top();
    nums.pop();
  }

  printf("%d\n", sum);

  return 0;
}
