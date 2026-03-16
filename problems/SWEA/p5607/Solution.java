/*
 * (5607) [Professional] 조합
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGKdbqczEDFAUo&categoryId=AWXGKdbqczEDFAUo&categoryType=CODE&problemTitle=5607&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - [Professional] 조합
 * @author YeJun, Jung
 * 
 * [분석]
 *   - N개 중에서 R개를 선택하여 만들수 있는 모든 조합의 개수를 구하라
 *     - 조합 공식 N Comb R => N! / (R! * (N-R)!)
 *   - 정답(조합의 개수)를 1234567891로 나눈 나머지를 출력하라
 *     - 원래는 계산 과정중에 오버플로우가 발생하여 정확한 값을 알 수 없다.
 *     - "A (mod N)" 연산(나누기의 나머지)은 A값이 N보다 커지지 않도록 만들어 준다.
 *       - A=0...10, N=3  =>  0, 1, 2, 0, 1, 2, 0, 1, 2, 0
 * 
 * [접근법]
 *   - 팩토리얼에 대해서
 *     - N의 최대값이 1,000,000이고 조합 과정에서 (0! ~ 1,000,000!) 범위의 값을 사용할 예정이다.
 *     - 테스트 케이스(T <= 20)가 존재하는 문제로 미리 팩토리얼을 최대값 까지 계산해 놓을 수 있다면 중복 연산을 단축시킬 수 있다.
 *     - 1억번 반복에 1초가 걸린다고 가정할 때 1,000,000 반복은 충분히 수행 가능하다.
 *   - 조합 계산에 대해서
 *     - 조합 공식은 "N Comb R => N! / (R! * (N-R)!)" 이다.
 *     - 여기서 조합 결과값이 너무 커져 오버플로우가 발생할 수 있기 때문에 MOD=1234567891로 나눈 나머지를 구해야 하는 것이 문제이다.
 *       - 모듈러 연산은 "분배법칙"이 성립하지 않는다.
 *         - 따라서 N! / ((R! * (N-R)!) % MOD) 는 수학적으로 불가능하다.
 *     - 대안: 페르마의 소정리
 *       - 정리) 소수 p와 p로 나누어 떨어지지 않는 정수 a에 대해서 a^{p-1} ≡ 1 (mod p) 가 성립한다.
 *         - 즉, a^{p-1} % p == 0 이라는 의미이다.
 *     - 확장: 모듈러 역원
 *       - 정리) 페르마의 소정리에 따라 a^{p-1} ≡ 1 (mod p) 가 성립한다. 따라서 a^{-1}을 나누기 위해 필요한 수는 a^{p-2}와 같다.
 *         - 소수 p로 나누는 공간에서 a^{p-1}은 항상 1이다.(페르마의 소정리) 따라서 a^{p-2}는 a^{-1} = 1/a 이다.(모듈러 역원)
 *         - "a^{p-1} ≡ 1 (mod p)" 여기서 양변에 a의 역원인 a^{-1}을 곱하면 a^{p-2} ≡ a^{-1} (mod p)가 성립하기 때문이다.
 *         - 따라서 기존 조합 공식을 변형시켜 풀이 가능하게 고쳐 쓸 수 있게된다.
 *     - (N Comb R) (mod p)
 *       => ( N! / (R! * (N-R)!) ) (mod p) .......... 조합의 개수 공식
 *       => ( A  /  B ) (mod p) ..................... 계산의 편의를 위해 A, B로 치환함
 *       => ( A * B^{p - 2} ) (mod p) ............... 모듈러 역원 적용
 *       => ( N! * (R! * (N-R)!)^{p-1} ) (mod p) .... A, B를 풀어서 원래 수식으로 변환
 *   - 빠른 거듭제곱에 대해서
 *     - 참고자료: https://m.blog.naver.com/ryu_eclipse/222309696493
 *     - 예를 들어 3^13를 계산한다고 할 때
 *       - 3^13 = (3^8) * (3^4) * (3^1) 으로 바꿔 쓸 수 있다.
 *       - 13을 이진수로 표현하면 0b1101 인데 오른쪽 부터 차례대로...
 *         - 1    :    2^1 = 2     <---->    3^1
 *         - 0    :        = 0
 *         - 1    :    2^2 = 4     <---->    3^4
 *         - 1    :    2^3 = 8     <---->    3^8
 *       - 거듭제곱 할때에 지수 만큼 반복하는 대신 지수의 이진법을 차례로 읽으면서 연산 횟수를 최소화 시킬 수 있다.
 * 
 * [전략]
 *   - 0! ~ 1,000,000! 까지 사전에 팩토리얼 계산을 해서 배열에 저장해 둔다.
 *   - 조합 % MOD 수식을 페르마의 소정리 및 모듈러의 역원에 의해 ( A  /  B ) (mod p) 나눗셈 대신 거듭제곱으로 바꿔 계산한다.
 *     - 빠른 거듭제곱 기법을 사용해서 조합 결과를 계산한다.
 * 
 * [후기]
 *   - 단순 알고리즘 유형이 아니라, 수학적 지식을 요구하는 고난도 문제이다.
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

  static final long MOD = 1234567891L;
  static long[] fact = new long[1000001];

  static {
    fact[0] = 1; // 0! = 1
    for (int num = 1; num < 1000001; num++) {
      fact[num] = (num * fact[num - 1]) % MOD;
    }
  }

  int testCase;
  long answer;
  int N;
  int R;

  public Solution(int testCase) { this.testCase = testCase; }
  public void run() throws IOException { input(); solve(); print(); }

  private void input() throws IOException {
    N = nextInt();
    R = nextInt();
  }

  private void solve() {
    long temp = fastPow((fact[R] * fact[N-R]) % MOD, MOD-2);
    answer = (fact[N] * temp) % MOD;
  }

  private long fastPow(long base, long exponent) {
    // REF: https://m.blog.naver.com/ryu_eclipse/222309696493

    long result = 1;
    base %= MOD;

    while (exponent > 0) {
      if ((exponent & 1) == 1) {
        result = (result * base) % MOD;
      }
      base = (base * base) % MOD;
      exponent >>= 1;
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

  static long nextLong() throws IOException {
    return Long.parseLong(next());
  }
}
