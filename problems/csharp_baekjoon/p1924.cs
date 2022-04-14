/*
 * (220414) 2007년
 * https://www.acmicpc.net/problem/1924
 *
 * [풀이]
 * 달마다 일수가 다르다는 것에 주의한다.
 */

using System;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      string [] inputs = Console.ReadLine().Split();
      int month = int.Parse(inputs[0]);
      int day = int.Parse(inputs[1]);

      string [] WEEK = {"SUN","MON","TUE","WED","THU","FRI","SAT"};

      int current = day;

      for (int m = 1; m < month; m ++) {
        switch (m) {
          case 2:
            current += 28;
            break;
            
          case 4:
          case 6:
          case 9:
          case 11:
            current += 30;
            break;

          default:
            current += 31;
            break;
        }
      }

      Console.WriteLine(WEEK[current % 7]);
    }
  }
}
