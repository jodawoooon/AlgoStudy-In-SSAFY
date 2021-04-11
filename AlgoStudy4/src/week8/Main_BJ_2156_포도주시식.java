package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BJ_2156_포도주시식 {
	/*
	 * 
	 * 포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마셔야 하고, 
	 * 마신 후에는 원래 위치에 다시 놓아야 한다. 연속으로 놓여 있는 3잔을 모두 마실 수는 없다.
	 * 될 수 있는 대로 많은 양의 포도주를 맛보고 싶다.
	 * 1~n 번호의 포도 주 잔
	 */
	
	
	
	static int N, arr[], dp[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //포도주 잔의 개수
		
		arr = new int[N+1]; //각 잔의 포도주 양
		dp =  new int[N+1][2]; //dp[n][d] : n은 포도주 잔의 번호, 직전 포도주 먹었는지

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		//N=1
		dp[1][0] = arr[1]; //한 잔 마심
		
		if(N>1) {
			//N=2
			dp[2][0] = arr[2];
			dp[2][1] = dp[1][0] + arr[2];
		}
		
		
		
		for (int i = 3; i <= N; i++) {
			dp[i][0] = Math.max(dp[i-2][0],dp[i-2][1]) + arr[i];
					//이전에 포도주를 먹지 않았다.
			dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] +arr[i]);
					//연속으로 포도주를 먹었다. => 이번 포도주 먹으면 안됨
		}
		
		int ans = Math.max(dp[N][0], dp[N][1]);
		System.out.println(ans);
		
	}
}
