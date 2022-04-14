/*
 * (220414) A+B - 7
 * https://www.acmicpc.net/problem/11021
 */

using System;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      int T = int.Parse(Console.ReadLine());

      for (int i = 1; i <= T; i ++) {
        int [] nums = Array.ConvertAll(Console.ReadLine().Split(), int.Parse);

        Console.WriteLine("Case #{0}: {1}", i, nums[0] + nums[1]);
      }
    }
  }
}
