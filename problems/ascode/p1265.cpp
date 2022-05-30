/*
 * (220526) 01000001
 * http://www.ascode.org/problem.php?id=1265
 *
 * [풀이]
 * 2진수로 이루어진 두 수를 입력받아서 합친 결과를 출력하는 문제이다.
 * 원리 자체는 어렵지 않지만 세밀한 구현을 필요로 한다.
 */

#include <cstdio>
#include <cstring>
#include <cstdlib>

#define MAX_LEN 81

#define BIT(a) ((a) - '0' ? 1 : 0)
#define CARRY(a) ((a) ? 1 : 0)

inline void swap(int *a, int *b) {
  int buf = *a;
  *a = *b;
  *b = buf;
}

inline void swap(char **str1, char **str2) {
  char *buf = *str1;
  *str1 = *str2;
  *str2 = buf;
}

bool bitSum(char *str1, int len1, char *str2, int len2) {
  int current;
  bool carry = false;

  for (int i = (len1 - 1), j = (len2 - 1); i >=0; i --, j --) {
    current = BIT(str1[i]) + CARRY(carry);
    if (j >= 0) current += BIT(str2[j]);

    // 0 -> 0 0
    // 1 -> 1 0
    // 2 -> 0 1
    // 3 -> 1 1

    str1[i] = (current % 2 != 0) ? '1' : '0';
    carry = current > 1;
  }

  return carry;
}

void printBits(char *str, bool carry) {
  if (carry) {
    printf("1");
  }

  for (int i = 0; str[i] != '\0'; i ++) {
    if (!carry && str[i] == '0') continue;

    carry = true;
    printf("%c", str[i]);
  }

  if (!carry) printf("0");
  printf("\n");
}

int main() {
  int T;
  scanf("%d", &T);

  for (int testCase = 1; testCase <= T; testCase ++) {
    // 입력받은 문자열 두 개 동적할당
    char *str1 = (char*)malloc(sizeof(char) * MAX_LEN);
    char *str2 = (char*)malloc(sizeof(char) * MAX_LEN);

    // 2진수 두 개 입력
    scanf("%s %s", str1, str2);

    // 자리수 세기
    int len1 = strlen(str1);
    int len2 = strlen(str2);

    // 자리수가 큰 수를 str1으로 지정
    if (len2 > len1) {
      swap(&len1, &len2);
      swap(&str1, &str2);
    }

    // 테스트 케이스 출력
    printf("%d ", testCase);

    // 계산
    bool carry = bitSum(str1, len1, str2, len2);

    // 출력
    printBits(str1, carry);

    // 메모리 해제
    free(str1);
    free(str2);
  }

  return 0;
}
