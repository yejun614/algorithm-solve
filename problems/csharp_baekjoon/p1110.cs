/*
 * (220414) 더하기 사이클
 * https://www.acmicpc.net/problem/1110
 *
 * [풀이]
 * 각 자리의 숫자를 더하는 작업과,
 * 두 수의 가장 오른쪽 자리에 있는 수를 합치는 작업은 함수로 만드는게 좋다.
 */

using System;

namespace Baekjoon
{
  class Program
  {
    static int sumNum(int num) {
      int result = 0;

      while (num > 0) {
        result += num % 10;
        num /= 10;
      }

      return result;
    }

    static int lastNums(int numA, int numB) {
      return (numA % 10) * 10 + (numB % 10);
    }
  
    static void Main() {
      int inputNumber = int.Parse(Console.ReadLine());

      int current = inputNumber, counter = 0;

      do {
        current = lastNums(current, sumNum(current));
        counter++;
      } while (inputNumber != current);

      Console.WriteLine(counter);
    }
  }
}
