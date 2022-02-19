/*
 * (220219) 숫자 카드 2
 * https://www.acmicpc.net/problem/10816
 *
 * [풀이]
 * 정수로 이루어진 배열을 입력받아 배열안에 특정 수가 몇 개 있는지 세는 문제입니다.
 * 이진 탐색을 응용해서 중복된 값이 존재하는 배열에서 특정 값의 첫번째 요소의 위치와,
 * 마지막 요소의 위치를 알아내어 수의 개수를 계산합니다.
 *
 * (참고자료) https://yu5501.tistory.com/80
 */

#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

int counter(vector<int>& arr, int target) {
  int first, last;

  // Binary Search (Find first index)
  int low = 0, high = arr.size() - 1, mid;

  while (low <= high) {
    mid = (low + high) / 2;

    if (arr[mid] < target) {
      low = mid + 1;
      
    } else {
      high = mid - 1;
    }
  }

  first = low;

  // Binary Search (Find last index)
  low = 0; high = arr.size() - 1;

  while (low <= high) {
    mid = (low + high) / 2;

    if (arr[mid] <= target) {
      low = mid + 1;
      
    } else {
      high = mid - 1;
    }
  }

  last = high;
  
  return last - first + 1;
}

int main() {
  int N, M, current;
  
  scanf("%d", &N);
  vector<int> cards(N);

  for (int i = 0; i < N; i ++)
    scanf("%d", &cards[i]);
  sort(cards.begin(), cards.end());

  scanf("%d", &M);

  while (M --) {
    scanf("%d", &current);

    printf("%d ", counter(cards, current));
  }
  printf("\n");
  
  return 0;
}
