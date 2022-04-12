/*
 * (220413) 사칙연산
 * https://www.acmicpc.net/problem/10869
 */

using System;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      int [] nums = Array.ConvertAll(Console.ReadLine().Split(), int.Parse);

      Console.WriteLine(nums[0] + nums[1]);
      Console.WriteLine(nums[0] - nums[1]);
      Console.WriteLine(nums[0] * nums[1]);
      Console.WriteLine(nums[0] / nums[1]);
      Console.WriteLine(nums[0] % nums[1]);
    }
  }
}
