/*
 * (220414) A+B - 8
 * https://www.acmicpc.net/problem/11022
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

        Console.WriteLine($"Case #{i}: {nums[0]} + {nums[1]} = {nums[0] + nums[1]}");
      }
    }
  }
}
