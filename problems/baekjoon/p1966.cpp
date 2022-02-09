/*
 * (220209) 프린터 큐
 * https://www.acmicpc.net/problem/1966
 *
 * [풀이]
 * 프린터에 입력된 문서에 우선순위가 있을 경우 특정 문서가 몇번째로
 * 프린트 될지 찾아내는 문제이다.
 *
 * 문제에서 제시된 것 처럼 큐를 이용하면 해결 할 수 있다.
 *
 * [실수]
 * (+) 문제에서는 M번째 문서가 몇번째로 프린트 될지를 찾는 것인데,
 *     큐에 우선순위와 같이 몇번째로 입력된 문서인지를 저장해야 한다.
 *     구조체를 이용해서 해결했다.
 */

#include <cstdio>
#include <queue>
#include <vector>
#include <algorithm>
#include <functional>

using namespace std;

typedef struct Document {
  short level;
  short id;
} Document;

int main() {
  int T;
  scanf("%d", &T);

  short N, M, input;
        
  while (T --) {
    scanf("%hd %hd", &N, &M);

    queue<Document> documents;
    vector<short> order(N);

    for (short i = 0; i < N; i ++) {
      scanf("%hd", &input);

      documents.push({input, i});
      order[i] = input;
    }

    sort(order.begin(), order.end(), greater<short>());
    
    int index = 0;
    Document current;

    while (true) {
      current = documents.front();
      documents.pop();
      
      if (current.level == order[index]) {
        index ++;
        if (current.id == M) break;
        
      } else {
        documents.push(current);
      }
    }

    printf("%d\n", index);
  }

  return 0;
}
