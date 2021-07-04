/**
 * (210704) 잃어버린 괄호
 * https://www.acmicpc.net/problem/1541
 *
 * [풀이]
 * 덧셈 연산자들끼리만 괄호로 묶어주면 항상 최소값이 나온다.
 */

#include <string>
#include <vector>
#include <iostream>

using namespace std;

int main() {
	// input
	string str;
	cin >> str;

	vector<int> nums;
	vector<char> ops;

	bool check = false;

	// split number and operator.
	for (string::iterator ch=str.begin(); ch!=str.end(); ch++) {
		if (*ch >= '0' && *ch <= '9') {
			if (!check) nums.push_back(0);

			vector<int>::reverse_iterator num = nums.rbegin();

			(*num) *= 10;
			(*num) += (*ch)-'0';

			check = true;

		} else {
			ops.push_back(*ch);
			check = false;
		}
	}
	ops.push_back('\0');

	// solve
	vector<int> groups(1, 0);
	int size = nums.size();

	for (int i=0; i<size; i++) {
		vector<int>::reverse_iterator group = groups.rbegin();
		(*group) += nums[i];

		if (ops[i] == '-') groups.push_back(0);
	}

	int answer = groups.front();

	for (vector<int>::iterator it=groups.begin()+1; it!=groups.end(); it++) {
		answer -= (*it);
	}

	cout << answer << endl;

	return 0;
}

