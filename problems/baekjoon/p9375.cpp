/*
 * (230312) 패션왕 신해빈
 * https://www.acmicpc.net/problem/9375
 *
 * [풀이]
 * 입력으로 의상의 이름과 종류를 입력받아 종류별로 각 의상을 조합할 수 있는 개수를
 * 출력하는 문제이다. 최소 하나 이상 종류의 의상을 선택할 수 있으며 같은 종류의 의상을
 * 여러개 선택하는 것은 안된다. (자세한 문제 내용은 링크를 참고한다)
 *
 * [잘못된 풀이]
 * 어렵게 설명하면 의상의 종류를 순서없이 선택하여 각 종류별로 하나씩 의상을 선택하여
 * 조합하고 그 조합의 개수를 출력하는 것이 핵심이다.
 *
 * 우선 의상 종류를 순서없이 선택하는 것은 2진법의 덧셈으로 구현할 수 있다.
 *
 * 만약 의상의 종류 N이 3인 경우
 *
 * 0 | 000   -> 의상을 하나도 선택하지 않은 경우는 제외된다.
 * 1 | 001
 * 2 | 010
 * 3 | 011
 * 4 | 100
 * 5 | 101
 * 6 | 110
 * 7 | 111
 *
 * 위의 표에서 의상의 종류를 선택하는 경우의 수는 7가지 존재한다.
 * 그리고 선택된 의상의 종류들 중에서 의상을 조합하는 경우의 수를 더하여 정답을 구할 수 있다.
 *
 * 만약 모자 종류가 3개, 바지 종류가 2개, 셔츠 종류가 1개 있다고 가정하면
 * 다음과 같이 계산된다.
 *
 * --+-----+-----------------
 * 0 | 000 | 0
 * 1 | 001 | 1
 * 2 | 010 | 2
 * 3 | 011 | 2 * 1 = 2
 * 4 | 100 | 3
 * 5 | 101 | 3 * 1 = 3
 * 6 | 110 | 3 * 2 = 6
 * 7 | 111 | 3 * 2 * 1 = 6
 * --+-----+-----------------
 *   |     | 23
 * --+-----+-----------------
 *
 *  정답은 23이다.
 *
 * [정답 풀이]
 * 각 의상의 종류의 개수에 1을 더한 후 모두 곱한다 그리고 그 결과에 1을 빼면 정답이다.
 *
 * answer = multiply([type1.size + 1, type2.size + 1, ...]) - 1
 *
 * 설명하자면 각 종류의 개수에 1을 더하는 이유는 각 의상이 포함되지 않을 경우를 생각하는
 * 것이고 이렇게 구해진 각 종류별 개수에 1을 더한 후 곱한 것은 각 의상 종류별로 의상을 입지
 * 않거나 혹은 하나의 의상을 선택해 입는 경우의 수를 모두 구한 것이다.
 *
 * 결과에 1을 빼는 이유는 의상을 하나도 선택하지 않은 경우의 수 1을 뺀것이다.
 *
 * 만약 모자 종류가 3개, 바지 종류가 2개, 셔츠 종류가 1개 있다고 가정하면
 * 다음과 같이 계산된다.
 *
 * answer
 *  = ((3 + 1) * (2 + 1) * (1 + 1)) - 1
 *  = (4 * 3 * 2 - 1)
 *  = (24) - 1
 *  = 23
 */

#include <iostream>
#include <map>
using namespace std;

int main() {
  ios_base::sync_with_stdio(false);
  cout.tie(NULL);
  cin.tie(NULL);

  int testCase;
  cin >> testCase;

  int N;
  while (testCase--) {
    cin >> N;

    map<string, size_t> clothes;
    string inValue, inType;

    for (int i = 0; i < N; ++i) {
      cin >> inValue >> inType;

      if (clothes.find(inType) == clothes.end()) {
        clothes[inType] = 1;
      } else {
        ++clothes[inType];
      }
    }

    int answer = 1;
    for (auto it = clothes.begin(); it != clothes.end(); ++it) {
      answer *= it->second + 1;
    }
    cout << --answer << '\n';
  }

  return 0;
}
