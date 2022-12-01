/*
 * 쿼드트리
 * https://www.acmicpc.net/problem/1992
 *
 * [풀이]
 * 분할 정복 문제로 재귀 함수를 이용해서 풀이 가능하다.
 * 1. 행렬을 입력 받는다.
 * 2. 재귀 호출을 이용해서 행렬의 전체부터 부분 행렬로 반복해 나간다.
 * 3. 현재 부분행렬의 모든 요소가 같다면 해당 요소를 문자열로 반환한다.
 * 4. 부분행렬의 각 요소가 다르다면 4개의 부분 행렬로 나누어 재귀 호출한다.
 * 5. 4개의 부분행렬 결과 문자열은 소괄호()로 묶어서 반환한다.
 */

#include <iostream>
#include <string>
using namespace std;

#define MAX_SIZE 64
char Img[MAX_SIZE][MAX_SIZE];

const short DIR_X[] = { 0, 1, 0, 1 };
const short DIR_Y[] = { 0, 0, 1, 1 };

bool Check(int sx, int sy, int size) {
  char ch = Img[sy][sx];
  
  for (int y = sy; y < sy + size; y++) {
    for (int x = sx; x < sx + size; x++) {
      if (ch != Img[y][x]) return false;
    }
  }
  
  return true;
}

string Solve(int x, int y, int size) {
  if (size == 1 || Check(x, y, size)) return string(1, Img[y][x]);
  
  string result = "(";
  size /= 2;
  
  for (int i = 0; i < 4; i++) {
    result += Solve(x + DIR_X[i] * size, y + DIR_Y[i] * size, size);
  }
  
  return result + ")";
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);
  
  int N;
  cin >> N;
  
  for (int y = 0; y < N; y++) {
    for (int x = 0; x < N; x++) {
      cin >> Img[y][x];
    }
  }
  
  cout << Solve(0, 0, N) << '\n';
  
  return 0;
}
