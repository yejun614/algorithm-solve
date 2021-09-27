'''
 * (210927) 최소직사각형
 * https://programmers.co.kr/learn/courses/30/lessons/86491
'''

def solution(sizes):
    width = []
    height = []
    
    for item in sizes:
        if item[1] > item[0]:
            item[0], item[1] = item[1], item[0]
        
        width.append(item[0])
        height.append(item[1])
    
    max_width = max(width)
    max_height = max(height)
    
    return max_width * max_height