/*
 * (220413) 곱셈
 * https://www.acmicpc.net/problem/2588
 *
 * [풀이]
 * 정수A 를 뒤에서 부터 한자리씩 읽기 위해서는
 *
 * Console.WriteLine(A % 10);
 * A /= 10
 *
 * 위 두 코드를 A가 0 이하가 될 때까지 반복하면 된다.
 */

using System;
using System.Collections.Generic;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      int A = int.Parse(Console.ReadLine());
      int B = int.Parse(Console.ReadLine());

      List<int> nums = new List<int>();
      int counter = 0, current;

      while (B > 0) {
        current = A * (B % 10);
        Console.WriteLine(current);
        
        nums.Add(current * (int)Math.Pow(10, counter++));
        
        B /= 10;
      }

      int answer = 0;

      foreach (int num in nums) {
        answer += num;
      }

      Console.WriteLine(answer);
    }
  }
}
