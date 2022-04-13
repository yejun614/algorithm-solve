/*
 * (220413) A/B
 * https://www.acmicpc.net/problem/1008
 */

using System;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      double [] nums = Array.ConvertAll(Console.ReadLine().Split(), double.Parse);

      Console.WriteLine("{0}", nums[0]/nums[1]);
    }
  }
}
