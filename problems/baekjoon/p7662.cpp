#include <cstdio>
#include <set>
#include <iterator>
using namespace std;

int main() {
  int T;
  scanf("%d", &T);

  char cmd;
  int cmdCount, arg;

  while (T--) {
    multiset<int> Q;
    scanf("%d", &cmdCount);

    while (cmdCount--) {
      // C언어 scanf 함수에서 %c 사용시 white space(" ") 혹은 new line("\n")
      // 이 모두 입력되기 때문에 의도하지 않은 문자가 입력될 수 있다.
      // 이때 white space, new line을 제외하고 입력받기 위해서는 %c 앞에 공백을
      // 하나 붙여주면 제일 간단하게 해결된다. (e.g. " %c")
      scanf(" %c %d", &cmd, &arg);

      if (cmd == 'I') {            // CMD: Insert
        Q.insert(arg);
      } else if (!Q.empty()) {
        if (arg == 1) {            // CMD: Delete Maximum Value
          Q.erase(prev(Q.end()));
        } else {                   // CMD: Delete Minimum Value
          Q.erase(Q.begin());
        }
      }    
    }

    if (Q.empty()) printf("EMPTY\n");
    else printf("%d %d\n", *Q.rbegin(), *Q.begin());
  }

  return 0;
}

