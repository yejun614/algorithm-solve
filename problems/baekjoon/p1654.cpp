/*
 * (220208) 랜선 자르기
 * https://www.acmicpc.net/problem/1654
 *
 * [풀이]
 * 이 문제는 주어진 수들을 보고 K개 이상 나눌수 있는 최대 몫을 찾는 문제이다.
 * 탐색문제로 이진 탐색을 이용하면 해결할 수 있다. 다만 단순 이진 탐색으로는
 * 최대값을 찾아낼 수 없는데, 매개 변수 탐색이라는 기법을 이용해서 최대값을 찾아낸다.
 *
 * (아래 코드 53 ~ 61 라인을 보면 일반적인 이진탐색과 조건이 다르다는 것을 알 수 있다.)
 *
 * [실수]
 * (+) 처음 문제를 보고 생각했던 알고리즘은 입력받은 랜선 값 중 최대값을 가져와
 *     최대값 나누기 0~N 을 계산할려고 했지만, 근삿값은 얻을 수 있어도 정확한 값은
 *     얻을 수 없어서 실패.
 *
 * (+) 알고리즘은 이진탐색으로 변경하였지만 최대값을 찾지 못하고 역시나 근삿값을
 *     출력하여 실패했다. 이진탐색에서 매개 변수 탐색이라고 불리는 기법을 적용해서 해결.
 *
 * (+) 문제에서 제시된 자료형의 크기가 2^31-1 로 적혀있어 int 사용했다가 틀렸다.
 *     입력을 받고, 이진 탐색과정 중 범위 조정하다가 오버플로우를 이르킨 것으로 추정.
 *     unsigned int 로 자료형 크기를 늘려준 덕분에 통과!
 */

#include <cstdio>
#include <vector>

using namespace std;

inline int counting(vector<unsigned int>& lines, unsigned int value) {
  int count = 0;

  for (auto line : lines) {
    count += line / value;
  }

  return count;
}

int main() {
  int K, N;
  scanf("%d %d", &K, &N);

  unsigned int min_value = 1, max_value = 1, mid_value = 0;
  vector<unsigned int> lines(K);

  for (int i = 0; i < K; i ++) {
    scanf("%u", &lines[i]);

    if (lines[i] > max_value) max_value = lines[i];
  }

  while (min_value <= max_value) {
    mid_value = (min_value + max_value) / 2;

    if (counting(lines, mid_value) >= N) {
      min_value = mid_value + 1;
    } else {
      max_value = mid_value - 1;
    }
  }

  printf("%u\n", max_value);

  return 0;
}
