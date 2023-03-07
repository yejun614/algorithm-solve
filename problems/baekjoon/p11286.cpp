/*
 * (230308) 절댓값 힙
 * https://www.acmicpc.net/problem/11286
 *
 * [풀이]
 * C++ STL의 priority_queue의 비교함수를 직접 구현하는 방식으로
 * 문제에서 요구하는 조건의 힙 자료구조 구현이 가능하다.
 *
 * (문제 내용은 링크를 참고)
 */

#include <cstdio>
#include <queue>
#include <vector>
#include <cmath>
using namespace std;

typedef struct comp {
  bool operator() (const int A, const int B) {
    const int _A = abs(A), _B = abs(B);

    if (_A == _B) {
      return A > B;
    } else {
      return _A > _B;
    }
  }
} comp;

int main() {
  int N;
  scanf("%d", &N);

  int input;
  priority_queue<int, vector<int>, comp> maxHeap;

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
