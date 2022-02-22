/*
 * (220219) 균형잡힌 세상
 * https://www.acmicpc.net/problem/4949
 *
 * [풀이]
 * 소괄호() 와 대괄호[] 가 포함된 문자열이 입력되고, 문자열안에서 괄호가
 * 올바르게 열리고, 닫혔는지 확인하는 문제 입니다.
 *
 * 스택(Last In First Out)을 사용해서 괄호가 닫힐 때 마지막으로 열린 괄호와
 * 비교하여 문제에서 제시된 조건과 일치하는 지 확인 합니다.
 *
 * [실수]
 * (+) 괄호가 닫히지 않은 상태에서 문자열이 끝나면 균형잡힌 문자열으로 처리되어서 실패.
 *     문자열이 끝난후에 스택에 남아있는 문자가 없는지 확인함으로서 통과했습니다.
 */

#include <iostream>
#include <string>
#include <stack>

using namespace std;

inline char popValue(stack<char>& target) {
  target.pop();
  
  if (target.size() == 0) return '\0';
  return target.top();
}

bool solve(string str) {
  stack<char> brakets;
  char current = '\0';

  for (char ch : str) {
    if (ch == '(' || ch == '[') {
      brakets.push(ch);
      current = ch;
      
    } else if (ch == ')') {
      if (current != '(') return false;
      current = popValue(brakets);
      
    } else if (ch == ']') {
      if (current != '[') return false;
      current = popValue(brakets);
    }
  }

  return (current == '\0') ? true : false;
}

int main() {
  string str;

  while (true) {
    getline(cin, str);
    if (str == ".") break;

    if (solve(str)) {
      cout << "yes" << endl;

    } else {
      cout << "no" << endl;
    }
  }

  return 0;
}
