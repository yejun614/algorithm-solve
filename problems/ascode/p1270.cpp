/*
 * (220531) 지하철을 빠르게 빠르게 EASY
 * http://www.ascode.org/problem.php?id=1270
 *
 * [풀이]
 * 지하철의 속도와 1원을 투자할 때 빨라지는 속도를 각각 입력받는다.
 * 주어진 돈을 가지고 지하철의 속도를 최대로 만드는 것이 문제의 목표이다.
 *
 * 우선순위 큐를 이용해서 최소힙을 사용한다. 최소힙의 첫번째 요소는 항상
 * 최소값을 가르킨다. 주어진 열차 중에서 가장 느리면서 1원을 투자했을 때,
 * 가장 속도가 크게 올라가는 열차를 골라서 투자한다.
 * 돈을 다 사용할 때 까지 반복하여, 반복이 끝나고 우선순위 큐의 제일
 * 첫번째 요소가 가르키는 열차의 속도가 정답이 된다.
 *
 * 이 다음 문제인 1271 문제의 경우 문제 자체는 동일하지만, 해당 알고리즘은
 * 시간초과를 일으킨다.
 */

#include <cstdio>
#include <queue>
#include <algorithm>
#include <vector>

using namespace std;

typedef struct Train {
  int speed;
  int level;
} Train;

struct compare {
  bool operator()(Train &A, Train &B) {
    if (A.speed == B.speed) return A.level < B.level;
    return A.speed > B.speed;
  }
};

int main() {
  int T;
  scanf("%d", &T);

  while (T--) {
    int N, Money;
    scanf("%d %d", &N, &Money);

    vector<Train> trains(N);

    for (int i = 0; i < N; i ++) scanf("%d", &(trains[i].speed));
    for (int i = 0; i < N; i ++) scanf("%d", &(trains[i].level));

    priority_queue<Train, vector<Train>, compare> pq;
    for (int i = 0; i < N; i ++) pq.push(trains[i]);

    while (Money --) {
      Train current = pq.top();
      pq.pop();

      current.speed += current.level;
      pq.push(current);
    }

    printf("%d\n", pq.top().speed);
  }

  return 0;
}
