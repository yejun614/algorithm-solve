/*
 * (42862) 체육복
 * https://school.programmers.co.kr/learn/courses/30/lessons/42862
 */

#include <iostream>

#include <vector>
#include <algorithm>
using namespace std;

// A, B의 교집합
vector<int> intersection(vector<int> A, vector<int> B) {
  vector<int> result;

  for (int a : A) for (int b : B)
      if (a == b) result.push_back(a);

  return result;
}

// target에서 remove_items 요소 삭제
vector<int> remove_all(vector<int> remove_items, vector<int> target) {
  vector<int> result;

  for (int item : target) {
    result.push_back(item);

    for (int check : remove_items) {
      if (item == check) {
        result.pop_back();
        break;
      }
    }
  }

  return result;
}

int solution(int n, vector<int> lost, vector<int> reserve) {
  // lost, reserve 교집합 산출 후 제거
  vector<int> common = intersection(lost, reserve);
  lost = remove_all(common, lost);
  reserve = remove_all(common, reserve);

  // 오름차순 정렬
  sort(lost.begin(), lost.end());
  sort(reserve.begin(), reserve.end());

  // 일단 (전체 학생 수) - (도둑 맞은 학생) 으로 answer 초기화
  // 여기서 도둑 맞은 학생이라도 여분이 있다면 수업에 참가할 수 있음에 주의
  int answer = n - lost.size();

  vector<int> check_arr(2);
  for (int student : lost) {
    // 도둑 맞은 학생은 앞, 뒤의 학생에게 운동복을 빌릴 수 있다
    check_arr[0] = student - 1;
    check_arr[1] = student + 1;

    for (int check : check_arr) {
      // reserve 에서 check 값 찾기
      auto iter = reserve.begin();
      for (; iter != reserve.end(); ++iter) {
        if (check == *iter) {
          break;
        }
      }

      // reserve에 check 값이 존재한다면
      if (iter != reserve.end()) {
        reserve.erase(iter);
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

