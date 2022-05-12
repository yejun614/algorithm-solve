/*
 * (220511) 세 수의 합
 * https://www.acmicpc.net/problem/2295
 *
 * [풀이]
 * 이 문제는 해결방법이 거의 한가지로 명확하게 정해져 있는 듯 하다.
 *
 * 배열을 입력받아 배열안의 3개의 수를 더해서 새로운 수를 만드는데,
 * 이렇게 만들어진 수가 배열안에서 최대한 큰 값이 되도록 하는 수를 찾는 문제이다.
 *
 * x + y + z = w
 * 여기서 w가 배열안에서 가능한 큰 값이 되도록 해야 한다.
 *
 * x + y = w - z
 * 상수 항을 하나 이항시켜 보면 컴퓨터 상에서 알고리즘 복잡도가 O(N^2)로 작아짐을 알 수 있다.
 * 이 식이 핵심인데, 컴퓨터 입장에서 보면 두 수의 조합을 두번 작업함으로서 O(N^3) 작업이 O(N^2)로
 * 줄었다.
 *
 * (1). A 배열을 입력받아서 두 숫자끼리 더하여 따른 B 배열에 저장한다.
 *      (이렇게 만들어진 B 배열은 오름차순으로 정렬한다)
 * (2). A 배열에서 두 숫자를 서로 뺄셈한 값이 B 배열안에 존재하는지 확인한다.
 *      (이분탐색 혹은 Bitwise 배열을 이용할 수 있다)
 * (3). 만약 존재한다면 A 배열에서 선택한 두 수에서 더 큰 값을 출력하면 된다.
 * 
 *
 * [실수 & 느낌점]
 * (+) 참고자료에 bitwise 기법을 사용해서 12ms 안으로 통과하는 코드가 있으니 참고하면 좋다.
 *
 * [참고자료]
 * https://blog.naver.com/jinhan814/222425223163
 */

#include <cstdio>
#include <algorithm>

using namespace std;

int main() {
  int N;
  scanf("%d", &N);

  int *nums = new int[N];
  for (int i = 0; i < N; i ++) scanf("%d", nums + i);
  sort(nums, nums + N);

  int cache_size = N*N, index = 0;
  int *cache = new int[cache_size];
  
  for (int y = 0; y < N; y ++) {
    for (int x = 0; x < N; x ++) {
      cache[index++] = nums[y] + nums[x];
    }
  }
  sort(cache, cache + cache_size);

  for (int i = N - 1; i >= 0; i --) {
    for (int j = i - 1; j >= 0; j --) {
      if (binary_search(cache, cache + cache_size, nums[i] - nums[j])) {
        printf("%d\n", nums[i]);

        delete [] cache;
        delete [] nums;
        return 0;
      }
    }
  }

  delete [] cache;
  delete [] nums;
  return 0;
}
