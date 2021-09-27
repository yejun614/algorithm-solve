/*
 * (210927) 최소직사각형
 * https://programmers.co.kr/learn/courses/30/lessons/86491
 */

#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> sizes) {
    vector<int> width, height;
    
    for (auto item : sizes) {
        if (item[1] > item[0])
            swap(item[0], item[1]);
        
        width.push_back(item[0]);
        height.push_back(item[1]);
    }
    
    int max_width = *max_element(width.begin(), width.end());
    int max_height = *max_element(height.begin(), height.end());
    
    return max_width * max_height;
}