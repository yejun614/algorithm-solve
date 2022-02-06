/*
 * (220206) 단어 정렬
 * https://www.acmicpc.net/problem/1181
 *
 * [풀이]
 * 문제에서 제시된 정렬 조건은 다음과 같다.
 * 1. 길이가 짧은 것부터
 * 2. 길이가 같으면 사전 순으로
 * 3. 중복되는 단어는 한번만 출력
 *
 * C++ 표준 라이브러리인 algorithm의 sort 함수를 이용했다.
 * compare 라는 비교 함수를 직접 구현함으로서 문제에서 요구하는
 * 정렬 조건을 충족 시켰다.
 */

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(string A, string B) {
  const int lenA = A.size();
  const int lenB = B.size();

  if (lenA != lenB) {
    return lenA < lenB;
  } else {
    return A < B;
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int N;
  cin >> N;

  vector<string> arr(N);
  while (N--) cin >> arr[N];

  sort(arr.begin(), arr.end(), compare);

  string prev = "";
  for (string value : arr) {
    if (value == prev) continue;
    
    cout << value << endl;
    prev = value;
  }

  return 0;
}
