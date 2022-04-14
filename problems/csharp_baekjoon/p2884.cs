/*
 * (220414) 알람 시계
 * https://www.acmicpc.net/problem/2884
 *
 * [풀이]
 * 시간에서 시(Hour)은 0 ~ 24 범위이고,
 * 분(Minutes)은 0 ~ 60 범위임을 기억한다.
 */

using System;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      string [] inputs = Console.ReadLine().Split();
      
      int hour = int.Parse(inputs[0]);
      int minutes = int.Parse(inputs[1]);

      minutes -= 45;

      if (minutes < 0) {
        hour --;
        minutes = 60 + minutes;
      }

      if (hour < 0) {
        hour = 24 + hour;
      }

      Console.WriteLine("{0} {1}", hour, minutes);
    }
  }
}
