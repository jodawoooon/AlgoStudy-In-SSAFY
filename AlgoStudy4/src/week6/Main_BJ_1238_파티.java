package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1238_파티 {


	static int N, M,X, map[][], ans;
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken()); //N개의 마을 (1~N) 각각 학생 한명씩 산다
		M = Integer.parseInt(st.nextToken()); //M개의 단방향도로
		X = Integer.parseInt(st.nextToken()); //목적지
		
		//각 학생들이 본인 마을에서 출발해서 X마을까지 간뒤 다시 자기 마을로 돌아오는 최소비용 구하고
		//그 중 가장 큰 마을에 사는 학생의 소요시간 출력

		map = new int[N+1][N+1];
		
		for(int i=1; i<=N; ++i) {
			for(int j=1; j<=N; ++j) {
				if(i==j) map[i][i] = 0;
				else map[i][j] = Integer.MAX_VALUE;
			}
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken());  //도로의 시작점
			int to = Integer.parseInt(st.nextToken()); //끝점
			int time = Integer.parseInt(st.nextToken());  //소요시간
			map[from][to] = time;
		}
		
		
		
		//플로이드 와샬 알고리즘
		for(int k=1; k<=N ; ++k) {
			for(int i=1; i<=N; ++i) {
				if(map[i][k]>=Integer.MAX_VALUE) continue; //가지치기
				if(i==k) continue;//가지치기
				
				for(int j=1; j<=N; ++j) {
					if(map[k][j]>=Integer.MAX_VALUE) continue;//가지치기
					if(i==j || k==j) continue;
					////경유지 들러서 가는게 더 빠르면
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		
		}
		
		
		for (int n = 1; n <= N; n++) {
			if(n==X) continue;//가지치기
			ans = Math.max(map[n][X]+map[X][n], ans);
			// N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간
		}
		
		System.out.println(ans);
		
	}

}
