/*
 * (221006) 수열의 변화
 * https://www.acmicpc.net/problem/1551
 *
 * [풀이]
 * 단순 구현문제로 특별히 함정 같은건 없다.
 * 
 * C++로 입력을 받을 때 문자열 split 같은경우 한번에 해결해 주는 함수가 없어
 * 직접 구현해야 한다. (아래 split 함수를 참고)
 */

#include <iostream>
#include <string>
#include <vector>
#include <sstream>

using namespace std;

vector<int> split(string str) {
	vector<int> result;

	size_t index = 0;
	size_t pos = str.find(',', index);

	while (pos != string::npos) {
		result.push_back(stoi(str.substr(index, pos)));

		index = pos + 1;
		pos = str.find(',', index);
	}

	result.push_back(stoi(str.substr(index, str.size())));

	return result;
}

int main() {
	// 아래 옵션은 cin, cout의 입출력 속도를 빠르게 해준다.
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	// INPUT
	int N, K;
	cin >> N >> K;

	string str;
	cin.ignore(100, '\n');
	getline(cin, str);

	vector<int> nums = split(str);
	int size = nums.size();

	// SOLUTION
	while (K--) {
		for (int i = 0; i < size - 1; i++) {
			nums[i] = nums[i + 1] - nums[i];
		}

		nums.erase(nums.end() - 1);
		size--;
	}

	// OUTPUT
	for (int i = 0; i < size - 1; i++) {
		cout << nums[i] << ",";
	}
	cout << nums[size - 1] << endl;

	return 0;
}

