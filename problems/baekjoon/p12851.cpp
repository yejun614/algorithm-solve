/*
 * (221117) 숨바꼭질 2
 * https://www.acmicpc.net/problem/12851
 *
 * [풀이]
 * 숨바꼭질(p1697) 문제에서 이어지는 내용이다.
 * 수빈이가 동생에게 가는 방법을 여러가지 있지만 최소 시간으로 도착하는 방법의
 * 개수를 출력해야 된다.
 *
 * 정해진 횟수만큼 반복문을 진행한다. 반복문 내부에서 수빈이가 동생에게 도착하는
 * 경우 그 때의 횟수를 따로 배열에 저장해 둔다.
 *
 * 반복문을 끝나고 배열에 저장된 수 중에서 가장 작은 수의 개수를 세어서 출력하면
 * 출력된 수가 바로 최소 시간으로 도착하는 방법의 개수이다.
 */

#include <cstdio>
#include <queue>
#include <list>
#include <algorithm>
using namespace std;

#define SIZE 200001
int Memory[SIZE];

int main() {
  // Input
  int N, K;
  scanf("%d %d", &N, &K);

  // Reset Memory
  for (int i = 0; i < SIZE; i++) {
    Memory[i] = SIZE; // assign infinite
  }

  // Start position is zero
  Memory[N] = 0;

  list<int> answers;
  queue<int> positions;
  positions.push(N);

  for (int n = 0; n < SIZE*4; n++) {
    int size = positions.size();

    for (int i = 0; i < size; i++) {
      // Get current position
      int position = positions.front();
      positions.pop();

      // EXCEPTION!
      if (position < 0 || position >= SIZE) continue;

      // Next positions
      if (position < K && Memory[position*2] >= Memory[position]+1) {
        Memory[position*2] = Memory[position]+1;
        positions.push(position*2);

        if (position*2 == K) {
          answers.push_back(Memory[position*2]);
        }
      }

      if (Memory[position-1] >= Memory[position]+1) {
        Memory[position-1] = Memory[position]+1;
        positions.push(position-1);

        if (position-1 == K) {
          answers.push_back(Memory[position-1]);
        }
      }

      if (position < K && Memory[position+1] >= Memory[position]+1) {
        Memory[position+1] = Memory[position]+1;
        positions.push(position+1);

        if (position+1 == K) {
          answers.push_back(Memory[position+1]);
        }
      }
    }
  }

  // Print answer
  printf("%d\n", Memory[K]);

  // Count answer
  long int answerCount = count(answers.begin(), answers.end(), Memory[K]);
  printf("%ld\n", answerCount == 0 ? 1 : answerCount);

  return 0;
}
