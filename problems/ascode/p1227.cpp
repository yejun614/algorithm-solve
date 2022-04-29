/*
 * (220429) 부분 수열의 최대값
 * http://www.ascode.org/problem.php?id=1227
 */

#include <cstdio>
#include <vector>

using namespace std;

int main() {
  int T;
  scanf("%d", &T);

  while (T--) {
    int N, num;
    scanf("%d", &N);

    int prev = 0;
    int max_value = -1001, start_index = 0, end_index = 0;
    vector<int> forward(N), backward(N);

    for (int i = 0; i < N; i ++) {
      scanf("%d", &num);
      
      forward[i] = num; // TODO: REMOVE THIS LINE!

      if (num < 0) {
        prev = 0;
      } else {
        prev += num;
      }

      backward[i] = prev;
      printf("%d, ", prev);

      if (prev > max_value) {
        max_value = prev;
        end_index = i;
      }
    }
    printf("\n");

    prev = max_value;
    for (start_index = end_index-1; start_index >= 0; start_index --) {
      if (prev - backward[start_index] >= prev) break;
    }

    printf("(%d, %d)\n", max_value, start_index);

    if (start_index >= 0) {
      max_value -= backward[start_index];
    }

    printf("%d %d %d\n", start_index+2, end_index+1, max_value);
  }

  return 0;
}
