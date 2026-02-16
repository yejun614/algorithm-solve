/*
 * (1952) [모의 SW 역량테스트] 수영장
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq&categoryId=AV5PpFQaAQMDFAUq&categoryType=CODE&problemTitle=1952&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

/**
 * SW Expert Academy - 1952. [모의 SW 역량테스트] 수영장
 * @author YeJun, Jung
 * 
 * [분석]
 *   - 각 월에 대해서 1일, 1달, 3달 이용권 중 하나를 선택해서 비용의 최소화 해야 한다.
 * 
 * [전략]
 *   - DFS(재귀함수)를 사용해서 각 월에 대해서 1일, 1달, 3달 이용권을 시도한다.
 *   - 만약 계산된 가격이 현재까지의 최적 가격보다 높다면 더 이상 탐색하지 않는다. (backtracking)
 *   - 각 월에 대한 최적 가격을 기록하여 탐색 횟수를 최적화한다.
 */
public class Solution {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer input;
	
	// --------------------------------------------------------
	
	public static void main(String[] args) throws IOException {
		final int testCount = Integer.parseInt(reader.readLine().trim());
		
		for (int testCase = 1; testCase <= testCount; testCase++) {
			new Solution(testCase).run();
		}
	}
	
	// --------------------------------------------------------
	
	static final int TICKET_TYPE_LEN = 4;
	static final int MONTH_LEN = 12;
	
	static final int ONE_DAY_TICKET = 0;
	static final int ONE_MONTH_TICKET = 1;
	static final int THREE_MONTH_TICKET = 2;
	static final int ONE_YEAR_TICKET = 3;
	
	int testCase;
	int bestPrice;
	
	boolean noPlan;
	int[] priceArr;
	int[] planArr;
	
	public Solution(int testCase) {
		this.testCase = testCase;
	}

	public void run() throws IOException {
		input();
		solve();
		print();
	}
	
	private void input() throws IOException {
		getLine();
		priceArr = new int[TICKET_TYPE_LEN];
		for (int index = 0; index < TICKET_TYPE_LEN; index++) {
			priceArr[index] = Integer.parseInt(input.nextToken());
		}
		
		getLine();
		noPlan = true;
		planArr = new int[MONTH_LEN];
		for (int index = 0; index < MONTH_LEN; index++) {
			planArr[index] = Integer.parseInt(input.nextToken());
			if (planArr[index] != 0) noPlan = false;
		}
	}
	
	private void solve() {
		// 만약 이용계획이 없는 경우 bestPrice는 0이 된다.
		if (noPlan) {
			bestPrice = 0;
			return;
		}
		
		bestPrice = priceArr[ONE_YEAR_TICKET]; // 1년 이용권
		offset = bestPrice * 2;
		cache = new boolean[13 * offset];

		searchBestPrice(0, 0);
	}

	int offset;
	boolean[] cache;
	
	private void searchBestPrice(int month, int currentPrice) {
		// month가 12월을 넘어가지 않도록 한다.
		month = Math.min(month, MONTH_LEN);

		// 메모이제이션
		int cacheKey = month * offset + currentPrice;
		if (cache[cacheKey]) return;
		cache[cacheKey] = true;

		// 이용하지 않는 달은 넘어간다.
		while ( month < MONTH_LEN && planArr[month] == 0) month++;

		// 기저조건
		if (month >= MONTH_LEN) {
			if (currentPrice < bestPrice) bestPrice = currentPrice;
			return;
		}
		
		// 1일 이용권
		int price;
		price = currentPrice + priceArr[ONE_DAY_TICKET] * planArr[month];
		if (price < bestPrice) {
			searchBestPrice(month + 1, price);
		}
		
		// 1달 이용권
		price = currentPrice + priceArr[ONE_MONTH_TICKET];
		if (price < bestPrice) {
			searchBestPrice(month + 1, price);
		}
		
		// 3달 이용권
		price = currentPrice + priceArr[THREE_MONTH_TICKET];
		if (price < bestPrice) {
			searchBestPrice(month + 3, price);
		}

		// 캐시
		cache[cacheKey] = true;
	}
	
	private void print() throws IOException {
		writer.write("#" + testCase);
		writer.write(" " + bestPrice);
		writer.write("\n");
		writer.flush();
	}
	
	// --------------------------------------------------------
	
	private void getLine() throws IOException {
		input = new StringTokenizer(reader.readLine().trim());
	}
}
