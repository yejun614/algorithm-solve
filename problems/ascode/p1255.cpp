/*
 * (220517) 더블 미역
 * http://www.ascode.org/problem.php?id=1255
 *
 * [풀이]
 * 문제에서 복잡하게 적어 놓긴했지만 2차원 평면(1사분면)에서
 * 두 사각형이 있을 때 두 사각형이 서로 겹쳐지는 면적을 구하는 문제이다.
 *
 * AABB 충동 판정 알고리즘을 응용하는 문제이다.
 * X축과 Y축위에 두 사각형의 그림자를 보고 면적을 구할 수 있다.
 *
 * 이 문제에서는 각 좌표의 대소관계를 잘 이해해야 하는데,
 * 각 축에서 안쪽에 모여있는 두 좌표를 서로 뺄셈하여 가로, 세로 길이를 구한다.
 * 이때, 가로 혹은 세로가 음수가 되는 경우 서로 겹치는 부분이 없다는 의미로
 * 0을 출력해야 한다.
 * 반대로 가로, 세로 둘다 양수인 경우 가로 x 세로 = 면적을 계산해서 출력한다.
 *
 * [주의]
 * C/C++ 표준 min(), max() 함수 사용하면 문제 틀린다.
 */

#include <cstdio>
#define MIN(a,b) ((a) < (b) ? (a) : (b))
#define MAX(a,b) ((a) > (b) ? (a) : (b))
#define NAT(a) ((a) < 0 ? 0 : (a))

int main() {
  int T;
  scanf("%d", &T);

  while (T--) {
    int first[4], second[4];

    for (int i = 0; i < 4; i ++) scanf("%d", first+i);
    for (int i = 0; i < 4; i ++) scanf("%d", second+i);

    int X1 = MAX(MIN(first[0], first[2]), MIN(second[0], second[2]));
    int X2 = MIN(MAX(first[0], first[2]), MAX(second[0], second[2]));

    int Y1 = MAX(MIN(first[1], first[3]), MIN(second[1], second[3]));
    int Y2 = MIN(MAX(first[1], first[3]), MAX(second[1], second[3]));

    int width = NAT(X2 - X1);
    int height = NAT(Y2 - Y1);

    printf("%d\n", width * height);
  }

  return 0;
}
