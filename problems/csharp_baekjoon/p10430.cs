/*
 * (220413) 나머지
 * https://www.acmicpc.net/problem/10430
 */

using System;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      int [] nums = Array.ConvertAll(Console.ReadLine().Split(), int.Parse);

      int A = nums[0];
      int B = nums[1];
      int C = nums[2];

      Console.WriteLine( (A+B)%C );
      Console.WriteLine( ((A%C)+(B%C))%C );
      Console.WriteLine( (A*B)%C );
      Console.WriteLine( ((A%C)*(B%C))%C );
    }
  }
}
