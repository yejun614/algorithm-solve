/*
 * (220221) 마인크래프트
 * https://www.acmicpc.net/problem/18111
 *
 * [풀이]
 * 마인크래프트 게임에서 특정 지역의 모든 블럭들의 높이를 똑같이 만드는 일명,
 * "평탄화" 작업을 할려고 하는데, 블록을 제거한 후 인벤토리에 넣는건 1초가 걸리고,
 * 인벤토리에서 블록을 꺼내서 블록을 설치하는건 2초가 걸린다고 가정합니다.
 * 지역의 가로, 세로 크기와 초기 인벤토리 블록의 개수가 주어질 때 평탄화 작업을
 * 하는데 걸리는 최소 시간을 구하는 문제 입니다.
 * (정답이 여러개인 경우 블록 높이가 가장높은 것을 출력합니다.)
 *
 * Brute force 방식으로 해결가능 합니다.
 * (1). 세로, 가로, 인벤토리를 입력 받습니다.
 * (2). 블록의 높이는 0부터 256까지 정수 임으로, 배열 방인 257개인 1차원 배열을 선언하고
 *      0으로 초기화 합니다.
 * (3). 땅의 높이를 입력받으면서, 1차원 배열안에 입력받은 높이를 index로 가지는
 *      배열 방 값을 1씩 가산합니다. 동시에 최소 높이와 최대 높이를 구합니다.
 * (4). 최소 높이 부터 최대 높이 까지 1씩 늘려가면서 최선의 높이를 찾아 갑니다.
 * (5). 256부터 0까지 배열을 꺼꾸로 탐색하면서, 현재 높이에 맞도록 블록을 추가하거나, 제거합니다.
 *      이 때, 블록을 추가하는데 인벤토리의 블록이 부족하다면 현재 높이에 맞출수 없기 때문에
 *      바로 다음 높이로 넘어갑니다.
 * (6). 4 ~ 5 번을 반복하여 최적의 높이의 최소의 시간을 찾아 출력합니다.
 */

#include <cstdio>
#include <vector>

using namespace std;

int main() {
  int N, M, B;
  scanf("%d %d %d", &N, &M, &B);

  vector<int> count(257, 0);
  int number, min_value = 257, max_value = -1;

  for (int y = 0; y < N; y ++) {
    for (int x = 0; x < M; x ++) {
      scanf("%d", &number);

      count[number] ++;

      if (number < min_value) min_value = number;
      if (number > max_value) max_value = number;
    }
  }

  int Time = -1, Height = min_value,
      currentTime, currentB, cost, i;

  min_value --;

  while (++ min_value <= max_value) {
    currentTime = 0;
    currentB = B;

    for (i = 256; i >= 0; i --) {
      if (count[i] == 0) continue;

      if (min_value - i > 0) {
        cost = count[i] * (min_value - i);

        if (cost > currentB) break;
        
        currentTime += count[i] * (min_value - i);
        currentB -= count[i] * (min_value - i);
        
      } else if (i - min_value > 0) {
        cost = count[i] * (i - min_value);
        
        currentTime += 2 * cost;
        currentB += cost;
      }
    }

    if (i >= 0) {
      continue;
      
    } else if (currentTime == Time) {
      Height = min_value > Height ? min_value : Height;
      
    } else if (Time == -1 || currentTime < Time) {
      Time = currentTime;
      Height = min_value;
    }
  }

  printf("%d %d\n", Time, Height);

  return 0;
}
