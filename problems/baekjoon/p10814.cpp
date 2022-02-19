/*
 * (220219) 나이순 정렬
 * https://www.acmicpc.net/problem/10814
 *
 * [풀이]
 * 나이하고 사람이름이 순서대로 입력되면, 나이를 오름차순으로 정렬하되, 나이가 같은 경우
 * 먼저 입력된 순으로 정렬하는 문제 입니다.
 *
 * std::sort 함수를 compare 함수를 통해서 정렬하여 문제에서 제시하는 조건을 만족하는
 * 정렬 프로그램을 구현할 수 있습니다.
 *
 * [실수]
 * (+) iostream의 cin, cout을 사용하는 경우 입출력에 시간이 오래 걸려 시간초과를 받을 수 있습니다.
 *
 *     ios_base::sync_with_stdio(false);
 *     cin.tie(NULL);
 *     cout.tie(NULL);
 *
 *     관련 옵션을 껐지만 여전히 시간 초과를 받아 stdio의 printf, scanf로 변경하고
 *     통과할 수 있었습니다.
 */

#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

typedef struct User {
  int index;
  int age;
  char name[101];
} User;

bool compare(User A, User B) {
  if (A.age == B.age) return A.index < B.index;
  return A.age < B.age;
}

int main() {

  int N;
  scanf("%d", &N);
  
  vector<User> users(N);
  
  for (int i = 0; i < N; i ++) {
    scanf("%d %s", &users[i].age, users[i].name);
    
    users[i].index = i;
  }

  sort(users.begin(), users.end(), compare);

  for (User user : users)
    printf("%d %s\n", user.age, user.name);

  return 0;
}
