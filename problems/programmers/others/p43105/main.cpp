/*
 * (43105) 정수 삼각형
 */

#include <iostream>

#include <string>
#include <vector>

using namespace std;

int solution(vector<vector<int>> triangles) {
  int H = triangles.size();

  // 밑에서 부터 위로 올라가면서 가장 큰 값만 남김
  for (int y = H - 1; y > 0; --y) {
    for (int x = 0; x < y; ++x) {
      if (triangles[y][x] < triangles[y][x + 1]) {
        triangles[y-1][x] += triangles[y][x + 1];
      } else {
        triangles[y-1][x] += triangles[y][x];
      }
    }
  }

  return triangles[0][0];
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  /*
   * 테스트 케이스가 입력됨.
   *
   * 각 테스트에 대해서 첫번째 줄에 높이가 입력되고 다음 줄부터 높이 개수까지
   * 삼각형 정보가 띄어쓰기 기준으로 입력됨
   */

  int test_count;
  cin >> test_count;

  for (int test_case = 1; test_case <= test_count; ++test_case) {
    int height;
    cin >> height;

    vector<vector<int>> triangles(height);

    for (int h = 0; h < height; ++h) {
      triangles[h].resize(h + 1);

      for (int i = 0; i < h + 1; ++i) {
        cin >> triangles[h][i];
      }
    }

    auto answer = solution(triangles);
    cout << "#" << test_case << " " << answer << "\n";
  }

  return 0;
}
