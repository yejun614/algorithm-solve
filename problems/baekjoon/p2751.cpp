/*
 * (220211) 수 정렬하기 2
 * https://www.acmicpc.net/problem/2751
 *
 * [풀이]
 * 주어진 수를 오름차순으로 정렬하는 문제이다.
 * std::sort 함수로 해결.
 */

#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
  int N;
  scanf("%d", &N);

  vector<int> nums(N);

  while (N --) {
    scanf("%d", &nums[N]);
  }

  sort(nums.begin(), nums.end());

  for (int num : nums) {
    printf("%d\n", num);
  }

  return 0;
}
