/*
 * (8275) 햄스터
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWxQ310aOlQDFAWL&categoryId=AWxQ310aOlQDFAWL&categoryType=CODE&problemTitle=8275&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 8275. 햄스터
 * @author YeJun, Jung
 *
 * @see #main(String[] args)
 * 1. 테스트 케이스를 입력받는다.
 * 2. 각 테스트 케이스에 대해서 솔루션을 실행한다.
 * 
 * @see #input()
 * 3. 햄스터 우리의 개수는 caseCount,
 *    각 우리에 보관할 수 있는 최대 햄스터 마리수는 hamsterCount,
 *    기록의 개수는 logCount에 저장한다.
 * 4. 기록을 저장할 2차원 배열 logArr를 초기화한다.
 * 5. logCount만큼 반복하면서 기록 내용을 logArr에 입력한다.
 * 
 * @see #solve()
 * 6. 조합을 보관할 리스트를 선언한다.
 * 7. 각 우리에 햄스터가 몇마리 있는지에 대해서 조합을 만든다.
 * 8. 만들어진 조합을 기록과 비교하면서 잘못된 조합은 삭제한다.
 * 9. 남은 조합들을 비교하여 정답을 구한다.
 * 
 * @see #searchAll()
 * 10. 첫번째 기록을 기준으로 가능한 조합을 모두 생성한다.
 * 
 * @see #makeCaseCombinationWithLog(int)
 * 11. 입력된 로그 index를 기준으로 가능한 조합을 모두 생성한다.
 *  11-1. 조합 생성 과정중에 사용할 상태 배열을 선언한다.
 *  11-2. 로그에서 데이터를 가져와서 저장한다.
 *  11-3. 로그 데이터를 바탕으로 1차 조합을 생성한다.
 *    11-3-1. 배열의 개수를 체크포인트로 기록한다.
 *  11-4. 1차 조합 범위의 왼쪽에 들어갈 수 있는 2차 조합을 생성한다.
 *    11-4-1. 체크포인트 부터 끝까지 서브 리스트를 만들어서 업데이트 한다.
 *    11-4-2. 체크포인트를 갱신한다.
 *  11-5. 2차 조합 범위의 오른쪽에 들어갈 수 있는 3차 조합을 생성한다.
 *    11-5-1. 체크포인트 부터 끝까지 서브 리스트를 만들어서 업데이트 한다.
 *    11-5-2. 체크포인트를 갱신한다.
 * 
 * @see #makeCaseCombinationWithLog(int, int[], int, int, int)
 * 12. 주어진 범위에 대해서 가능한 모든 조합을 생성하고 입력된 로그의 조건에 일치하는지 검사하여 해당 조합을 등록한다.
 *  12-1. 기저 조건을 확인한다.
 *    12-1-1. 지금 조합이 로그 데이터와 일치하는지 검사한다.
 *      12-1-1-2. 로그 데이터와 일치한다면, 해당 조합을 저장한다.
 *    12-1-2. 재귀함수를 탈출한다.
 *  12-2. 주어진 범위에 대해서 반복한다.
 *    12-2-1. 0부터 주어진 숫자까지 반복한다.
 *    12-2-1-1. 조합을 복사하여 새로운 조합을 만든다.
 *    12-2-1-2. 조합의 현재 위치에 값을 입력한다.
 *    12-2-1-3. 시작 지점을 하나 늘리고 숫자를 감소시켜 다음 햄스터 우리에 들어갈 값 조합을 만든다.
 * 
 * @see #makeCaseCombination(int[], int, int)
 * 13. 주어진 범위에 대해서 가능한 모든 조합을 생성하여 등록한다.
 *  13-1. 기저조건을 확인한다.
 *    13-1-1. 해당 조합을 등록한다.
 *    13-1-2. 재귀함수를 탈출한다.
 *  13-2. 주어진 범위에 대해서 반복한다.
 *    13-2-1. 0부터 주어진 숫자까지 반복한다.
 *    13-2-1-1. 조합을 복사하여 새로운 조합을 만든다.
 *    13-2-1-2. 조합의 현재 위치에 값을 입력한다.
 *    13-2-1-3. 시작 지점을 하나 늘려서, 다음 햄스터 우리에 들어갈 값 조합을 만든다.
 * 
 * @see #isLogCorrection(int, int[])
 * 14. 로그와 조합을 입력받아, 해당 조합이 로그 데이터에 부합하는지 검사한다.
 *  14-1. 로그 데이터를 가져온다.
 *  14-2. 로그 데이터에 기입된 범위를 탐색한다.
 *    14-2-1. 각 우리에 보관된 햄스터의 개수가 최대 개수를 넘지 않는지 검사한다.
 *    14-2-2. 현재 우리에 보관된 햄수터의 개수를 카운트 한다.
 *  14-3. 데이터에 기입된 햄스터의 개수와 실제 우리들에 보관된 햄스터의 개수가 일치하는지 검사한다.
 * 
 * @see #removeAllIncorrectCases()
 * 15. 전체 기록을 보고 생성된 조합들 중에서 잘못된 조합을 삭제한다.
 *  15-1. 올바른 조합 index를 보관할 집합을 선언하고 초기화 한다.
 *  15-2. 2번째 기록부터 끝가지 확인한다. (1번째 기록을 기준으로 조합이 만들어져 있기 때문에)
 *    15-2-1. 조합을 하나 가져온다.
 *    15-2-2. 조합이 기록 데이터에 부합하는지 검사한다.
 *    15-2-2-1. 잘못된 조합이라면 index를 삭제한다.
 *  15-3. 올바른 조합 index만 모인 리스트로 새로운 리스트를 생성한다.
 * 
 * @see #updateAnswer()
 * 16. 가장 적절한 정답 하나를 선택해서 answer 변수에 기록한다.
 *  16-1. 정답이 없다면 -1을 answer 변수에 기록한다.
 *  16-2. 정답 index로 조합을 꺼내어 answer 변수에 기록한다.
 * 
 * @see #getBestIndex()
 * 17. 남은 조합 중에서 가장 적절한 조합 하나를 선택해서 해당 index를 반환한다.
 *  17-1. 조합이 하나도 없다면 -1을 반환한다.
 *  17-2. 첫번째 조합을 기준으로 나머지 조합들과 비교하면서 최선의 조합을 찾는다.
 *  17-3. 두 조합을 비교했을때 햄스터의 개수가 더 많은 조합이 선택된다.
 *  17-4. 두 조합을 비교했을때 사전 순으로 더 빠른 조합이 선택된다.
 * 
 * @see #print()
 * 18. 화면에 테스트 케이스와 answer 배열 요소를 띄어쓰기를 기준으로 화면에 출력한다.
 */
public class Solution {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer input;
  
  // --------------------------------------------------------
  
  public static void main(String[] args) throws IOException {
    // 1. 테스트 케이스를 입력받는다.
    final int testCount = Integer.parseInt(reader.readLine().trim());
    
    // 2. 각 테스트 케이스에 대해서 솔루션을 실행한다.
    for (int testCase = 1; testCase <= testCount; testCase++) {
      new Solution(testCase).run();
    }
  }
  
  // --------------------------------------------------------
  
  private int testCase;
  private int[] answer;
  
  private int caseCount; // 6
  private int hamsterCount; // 10
  private int logCount;
  private int[][] logArr;
  
  private List<int[]> caseCombinationList;
  
  public Solution(int testCase) {
    this.testCase = testCase;
  }
  
  public void run() throws IOException {
    input();
    solve();
    print();
  }
  
  private void input() throws IOException {
    // 3. 햄스터 우리의 개수는 caseCount,
    //    각 우리에 보관할 수 있는 최대 햄스터 마리수는 hamsterCount,
    //    기록의 개수는 logCount에 저장한다.
    getLine();
    caseCount = Integer.parseInt(input.nextToken());
    hamsterCount = Integer.parseInt(input.nextToken());
    logCount = Integer.parseInt(input.nextToken());
    
    // 4. 기록을 저장할 2차원 배열 logArr를 초기화한다.
    logArr = new int[logCount][3];
    
    // 5. logCount만큼 반복하면서 기록 내용을 logArr에 입력한다.
    for (int row = 0; row < logCount; row++) {
      getLine();
      for (int col = 0; col < 3; col++) {
        logArr[row][col] = Integer.parseInt(input.nextToken());
      }
    }
  }
  
  private void solve() throws IOException {
    // 6. 조합을 보관할 리스트를 선언한다.
    caseCombinationList = new ArrayList<>();
    
    // 7. 각 우리에 햄스터가 몇마리 있는지에 대해서 조합을 만든다.
    searchAll();
    
    // 8. 만들어진 조합을 기록과 비교하면서 잘못된 조합은 삭제한다.
    removeAllIncorrectCases();
    
    // 9. 남은 조합들을 비교하여 정답을 구한다.
    updateAnswer();
  }
  
  private void searchAll() {
    // 10. 첫번째 기록을 기준으로 가능한 조합을 모두 생성한다.
    makeCaseCombinationWithLog(0);
  }
  
  private void makeCaseCombinationWithLog(int log) {
    // 11. 입력된 로그 index를 기준으로 가능한 조합을 모두 생성한다.
    
    // 11-1. 조합 생성 과정중에 사용할 상태 배열을 선언한다.
    int[] state = new int[caseCount];
    
    // 11-2. 로그에서 데이터를 가져와서 저장한다.
    int begin = logArr[log][0] - 1;
    int end = logArr[log][1] - 1;
    int num = logArr[log][2];
    
    // 11-3. 로그 데이터를 바탕으로 1차 조합을 생성한다.
    makeCaseCombinationWithLog(log, state, begin, end, num);
    // 11-3-1. 배열의 개수를 체크포인트로 기록한다.
    int checkPoint = caseCombinationList.size();
    
    // 11-4. 1차 조합 범위의 왼쪽에 들어갈 수 있는 2차 조합을 생성한다.
    for (int searchIndex = 0; searchIndex < checkPoint; searchIndex++) {
      makeCaseCombination(caseCombinationList.get(searchIndex), 0, begin - 1);
    }
    // 11-4-1. 체크포인트 부터 끝까지 서브 리스트를 만들어서 업데이트 한다.
    caseCombinationList = caseCombinationList.subList(checkPoint, caseCombinationList.size());
    // 11-4-2. 체크포인트를 갱신한다.
    checkPoint = caseCombinationList.size();
    
    // 11-5. 2차 조합 범위의 오른쪽에 들어갈 수 있는 3차 조합을 생성한다.
    for (int searchIndex = 0; searchIndex < checkPoint; searchIndex++) {
      makeCaseCombination(caseCombinationList.get(searchIndex), end + 1, caseCount - 1);
    }
    // 11-5-1. 체크포인트 부터 끝까지 서브 리스트를 만들어서 업데이트 한다.
    caseCombinationList = caseCombinationList.subList(checkPoint, caseCombinationList.size());
    // 11-5-2. 체크포인트를 갱신한다.
    checkPoint = caseCombinationList.size();
  }
  
  private void makeCaseCombinationWithLog(int log, int[] state, int begin, int end, int num) {
    // 12. 주어진 범위에 대해서 가능한 모든 조합을 생성하고 입력된 로그의 조건에 일치하는지 검사하여 해당 조합을 등록한다.
    
    // 12-1. 기저 조건을 확인한다.
    if (begin > end) {
      // 12-1-1. 지금 조합이 로그 데이터와 일치하는지 검사한다.
      if (isLogCorrection(log, state)) {
        // 12-1-1-2. 로그 데이터와 일치한다면, 해당 조합을 저장한다.
        caseCombinationList.add(state);
      }
      
      // 12-1-2. 재귀함수를 탈출한다.
      return;
    }
    
    // 12-2. 주어진 범위에 대해서 반복한다.
    for (int pos = begin; pos <= end; pos++) {
      // 12-2-1. 0부터 주어진 숫자까지 반복한다.
      for (int n = 0; n <= num; n++) {
        int[] nextState = state.clone(); // 12-2-1-1. 조합을 복사하여 새로운 조합을 만든다.
        nextState[pos] = n;              // 12-2-1-2. 조합의 현재 위치에 값을 입력한다.
        
        // 12-2-1-3. 시작 지점을 하나 늘리고 숫자를 감소시켜 다음 햄스터 우리에 들어갈 값 조합을 만든다.
        makeCaseCombinationWithLog(log, nextState, pos + 1, end, num - n);
      }
    }
  }
  
  private void makeCaseCombination(int[] state, int begin, int end) {
    // 13. 주어진 범위에 대해서 가능한 모든 조합을 생성하여 등록한다.
    
    // 13-1. 기저조건을 확인한다.
    if (begin > end) {
      caseCombinationList.add(state); // 13-1-1. 해당 조합을 등록한다.
      return;                         // 13-1-2. 재귀함수를 탈출한다.
    }
    
    // 13-2. 주어진 범위에 대해서 반복한다.
    for (int pos = begin; pos <= end; pos++) {
      // 13-2-1. 0부터 주어진 숫자까지 반복한다.
      for (int n = 0; n <= hamsterCount; n++) {
        int[] nextState = state.clone(); // 13-2-1-1. 조합을 복사하여 새로운 조합을 만든다.
        nextState[pos] = n;              // 13-2-1-2. 조합의 현재 위치에 값을 입력한다.
        
        // 13-2-1-3. 시작 지점을 하나 늘려서, 다음 햄스터 우리에 들어갈 값 조합을 만든다.
        makeCaseCombination(nextState, pos + 1, end);
      }
    }
  }
  
  private boolean isLogCorrection(int log, int[] state) {
    // 14. 로그와 조합을 입력받아, 해당 조합이 로그 데이터에 부합하는지 검사한다.
    
    // 14-1. 로그 데이터를 가져온다.
    int begin = logArr[log][0] - 1;
    int end = logArr[log][1] - 1;
    int num = logArr[log][2];
    
    // 14-2. 로그 데이터에 기입된 범위를 탐색한다.
    for (int pos = begin; pos <= end; pos++) {
      // 14-2-1. 각 우리에 보관된 햄스터의 개수가 최대 개수를 넘지 않는지 검사한다.
      if (state[pos] > hamsterCount) return false;
      // 14-2-2. 현재 우리에 보관된 햄수터의 개수를 카운트 한다.
      num -= state[pos];
    }
    
    // 14-3. 데이터에 기입된 햄스터의 개수와 실제 우리들에 보관된 햄스터의 개수가 일치하는지 검사한다.
    return num == 0;
  }
  
  private void removeAllIncorrectCases() {
    // 15. 전체 기록을 보고 생성된 조합들 중에서 잘못된 조합을 삭제한다.
    
    // 15-1. 올바른 조합 index를 보관할 집합을 선언하고 초기화 한다.
    Set<Integer> indexList = new HashSet<>();
    for (int caseIndex = 0; caseIndex < caseCombinationList.size(); caseIndex++) {
      indexList.add(caseIndex);
    }
    
    // 15-2. 2번째 기록부터 끝가지 확인한다. (1번째 기록을 기준으로 조합이 만들어져 있기 때문에)
    for (int logIndex = 1; logIndex < logCount; logIndex++) {
      for (int caseIndex = 0; caseIndex < caseCombinationList.size(); caseIndex++) {
        // 15-2-1. 조합을 하나 가져온다.
        int[] cases = caseCombinationList.get(caseIndex);
        
        // 15-2-2. 조합이 기록 데이터에 부합하는지 검사한다.
        if (!isLogCorrection(logIndex, cases)) {
          // 15-2-2-1. 잘못된 조합이라면 index를 삭제한다.
          indexList.remove(caseIndex);
        }
      }
    }
    
    // 15-3. 올바른 조합 index만 모인 리스트로 새로운 리스트를 생성한다.
    List<int[]> result = new ArrayList<>();
    for (int index : indexList) result.add(caseCombinationList.get(index));
    caseCombinationList = result;
  }
  
  private void updateAnswer() {
    // 16. 가장 적절한 정답 하나를 선택해서 answer 변수에 기록한다.
    
    int bestIndex = getBestIndex();
    
    if (bestIndex == -1) {
      // 16-1. 정답이 없다면 -1을 answer 변수에 기록한다.
      answer = new int[] {-1};
    } else {
      // 16-2. 정답 index로 조합을 꺼내어 answer 변수에 기록한다.
      answer = caseCombinationList.get(bestIndex);
    }
  }
  
  private int getBestIndex() {
    // 17. 남은 조합 중에서 가장 적절한 조합 하나를 선택해서 해당 index를 반환한다.
    
    // 17-1. 조합이 하나도 없다면 -1을 반환한다.
    if (caseCombinationList.size() == 0) return -1;
    
    // 17-2. 첫번째 조합을 기준으로 나머지 조합들과 비교하면서 최선의 조합을 찾는다.
    int bestIndex = 0;
    int[] best = caseCombinationList.get(0);
    int bestHamsterCount = Arrays.stream(caseCombinationList.get(0)).sum();
    
    for (int caseIndex = 0; caseIndex < caseCombinationList.size(); caseIndex++) {
      int[] current = caseCombinationList.get(caseIndex);
      int currentCount = Arrays.stream(current).sum();
      
      if (bestHamsterCount != currentCount) {
        // 17-3. 두 조합을 비교했을때 햄스터의 개수가 더 많은 조합이 선택된다.
        if (bestHamsterCount < currentCount) {
          bestIndex = caseIndex;
          best = current;
          bestHamsterCount = currentCount;
        }
      } else {
        // 17-4. 두 조합을 비교했을때 사전 순으로 더 빠른 조합이 선택된다.
        int index;
        for (index = 0; index < caseCount; index++) {
          if (best[index] < current[index]) break;
        }
        
        if (index == caseCount) {
          bestIndex = caseIndex;
          best = current;
          bestHamsterCount = currentCount;
        }
      }
    }
    
    return bestIndex;
  }
  
  private void print() throws IOException {
    // 18. 화면에 테스트 케이스와 answer 배열 요소를 띄어쓰기를 기준으로 화면에 출력한다.
    writer.write("#" + testCase);
    
    for (int hamster : answer) {
      writer.write(" " + hamster);
    }
    
    writer.write("\n");
    writer.flush();
  }
  
  // --------------------------------------------------------
  
  private static void getLine() throws IOException {
    input = new StringTokenizer(reader.readLine().trim());
  }
}
