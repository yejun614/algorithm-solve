package practice.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Program {
	private static class PriorityQueue<T extends Comparable<T>> {
		private List<T> memory = new ArrayList<>();
		
		public PriorityQueue() { memory.add(null); }
		
		public void push(T item) {
			memory.add(item);
			
			int index = memory.size() - 1;
			int parentIndex = index / 2;
			
			while (parentIndex >= 1 && compareIndex(parentIndex, item)) {
				Collections.swap(memory, parentIndex, index);
				
				index = parentIndex;
				parentIndex /= 2;
			}
		}
		
		public T pop() {
			final T item = memory.get(1);
			int last = memory.size() - 1;
			
			memory.set(1, memory.get(last));
			memory.remove(last--);
			
			int index = 1;
			int childIndex = getChildIndex(index * 2);
			
			while (childIndex <= last && compareIndex(index, childIndex)) {
				Collections.swap(memory, index, childIndex);
				
				index = childIndex;
				childIndex = getChildIndex(index * 2);
			}
			
			return item;
		}
		
		private boolean compareIndex(int indexA, int indexB) {
			return memory.get(indexA).compareTo(memory.get(indexB)) > 0;
		}
		
		private boolean compareIndex(int indexA, T value) {
			return memory.get(indexA).compareTo(value) > 0;
		}
		
		private int getChildIndex(int index) {
			final int size = memory.size();
			if (index >= size) return size;
			if (index == size - 1) return index;
			if (compareIndex(index, index + 1)) index++;
			return index;
		}
		
		public boolean isEmpty() { return memory.size() < 2; }
		@Override public String toString() { return "PriorityQueue " + memory; }
	}
	
	private static <T extends Comparable<T>> Object[] heapSort(T[] arr) {
		// 입력된 데이터를 모두 최소 힙에 입력한다
		PriorityQueue<T> priorityQueue = new PriorityQueue<>();
		for (T item : arr) priorityQueue.push(item);
		System.out.println(priorityQueue);
		
		// 최소 힙에 있는 데이터를 모두 pop 하여 정렬한다
		List<T> result = new ArrayList<>();
		while (!priorityQueue.isEmpty()) {
			result.add(priorityQueue.pop());
			System.out.println(priorityQueue);
		}
		
		// 결과 반환
		return result.toArray();
	}
	
	public static void main(String[] args) {
		// 정렬할 배열 준비
		Integer[] arr = {4, 3, 5, 6, 3, 6, 899, 32, 54, 6, 7};
		
		// 최소 힙 정렬
		Object[] sortedArr = heapSort(arr);
		System.out.println();
		
		// 정렬 결과를 정수 배열로 변환하여 출력
		Integer[] sortedIntArr = Arrays.copyOf(sortedArr, arr.length, Integer[].class);
		System.out.println("heap sort: " + Arrays.toString(sortedIntArr));
		
		// 결과 비교
		Arrays.sort(arr);
		System.out.println("sort     : " + Arrays.toString(arr));
		System.out.println("compare  : " + Arrays.equals(arr, sortedIntArr));
	}
}
