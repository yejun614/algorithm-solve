/*
 * (220413) A*B
 * https://www.acmicpc.net/problem/10998
 */

using System;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      int [] nums = Array.ConvertAll(Console.ReadLine().Split(), Int32.Parse);

      Console.WriteLine(nums[0] * nums[1]);
    }
  }
}
