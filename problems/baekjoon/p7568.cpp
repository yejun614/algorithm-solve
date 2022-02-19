/*
 * (220219) 덩치
 * https://www.acmicpc.net/problem/7568
 *
 * [풀이]
 * N명의 사람들에 대한 몸무게와 키가 주어졌을 때 순위를 매겨 출력하는 문제 입니다.
 * 이중 반복문을 이용하여 두 사람간의 몸무게와 키를 비교하여,
 * 몸무게와 키 둘다 크다면 등수가 내려가는 방식으로 풀이 가능합니다.
 *
 * 시간 복잡도는 O(N^2) 으로 좀더 빠르게 계산하고 싶다면,
 * 이중 반복문이 아니라 정렬 알고리즘을 활용하여 계산하면 효율적으로
 * 풀이 가능합니다.
 */

#include <cstdio>
#include <cstdlib>

typedef struct Person {
  int weight;
  int height;
} Person;

int main() {
  int N;
  scanf("%d", &N);

  Person *people = (Person*)malloc(sizeof(Person) * N);

  for (int i = 0; i < N; i ++)
    scanf("%d %d", &people[i].weight, &people[i].height);

  int rank;

  for (int i = 0; i < N; i ++) {
    rank = 1;

    for (int j = 0; j < N; j ++) {
      if (i == j) continue;
      
      if (people[i].weight < people[j].weight &&
          people[i].height < people[j].height) rank ++;
    }

    printf("%d ", rank);
  }
  
  printf("\n");

  free(people);
  return 0;
}
