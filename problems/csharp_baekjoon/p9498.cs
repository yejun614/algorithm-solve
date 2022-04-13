/*
 * (220413) 시험 성적
 * https://www.acmicpc.net/problem/9498
 *
 * [풀이]
 * 조건문을 이용하거나, ASCII Code를 응용하거나
 * TABLE을 만들어서 입력값과 대응시키는 등 다양한 풀이가 존재한다.
 */

using System;

namespace Baekjoon
{
  class Program
  {
    static void Main()
    {
      string [] SCORE_TABLE = { "F","F","F","F","F","F","D","C","B","A","A" };
      int score = int.Parse(Console.ReadLine());

      Console.WriteLine(SCORE_TABLE[score / 10]);
    }
  }
}
