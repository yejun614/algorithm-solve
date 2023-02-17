/*
 * (230217) 개수 세기
 * https://www.acmicpc.net/problem/10807
 */

#include <cstdio>

int Arr[101];

int main() {
  int size, find;

  scanf("%d", &size);
  for (int i = 0; i < size; ++i)
    scanf("%d", Arr + i);
  scanf("%d", &find);

  int answer = 0;
  for (int i = 0; i < size; ++i)
    if (find == Arr[i]) ++answer;
  printf("%d\n", answer);

  return 0;
}

