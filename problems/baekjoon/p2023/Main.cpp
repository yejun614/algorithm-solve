#include <cstdio>
#include <cmath>
using namespace std;

const int START_NUM[] = { 2, 3, 5, 7 };
const int MAX_N = 10;

int sized_num[MAX_N];

void makeSizedNum() {
  sized_num[0] = sized_num[1] = 1;

  for (int index = 2; index < MAX_N; index++) {
    sized_num[index] = sized_num[index - 1] * 10;
  }
}

bool isPrimeNum(int num) {
  if (num <= 1) return false;
  if (num <= 3) return true;
  if ((num & 1) == 0) return false;

  const int last = (int)sqrt(num);

  for (int x = 3; x <= last; x += 2) {
    if (num % x == 0) return false;
  }
  return true;
}

bool isSpecialPrimeNum(int num, int pos, int size) {
  if (pos > size) return true;

  int current = num / sized_num[size - pos + 1];

  if (!isPrimeNum(current)) return false;
  return isSpecialPrimeNum(num, pos + 1, size);
}

int main() {
  int N;
  scanf("%d", &N);

  makeSizedNum();
  int begin = sized_num[N];
  int end = sized_num[N + 1] - 1;

  for (int start : START_NUM) {
    int num = begin * start;
    for (int count = 0; count < sized_num[N]; count++) {
      if (isSpecialPrimeNum(num, 2, N)) printf("%d\n", num);
      num++;
    }
  }

  return 0;
}
