/*
 * (220209) 통계학
 * https://www.acmicpc.net/problem/2108
 *
 * [풀이]
 * N개의 정수를 입력받아 문제에서 제시하는 4가지 조건대로 계산하여
 * 출력하는 문제이다.
 *
 * 1. 산술평균 : N개의 수들의 합을 N으로 나눈 값
 *    (소수점 이하 첫째 자리에서 반올림한 값을 출력한다.)
 *
 * 2. 중앙값 : N개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값
 *
 * 3. 최빈값 : N개의 수들 중 가장 많이 나타나는 값
 *    (여러 개 있을 때에는 최빈값 중 두 번째로 작은 값을 출력한다.)
 *
 * 4. 범위 : N개의 수들 중 최댓값과 최솟값의 차이
 *
 * [실수]
 * (+) 반올림 할 때 round 함수를 직접 구현할려고 하다가 한번 틀렸다.
 *     cmath의 round함수를 사용함으로써 해결.
 */

#include <cstdio>
#include <cmath>
#include <vector>
#include <map>
#include <queue>
#include <algorithm>
#include <functional>

using namespace std;

int main() {
  int N;
  scanf("%d", &N);

  int sum = 0;
  vector<int> nums(N);
  
  map<int, int> freq;
  map<int, int>::iterator it;

  for (int i = 0; i < N; i ++) {
    scanf("%d", &nums[i]);

    sum += nums[i];

    it = freq.find(nums[i]);
    
    if (it == freq.end()) {
      freq.insert(make_pair(nums[i], 1));
    } else {
      it->second ++;
    }
  }

  // 1.
  printf("%d\n", (int)round((float)sum / (float)N));

  // 2.
  sort(nums.begin(), nums.end());
  printf("%d\n", nums[N / 2]);

  // 3.
  priority_queue<pair<int, int> > pq;
  for (it = freq.begin(); it != freq.end(); it ++) {
    pq.push(make_pair(it->second, it->first));
  }

  int max_freq_value = pq.top().first, count = 0;
  vector<int> max_freq;

  while (!pq.empty() && pq.top().first == max_freq_value) {
    max_freq.push_back(pq.top().second);
    pq.pop();
    count ++;
  }

  if (count == 1) {
    printf("%d\n", max_freq[0]);
  } else {
    printf("%d\n", max_freq[count-2]);
  }

  // 4.
  printf("%d\n", nums[N-1] - nums[0]);

  return 0;
}
