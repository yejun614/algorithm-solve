/*
 * {풀이 하는 중} (1952) [모의 SW 역량테스트] 수영장
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq&categoryId=AV5PpFQaAQMDFAUq&categoryType=CODE&problemTitle=1952&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

import java.io.*;
import java.util.*;

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
	
	int[] oneDayTicketArr;
	boolean[] monthTicketArr;
	
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
		
		oneDayTicketArr = new int[MONTH_LEN];
		monthTicketArr = new boolean[MONTH_LEN];

		offset = bestPrice * 2;
		cache = new boolean[13 * offset];

		searchBestPrice(0, 0);
	}

	int offset;
	boolean[] cache;
	
	private void searchBestPrice(int month, int currentPrice) {
		if (month > MONTH_LEN || currentPrice >= bestPrice) return;

		int cacheKey = month * offset + currentPrice;
		// System.out.printf("searchBestPrice(%d, %d)\n", month, currentPrice);
		if (cache[cacheKey]) return;
		cache[cacheKey] = true;

		while ( (month < MONTH_LEN) &&
				(monthTicketArr[month] || oneDayTicketArr[month] >= planArr[month])
		) {
			month++;
		}
		
		// 기저조건
		if (month >= MONTH_LEN) {
			if (currentPrice < bestPrice) bestPrice = currentPrice;
			cache[cacheKey] = true;
			return;
		}

		// System.out.printf("[%d] searchBestPrice(%d, %d)\n", testCase, month, currentPrice);
		
		int price;
		
		// 1일 이용권
		price = currentPrice + priceArr[ONE_DAY_TICKET];
		if (price < bestPrice) {
			oneDayTicketArr[month]++;
			if (oneDayTicketArr[month] >= planArr[month]) {
				searchBestPrice(month + 1, price);
			} else {
				searchBestPrice(month, price);
			}
			oneDayTicketArr[month]--;
		}
		
		// 1달 이용권
		price = currentPrice + priceArr[ONE_MONTH_TICKET];
		if (price < bestPrice) {
			monthTicketArr[month] = true;
			searchBestPrice(month + 1, price);
			monthTicketArr[month] = false;
		}
		
		// 3달 이용권
		price = currentPrice + priceArr[THREE_MONTH_TICKET];
		if (price < bestPrice) {
			for (int m = month; m < month + 2 && m < MONTH_LEN; m++) monthTicketArr[m] = true;
			searchBestPrice(month + 3, price);
			for (int m = month; m < month + 2 && m < MONTH_LEN; m++) monthTicketArr[m] = false;
		}

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
