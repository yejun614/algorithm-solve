/*
 * (220220) 스택
 * https://www.acmicpc.net/problem/10828
 *
 * [풀이]
 * 스택 명령어를 입력받고 적절한 값을 출력해야 하는 문제 입니다.
 * 스택을 직접 구현하거나 아니면 표준 라이브러리를 잘 사용하는지 물어보는 문제 입니다.
 */

#include <cstdio>
#include <cstring>
#include <stack>

#define MAX_LEN 6

using namespace std;

int main() {
  int N, num;
  char command[MAX_LEN];
  stack<int> st;

  scanf("%d", &N);

  while (N --) {
    scanf("%s", command);

    if (strcmp(command, "push") == 0) {
      scanf("%d", &num);
      st.push(num);
      
    } else if (strcmp(command, "pop") == 0) {
      if (st.size() == 0) {
        printf("-1\n");
        
      } else {
        printf("%d\n", st.top());
        st.pop();
      }
      
    } else if (strcmp(command, "size") == 0) {
      printf("%d\n", (int)st.size());
      
    } else if (strcmp(command, "empty") == 0) {
      printf("%d\n", st.empty() ? 1 : 0);
      
    } else if (strcmp(command, "top") == 0) {
      if (st.size() == 0) {
        printf("-1\n");
        
      } else {
        printf("%d\n", st.top());
      }
    }
  }

  return 0;
}
