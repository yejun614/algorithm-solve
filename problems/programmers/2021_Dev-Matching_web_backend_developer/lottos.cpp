#include <string>
#include <vector>

using namespace std;

const int RANK_SCORES[] = { 6, 6, 5, 4, 3, 2, 1 };

vector<int> solution(vector<int> lottos, vector<int> win_nums) {
    int correct = 0, zeroes = 0;
    
    for (auto num=lottos.begin(); num!=lottos.end(); num++) {
        if (*num == 0) {
            zeroes ++;
            continue;
        }
        
        for (auto check=win_nums.begin(); check!=win_nums.end(); check++) {
            if (*num == *check) {
                correct ++;
                win_nums.erase(check);
                break;
            }
        }
    }
    
    vector<int> answer;
    answer.push_back(RANK_SCORES[correct+zeroes]);
    answer.push_back(RANK_SCORES[correct]);
    
    return answer;
}

