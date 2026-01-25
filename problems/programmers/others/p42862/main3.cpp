/*
 * (42862) 체육복
 * https://school.programmers.co.kr/learn/courses/30/lessons/42862
 */

#include <iostream>

#include <string>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

int solution(int n, vector<int> lost, vector<int> reserve) {
  // lost, reserve를 set 컨테이너로 복사(정렬 및 삭제연산 유리함)
  set<int> lost_set(lost.begin(), lost.end());
  set<int> reserve_set(reserve.begin(), reserve.end());

  // lost, reserve 교집합 찾기
  vector<int> common;

  // set_intersection 함수는 호출전에 대상 배열들이 정렬되어 있어야 한다
  set_intersection(
    lost_set.begin(), lost_set.end(), reserve_set.begin(), reserve_set.end(),
    back_inserter(common));

  // 교집합 삭제
  for (int it : common) {
    lost_set.erase(it);
    reserve_set.erase(it);
  }

  // 일단 (전체 학생 수) - (도둑 맞은 학생) 으로 answer 초기화
  // 여기서 도둑 맞은 학생이라도 여분이 있다면 수업에 참가할 수 있음에 주의
  int answer = n - lost_set.size();

  vector<int> check_arr(2);
  for (int student : lost_set) {
    // 도둑 맞은 학생은 앞, 뒤의 학생에게 운동복을 빌릴 수 있다
    check_arr[0] = student - 1;
    check_arr[1] = student + 1;

    for (int check : check_arr) {
      if (auto iter = reserve_set.find(check); iter != reserve_set.end()) {
        reserve_set.erase(iter);
        ++answer; // answer 수정
        break;
      }
    }
  }

  return answer;
}

int main() {
  cout << "#1 " << solution(5, {2, 4}, {1, 3, 5}) << endl; // 5
  cout << "#2 " << solution(5, {2, 4}, {3}) << endl; // 4
  cout << "#3 " << solution(3, {3}, {1}) << endl; // 2
  cout << "#4 " << solution(5, {4, 2}, {3, 5}) << endl; // 5

  return 0;
}

