/*
 * (220511) 부분 수열의 최대값
 * http://www.ascode.org/problem.php?id=1227
 *
 * [풀이]
 * (1). 배열을 입력받은 후 (혹은 입력받으면서)
 * (2). 배열의 각 요소에 바로 앞에 있는 요소와 더한 값을 저장한다.
 *      (두번째 배열부터 작업 진행)
 * (3). 이 때, 앞에 있는 요소가 "0 혹은 음수라면" 더하지 않고 값을 유지한다.
 * (4). 새롭게 만들어진 배열에서 최대값을 찾는다.
 * (5). 해당 값이 부분 수열의 최대값이며, 최대값의 위치가 수열의 끝점이 된다.
 * (6). 시작점을 찾기 위해 끝점부터 첫번째 요소까지 배열을 탐색하면서 "0 혹은 음수를" 찾는다.
 * (7). 해당 지점이 시작점 이다.
 * (8). 시작점과 끝점을 출력할 때는 0부터 세는게 아니라 1부터 센다는 점에 유의한다.
 *      (시작점과 끝점에 각각 +1 해서 출력한다.)
 *
 * [실수 & 느낌점]
 * (+) 이번 문제는 실수가 너무 많았다. 문제를 정확하게 이해하지 못한 상태로 코딩을 해서
 *     더욱 실수가 많았고, 막막하기도 했다.
 * (+) 우영운 교수님 께서 전공버프 수업을 하면서 주신 힌트를 기반으로 알고리즘을 작성했는데,
 *     배열의 각 요소를 바로 앞에 요소와 더해야 한다는 점만 기억하고 풀이를 작성했다.
 */

#include <cstdio>
#define MIN_VAL -1001

using namespace std;

int main() {
  int T;
  scanf("%d", &T);

  while (T --) {
    int N;
    scanf("%d", &N);

    int *nums = new int[N];
    int max_value = MIN_VAL, max_value_index = 0;

    for (int i = 0; i < N; i ++) {
      scanf("%d", nums + i);
      if (i > 0 && nums[i-1] > 0) nums[i] += nums[i-1];
      
      if (nums[i] > max_value) {
        max_value = nums[i];
        max_value_index = i;
      }
    }

    int start_index;
    for (start_index = max_value_index - 1; start_index >= 0; start_index --) {
      if (nums[start_index] <= 0) break;
    }
      
    printf("%d %d %d\n", start_index+2, max_value_index+1, max_value);
    delete [] nums;
  }

  return 0;
}
