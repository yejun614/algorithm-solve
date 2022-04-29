/*
 * (220429) 비밀 지도
 * http://www.ascode.org/problem.php?id=1043
 */

#include <cstdio>
#include <vector>

using namespace std;

int main() {
  int size, current;
  scanf("%d", &size);

  vector<int> board(size);

  for (int i = 0; i < size; i ++)
    scanf("%d", &board[i]);

  for (int i = 0; i < size; i ++) {
    scanf("%d", &current);
    board[i] |= current;

    printf("[");
    for (int x = size - 1; x >= 0; x --) {
      printf("%c", board[i] & (1 << x) ? '#' : ' ');
    }
    printf("]\n");
  }

  return 0;
}
