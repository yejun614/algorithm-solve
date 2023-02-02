/*
 * (230202) 이중 우선순위 큐
 * https://www.acmicpc.net/problem/7662
 * 
 * [풀이]
 * 자료구조 문제로 문제의 조건에 부합하는 자료구조를 준비한 다음
 * 주어진 명령들을 처리한 이후 결과를 출력하는 문제이다.
 *
 * 이번 문제에서 요구된 자료구조는 일반적인 우선순위 큐를 변형한
 * 형태인데, 삽입은 동일하지만 삭제시 최대값 혹은 최소값만 삭제
 * 가능한 우선순위 큐에 비해 최대값 혹은 최소값중 하나를 선택적으로
 * 삭제할 수 있어야 한다.
 *
 * 가장 적합한 자료구조는 Min-Max Heap 이라는 게 존재한다.
 *
 * 이번 풀이는 Min-Max Heap이 아니라 Red-Black Tree를 기반으로 하는
 * stl::multiset으로 풀이 하였다. (별도 구현이 필요없음)
 *
 * 시간은 1.9초 정도로 Min-Max Heap을 사용한 풀이는 0.212초 정도의
 * 실행시간 차이가 존재한다.
 */

#include <cstdio>
#include <set>
#include <iterator>
using namespace std;

int main() {
  int T;
  scanf("%d", &T);

  char cmd;
  int cmdCount, arg;

  while (T--) {
    multiset<int> Q;
    scanf("%d", &cmdCount);

    while (cmdCount--) {
      // C언어 scanf 함수에서 %c 사용시 white space(" ") 혹은 new line("\n")
      // 이 모두 입력되기 때문에 의도하지 않은 문자가 입력될 수 있다.
      // 이때 white space, new line을 제외하고 입력받기 위해서는 %c 앞에 공백을
      // 하나 붙여주면 제일 간단하게 해결된다. (e.g. " %c")
      scanf(" %c %d", &cmd, &arg);

      if (cmd == 'I') {            // CMD: Insert
        Q.insert(arg);
      } else if (!Q.empty()) {
        if (arg == 1) {            // CMD: Delete Maximum Value
          Q.erase(prev(Q.end()));
        } else {                   // CMD: Delete Minimum Value
          Q.erase(Q.begin());
        }
      }    
    }

    if (Q.empty()) printf("EMPTY\n");
    else printf("%d %d\n", *Q.rbegin(), *Q.begin());
  }

  return 0;
}

