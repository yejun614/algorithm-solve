/*
 * (220220) 이항 계수 1
 * https://www.acmicpc.net/problem/11050
 *
 * [풀이]
 * 수 두를 입력받아 이항 계수를 출력하는 문제 입니다. 공식을 이용하는게 좋습니다.
 * 팩토리얼 연산을 할 때는 다이나믹 프로그래밍 기법을 사용해야 시간 안에 출력 가능합니다.
 *
 * [실수]
 * (+) Dynamic Programming 기법 없이 팩토리얼을 구현했다가 시간초과로 실패.
 * (+) cache 변수를 -1로 초기화 하는걸 까먹어서 실패.
 */

#include <cstdio>
#include <cstdlib>
#define MAX_NUM 100

int *cache;

inline int factorial(int n) {
  if (n <= 1) return 1;
  if (cache[n-1] >= 0) return cache[n-1];

  cache[n-1] = n * factorial(n-1);
  return cache[n-1];
}

int main() {
  int N, K;
  scanf("%d %d", &N, &K);

  cache = (int*)malloc(sizeof(int) * MAX_NUM);
  for (int i = 0; i < MAX_NUM; i ++)
    cache[i] = -1;

  int answer = factorial(N) / (factorial(K) * factorial(N - K));
  printf("%d\n", answer);

  free(cache);
  return 0;
}
