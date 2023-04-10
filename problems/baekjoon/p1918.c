/*
 * (230411) 후위 표기식
 * https://www.acmicpc.net/problem/1918
 *
 * [풀이]
 * 문자열로 중위표기식을 입력받아 후위표기식으로 변환하는 문제이다.
 * 트리 자료구조를 응용하여 재귀함수를 설계하면 간단하게 풀이할 수 있다.
 *
 * 예를 들어 입력으로 "A+B*C-D/E"가 들어온 경우
 * 다음 규칙을 사용해서 트리 자료구조를 만들어 준다.
 *
 * (1) '+', '-', '*', '/' 연산자를 중심으로(해당 연산자를 부모 노드로 취급)
 *    왼쪽 부분 문자열을 트리 왼쪽 자식으로 가지고,
 *    오른쪽 부분 문자열을 트리 오른쪽 자식으로 가진다.
 * (2) 연산자가 여러개 존재하는 경우 '+', '-'가 '*', '/'보다 우선 처리되며,
 *    왼쪽에 있는 연산자보다 오른쪽에 있는 연산자를 우선 처리한다.
 * (3) 괄호로 묶여있는 부분은 내용에 관계없이 하나의 피연산자로 취급한다.
 * (4) 자식으로 만들어진 부분 문자열에 대해서 각각 (1)번부터 연산을 수행한다.
 *
 *              '-'
 *            /     \
 *          '+'     '/'
 *          / \     / \
 *        'A' '*' 'D' 'E'
 *            / \
 *          'B' 'C'
 *
 * 이렇게 만들어진 트리를 가장 Leaf 노드에서 부터 후위표기식으로 병합을 진행한다.
 * 최하단 노드를 각각 붙여주고 해당 노드의 부모 노드를 오른쪽에 붙여주는 방식으로
 * 진행하면 후위 표기식을 완성할 수 있다.
 *
 * +--+------------+--------+
 * | 1| BC*        |        |
 * | 2| ABC*+      |   DE/  |
 * | 3| ABC*+DE/-  |        |
 * +--+------------+--------+
 *
 * 결과 "A+B*C-D/E"의 중위표기식을 "ABC*+DE/-"의 후위표기식으로 변환했다.
 * 또한 재귀함수를 이용하면 트리 자료구조를 직접적으로 사용하지 않더라도 간단하게
 * 구현할 수 있다.
 *
 * [실수]
 * (+) 괄호를 처리하는게 가장 까다로웠다.
 * (+) 중첩된 괄호를 전부 제거하고 함수에 입력해야 한다. (((A+B)))
 * (+) 괄호를 제거하는 과정에서 전체 문자열의 길이가 줄어든다는 것을 반영해야 한다.
 *
 * [반례]
 *  (A+B)*(D-C)
 *  A/(B+C)/(E-F)
 *  A/(B+C*D)/(E-F)
 *  A/(B+C*D)/(E-F)*(G+H)
 *  (((A+B)))
 *
 * [디버깅 옵션]
 * gcc -o ./bin/p1918 p1918.cpp -D DEBUG
 */

// Includes
#include <stdio.h>
#include <string.h>

// C언어에는 Boolean type이 존재하지 않는다.
#define bool char
#define true 1
#define false 0

// 사용자 입력 최대 크기 100 + 1(NULL)
#define SIZE 101

#ifdef DEBUG
/**
 * 부분 문자열 출력 함수
 *
 * @param begin 부분 문자열 시작 포인터
 * @param end 부분 문자열 종료 포인터
 */
void print(char* begin, char* end) {
  for (; begin != end; ++begin) {
    printf("%c", *begin);
  }
  printf("\n");
}
#endif

/**
 * 부분 문자열이 괄호로 묶여 있는지 확인하는 함수
 *
 * @param begin 부분 문자열 시작 포인터
 * @param end 부분 문자열 종료 포인터
 * @return 괄호로 전체가 묶여 있다면 true 아니면 false 반환
 */
bool IsBraket(char* begin, char* end) {
  if (*begin != '(' || *(end-1) != ')') {
    // 문자열의 제일 앞과 뒤에 괄호가 없다면 false 반환
    return false;
  }

  // 문자열 내부에 괄호 열리고 닫히는걸 체크하기 위한 count 변수
  size_t count = 0;

  for (; begin != end; ++begin) {
    // 부분 문자열 내부의 문자들 순회
    switch (*begin) {
    case '(':
      // 괄호 열리는 부분
      ++count;
      break;

    case ')':
      // 괄호 닫히는 부분
      --count;
      if (count == 0 && begin != end - 1) {
        // 마지막 괄호가 닫히는데 위치가 문자열 중심에 있다면
        // 괄호가 문자열 전부를 묶지 않는다고 판단
        return false;
      }
      break;
    }
  }

  // 문자열이 괄호로 묶여 있다고 판단
  return true;
}

/**
 * 중위표기식 부분 문자열을 후위 표기식으로 변환해주는 함수
 *
 * @param begin 부분 문자열 시작 포인터
 * @param end 부분 문자열 종료 포인터
 * @return 후위 표기식 변환 이후 문자열의 길이를 반환
 */
size_t Solve(char* begin, char* end) {
#ifdef DEBUG
  printf("solve: ");
  print(begin, end);
#endif

  // 부분 문자열의 길이
  size_t len = end - begin;

  if (len == 0) {
    // 문자열이 비어있는 경우 함수를 종료 (반환값: 0)
    return len;
  }

  // 부분 문자열 시작 주소 백업
  char* pivot = begin;

  while (IsBraket(begin, end)) {
    // 부분 문자열에 묶여 있는 괄호를 풀어주는 작업 진행
    ++begin;
    --end;
    len -= 2;

#ifdef DEBUG
    printf(" braket: ");
    print(begin, end);
#endif
  }

  size_t brakets = 0;
  size_t op = 0;
  short level = 0;

  for (size_t i = 0; i < len; ++i) {
    // 부분 문자열 내부의 모든 문자들 순회
    switch (begin[i]) {
    case '(':
      // 내부 괄호 열리는 부분
      ++brakets;
      break;

    case ')':
      // 내부 괄호 닫히는 부분
      --brakets;
      break;

    case '*':
    case '/':
      if (brakets == 0 && level <= 1) {
        // 우선순위 조건에 부합하는 경우 해당 연산자를 기준으로 설정
        op = i;
        level = 1;
      }
      break;

    case '+':
    case '-':
      if (brakets == 0 && level <= 2) {
        // 우선순위 조건에 부합하는 경우 해당 연산자를 기준으로 설정
        op = i;
        level = 2;
      }
      break;
    }
  }

  if (op == 0) {
    // 연산자가 부분 문자열 내부에 존재하지 않는 경우에 함수 종료
    return len;
  }

  // 기준 연산자 문자 설정
  char ch = begin[op];

  // 기준 연산자를 중심으로 왼쪽, 오른쪽 부분 문자열을 재귀함수로 호출 
  size_t clen = 0;
  clen += Solve(begin, begin + op);
  clen += Solve(begin + op + 1, end);

  // 중위 표기법에서 후위 표기법으로 병합하는 부분
  begin[op] = '\0';

  char buf[SIZE] = "";
  strcat(buf, begin);
  strcat(buf, begin + op + 1);

  buf[clen] = ch;
  buf[++clen] = '\0';

#ifdef DEBUG
  printf(" op: %lu, ch: %c, len: %lu\n", op, ch, len);
  printf(" clen: %lu\n", clen);
  printf(" buf: %s\n", buf);
#endif

  // buf에 저장된 후위 표기법을 원본 문자열에 복사
  strcpy(pivot, buf);

  // 계산한 부분 문자열의 길이를 반환
  return clen;
}

/**
 * Entry point
 */
int main() {
  // 사용자 입력 (중위 표기식)
  char input[SIZE];
  scanf("%s", input);

  // 문자열 마지막 포인터 탐색
  char* end = input;
  while (*end != '\0') ++end;

  // 후위 표기식 변환 알고리즘 실행
  Solve(input, end);

  // 결과 출력
  printf("%s\n", input);

  return 0;
}
