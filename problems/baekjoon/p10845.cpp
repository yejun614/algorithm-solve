/*
 * (220220) 큐
 * https://www.acmicpc.net/problem/10845
 *
 * [풀이]
 * 큐 관련 명령어를 입력받아서 올바르게 출력해야 하는 문제 입니다.
 * 큐를 직접 구현하거나 표준 라이브러리를 사용할 수 있는지 묻는 문제 입니다.
 */

#include <cstdio>
#include <cstring>
#include <queue>

#define MAX_LEN 6

using namespace std;

int main() {
  int N, num;
  char command[MAX_LEN];
  queue<int> qu;
  
  scanf("%d", &N);

  while (N --) {
    scanf("%s", command);

    if (strcmp(command, "push") == 0) {
      scanf("%d", &num);
      qu.push(num);
      
    } else if (strcmp(command, "pop") == 0) {
      if (qu.size() == 0) {
        printf("-1\n");
        
      } else {
        printf("%d\n", qu.front());
        qu.pop();
      }
      
    } else if (strcmp(command, "size") == 0) {
      printf("%d\n", (int)qu.size());
      
    } else if (strcmp(command, "empty") == 0) {
      printf("%d\n", qu.empty() ? 1 : 0);
      
    } else if (strcmp(command, "front") == 0) {
      if (qu.size() == 0) {
        printf("-1\n");
        
      } else {
        printf("%d\n", qu.front());
      }
      
    } else if (strcmp(command, "back") == 0) {
      if (qu.size() == 0) {
        printf("-1\n");
        
      } else {
        printf("%d\n", qu.back());
      }
    }
  }

  return 0;
}
