/*
 * (220429) 차차의 광작(廣作) 이야기
 * http://www.ascode.org/problem.php?id=1023
 */

#include <cstdio>
#include <cmath>
#include <vector>

using namespace std;

void printVector(vector<vector<int> >& board) {
  for (auto height : board) {
    for (int value : height) {
      printf("%d", value);
    }
    printf("\n");
  }
}

int main() {
  int T, size;
  scanf("%d", &T);

  while (T --) {
    scanf("%d", &size);
    vector<vector<int> > board(size, vector<int>(size, 1));

    int current = ceil((float)size / 2.0f), index = 0, width = size;

    while (current > 1) {
      // Fill Columns
      for (int i = index; i < width; i ++) {
        board[index][i] = current;
        board[size-index-1][i] = current;
      }

      // Fill Rows
      for (int i = index; i <= width-2; i ++) {
        board[i][index] = current;
        board[i][size-index-1] = current;
      }

      current --;
      index ++;
      width --;
    }

    printVector(board);
  }

  return 0;
}
