package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_14889_스타트와링크 {
	
	/*
	 * 축구
	 * N명(짝수)
	 * 스타트팀 N/2명, 링크팀 N/2명
	 * 
	 * 능력 배열치 : S
	 * S(i,j)는 i번 사람과 j번 사람이 같은 팀에 속했을 때 팀에 더해지는 능력
	 * 팀의 능력치는 팀에 속한 "모든 쌍"의 S(i,j) 합
	 * S(j,i)와 S(i,j)는 다를수도 있다
	 * 
	 *스타트팀과 링크 팀의 능력차이를 최소로 하려할때 최솟값 출력
	 */
	
	static int N, S[][], min;
	static boolean[] select;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N= Integer.parseInt(br.readLine());
		S = new int[N+1][N+1];
		min = Integer.MAX_VALUE;
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());

			}
		}
		
//		for (int[] ia : S) {
//			System.out.println(Arrays.toString(ia));
//		}
		
		//먼저 조합으로 팀을 나누고
		//능력치 구하기
		select = new boolean[N+1];
		comb(0,1,new ArrayList<>(),new ArrayList<>());
		System.out.println(min);
	}
	
	
	private static void comb(int cnt, int k, ArrayList start, ArrayList link) {
		if(cnt==N/2) {
			//능력치 구하는 함수 호출
			for (int i = 1; i <= N; i++) {
				if(select[i]) {
					start.add(i);
				}else {
					link.add(i);
				}
			}
			
			solve(start,link);
			
//			System.out.println("st: " +Arrays.toString(start.toArray()));
//			System.out.println("li: " +Arrays.toString(link.toArray()));
			
			start.clear();
			link.clear();
			return;
		}
		
		for (int i = k; i <= N; i++) {
			if(!select[i]) {
				select[i] = true;
				comb(cnt+1,i+1,start,link);
				select[i] = false;
			}
			
		}
		
	}


	private static void solve(ArrayList<Integer> st, ArrayList<Integer> li) {
		int startSum = 0;
		int linkSum = 0;
		boolean[][] visited = new boolean[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(!visited[i][j]&&!visited[j][i]&&st.contains(i)&&st.contains(j)&&i!=j) {
					startSum += S[i][j];
					startSum += S[j][i];
					visited[i][j] = true;
					visited[j][i] = true;
					
				}
			}
		}
		visited = new boolean[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(!visited[i][j]&&!visited[j][i]&&li.contains(i)&&li.contains(j)&&i!=j) {
					//System.out.println(S[i][j]+" "+S[j][i]);
					linkSum += S[i][j];
					linkSum += S[j][i];
					visited[i][j] = true;
					visited[j][i] = true;
				}
			}
		}
		//System.out.println("stSum:"+startSum+" liSum"+linkSum);
		min = Math.min(min, Math.abs(startSum-linkSum));
	}


}
