/*
 * (210715) 단어 수학
 * https://www.acmicpc.net/problem/1339
 *
 * [풀이]
 * 백준알고리즘 질문에서 풀이를 얻을 수 있었다.
 * 입력받을때 문자열의 각 자리수를 알아낸다. ABC = 100A + 10B + C
 * 문자들의 점수가 저장될 배열을 만들고 안에 자리수를 더한다. scores[A] += 100
 * 점수가 가장 높은 순으로 9부터 0까지 지정하고
 * 문자열의 각 문자를 숫자로 치환하여 덧셈하고, 결과를 출력한다.
 */

#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <cmath>
#include <map>

using namespace std;

int main() {
	int N;
	cin >> N;

	vector<int> scores(26, 0);
	vector<string> str(10, "");

	for (int i=0; i<N; i++) {
		cin >> str[i];

		int current = pow(10, str[i].size()), index;
		
		for (string::iterator ch=str[i].begin(); ch!=str[i].end(); ch++) {
			index = (*ch) - 'A';
			scores[index] += current;

			current /= 10;
		}
	}

	map<char, int> table;
	vector<int>::iterator it;

	int current_score = 9;

	for (int i=0; i<10; i++) {
		it = max_element(scores.begin(), scores.end());
		char key = distance(scores.begin(), it) + 'A';

		table.insert(pair<char, int>(key, current_score--));

		*it = -1;
	}

	int answer = 0, num;

	for (int i=0; i<N; i++) {
		num = 0;

		for (string::iterator ch=str[i].begin(); ch!=str[i].end(); ch++) {
			num *= 10;
			num += table[*ch];
		}

		answer += num;
	}

	cout << answer << endl;

	return 0;
}
