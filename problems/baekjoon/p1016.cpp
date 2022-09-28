/*
 * (220927) 제곱 ㄴㄴ 수
 * https://www.acmicpc.net/problem/1016
 *
 * [풀이]
 * 범위(min ~ max)안의 수가 소수의 제곱으로 나누어 떨어지는 수를 카운트하는 문제이다.
 *
 * 소수의 제곱을 사용하는 이유는 모든 경우의 수를 확인하기에는 너무 많기 때문에
 * 소수(1과 자기자신으로만 나누어 떨어지는 수)를 사용해서 경우의 수를 줄이는 방식을 사용한다.
 *
 * 또한 범위안의 개수(max - min + 1)에 소수를 나누어서 개수를 구할려고 했으나,
 * 소수의 제곱끼리 공통 배수가 생길 수 있어서 반복문으로 확인해 주어야 한다.
 */

#include <cstdio>
#include <cmath>
#include <vector>

using namespace std;

#define POW(x) ((x)*(x))
using llu = unsigned long long;

vector<llu> GetPrimes(llu max) {
	vector<llu> primes;
	
	for (llu i = 2; i <= max; i++) {
		llu limit = sqrt(i);
		bool isPrime = true;

		for (
			auto prime = primes.begin();
			prime != primes.end() && *prime <= limit;
			prime++
		) {
			if (i % *prime == 0) {
				isPrime = false;
				break;
			}
		}

		if (isPrime) {
			primes.push_back(i);
		}
	}

	return primes;
}

int main() {
	llu min, max;
	scanf("%llu %llu", &min, &max);

	llu count = max - min + 1;
	vector<llu> primes = GetPrimes(sqrt(max));

	vector<bool> check(count, false);

	for (auto it = primes.begin(); it != primes.end(); it++) {
		llu prime = POW(*it);

		for (llu i = (min / prime) * prime; i <= max; i += prime) {
			if (i >= min && !check[i - min]) {
				check[i - min] = true;
				count--;
			}
		}
	}

	printf("%llu\n", count);

	return 0;
}

