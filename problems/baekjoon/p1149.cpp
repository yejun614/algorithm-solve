/*
 * (210818) RGB거리
 * https://www.acmicpc.net/problem/1149
 */

#include <iostream>
#include <vector>

using namespace std;

typedef vector<int> vec;
typedef vector<vector<int> > vec2;

const int MAX_VAL = 1000001;

vec2 cache(0);

int solve(const vec2& house, size_t num=0, int cost=0, int prev=-1) {
	if (num >= house.size()) return cost;
	if (prev != -1 && cache[num][prev] > 0) return cost + cache[num][prev];

	int current, min_cost = MAX_VAL;

	for (int i=0; i<3; i++) {
		if (i == prev) continue;
		
		current = solve(house, num+1, cost+house[num][i], i);

		if (current < min_cost)
			min_cost = current;
	}

	const int result = min_cost - cost;
	const int p = cache[num][prev];

	if (prev != -1 && (p == -1 || result < p))
		cache[num][prev] = result;

	return min_cost;
}

int main() {
	int N;
	cin >> N;

	vec2 house(N, vec(3));

	for (auto home=house.begin(); home!=house.end(); home++) {
		for (auto cost=home->begin(); cost!=home->end(); cost++) {
			cin >> *cost;
		}
	}

	cache.resize(N, vec(3, -1));

	const int answer = solve(house);
	cout << answer << endl;

	return 0;
}
