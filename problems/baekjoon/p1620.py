'''
 * (221110) 나는야 포켓몬 마스터 이다솜
 * https://www.acmicpc.net/problem/1620
'''

N, M = map(lambda x: int(x), input().split())

pokemon = []
pokemonMap = {}

for i in range(N):
  name = input()
  pokemon.append(name)
  pokemonMap[name] = i + 1

for i in range(M):
  cmd = input()

  if (cmd.isdigit()):
    print(pokemon[int(cmd) - 1])

  else:
    print(pokemonMap[cmd])
