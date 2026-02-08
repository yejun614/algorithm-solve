/*
 * (12891) DNA 비밀번호
 * https://www.acmicpc.net/problem/12891
 */

import java.io.*;
import java.util.*;

/**
 * Baekjoon - DNA 비밀번호
 * @author YeJun, Jung
 * 
 * @see input()
 * 1. DNA문자열의 개수와 부분문자열의 개수를 입력받는다.
 * 2. DNA문자열을 입력받는다.
 * 3. 입력받은 DNA문자열을 dnaStr 배열에 저장한다.
 *  3-1. dnaStr의 0번 인덱스는 탐색 과정을 위해 비워둔다.
 * 4. 비밀번호 조건 4개를 차례로 입력받는다.
 * 
 * @see solve()
 * 5. 정답 변수 answer를 초기화한다.
 * 6. 슬라이딩 윈도우 탐색을 위한 범위 변수 begin, end를 설정한다.
 * 7. DNA 종류를 카운트할 배열 dnaTypeCount를 준비한다.
 *  7-1. A,C,G,T 이외의 요소를 카운트할 자리(index: 4)를 준비해둔다.
 * 8. 초기 부분문자열의 DNA 종류를 카운트하여 배열에 입력한다.
 * 9. 윈도우가 dnaStr 끝에 닿을때까지 윈도우를 오른쪽으로 밀면서 진행한다.
 *  9-1. 윈도우의 첫번째 및 마지막 요소만 계산한다.
 *  9-2. 현재 부분 문자열이 비밀번호 조건에 부합하는지 검사한다.
 *  9-3. 비밀번호 조건에 부합하는 부분문자열이라면 answer에 +1 더한다.
 * 
 * @see getDnaType()
 * 10. 파라미터인 dnaChar의 값을 숫자로 변환한다.
 * 
 * @see print()
 * 11. answer 값을 화면에 출력한다.
 */
public class Main {
  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  private static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    new Main().run();
  }

  // ----------------------------------------------------------

  private int answer;

  private int dnaLen;
  private int subLen;

  private char[] dnaStr;
  private int[] condition;

  public void run() throws IOException {
    input();
    solve();
    print();
  }

  private void input() throws IOException {
    // 1. DNA문자열의 개수와 부분문자열의 개수를 입력받는다.
    getLine();
    dnaLen = Integer.parseInt(input.nextToken());
    subLen = Integer.parseInt(input.nextToken());

    // 2. DNA문자열을 입력받는다.
    char[] buf = reader.readLine().trim().toCharArray();

    // 3. 입력받은 DNA문자열을 dnaStr 배열에 저장한다.
    dnaStr = new char[dnaLen + 1];
    // 3-1. dnaStr의 0번 인덱스는 탐색 과정을 위해 비워둔다.
    System.arraycopy(buf, 0, dnaStr, 1, dnaLen);

    // 4. 비밀번호 조건 4개를 차례로 입력받는다.
    getLine();
    condition = new int[4];
    for (int index = 0; index < 4; index++) {
      condition[index] = Integer.parseInt(input.nextToken());
    }
  }

  private void solve() {
    // 5. 정답 변수 answer를 초기화한다.
    answer = 0;

    // 6. 슬라이딩 윈도우 탐색을 위한 범위 변수 begin, end를 설정한다.
    int begin = 0, end = subLen - 1;

    // 7. DNA 종류를 카운트할 배열 dnaTypeCount를 준비한다.
    // 7-1. A,C,G,T 이외의 요소를 카운트할 자리(index: 4)를 준비해둔다.
    int[] dnaTypeCount = new int[5]; // A,C,G,T, others

    // 8. 초기 부분문자열의 DNA 종류를 카운트하여 배열에 입력한다.
    for (int pos = begin; pos <= end; pos++) {
      dnaTypeCount[getDnaType(dnaStr[pos])]++;
    }

    // 9. 윈도우가 dnaStr 끝에 닿을때까지 윈도우를 오른쪽으로 밀면서 진행한다.
    do {
      // 9-1. 윈도우의 첫번째 및 마지막 요소만 계산한다.
      dnaTypeCount[getDnaType(dnaStr[begin++])]--;
      dnaTypeCount[getDnaType(dnaStr[++end])]++;

      // 9-2. 현재 부분 문자열이 비밀번호 조건에 부합하는지 검사한다.
      int index = 0;
      for (; index < 4; index++) {
        if (dnaTypeCount[index] < condition[index]) break;
      }

      // 9-3. 비밀번호 조건에 부합하는 부분문자열이라면 answer에 +1 더한다.
      if (index == 4) answer++;
    } while (end < dnaLen);
  }

  private int getDnaType(char dnaChar) {
    // 10. 파라미터인 dnaChar의 값을 숫자로 변환한다.
    switch(dnaChar) {
    case 'A': return 0;
    case 'C': return 1;
    case 'G': return 2;
    case 'T': return 3;
    };
    return 4;
  }

  private void print() throws IOException {
    // 11. answer 값을 화면에 출력한다.
    writer.write(answer + "\n");
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
