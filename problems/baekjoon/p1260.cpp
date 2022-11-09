/*
 * (221110) DFS와 BFS
 * https://www.acmicpc.net/problem/1260
 */

#include <cstdio>
#include <cstring>
#include <queue>
#include <stack>

#define MAX 1001

using namespace std;

int N, M, V;
bool Graph[MAX][MAX];

void DFS() {
  bool visited[MAX];
  memset(visited, false, MAX * sizeof(bool));

  stack<int> nodes;
  nodes.push(V);
  visited[V] = true;
  printf("%d ", V);

  int current;

  while (!nodes.empty()) {
    current = nodes.top();
    nodes.pop();

    for (int i = 0; i < MAX; i++) {
      if (!Graph[current][i] || visited[i]) continue;

      nodes.push(current);

      printf("%d ", i);
      nodes.push(i);
      visited[i] = true;

      break;
    }
  }

  printf("\n");
}

void BFS() {
  bool visited[MAX];
  memset(visited, false, MAX * sizeof(bool));

  queue<int> nodes;
  nodes.push(V);
  visited[V] = true;

  int current;

  while (!nodes.empty()) {
    current = nodes.front();
    nodes.pop();

    printf("%d ", current);

    for (int i = 1; i < MAX; i++) {
      if (!Graph[current][i] || visited[i]) continue;

      nodes.push(i);
      visited[i] = true;
    }
  }

  printf("\n");
}

int main() {
  // Reset the graph
  for (int y = 1; y < MAX; y++) {
    for (int x = 1; x < MAX; x++) {
      Graph[y][x] = false;
    }
  }

  // Input
  scanf("%d %d %d", &N, &M, &V);

  int nodeA, nodeB;
  while (M--) {
    scanf("%d %d", &nodeA, &nodeB);

    Graph[nodeA][nodeB] = true;
    Graph[nodeB][nodeA] = true;
  }

  // Solution
  DFS();
  BFS();

  return 0;
}
