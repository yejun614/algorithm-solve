package practice.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Program {
	public static void main(String[] args) {
		/*
		 *                 (1)
		 *               /  |  \
		 *             (2) (3) (4)
		 *             / \    / | \
		 *           (5) (6)(7)(8)(9)
		 */
		final int[][] edges = { // 노드는 1번부터 시작
				{1, 2}, {1, 3}, {1, 4},
				{2, 5}, {2, 6},
				{4, 7}, {4, 8}, {4, 9},
		};
		final int nodeCount = 9;
		
		// 그래프를 자료형에 저장
		List<List<Integer>> graph = new ArrayList<>();
		for (int count = 0; count <= nodeCount; count++) graph.add(new ArrayList<>());
		for (int[] edge : edges) graph.get(edge[0]).add(edge[1]);
		
		// BFS
		Queue<Integer> bfsQueue = new ArrayDeque<>();
		bfsQueue.offer(1);
		
		System.out.println("BFS");
		while (!bfsQueue.isEmpty()) {
			int nodeIndex = bfsQueue.poll();
			System.out.println(nodeIndex);
			
			for (int child : graph.get(nodeIndex)) {
				bfsQueue.offer(child);
			}
		}
		System.out.println();
		
		// DFS
		Stack<Integer> dfsStack = new Stack<>();
		dfsStack.push(1);
		
		System.out.println("DFS");
		while (!dfsStack.isEmpty()) {
			int nodeIndex = dfsStack.pop();
			System.out.println(nodeIndex);
			
			for (int child : graph.get(nodeIndex)) {
				dfsStack.push(child);
			}
		}
		
	}
}
