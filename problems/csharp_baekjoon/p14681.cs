/*
 * (220414) 사분면 고르기
 * https://www.acmicpc.net/problem/14681
 *
 * [풀이]
 * (x, y) 2차원 좌표를 입력받아서 어느 사분면에 속하는지 출력하는 문제이다.
 * 조건문을 활용해도 되지만 공식화 시켜 보았다.
 *
 * 입력 받은 x, y 좌표의 각 값이 양수라면 1으로 음수라면 0로 만들어 준다.
 * 그리고 다음 공식에 입력하면 해당하는 사분면이 출력 된다.
 * 
 * (3 - (-x) - y) - (2 * x * y)
 */

using System;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      int x = int.Parse(Console.ReadLine()) < 0 ? 0 : 1;
      int y = int.Parse(Console.ReadLine()) < 0 ? 0 : 1;

      Console.WriteLine((3 - (-x) - y) - (2 * x * y));
    }
  }
}
