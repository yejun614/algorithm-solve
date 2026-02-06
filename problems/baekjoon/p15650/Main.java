/*
 * (15650) N과 M (2)
 * https://www.acmicpc.net/problem/15650
 */

package baekjoon.p15650;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Baekjoon - N과 M (2)
 * @author YeJun, Jung
 *
 * @see #main(String[])
 * 1. 입출력을 초기화한다.
 * 2. N과 M을 입력받는다.
 * 3. 1부터 N까지의 숫자로 구성된 배열을 생성한다.
 * 4. 반복문을 통해 순열을 생성하며 조건을 확인한다.
 *  4-1. 선택된 M개의 숫자가 오름차순인지 확인한다.
 *  4-2. 오름차순일 경우 해당 숫자들을 출력한다.
 *  4-3. 중복 계산을 피하기 위해 배열의 뒷부분을 뒤집고 다음 순열로 넘어간다.
 * 
 * @see #nextPermutation(int[])
 * 5. 현재 배열을 사전순으로 다음에 오는 순열로 변형한다.
 *  5-1. 뒤에서부터 감소하는 지점(a-1)을 찾는다.
 *  5-2. 다시 뒤에서부터 x[a-1]보다 큰 값(b)을 찾아 교환한다.
 *  5-3. a 지점부터 끝까지 배열을 뒤집어 사전순 최소로 만든다.
 * 
 * @see #reverse(int[], int, int)
 * 6. 지정된 범위 내의 배열 요소 순서를 반전시킨다.
 */
public class Main {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;
  
  public static void main(String[] args) throws IOException {
    // 1. 입출력을 초기화한다.
    getLine();
    
    // 2. N과 M을 입력받는다.
    final int N = Integer.parseInt(input.nextToken());
    final int M = Integer.parseInt(input.nextToken());
    
    // 3. 1부터 N까지의 숫자로 구성된 배열을 생성한다.
    int[] arr = IntStream.rangeClosed(1, N).toArray();
    
    // 4. 반복문을 통해 순열을 생성하며 조건을 확인한다.
    do {
      // 4-1. 선택된 M개의 숫자가 오름차순인지 확인한다.
      int index = 1;
      while (index < M && arr[index - 1] <= arr[index]) index++;
      
      // 4-2. 오름차순일 경우 해당 숫자들을 출력한다.
      if (index == M) {
        for (index = 0; index < M; index++) {
          writer.write(arr[index] + " ");
        }
        writer.write("\n");
      }
      
      // 4-3. 중복 계산을 피하기 위해 배열의 뒷부분을 뒤집고 다음 순열로 넘어간다.
      // (조합을 구하는 문제이므로 M 이후의 순서는 중요하지 않음)
      reverse(arr, M, N - 1);
    } while (nextPermutation(arr));
    
    writer.flush();
  }
  
  // --------------------------------------------------------
  
  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
  
  private static boolean nextPermutation(int[] x) {
    // 5. 현재 배열을 사전순으로 다음에 오는 순열로 변형한다.
    int n = x.length - 1, a = n, b = n;
    
    // 5-1. 뒤에서부터 감소하는 지점(a-1)을 찾는다.
    while (a > 0 && x[a - 1] >= x[a]) a--;
    if (a == 0) return false;
    
    // 5-2. 다시 뒤에서부터 x[a-1]보다 큰 값(b)을 찾아 교환한다.
    while (x[a - 1] >= x[b]) b--;
    swap(x, a - 1, b); 
    
    // 5-3. a 지점부터 끝까지 배열을 뒤집어 사전순 최소로 만든다.
    reverse(x, a, n);
    return true;
  }
  
  private static void swap(int[] x, int a, int b) {
    int t = x[a]; x[a] = x[b]; x[b] = t;
  }
  
  private static void reverse(int[] x, int a, int b) {
    // 6. 지정된 범위 내의 배열 요소 순서를 반전시킨다.
    while (a < b) swap(x, a++, b--);
  }
}