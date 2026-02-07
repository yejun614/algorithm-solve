import java.io.*;
import java.util.*;

public class Main {
  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

  private static final int[] START_NUM = { 2, 3, 5, 7 };
  private static final int MAX_N = 10;
  private static int[] sizedNum;

  // ----------------------------------------------------------

  public static void main(String[] args) throws IOException {
    int N = Integer.parseInt(reader.readLine().trim());

    makeSizedNum();
    int begin = sizedNum[N];
    int end = sizedNum[N + 1] - 1;

    for (int start : START_NUM) {
      int num = begin * start;
      for (int count = 0; count < sizedNum[N]; count++) {
        if (isSpecialPrimeNum(num, 2, N)) writer.write(num + "\n");
        num++;
      }
    }
    writer.flush();
  }

  // ----------------------------------------------------------

  private static void makeSizedNum() {
    sizedNum = new int[MAX_N];
    sizedNum[0] = sizedNum[1] = 1;

    for (int index = 2; index < MAX_N; index++) {
      sizedNum[index] = sizedNum[index - 1] * 10;
    }
  }

  private static boolean isSpecialPrimeNum(int num, int pos, int size) {
    if (pos > size) return true;

    int current = num / sizedNum[size - pos + 1];

    if (!isPrimeNum(current)) return false;
    return isSpecialPrimeNum(num, pos + 1, size);
  }

  private static boolean isPrimeNum(int num) {
    if (num <= 1) return false;
    if (num <= 3) return true;
    if (num % 2 == 0) return false;

    final int last = (int)Math.sqrt(num);

    for (int x = 3; x <= last; x += 2) {
      if (num % x == 0) return false;
    }
    return true;
  }
}
