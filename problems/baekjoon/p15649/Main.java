/*
 * (15649) N과 M (1)
 * https://www.acmicpc.net/problem/15649
 */

package baekjoon.p15649;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 백준 - 15649. N과 M (1)
 * @author YeJun, Jung
 *
 * @see #main(String[])
 * 1. 입출력을 초기화한다.
 * 2. N(전체 수의 범위)과 M(고를 수의 개수)을 입력받는다.
 * 3. 조합을 선택하기 위한 select 배열을 생성하고 상위 M개를 1로 채운다.
 * 4. prevPermutation을 활용하여 N개 중 M개를 뽑는 조합 시뮬레이션을 수행한다.
 * 5. 선택된 숫자들을 buffer 배열에 담는다.
 * 6. buffer에 담긴 숫자들을 다시 순열로 나열하기 위해 order 인덱스 배열을 생성한다.
 * 7. prevPermutation을 활용하여 선택된 M개의 숫자로 만들 수 있는 모든 순열을 생성한다.
 * 8. 생성된 각 순열을 StringBuilder에 담아 result 리스트에 저장한다.
 * 9. 모든 조합과 순열 생성이 끝나면 result 리스트를 사전순으로 정렬한다.
 * 10. 정답 리스트를 화면에 출력한다.
 *
 * @see #prevPermutation(int[])
 * 11. 현재 배열의 상태를 사전순의 이전 단계 순열로 변환한다.
 * 11-1. 뒤에서부터 인접한 두 원소가 오름차순(x[a-1] > x[a])인 지점을 찾는다.
 * 11-2. 다시 뒤에서부터 x[a-1]보다 작은 원소를 찾아 교환(swap)한다.
 * 11-3. 지점 a 이후의 배열을 뒤집어(reverse) 이전 순열을 완성한다.
 */
public class Main {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer line;

  public static void main(String[] args) throws IOException {
    // 1. 입출력을 초기화한다.
    getLine();
    
    // 2. N과 M을 입력받는다.
    int N = Integer.parseInt(line.nextToken());
    int M = Integer.parseInt(line.nextToken());

    // 3. 조합 선택용 배열 (1인 위치의 인덱스를 숫자로 사용)
    int[] select = new int[N];
    Arrays.fill(select, 0, M, 1);

    List<String> result = new ArrayList<>();
    
    // 4. 조합(Combination) 생성 루프
    do {
      // 5. 현재 조합에서 선택된 숫자들을 buffer에 저장
      int[] buffer = new int[M];
      for (int num = 0, index = 0; num < N; num++) {
        if (select[num] == 1) buffer[index++] = num + 1;
      }

      // 6. 선택된 숫자들 내에서 순열을 만들기 위한 인덱스 배열
      int[] order = IntStream.rangeClosed(0, M - 1).toArray();
      reverse(order, 0, M - 1); // 내림차순 정렬 상태에서 prevPermutation 시작
      
      // 7. 순열(Permutation) 생성 루프
      do {
        StringBuilder output = new StringBuilder();
        // 8. 현재 순열 순서대로 숫자를 이어붙여 결과 리스트에 추가
        for (int index = 0; index < M; index++) {
          output.append(buffer[order[index]] + " ");
        }
        result.add(output.toString());
      } while (prevPermutation(order)); // 11. 이전 순열이 존재하면 반복
      
    } while (prevPermutation(select));

    // 9. 모든 결과를 사전순으로 정렬한다.
    Collections.sort(result);

    // 10. 정답 배열 내용을 화면에 출력한다.
    for (String line : result) {
      writer.write(line);
      writer.write("\n");
    }
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void getLine() throws IOException {
    line = new StringTokenizer(reader.readLine().trim());
  }

  /**
   * 11. 사전순의 이전 순열을 생성하는 메서드
   */
  private static boolean prevPermutation(int[] x) {
    int n = x.length - 1, a = n, b = n;
    // 11-1. 뒤에서부터 하강 지점 탐색
    while (a > 0 && x[a - 1] <= x[a]) a--;
    if (a == 0) return false;
    
    // 11-2. 교환할 위치 탐색
    while (x[a - 1] <= x[b]) b--;
    
    // 11-3. 교환 및 반전
    swap(x, a - 1, b); 
    reverse(x, a, n);
    return true;
  }

  private static void reverse(int[] x, int a, int b) {
    while (a < b) swap(x, a++, b--);
  }

  private static void swap(int[] x, int a, int b) {
    int t = x[a]; x[a] = x[b]; x[b] = t;
  }
}