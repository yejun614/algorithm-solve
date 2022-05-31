/*
 * (220531) 0-1 수열
 * http://www.ascode.org/problem.php?id=1269
 *
 * [풀이]
 * 0과 1로 구성된 수열이 있고 그 사이의 범위를 입력받을 때,
 * 범위안에 수열이 전부 0이거나 1이면 Yes, 중간에 값이 변하면 No를 출력하는 문제이다.
 *
 * 수열의 크기 만큼의 배열방을 가진 정수 배열을 준비해주고,
 * 수열을 반복문으로 탐색하면서 이전 값과 비교했을 때 값이 변하는 횟수를 차례대로 누적한다.
 * 그리고 정수 배열에 해당하는 인덱스에 누적된 값을 차례대로 입력한다.
 *
 * 범위 i, j를 입력받고, 값이 변경된 횟수를 저장한 배열의
 * i번째 배열방과 j번재 배열방의 값이 서로 같다면 Yes, 아니면, No를 출력한다.
 */

#include <cstdio>
#define MAX_LEN 1000001

char str[MAX_LEN];
int count[MAX_LEN];

int main() {
  scanf("%s", str);

  int current = 0;
  char prev = str[0];

  for (int i = 0; str[i] != '\0'; i ++) {
    if (prev != str[i]) {
      current ++;
    }

    count[i] = current;
    prev = str[i];
  }

  int T;
  scanf("%d", &T);

  while (T--) {
    int i, j;
    scanf("%d %d", &i, &j);

    printf(count[i] == count[j] ? "Yes\n" : "No\n");
  }

  return 0;
}
