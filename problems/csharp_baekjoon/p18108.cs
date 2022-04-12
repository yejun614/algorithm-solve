/*
 * (220413) 1998년생인 내가 태국에서는 2541년생?!
 * https://www.acmicpc.net/problem/18108
 *
 * [풀이]
 * 2541 - 1998 = 543 임으로
 * 입력받은 년도에 543을 뺀 결과를 출력하면 된다.
 */

using System;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      int year = int.Parse(Console.ReadLine());

      Console.WriteLine(year - 543);
    }
  }
}
