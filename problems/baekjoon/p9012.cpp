/*
 * (220219) 괄호
 * https://www.acmicpc.net/problem/9012
 *
 * [풀이]
 * 올바르게 소괄호가 열리고, 닫혔는지 확인하는 문제 입니다.
 * 소괄호() 한 종류만 입력됨으로 스택이 필요하지 않습니다. 몇 개의 괄호가 열였는지
 * 카운트 한 후에 괄호가 닫힐 때 한 개씩 줄여 나갑니다.
 *
 * 이 때, 카운트 값이 0이거나 모든 검사가 끝난후에 카운트 값이 0이 아닌 경우
 * 잘못된 케이스로 판단합니다.
 */

#include <iostream>
#include <string>

using namespace std;

bool solve(string str) {
  int open = 0;

  for (char ch : str) {
    if (ch == '(') {
      open ++;
      
    } else if (open == 0) {
      return false;
      
    } else {
      open --;
    }
  }

  return (open == 0) ? true : false;
}

int main() {
  int T;
  scanf("%d", &T);

  string str;

  while (T --) {
    cin >> str;

    if (solve(str)) {
      cout << "YES" << endl;
    } else {
      cout << "NO" << endl;
    }
  }

  return 0;
}
