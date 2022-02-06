/*
 * (220206) 팰린드롬수
 * https://www.acmicpc.net/problem/1259
 *
 * [풀이]
 * 주어진 수가 뒤로 읽었을 때로 동일할 때 yes, 아니면 no를 출력하는 문제이다.
 * 10같은 경우 0이 앞에 붙으면 010이 되어 yes일 수도 있으나,
 * 이번 문제에서는 "무의미한 0이 앞에 올 수 없다고" 적혀 있다.
 *
 * 입력되는 수를 문자열로 받아서(nums), 뒤집어 저장한다.(reversed)
 * 두 문자열을 비교하여 동일하면 yes, 아니면 no를 출력한다.
 */

#include <cstdio>
#include <cstring>

#define MAX_LEN 6

int main() {
  char nums[MAX_LEN], reversed[MAX_LEN];

  while (true) {
    scanf("%s", nums);
    if (strcmp(nums, "0") == 0) break;

    const short len = strlen(nums);
    
    for (int i = 0; i < len; i ++)
      reversed[len - i - 1] = nums[i];
    reversed[len] = '\0';

    if (strcmp(nums, reversed) == 0) {
      printf("yes\n");
    } else {
      printf("no\n");
    }
  }

  return 0;
}
