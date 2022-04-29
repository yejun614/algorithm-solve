/*
 * (220414) 차차의 언어 능력 시험 #2
 * http://www.ascode.org/problem.php?id=1046
 *
 * [풀이]
 * 문자열 배열안에 숫자와 단위를 저장한다.
 * (한국어는 한글자당 3개(초성+중성+종성) 공간이 필요하며 NULL 문자 포함 한글자당 4개 공간이 필요하다.)
 *
 * 프로그래밍 편의를 위해서 숫자에서 영와 일은 비워 두었으며,
 * 단위도 맞찬가지로 첫번째 배열방은 비워 두었다.
 * 그리고 [십, 백, 천]과 [만, 억, 조, 경, 해] 두개 배열로 나누어 저장했다.
 *
 * 우선 입력을 문자열로 받는다.
 * 문자열을 하나씩 반복문으로 돌면서 현재 문자에 해당하는 숫자를 출력한다.
 * ASCII 코드 원리에 의해서 (현재 문자) - '0' 해서 나온 결과를 배열 인덱스로 넣고 출력한다.
 *
 * 단위의 경우 전체문자열의 길이를 length 라고 할 때,
 * 천까지의 단위는 step = (length -i -1) % 4 로 계산하고,
 * 만부터 해 까지의 단위는 big_step = (length -i - 1) / 4 로 계산한다.
 *
 * 계산한 단위를 각 배열 인덱스에 넣고 출력한다.
 *
 * [주의해야 하는 부분]
 * 입력으로 들어오는 숫자와 자리수에 따른 단위를 출력할 때 조건문 작성에 유의한다.
 *
 * 우선 "일"은 특별하게 "일억", "일조" 처럼 단위가 출력되기 바로 앞에만 출력된다.
 * 110000 이 입력되었을 때 "십일만"으로 출력해야지 "일십일만"으로 출력되어서는 안된다.
 * 또한 10001에서 "만일"로 출력되어야 하며, "일만일"이 출력되어서는 안된다.
 * 이후 단위에서 "일"은 "일억", "일조", "일경", "일해"로 표현된다.
 *
 * '0'의 경우는 "영"이라고 출력해서는 안된다.
 * 10001은 "만일"로 출력되어야 한다. "만영영영일"은 안된다.
 *
 * 만, 억, 조, 경, 해 단위는 코드 상에서 (step == 0 && check > 0)일때만 출력한다. 
 * check 변수는 단위 안에서 출력된 숫자가 있는지 체크한다.
 * 100000001 이 입력되었을 때 "일억 일"이 출력되어야 하며, "일억만 일"이 출력되어서는 안된다.
 *
 * 마지막으로 문제에서는 마지막에 띄어쓰기가 들어가면 안됨에 주의한다.
 * 
 * [실수]
 * (+) 50010000 입력이 들어올때 "오천만"으로 출력되었다. "오천일만"으로 출력되도록 수정
 * (+) 만단위 출력하는 부분이 까다로웠는데, 10000 에서는 "만"으로 출력하고,
 *     10010000 입력에서는 "천일만"으로 "일"이 붙어야 한다.
 */

#include <cstdio>
#include <cstring>

#define MAX_LEN 26

char NUMS_KR[][4] = {"", "", "이", "삼", "사", "오", "육", "칠", "팔", "구"};
char STEP_KR[][4] = {"", "십", "백", "천"};
char BIG_STEP_KR[][4] = {"", "만", "억", "조", "경", "해"};

int countLastNum(char *str, int length) {
  int count = 0;

  for (int i = length-1; i >= 0; i --) {
    if (str[i] != '0') break;
    count++;
  }

  return count;
}

void printStepsKR(char *str, int length) {
  int lastNum = length - countLastNum(str, length) - 1;

  int current,
      step,
      big_step,
      check = 0;

  for (int i = 0; i < length; i ++) {
    current = str[i] - '0';

    step = (length - i - 1) % 4;
    big_step = (length - i - 1) / 4;

    if (current == 1 && step == 0) {
      if ((big_step != 1) || (big_step == 1 && check > 0))
        printf("일");

    } else if (current > 0) {
      printf("%s%s", NUMS_KR[current], STEP_KR[step]);

      check++;
    }

    if (i == 0) check++;

    if (step == 0 && check > 0) {
      printf("%s", BIG_STEP_KR[big_step]);
      if (i < lastNum) printf(" ");

      check = 0;
    }
  }

  // New line
  printf("\n");
}

int main() {
  int T;
  char *input = new char[MAX_LEN];

  scanf("%d", &T);

  while (T--) {
    scanf("%s", input);

    printStepsKR(input, strlen(input));
  }

  delete [] input;
  return 0;
}
