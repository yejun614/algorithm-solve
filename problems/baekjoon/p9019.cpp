/*
 * (230217) DSLR
 * https://www.acmicpc.net/problem/9019
 *
 * [풀이]
 * 문제에서 D, S, L, R 4가지 명령어를 정의하고 이를 이용해서
 * 숫자A가 숫자B가 되기 위한 최소 길이의 명령어를 출력하는 문제이다.
 *
 * 풀이는 간단하게 너비우선탐색 브루트포스 방식으로 해결할 수 있다.
 * 현재 숫자를 기준으로 D, S, L, R 4개 연산을 수행한 결과를 큐에 삽입하여
 * 가능한 모든 경우의 수를 확인하고 원하는 수가 되면 반복을 멈춘다.
 *
 * 너무 복잡하게 생각하면 문제를 푸는데 오히려 어려움을 겪을 것이다.
 */

#include <cstdio>
#include <queue>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

typedef struct Node {
  int num;
  string cmd;
} Node;

queue<Node> Nodes;
vector<bool> Visited(10000);

void Push(int num, const string& cmd) {
  if (Visited[num]) return;
  else Visited[num] = true;

  Nodes.push({num, cmd});
}

string Solve(int start, int end) {
  // Clear
  queue<Node>().swap(Nodes);
  fill(Visited.begin(), Visited.end(), false);

  // Start Node
  Push(start, "");

  // Variables
  Node node;
  int num;

  // Loop
  while (!Nodes.empty()) {
    // Get Node
    node = Nodes.front();
    Nodes.pop();

    // Check
    if (node.num == end) return node.cmd;

    // Operator D
    num = node.num * 2;
    num = (num > 9999) ? (num % 10000) : num;
    Push(num, node.cmd + "D");

    // Operator S
    num = node.num - 1;
    num = (num < 0) ? 9999 : num;
    Push(num, node.cmd + "S");

    // Operator L
    num = node.num;
    num = (num / 1000) + ((num % 1000) * 10);
    Push(num, node.cmd + "L");

    // Operator R
    num = node.num;
    num = (num / 10) + ((num % 10) * 1000);
    Push(num, node.cmd + "R");
  }

  return "";
}

int main() {
  int T;
  scanf("%d", &T);

  int start, end;
  while (T--) {
    scanf("%d %d", &start, &end);
    printf("%s\n", Solve(start, end).c_str());
  }

  return 0;
}
