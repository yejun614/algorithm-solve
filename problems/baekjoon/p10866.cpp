/*
 * (220220) 덱
 * https://www.acmicpc.net/problem/10866
 *
 * [풀이]
 * 덱 관련 명령어를 입력받고, 올바른 출력을 해야하는 문제입니다.
 * 덱을 직접 구현하거나 표준 라이브러리를 사용할 수 있는지 물어보는 문제 입니다.
 */

#include <cstdio>
#include <cstring>
#include <deque>

#define MAX_LEN 11

using namespace std;

int main() {
  int N, num;
  char command[MAX_LEN];
  deque<int> de;

  scanf("%d", &N);

  while (N --) {
    scanf("%s", command);

    if (strcmp(command, "push_front") == 0) {
      scanf("%d", &num);
      de.push_front(num);

    } else if (strcmp(command, "push_back") == 0) {
      scanf("%d", &num);
      de.push_back(num);

    } else if (strcmp(command, "pop_front") == 0) {
      if (de.size() == 0) {
        printf("-1\n");
        
      } else {
        printf("%d\n", de.front());
        de.pop_front();
      }

    } else if (strcmp(command, "pop_back") == 0) {
      if (de.size() == 0) {
        printf("-1\n");
        
      } else {
        printf("%d\n", de.back());
        de.pop_back();
      }

    } else if (strcmp(command, "size") == 0) {
      printf("%d\n", (int)de.size());

    } else if (strcmp(command, "empty") == 0) {
      printf("%d\n", de.empty() ? 1 : 0);

    } else if (strcmp(command, "front") == 0) {
      if (de.size() == 0) {
        printf("-1\n");
        
      } else {
        printf("%d\n", de.front());
      }
      
    } else if (strcmp(command, "back") == 0) {
      if (de.size() == 0) {
        printf("-1\n");
        
      } else {
        printf("%d\n", de.back());
      }
    }
  }

  return 0;
}
