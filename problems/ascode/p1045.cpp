/*
 * (220429) 차차의 언어 능력 시험 #1
 * http://www.ascode.org/problem.php?id=1045
 */

#include <cstdio>
#include <cstring>

int main() {
  // 한국어는 2바이트 + NULL 문자 1개당 3바이트가 필요합니다.
  char KR_UNIT[5][4] = { "", "십", "백", "천", "만" };
  char KR_NUM[9][4] = { "", "이", "삼", "사", "오", "육", "칠", "팔", "구" };

  int T;
  char num[6];

  scanf("%d", &T);

  while (T --) {
    scanf("%s", num);
    const int len = strlen(num);

    for (int i = 0; i < len; i ++) {
      printf("%s", KR_NUM[num[i] - '1']);

      if (len == i + 1 && num[i] == '1') printf("일");
      else if (num[i] != '0') printf("%s", KR_UNIT[len - i - 1]);
    }

    printf("\n");
  }

  return 0;
}
