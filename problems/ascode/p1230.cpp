/*
 * (220429) 최대 삼각형 찾기
 * http://www.ascode.org/problem.php?id=1230
 */

#include <cstdio>
#include <algorithm>

int main() {
  int T;
  scanf("%d", &T);

  while (T--) {
    int N;
    scanf("%d", &N);
    
    int *arr = new int[N];
    for (int i = 0; i < N; i ++) scanf("%d", arr+i);

    std::sort(arr, arr+N);

    int answer = 0;
    for (int i = N-1; i >= 2; i --) {
      if (arr[i] < arr[i-1] + arr[i-2]) {
        answer = arr[i] + arr[i-1] + arr[i-2];
        break;
      }
    }

    printf("%d\n", answer);
    delete [] arr;
  }

  return 0;
}
