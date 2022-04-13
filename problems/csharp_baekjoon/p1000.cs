/*
 * (220413) A+B
 * https://www.acmicpc.net/problem/1000
 *
 * [풀이]
 * C#에서 문자열 형태로 된 정수를 변환하는 방법을 공부함.
 */

using System;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      int [] nums = Array.ConvertAll(Console.ReadLine().Split(), Int32.Parse);

      Console.WriteLine(nums[0] + nums[1]);
    }
  }
}
