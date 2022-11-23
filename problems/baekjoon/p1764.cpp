/*
 * (221123) 듣보잡
 * https://www.acmicpc.net/problem/1764
 *
 * [풀이]
 * set과 list의 사용방법을 공부할 수 있는 문제이다.
 * 듣도 못한 사람을 set 자료형에 집어 넣고 보도 못한 사람을 차례대로 입력받으면서
 * 듣도 못한 사람 set 안에 존재하는지 확인하고, 만약 존재한다면 list에 추가한다.
 * 
 * 입력을 모두 받고 list 맴버 함수인 sort를 이용해서 사전순으로 정렬하고
 * list의 개수와 그 요소들을 차례대로 출력한다.
 */

#include <iostream>
#include <string>
#include <list>
#include <set>

using namespace std;

int main() {
  ios_base::sync_with_stdio(false);
  cout.tie(NULL);
  cin.tie(NULL);

  int N, M;
  cin >> N >> M;

  set<string> people;
  list<string> answer;
  string input;

  while (N--) {
    cin >> input;
    people.insert(input);
  }

  while (M--) {
    cin >> input;

    if (people.find(input) != people.end()) {
      answer.push_back(input);
    }
  }

  answer.sort();

  cout << answer.size() << '\n';

  for (auto it = answer.begin(); it != answer.end(); it++) {
    cout << *it << '\n';
  }

  return 0;
}
