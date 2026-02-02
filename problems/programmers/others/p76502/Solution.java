/*
 * (76502) 괄호 회전하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/76502
 */

import java.util.Stack;

class Solution {
  public int solution(String s) {
    final int len = s.length();
    int answer = 0;

    for (int count = 0; count < len; count++) {
      s = shiftLeft(s);
      if (isCorrect(s)) answer++;
    }

    return answer;
  }

  private String shiftLeft(String s) {
    return s.substring(1) + s.charAt(0);
  }

  private boolean isCorrect(String s) {
    Stack<Character> charStack = new Stack<>();

    for (char ch : s.toCharArray()) {
      if (ch == '(' || ch == '[' || ch == '{') {
        charStack.push(ch);
      } else if (charStack.isEmpty()) {
        return false;
      } else {
        char top = charStack.pop();

        if (
          (top == '(' && ch != ')') ||
          (top == '[' && ch != ']') || 
          (top == '{' && ch != '}')
        ) {
          return false;
        }
      }
    }

    return charStack.isEmpty();
  }

  public static void main(String[] args) {
    System.out.println(new Solution().solution("[](){}")); // 3
    System.out.println(new Solution().solution("}]()[{")); // 2
    System.out.println(new Solution().solution("[)(]")); // 0
    System.out.println(new Solution().solution("}}}")); // 0
  }
}
