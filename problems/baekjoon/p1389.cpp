/*
 * (221110) 케빈 베이컨의 6단계 법칙
 * https://www.acmicpc.net/problem/1389
 *
 * [풀이]
 * 플로이드 와샬 알고리즘을 사용해서 풀이 가능하다.
 */

#include <cstdio>

#define MAX 5100
#define USERS 101

int Friends[USERS][USERS];

void ClearFriends() {
  for (int y = 0; y < USERS; y++) {
    for (int x = 0; x < USERS; x++) {
      Friends[y][x] = x == y ? 0 : MAX;
    }
  }
}

void FloydWarshall() {
  int distance;

  for (int mid = 1; mid < USERS; mid++) {
    for (int start = 1; start < USERS; start++) {
      for (int end = 1; end < USERS; end++) {
        distance = Friends[start][mid] + Friends[mid][end];

        if (Friends[start][end] > distance) {
          Friends[start][end] = distance;
        }
      }
    }
  }
}

int main() {
  // Clear
  ClearFriends();

  // Input
  int N, M;
  scanf("%d %d", &N, &M);

  int userA, userB;
  while (M--) {
    scanf("%d %d", &userA, &userB);

    Friends[userA][userB] = 1;
    Friends[userB][userA] = 1;
  }

  // Solution
  FloydWarshall();

  // Get minimum value
  int answer = 0, current, minDistance = MAX * USERS;

  for (int user = 1; user < USERS; user++) {
    current = 0;
    for (int i = 1; i < USERS; i++) {
      current += Friends[user][i];
    }

    if (minDistance > current) {
      answer = user;
      minDistance = current;
    }
  }

  // Print the answer
  printf("%d\n", answer);

  return 0;
}
