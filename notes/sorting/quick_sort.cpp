#include <cstdio>
#include <cstdlib>

inline void swap(int *A, int *B) {
  int temp = *A;
  *A = *B;
  *B = temp;
}

void print_arr(int *begin, int *end) {
  while (begin < end) {
    printf("%d ", *begin);
    begin ++;
  }
  printf("\n");
}

void QuickSort(int *begin, int *end) {
  if (end - begin <= 0) return;

  int *pivot = begin,
      *low = begin,
      *high = end - 1;
      
  while (low <= high) {
    if (*high > *pivot) {
      high --;
      
    } else if (*low > *pivot) {
      swap(low, high);
      low ++;
      high --;
      
    } else {
      low ++;
    }
  }

  swap(pivot, high);

  QuickSort(begin, high);
  QuickSort(high+1, end);
}

int main() {
  int N;
  scanf("%d", &N);

  int *nums = (int*)malloc(sizeof(int) * N);

  for (int i = 0; i < N; i ++)
    scanf("%d", nums + i);

  QuickSort(nums, nums + N);

  for (int i = 0; i < N; i ++)
    printf("%d\n", nums[i]);

  free(nums);
  return 0;
}
