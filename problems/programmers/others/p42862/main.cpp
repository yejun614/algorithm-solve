/*
 * (42862) 체육복
 * https://school.programmers.co.kr/learn/courses/30/lessons/42862
 */

#include <iostream>

#include <vector>
using namespace std;

int solution(int n, vector<int> lost, vector<int> reserve) {
  int answer = n;

  vector<int> students(n + 2, 1);
  for (int i : reserve) students[i] = 2;
  for (int i : lost) --students[i];

  for (int s = 1; s <= n; ++s) {
    if (students[s] > 0) continue;
    if (students[s - 1] > 1) --students[s - 1];
    else if (students[s + 1] > 1) --students[s + 1];
    else --answer;
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

