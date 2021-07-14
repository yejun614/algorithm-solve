/*
 * (210714) OX퀴즈
 * https://www.acmicpc.net/problem/8958
 *
 * [풀이]
 * 입력 받은 문자열을 하나씩 순회하면서 O가 나오면 현재 점수에 1을 더해
 * 정답변수에 더하고, X가 나오면 현재 점수를 0으로 초기화 한다.
 */

#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main() {
	int T;
	cin >> T;

	string str;

	for (int testcase=0; testcase<T; testcase++) {
		cin >> str;
		
		int answer = 0, score = 0;
		for (string::iterator ch=str.begin(); ch!=str.end(); ch++) {
			if (*ch == 'O') {
				answer += ++score;
			} else {
				score = 0;
			}
		}

		cout << answer << endl;
	}

	return 0;
}
