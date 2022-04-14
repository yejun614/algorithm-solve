/*
 * (220414) 오븐 시계
 * https://www.acmicpc.net/problem/2525
 */

using System;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      string [] currentTime = Console.ReadLine().Split();

      int hour = int.Parse(currentTime[0]);
      int minutes = int.Parse(currentTime[1]);
      int cookMinutes = int.Parse(Console.ReadLine());

      minutes += cookMinutes;

      if (minutes >= 60) {
        hour += minutes / 60;
        minutes %= 60;
      }

      if (hour >= 24) {
        hour %= 24;
      }

      Console.WriteLine("{0} {1}", hour, minutes);
    }
  }
}
