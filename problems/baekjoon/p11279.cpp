/*
 * (230308) 최대 힙
 * https://www.acmicpc.net/problem/11279
 * 
 * [풀이]
 * C++ STL의 priority_queue를 사용하여 풀이 가능하다.
 * (문제 내용은 링크 참고)
 */

#include <cstdio>
#include <queue>
using namespace std;

int main() {
  int N;
  scanf("%d", &N);

  int input;
  priority_queue<int> maxHeap;

  while (N--) {
    scanf("%d", &input);

    if (input == 0) {
      if (maxHeap.empty()) {
        printf("0\n");
      } else {
        printf("%d\n", maxHeap.top());
        maxHeap.pop();
      }
    } else {
      maxHeap.push(input);
    }
  }

  return 0;
}
