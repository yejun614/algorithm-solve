/*
 * (220414) 윤년
 * https://www.acmicpc.net/problem/2753
 */

using System;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      int year = int.Parse(Console.ReadLine());

      Console.WriteLine((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) ? 1 : 0);
    }
  }
}
