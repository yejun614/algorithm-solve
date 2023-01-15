/*
 * (230115) 단지번호붙이기
 * https://www.acmicpc.net/problem/2667
 *
 * [풀이]
 * 0와 1로 구성된 2차원 배열이 주어졌을 때 1과 상하좌우로 인접한
 * 1들의 그룹 개수와 그룹 내부의 원소들의 개수를 출력하는 문제이다.
 * (자세한 문제 내용은 백준 페이지 참조)
 *
 * 2차원 배열을 입력받을 때 문자열로 한 줄씩 입력을 받고,
 * Solve 함수를 이용하여 재귀적으로 탐색하였다.
 * 또한 탐색한 원소는 '0'으로 변경하여 두번 이상 탐색하지 않도록 하였다.
 */

#include <cstdio>
#include <algorithm>
#define MAX_SIZE 26

const short X_DIR[] = { -1,  1,  0,  0 };
const short Y_DIR[] = {  0,  0, -1,  1 };

short Size;
char Board[MAX_SIZE][MAX_SIZE];

short Solve(short ypos, short xpos) {
  if (ypos < 0 || ypos >= Size || xpos < 0 || xpos >= Size) return 0;
  else if (Board[ypos][xpos] == '0') return 0;
  else Board[ypos][xpos] = '0';

  short result = 1;

  for (short i = 0; i < 4; ++i) {
    result += Solve(ypos + Y_DIR[i], xpos + X_DIR[i]);
  }

  return result;
}

int main() {
  scanf("%hd", &Size);

  for (short y = 0; y < Size; ++y) {
    scanf("%s", Board[y]);
  }

  short count = 0;
  short nums[MAX_SIZE * MAX_SIZE];

  for (short y = 0; y < Size; ++y) {
    for (short x = 0; x < Size; ++x) {
      nums[count] = Solve(y, x);
      if (nums[count] != 0) ++count;
    }
  }

  printf("%hd\n", count);

  std::sort(nums, nums + count);
  
  for (short i = 0; i < count; ++i) {
    printf("%hd\n", nums[i]);
  }

	return 0;
}
