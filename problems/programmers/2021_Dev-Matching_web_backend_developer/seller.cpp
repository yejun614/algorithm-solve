/*
 * (210816) 다단계 칫솔 판매
 * https://programmers.co.kr/learn/courses/30/lessons/77486
 */

#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

vector<int> solution(vector<string> enroll, vector<string> referral, vector<string> seller, vector<int> amount) {
    const int enroll_num = enroll.size();
    const int seller_num = seller.size();
    
    unordered_map<string, int> people;
    unordered_map<string, string> refer;
    
    for (int n = 0; n < enroll_num; n ++) {
        people.insert(make_pair(enroll[n], n));
        refer.insert(make_pair(enroll[n], referral[n]));
    }
    
    vector<int> answer(enroll_num, 0);
    
    for (int i = 0; i < seller_num; i ++) {
        int rest = amount[i] * 100;
        string current = seller[i];
        
        while (current != "-" && rest >= 1) {
            const int index = people[current];
            const int money = rest * 0.1;
            
            answer[index] += rest - money;
            rest = money;
            
            current = refer[current];
        }
    }
    
    return answer;
}

