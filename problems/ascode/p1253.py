'''
 * (220517) IP Address 2
 * http://www.ascode.org/problem.php?id=1253
 *
 * 작정하고 짧게 한 줄로 작성한 코드 2
'''

[print(''.join(ip)) for ip in [list(map(lambda x: '{0:08b}'.format(int(x)), input().split('.'))) for n in range(int(input()))]]
