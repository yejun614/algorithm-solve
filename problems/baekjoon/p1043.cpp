/*
 * (220225) 거짓말
 * https://www.acmicpc.net/problem/1043
 *
 * [풀이]
 * 파티를 다니면서 거짓말을 하고 다니는 사람이 진실을 아는 사람들이 있는 파티에서는 진실만을
 * 말하고, 진실을 아무도 모르는 파티에서는 거짓말을 말하다고 할 때 거짓말을 한 파티의 횟수를
 * 구하는 문제 입니다.
 *
 * 문제의 핵심은 앞선 파티에서는 진실을 모르고 거짓말을 들었던 사람들이
 * 이후 파티에서 진실을 들으면 안된다는 것에 있습니다.
 * 때문에 파티에 참가하는 명단을 보고 진실을 들은 사람들을 찾아낸 다음 한번더 명단을 보면서
 * 해당 파티에서 거짓말을 할 수 있는지 없는지 확인해야 합니다.
 *
 * 문제를 풀기 위해서는 파티에 참가했던 사람들 끼리 관계를 정리해여 거짓말을 들은 집단과
 * 진실을 들은 집단을 구분해야 합니다. Union Find 라는 기법을 이용해서 풀이 가능합니다.
 *
 * [실수]
 * (+) 마치 삼단 논법처럼 진실을 하는 A가 이전 파티에서 B와 만났고, B는 이전파티에서
 *     C를 만나게 되어 A, B, C 세명이 진실을 알게 되는 케이스를 고려하고 못해서 실패.
 * (+) 단순 불리언 배열이 아니라 트리 구조로 참가자들의 관계를 연결해야 풀수 있습니다.
 *
 * [참고자료] https://gmlwjd9405.github.io/2018/08/31/algorithm-union-find.html
 * [반례] https://www.acmicpc.net/board/view/72203
 */

#include <cstdio>
#include <vector>

using namespace std;

int getRoot(vector<int>& roots, int index) {
  if (roots[index] == index) return index;

  int result = getRoot(roots, roots[index]);
  roots[index] = result;

  return result;
}

int main() {
  int N, M;
  scanf("%d %d", &N, &M);

  vector<bool> people(N, false);
  vector<int> roots(N);
  vector<vector<int> > parties(M, vector<int>(0) );

  for (int i = 0; i < N; i ++)
    roots[i] = i;

  int number, person;
  scanf("%d", &number);

  while (number --) {
    scanf("%d", &person);
    
    people[person-1] = true;
  }

  int root, current;
  bool TParty;

  for (int i = 0; i < M; i ++) {
    root = -1;
    scanf("%d", &number);

    while (number --) {
      scanf("%d", &person);
      parties[i].push_back(person);

      current = getRoot(roots, person-1);
      
      if (root == -1 || people[current]) {
        root = current;
      }
    }

    for (int p : parties[i]) {
      roots[getRoot(roots, p-1)] = root;
    }
  }

  int answer = 0;

  for (auto party : parties) {
    TParty = false;

    for (int p : party) {
      current = getRoot(roots, p-1);

      if (people[current]) {
        TParty = true;
        break;
      }
    }

    if (!TParty) answer ++;
  }

  printf("%d\n", answer);

  return 0;
}
