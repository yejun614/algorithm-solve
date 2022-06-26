/*
 * (220626) 신규 아이디 추천
 * https://programmers.co.kr/learn/courses/30/lessons/72410
 *
 * [풀이]
 * 구현 문제로 요구 사항을 정확하게 구현할 수 있는지 물어보는 문제이다.
 * 문자열 처리에 관한 지식을 요구한다.
 * (자세한 풀이는 소스코드 참고)
 */

#include <iostream>
#include <string>
using namespace std;

constexpr int DIFF = 'a' - 'A';

string toLower(string str, int size) {
    string result = str;
    
    for (int i = 0; i < size; i ++)
        if (result[i] >= 'A' && result[i] <= 'Z')
            result[i] = result[i] + DIFF;
    
    return result;
}

string removeSpecialCharacter(string str, int size) {
    string result = "";
    
    for (int i = 0; i < size; i ++) {
        if (
            (str[i] >= '0' && str[i] <= '9') ||
            (str[i] >= 'A' && str[i] <= 'Z') ||
            (str[i] >= 'a' && str[i] <= 'z') ||
            str[i] == '-' ||
            str[i] == '_' ||
            str[i] == '.'
        )
            result += str[i];
    }
    
    return result;
}

string removeMultipleDots(string str, int size) {
    string result = "";
    char prev = ' ';
    
    for (int i = 0; i < size; i ++) {
        if (str[i] == '.' && prev == '.') continue;
        
        result += str[i];
        prev = str[i];
    }
    
    return result;
}

inline string removeFirstAndLastDot(string str, int size) {
    if (str[size-1] == '.') str.erase(size - 1, 1);
    if (str[0] == '.') str.erase(0, 1);
    
    return str;
}

inline string checkBlinkString(string str) {
    if (str.empty()) return "a";
    return str;
}

string reduceStringLength(string str, int size, int length = 15) {
    if (size <= length) return str;
    
    length = str[length-1] == '.' ? length - 1 : length;
    
    string result = str.substr(0, length);
    return result;
}

string checkShortString(string str, int size, int length = 3) {
    if (size >= length) return str;
    
    char last = str[size - 1];
    
    for (; size < length; size ++)
        str += last;
    
    return str;
}

string solution(string new_id) {
    string answer = new_id;
    
    // 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
    answer = toLower(answer, answer.size());
    
    // 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
    answer = removeSpecialCharacter(answer, answer.size());
    
    // 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
    answer = removeMultipleDots(answer, answer.size());
    
    // 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
    answer = removeFirstAndLastDot(answer, answer.size());
    
    // 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
    answer = checkBlinkString(answer);
    
    // 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
    //      만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
    answer = reduceStringLength(answer, answer.size());
    
    // 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
    answer = checkShortString(answer, answer.size());
    
    return answer;
}

int main() {
    string input;
    cin >> input;

    string result = solution(input);
    cout << result << "\n";

    return 0;
}
