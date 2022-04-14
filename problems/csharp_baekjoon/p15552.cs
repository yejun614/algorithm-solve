/*
 * (220414) 빠른 A+B
 * https://www.acmicpc.net/problem/15552
 *
 * [풀이]
 * 이 문제는 입출력을 빨리할 수 있는 코드를 짜는게 핵심이다.
 * C#은 출력할 때 StringBuilder를 사용하면 통과 가능하다.
 *
 * 다른 언어는 다음 링크를 참고한다.
 * https://www.acmicpc.net/board/view/22716
 */

using System;
using System.Text;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      int TestCase = int.Parse(Console.ReadLine());

      StringBuilder output = new StringBuilder();

      while (TestCase-- > 0) {
        int [] nums = Array.ConvertAll(Console.ReadLine().Split(), int.Parse);
        output.AppendLine((nums[0] + nums[1]).ToString());
      }

      Console.Write(output.ToString());
    }
  }
}
