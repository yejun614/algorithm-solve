/*
 * (220414) 주사위 세개
 * https://www.acmicpc.net/problem/2480
 *
 * [풀이]
 * 입력받은 주사위 값을 배열로 저장한 다음 오름차순을 정렬했다.
 * 배열 앞에서 부터 하나씩 반복문을 돌면서 이전 주사위와 같은 값이 있는지 확인한다.
 * 주사위 개수가 4개 이상이 되면 다른 알고리즘을 사용해야 한다.
 */

using System;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      int [] diceNums = Array.ConvertAll(Console.ReadLine().Split(), int.Parse);
      Array.Sort(diceNums);

      int counter = 1, prev = -1, lastNum = 0;

      foreach (int dice in diceNums) {
        if (prev == dice) {
          counter ++;
          lastNum = dice;
        }

        prev = dice;
      }

      int money = 0;

      if (counter == 3) money = 10000 + lastNum * 1000;
      else if (counter == 2) money = 1000 + lastNum * 100;
      else money = prev * 100;

      Console.WriteLine(money);
    }
  }
}
