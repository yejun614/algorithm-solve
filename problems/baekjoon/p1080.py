'''
 * (210904) 행렬
 * https://www.acmicpc.net/problem/1080
'''

H, W = map(int, input().split())

mat1 = [list(input()) for _ in range(H)]
mat2 = [list(input()) for _ in range(H)]

t = {'0': '1', '1': '0'}

def reverse(ypos, xpos):
  for y in range(ypos, ypos+3):
    for x in range(xpos, xpos+3):
      mat1[y][x] = t[mat1[y][x]]

answer = 0

for y in range(H-2):
  for x in range(W-2):
    if mat1[y][x] != mat2[y][x]:
      reverse(y, x)
      answer += 1

if mat1 == mat2:
  print(answer)
else:
  print(-1)
