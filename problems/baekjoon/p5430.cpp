/*
 * (230116) AC
 * https://www.acmicpc.net/problem/5430
 *
 * [풀이]
 * 입력에 따라 배열을 변형하고 그 결과를 출력하는 문제이다.
 *
 * 1. 문자열 파싱
 *    (콤마 문자를 기준으로 숫자를 분리해서 정수형으로 변환하는 과정이 필요)
 *
 * 2. 효율적인 배열 조작
 *    (문제를 보면 배열 첫번째와 마지막 값만 조작하고 중간 값에는 접근하지 않는다
 *     따라서 Solve 함수의 start, end, isReverse 변수를 사용해서 배열을 조작하고
 *     마지막에 결과를 출력한다)
 */

#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<short> ArrSplit(int arrCount, const string& arrStr) {
  size_t index = 0, pos = 0;
  vector<short> result(arrCount);

  if (arrStr.size() == 0) return result;

  while (true) {
    size_t find = arrStr.find(",", pos);

    if (find == string::npos) break;
    
    result[index++] = stoi(arrStr.substr(pos, find - pos));
    pos = find + 1;
  }

  result[index++] = stoi(arrStr.substr(pos));

  return result;
}

void Solve(string& cmd, int arrCount, string& arrStr) {
  // Split
  vector<short> arr = ArrSplit(arrCount, arrStr.substr(1, arrStr.size() - 2));

  // Run commands
  int start = 0, end = arr.size() - 1;
  bool isReverse = false;
  
  for (auto it = cmd.begin(); it != cmd.end(); ++it) {
    if (*it == 'R') {
      isReverse = !isReverse;
      
    } else if (*it == 'D') {
      if (start > end) {
        cout << "error\n";
        return;               // STOP (ERROR)
      }

      if (isReverse) --end;
      else ++start;
    }
  }

  // Outputs
  cout << "[";
  
  if (isReverse) {
    for (int index = end; index >= start; --index) {
      cout << arr[index];
      if (index > start) cout << ",";
    }
 
  } else {
    for (int index = start; index <= end; ++index) {
      cout << arr[index];
      if (index < end) cout << ",";
    }
  }
  
  cout << "]\n";
}

int main() {
  ios_base::sync_with_stdio(false);
  cout.tie(NULL);
  cin.tie(NULL);

  short testCase;
  cin >> testCase;

  while (testCase--) {
    string cmd, arrStr;
    int arrCount;

    cin >> cmd;
    cin >> arrCount;
    cin >> arrStr;

    Solve(cmd, arrCount, arrStr);
  }

  return 0;
}
