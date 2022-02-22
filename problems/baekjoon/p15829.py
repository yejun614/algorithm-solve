'''
 * (220221) Hashing
 * https://www.acmicpc.net/problem/15829
 *
 * [풀이]
 * 해시를 직접구현 하는 문제로, 문제에서 제시하는 공식에 따라 해시를 생성하는 문제 입니다.
 * 입력받는 문자열의 길이가 최대 50인데, 31^49 하면 값이 엄청 커지기 때문에 C/C++로 프로그램을
 * 작성하는 경우 "큰 수 계산하기" 원리를 참고해야 합니다.
 *
 * 파이썬의 경우 웬만큼 큰 수도 바로 계산가능 하기 때문에 편리합니다.
'''

R = 31
M = 1234567891

def hashing(text, L):
  result = 0
  
  for i in range(L):
    result += (ord(text[i]) - ord('a') + 1) * (R ** i);

  return result % M

def main():
  L = int(input())
  text = input()

  print(hashing(text, L));

main()
