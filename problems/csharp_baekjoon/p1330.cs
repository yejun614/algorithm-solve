/*
 * (220413) 두 수 비교하기
 * https://www.acmicpc.net/problem/1330
 */

using System;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      int [] nums = Array.ConvertAll(Console.ReadLine().Split(), int.Parse);

      if (nums[0] > nums[1]) Console.WriteLine(">");
      else if (nums[0] < nums[1]) Console.WriteLine("<");
      else Console.WriteLine("==");
    }
  }
}
