/*
 * (221123) 최소 힙
 * https://www.acmicpc.net/problem/1927
 *
 * [풀이]
 * 단순 구현 문제로 최소 힙에 대해서 공부할 수 있었다.
 */

#include <cstdio>
#define MAX_SIZE 100100

inline void swap(unsigned int* a, unsigned int* b) {
  unsigned int temp = *a;
  *a = *b;
  *b = temp;
}

void PushMinHeap(unsigned int value, unsigned int* arr, unsigned int* size) {
  unsigned int index = *size, parent;
  arr[index] = value;
  (*size)++;

  while (index > 0) {
    parent = (index - 1) / 2;
    if (arr[parent] < arr[index]) break;
    swap(arr + parent, arr + index);
    index = parent;
  }
}

unsigned int PopMinHeap(unsigned int* arr, unsigned int* size) {
  if (*size <= 0) return 0;

  const unsigned int result = arr[0];
  (*size)--;
  arr[0] = arr[*size];

  if (*size > 1) {
    unsigned int index = 0;
    unsigned int child = index * 2 + 1;

    while (index < *size - 1) {
      child = index * 2 + 1;
      if (child >= *size) break;
      if (child < *size - 1 && arr[child + 1] < arr[child]) child += 1;
      if (arr[index] < arr[child]) break;
      swap(arr + index, arr + child);
      index = child;
    }
  }

  return result;
}

int main() {
  unsigned int N, x;
  unsigned int arr[MAX_SIZE], size = 0;

  scanf("%u", &N);

  while (N--) {
    scanf("%u", &x);

    if (x == 0) {
      printf("%u\n", PopMinHeap(arr, &size));
    } else {
      PushMinHeap(x, arr, &size);
    }
  }

  return 0;
}
