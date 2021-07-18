/*
 * (210718) 단어 공부
 * https://www.acmicpc.net/problem/1157
 *
 * [풀이]
 * 입력받은 문자열을 전부 소문자로 변경해서 알파벳 26개 크기의 배열에
 * 알파벳의 개수를 카운팅 합니다. 그리고 앞에서부터 최댓값과 뒤에서부터 최댓값의
 * 위치를 비교하여 최댓값이 2개 이상인 경우 "?" 출력, 아니면 해당 문자를 출력합니다.
 */

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	string str;
	cin >> str;

	vector<int> count(26, 0);
	for (string::iterator ch=str.begin(); ch!=str.end(); ch++) {
		if (*ch >= 'a') *ch -= 32;
	
		count[(*ch)-'A'] ++;
	}

	int front_max = distance(count.begin(), max_element(count.begin(), count.end()));
	int back_max = distance(count.rbegin(), max_element(count.rbegin(), count.rend()));

	if (front_max + back_max != 25) {
		cout << "?" << endl;
	} else {
		cout << (char)('A'+front_max) << endl;
	}

	return 0;
}
