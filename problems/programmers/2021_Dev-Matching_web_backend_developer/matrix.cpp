/*
 * (210816) 행렬 테두리 회전하기
 * https://programmers.co.kr/learn/courses/30/lessons/77485
 */

#include <vector>
#include <algorithm>

using namespace std;

typedef vector<int> vec;
typedef vector<vector<int> > vec2;

vec2 make_board(int rows, int columns) {
    int count = 1;
    vec2 board(rows, vec(columns, 0));
    
    for (auto parent=board.begin(); parent!=board.end(); parent++) {
        for (auto child=parent->begin(); child!=parent->end(); child++) {
            *child = count++;
        }
    }
    
    return board;
}

int rotation_board(vec2& board, vec query) {
    const int HEIGHT = (query[2] - query[0]) + 1;
    const int WIDTH = (query[3] - query[1]) + 1;
    int count = (2 * WIDTH) + (HEIGHT - 2) * 2 + 1;

    for (int i=0; i<4; i++) query[i]--;

    int y = query[0], x = query[1];

    int min = board[y][x];
    int pivot = -1;

    while (count --) {
        // Min
        if (board[y][x] < min) min = board[y][x];

        if (pivot > 0) {
            // Swap
            int buf = board[y][x];
            board[y][x] = pivot;
            pivot = buf;
        } else {
            pivot = board[y][x];
        }

        // Move
        if (x == query[1] && y > query[0]) { y --; }
        else if (y == query[2]) { x --; }
        else if (x == query[3]) { y ++; }
        else { x ++; }
    }

    return min;
}

vec solution(int rows, int columns, vec2 queries) {
    vec answer;
    vec2 board = make_board(rows, columns);

    for (auto query : queries) {
        answer.push_back(rotation_board(board, query));
    }

    return answer;
}

