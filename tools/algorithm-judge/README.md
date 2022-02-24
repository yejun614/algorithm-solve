# Algorithm Judge

- 내가 아는 모든 테스트케이스를 사용했는데, 어디에 문제가 있는지 모르겠다.
- 반례를 좀 알고 싶다.
- 이런 상황을 위해서 제작한 프로그램 입니다.

## 사용 방법
1. 먼저 정답 코드를 준비합니다.
2. 시험하고 싶은 코드와 정답 코드를 각자 컴파일 해서 실행파일을 준비해 주세요.
  - 파이썬 처럼 인터프리터 언어인 경우 컴파일 하지 않아도 됩니다.
3. 반례 생성기 프로그램을 작성 합니다.
  - 문제에서 제시되는 범위안에서 난수를 생성하거나,
  - 모든 경우의 수를 생성하는 프로그램을 작성해 주세요.
  - 자세한 내용은 밑에서 설명해 드립니다.
4. Judge 프로그램에 시험 코드와 정답 코드 그리고 반례 생성기를 입력하세요.
5. 반례를 계속 생성해서 두 프로그램의 출력이 달라지는 경우를 찾아줍니다.

## 컴파일 방법
```bash
# Golang이 설치되지 않았다면 설치 (MacOS의 경우)
brew install golang

# 의존성 패키지 가져오기
go install .

# 컴파일
go build .

# 확인하기
./judeg -h
```

## CLI Usage
```bash
# 기본적인 사용법
judge -t <시험하고 싶은 프로그램> -a <정답 프로그램> -g <반례 생성기 프로그램>

# 반례를 찾으면 프로그램 중단.
judge -t target -a answer -g "python3 generator.py"

# 찾은 모든 반례를 출력하고 싶다면 -all 옵션 사용
judge -t target -a answer -g "python3 generator.py" -all

# 몇 개의 입력을 시험할 건지 -n 옵션으로 지정
judge -t target -a answer -g "python3 generator.py" -all -n 50

# 결과를 파일로 저장하고 싶다면 -s <파일명> 으로 지정
judge -t target -a answer -g "python3 generator.py" -all -n 50 -s output.txt

# 반례 탐색에 할당하고 싶은 CPU Cores 개수는 -cpu로 지정
# (-1 하면 모든 CPU 사용, 기본값: -1)
judge -t target -a answer -g "python3 generator.py" -cpu 1
```

## 반례 생성기 프로그램 작성방법
- 문제에서 제시되는 입력 값의 범위를 확인해 주세요.
- 생성기에는 여러가지 종류가 있습니다.
  - 난수 생성기
  - 모든 경우의 수 확인하기
  - 제한된 범위에서 경우의 수 확인하기
- 생성기가 실행되면 정수를 하나 입력 받습니다.
- 원하는 방식으로 입력 값을 생성한 후 출력 합니다.

## 예시 (A+B=C)
### 시험 코드
```C++
// test.cpp
#include <cstdio>

int main() {
  int A, B;
  scanf("%d %d", &A, &B);

  printf("%d\n", A+B);
  return 0;
}
```

### 정답 코드
```go
// answer.go
package main

import "fmt"

func main() {
  var A, B int

  fmt.Scanln(&A, &B);
  fmt.Println(A + B)
}
```

### 반례 생성기 코드
```Python3
import random

# 여기서 N값은 사용하지 않습니다.
n = int(input())

print("%d %d" % (random.randint(0, 10), random.randint(0, 10)))
```

### 명령어
```bash
judge -t target -a answer -g "python3 generator.py"
```

## 개발자 (Developers)
- YeJun, Jung (yejun614@naver.com)
