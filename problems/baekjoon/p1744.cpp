/*
 * (210709) 수 묶기
 * https://www.acmicpc.net/problem/1744
 *
 * [풀이]
 * 기본적인 풀이는 두 수간의 차이가 가장적인 숫자끼리 묶는 것이다.
 *
 * 입력 값 중에서 양의 정수의 경우
 * 내림차순으로 정렬하고 차례대로 두개씩 묶어준다. 홀수 인 경우 마지막 수는 덧셈 한다.
 * 1은 묶지 않고 바로 더하는데, 이는 1를 다른 수와 곱하는 것 보다 더할 때 수가 더 커지기 때문이다.
 *
 * 입력 값 중에서 음의 정수의 경우
 * 오름차순으로 정렬하고 차례대로 구개씩 묶어준다. 홀수 인 경우 0을 입력받았다면
 * 0이랑 묶어주고, 0을 입력받지 못했다면 그대로 더해준다.
 *
 * 0의 경우
 * 음의 정수가 홀 수 일때 마지막 수와 묶어주고 남은 0은 더해준다.
 * (혹은 그냥 버려도 됨)
 *
 * [추가]
 * 반례에 조심! 모든 경우를 상정하고 코드에 반영해야 한다.
 * https://www.acmicpc.net/board/view/65431
 */

#include <cstdio>

#include <vector>
#include <queue>
#include <functional>

using namespace std;

int main() {
	int N;
	scanf("%d", &N);

	int buf, answer = 0;
	bool zero_check = false;

	priority_queue<int> positive_nums;
	priority_queue<int, vector<int>, greater<int> > negative_nums;

	// input
	for (int i=0; i<N; i++) {
		scanf("%d", &buf);

		if (buf == 1) {
			answer ++;
		} else if (buf > 0) {
			positive_nums.push(buf);
		} else if (buf < 0) {
			negative_nums.push(buf);
		} else {
			zero_check = true;
		}
	}

	// answer
	int current;
	bool check = false;

	// solve
	while (!positive_nums.empty()) {
		if (!check) {
			current = positive_nums.top();
		} else {
			answer += current * positive_nums.top();
		}

		positive_nums.pop();
		check = !check;
	}
	answer += check ? positive_nums.top() : 0;

	check = false;
	while (!negative_nums.empty()) {
		if (!check) {
			current = negative_nums.top();
		} else {
			answer += current * negative_nums.top();
		}

		negative_nums.pop();
		check = !check;
	}
	answer += (check && !zero_check) ? current : 0;

	// print answer
	printf("%d\n", answer);

	return 0;
}
