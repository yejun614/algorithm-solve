/*
 * (5658) [모의 SW 역량테스트] 보물상자 비밀번호
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo&categoryId=AWXRUN9KfZ8DFAUo&categoryType=CODE&problemTitle=5658&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [모의 SW 역량테스트] 보물상자 비밀번호
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 사각형 뚜껑 위에 숫자가 적혀 있다. 이때 각 변의 숫자의 개수는 입력에 따라 달라진다.
 *   - 각 변의 숫자는 오른쪽으로 회전할 수 있다.
 *   - 숫자는 16진수로 주어진다. (0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F)
 *   - 숫자를 오른쪽으로 회전시키면서 만들 수 있는 모든 비밀번호를 모아 내림차순 정렬한 후 K번째를 선택하라
 * 
 * [전략]
 *   - 뚜껑 위의 숫자를 입력받아 StringBuilder로 만든다.
 *     - 오른쪽으로 회전시킬때 마지막 문자를 첫번째 index에 삽입한다.
 *     - 회전시킨 문자열을 부분 문자열로 만들어 비밀번호를 추출한다.
 *     - 비밀번호를 HashSet에 저장하여 중복 제거한다.
 *   - HashSet을 Array로 변환하고 정렬한 후 K번째 숫자를 선택한다.
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder output = new StringBuilder();
  static StringTokenizer input;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    final int testCount = nextInt();

    for (int testCase = 1; testCase <= testCount; testCase++) {
      new Solution(testCase).run();
    }

    System.out.print(output.toString());
  }

  // ----------------------------------------------------------

  int testCase;
  int answer;
  int strLen;
  int selectOrder;
  StringBuilder str;
  Set<String> codeSet;
  int subLen;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    strLen = nextInt();
    selectOrder = nextInt();
    str = new StringBuilder(next());
  }

  private void solve() {
    codeSet = new HashSet<>();
    subLen = strLen / 4;

    findAllCode();

    String[] codeArr = codeSet.toArray(new String[0]);
    int[] codeNumArr = hexStr2Int(codeArr);
    Arrays.sort(codeNumArr);

    answer = codeNumArr[codeNumArr.length - selectOrder];
  }

  private void findAllCode() {
    String init = str.toString();
    String buf = "";

    // 한바퀴 돌면 중단한다.
    while (!init.equals(buf)) {
      // 오른쪽으로 돌린다.
      str.insert(0, str.charAt(strLen - 1));
      str.deleteCharAt(strLen);

      // 각 변의 숫자를 codeSet에 등록한다.
      for (int idx = 0; idx < strLen; idx += subLen) {
        codeSet.add(str.substring(idx, idx + subLen));
      }

      buf = str.toString();
    }
  }

  private int[] hexStr2Int(String[] strArr) {
    int[] result = new int[strArr.length];

    for (int idx = 0; idx < strArr.length; idx++) {
      result[idx] = Integer.parseInt(strArr[idx], 16);
    }

    return result;
  }

  private void print() {
    output
      .append('#')
      .append(testCase)
      .append(' ')
      .append(answer)
      .append('\n');
  }

  // ----------------------------------------------------------

  static String next() throws IOException {
    if (input == null || !input.hasMoreTokens())
      input = new StringTokenizer(reader.readLine().trim());
    return input.nextToken();
  }

  static int nextInt() throws IOException {
    return Integer.parseInt(next());
  }
}
