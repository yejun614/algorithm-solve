/*
 * (230202) 그룹 단어 체커
 * https://www.acmicpc.net/problem/1316
 *
 * [풀이]
 * 문제에서 문자열이 주어지면 문자열 안에 있는 문자 그룹을 확인하여
 * 그룹 단어인지 아닌지 구분하고 그룹 단어의 개수가 몇 개인지 출력하는
 * 문제이다.
 *
 * 아래 코드에서 solve 함수를 통해서 해결이 가능한데 인자로 입력 문자열이
 * 들어오고 Check 배열을 사용해서 앞서 문자열이 존재했는지 아닌지 boolean으로
 * 저장한다. 문자열의 한 문자씩 떼어내서 이전에 등장한 문자와 비교하며
 * 서로 다를 때 현재 문자가 이전에 등장했었는지 확인한 후 등장 했다면 그룹 단어가
 * 아니라고 할 수 있다. 또한 문자열의 모든 문자를 확인 했지만 반복 등장하지
 * 않았다면 해당 문자열이 그룹 단어임을 알 수 있다.
 *
 * solve 함수 결과 ture인 단어의 개수를 세어서 출력하면 문제를 통과할 수 있다.
 * solve 함수 시간 복잡도: O(문자열의 길이)
 */

#include <cstdio>

#define MAX_SIZE 101
#define ALPHABET_LEN 26

bool Check[ALPHABET_LEN];

bool solve(char* str) {
  // Reset Check Array
  for (int i = 0; i < ALPHABET_LEN; ++i) Check[i] = false;

  // A Previous Character
  char prev = str[0];

  for (char *ch = str; *ch != '\0'; ++ch) {
    if (prev != *ch && Check[*ch - 'a']) return false;
    else Check[*ch - 'a'] = true;

    prev = *ch;
  }

  return true;
}

int main() {
  int N;
  scanf("%d", &N);

  char str[MAX_SIZE];
  int answer = 0;

  while (N--) {
    scanf("%s", str);
    if (solve(str)) ++answer;
  }

  printf("%d\n", answer);

  return 0;
}

