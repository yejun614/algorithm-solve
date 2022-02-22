/*
 * (220220) 수 정렬하기 3
 * https://www.acmicpc.net/problem/10989
 *
 * [풀이]
 * 자연수가 N개 입력되면 오름차순으로 정렬해서 출력하는 문제이다.
 * 시간제한 (5초), 메모리 제한(8MB), N(1 <= N <= 10,000,000),
 * 입력되는 수는 10,000 이하의 자연수 이다.
 *
 * 메모리 제한 8MB 인데 N값 즉, 입력받아야 하는 수가 너무 많음으로 전부 입력받아서
 * 표준 라이브러리를 이용한 정렬을 실시하면 메모리 초과 판정을 받는다.
 *
 * 이 때는 수의 범위 만큼 배열을 선언하고 카운팅하는 기법으로 해결가능 하다.
 * N 값이 최대 10,000,000 들어오기 때문에 int 자료형으로 10,000개의 배열 방을 가진
 * 배열을 선언하고 0으로 초기화 한다.
 *
 * 수를 입력받으면서 (입력받은 수 - 1) 배열 방의 수를 1씩 가산 한다.
 * 입력이 끝난 후 배열 방을 차례대로 돌면서 배열 방의 값 만큼 해당 수를 출력한다.
 * (하단 코드 참고)
 *
 * [실수]
 * (+) 모든 수를 한번에 입력받아 std::sort로 해결할려고 했으나, 메모리 초과로 실패.
 * (+) 배열 방의 크기를 실수로 N으로 해서 메모리 초과, MAX_NUM으로 수정 후 통과.
 */

#include <cstdio>
#include <cstdlib>

#define MAX_NUM 10000

inline void clear(int *arr, int size) {
  while (size) {
    arr[--size] = 0;
  }
}

int main() {
  int N, num;
  scanf("%d", &N);

  int *count = (int*)malloc(sizeof(int) * MAX_NUM);
  clear(count, MAX_NUM);

  for (int i = 0; i < N; i ++) {
    scanf("%d", &num);
    
    count[num - 1] ++;
  }

  for (int i = 0; i < MAX_NUM; i ++) {
    while (count[i] --) printf("%d\n", i+1);
  }
  
  free(count);
  return 0;
}
