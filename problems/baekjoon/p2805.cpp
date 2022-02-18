/*
 * (220218) 나무 자르기
 * https://www.acmicpc.net/problem/2805
 *
 * [풀이]
 * 이진 탐색에 매개변수 탐색을 적용하여 0부터 입력받은 나무의 높이 중
 * 최대값 까지 탐색하면서 가능한 최대 값을 찾는 것이 문제의 핵심 입니다.
 *
 * [실수]
 * (+) 처음에 Brute force 인줄 알고 내림차순으로 정렬 후 입력 받은
 *     나무 길이를 차례로 탐색했지만, 틀렸습니다.
 * (+) treeLength 함수의 반환형이 처음에 int 였지만 틀렸었고,
 *     최대 값이 1,000,000,000 ^ 2 가 될 수 있기 때문에
 *     unsigned long long 으로 타입을 변경하고 통과하였습니다.
 */

#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

typedef unsigned long long ull;

ull treeLength(vector<int>& trees, int height) {
  ull length = 0;

  for (auto it = trees.begin(); it != trees.end(); it ++) {
    if (*it <= height) continue;
    
    length += *it - height;
  }
  
  return length;
}

int solve(vector<int>& trees, int max_value, int M) {
  int answer = 0;
  int low = 0, high = max_value, mid;

  while (low <= high) {
    mid = (low + high) / 2;
    
    if (treeLength(trees, mid) >= (ull)M) {
      if (mid > answer) answer = mid;
      low = mid + 1;
      
    } else {
      high = mid - 1;
    }
  }

  return answer;
}

int main() {
  int N, M;
  scanf("%d %d", &N, &M);

  int max_value = 0;
  vector<int> trees(N);

  for (int i = 0; i < N; i ++) {
    scanf("%d", &trees[i]);

    if (trees[i] > max_value) max_value = trees[i];
  }

  const int answer = solve(trees, max_value, M);
  printf("%d\n", answer);

  return 0;
}
