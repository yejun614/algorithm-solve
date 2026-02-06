/*
 * (1218) [S/W 문제해결 기본] 4일차 - 괄호 짝 짓기
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14eWb6AAkCFAYD&categoryId=AV14eWb6AAkCFAYD&categoryType=CODE&problemTitle=1218&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

package swea.p1218;

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 1218. [S/W 문제해결 기본] 4일차 - 괄호 짝 짓기
 * @author YeJun, Jung
 *
 * @see #main(String[])
 * 1. 입출력을 초기화한다.
 * 2. 테스트 케이스 10개를 반복하여 실행한다.
 * 
 * @see #Solution(int)
 * 3. 멤버 변수를 초기화한다.
 * 
 * @see #run()
 * 4. 입력, 해결, 출력 순서로 실행한다.
 * 
 * @see #input()
 * 5. 문자열의 길이를 입력받으나 사용하지 않으므로 무시한다.
 * 6. 검사할 괄호 문자열을 입력받아 chars 배열에 저장한다.
 * 
 * @see #solve()
 * 7. 정답을 0(유효하지 않음)으로 초기화한다.
 * 8. Deque를 활용하여 스택(stack) 객체를 생성한다.
 * 9. 문자열의 각 문자를 순회하며 유효성을 검사한다.
 *  9-1. 닫는 괄호일 경우, 스택의 top이 대응하는 여는 괄호인지 확인한다.
 *  9-2. 일치하지 않으면 유효하지 않은 문자열로 판단하고 종료한다.
 *  9-3. 여는 괄호일 경우, 스택에 push한다.
 * 10. 모든 검사를 통과하면 정답을 1(유효함)로 설정한다.
 * 
 * @see #print()
 * 11. 결과값을 양식에 맞춰 출력한다.
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  
  public static void main(String[] args) throws IOException {
    // 1. 입출력을 초기화한다.
    final int testCount = 10;
    
    // 2. 테스트 케이스 10개를 반복하여 실행한다.
    for (int testCase = 1; testCase <= testCount; testCase++) {
      new Solution(testCase).run();
    }
  }
  
  // --------------------------------------------------------
  
  private int testCase;
  private char answer;
  private char[] chars;
  
  public Solution(int testCase) {
    // 3. 멤버 변수를 초기화한다.
    this.testCase = testCase;
  }
  
  public void run() throws IOException {
    // 4. 입력, 해결, 출력 순서로 실행한다.
    input();
    solve();
    print();
  }
  
  private void input() throws IOException {
    // 5. 문자열의 길이를 입력받으나 사용하지 않으므로 무시한다.
    reader.readLine(); 
    
    // 6. 검사할 괄호 문자열을 입력받아 chars 배열에 저장한다.
    chars = reader.readLine().trim().toCharArray();
  }
  
  private void solve() {
    // 7. 정답을 0(유효하지 않음)으로 초기화한다.
    answer = '0';
    
    // 8. Deque를 활용하여 스택(stack) 객체를 생성한다.
    Deque<Character> stack = new ArrayDeque<>();
    
    // 9. 문자열의 각 문자를 순회하며 유효성을 검사한다.
    for (char ch : chars) {
      Character peek = stack.peek();
      
      switch (ch) {
      case ')':
        // 9-1. 닫는 괄호일 경우, 스택의 top이 대응하는 여는 괄호인지 확인한다.
        if (peek != '(') return; // 9-2. 일치하지 않으면 유효하지 않은 문자열로 판단
        stack.pop();
        break;
      case ']':
        if (peek != '[') return;
        stack.pop();
        break;
      case '}':
        if (peek != '{') return;
        stack.pop();
        break;
      case '>':
        if (peek != '<') return;
        stack.pop();
        break;
      default:
        // 9-3. 여는 괄호일 경우, 스택에 push한다.
        stack.push(ch);
      }
    }
    
    // 10. 모든 검사를 통과하면 정답을 1(유효함)로 설정한다.
    answer = '1';
  }
  
  private void print() throws IOException {
    // 11. 결과값을 양식에 맞춰 출력한다.
    writer.write("#" + testCase);
    writer.write(" ");
    writer.write(answer);
    writer.write("\n");
    writer.flush();
  }
}