'''
 * (221006) 팩토리얼 0의 개수
 * https://www.acmicpc.net/problem/1676
 *
 * [풀이]
 * 팩토리얼을 구해서 뒤에서부터 0의 개수를 세는 프로그램을 작성한다.
 * 입력받는 수의 팩토리얼 계산 결과가 크기 때문에 파이썬을 사용하면 편리하다.
'''

import math

N = math.factorial(int(input()))
S = str(N)[::-1]

ans = 0

for i in S:
	if i != '0':
 		break
	ans += 1

print(ans)

