#include <cstdio>
#include <queue>
#include <vector>
#include <functional>

using namespace std;

int main() {
  int N, input;
  scanf("%d", &N);

  priority_queue<int, vector<int>, greater<int> > nums;

  for (int i = 0; i < N; i ++) {
    scanf("%d", &input);
    nums.push(input);
  }

  while (!nums.empty()) {
    printf("%d\n", nums.top());
    nums.pop();
  }

  return 0;
}
