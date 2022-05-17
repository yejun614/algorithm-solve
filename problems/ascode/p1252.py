'''
 * (220517) IP Address
 * http://www.ascode.org/problem.php?id=1252
 *
 * 작정하고 짧게 한 줄로 작성한 코드
'''

[[print(int(ip[i*8:i*8+8], 2), end=('\n' if i == 3 else '.')) for i in range(4)] for ip in [input() for n in range(int(input()))]]
