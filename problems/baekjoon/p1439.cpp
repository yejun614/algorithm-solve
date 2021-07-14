/*
 * (210714) 뒤집기
 * https://www.acmicpc.net/problem/1439
 *
 * [풀이]
 * 비교적 간단한 문제입니다.
 * 문자열을 하나 입력받은 후 각 문자들을 순회하면서 이전과
 * 다른 문자를 발견하면 group 변수를 가산 합니다.
 * 마지막에 group/2의 결과값을 정수형으로 출력하면 됩니다.
 */

#include <iostream>
#include <string>

using namespace std;

int main() {
	string str;
	cin >> str;

	int group = 1;
	string::iterator p = str.begin();

	for (string::iterator ch=str.begin()+1; ch!=str.end(); ch++) {
		if (*p != *ch) group ++;
		p++;
	}

	cout << (int)(group/2) << endl;

	return 0;
}
