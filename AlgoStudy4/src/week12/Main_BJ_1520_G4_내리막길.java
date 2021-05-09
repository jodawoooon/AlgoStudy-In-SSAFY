package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1520_G4_내리막길 {
	/*
	 * 
	 * dfs
	 *  */
	static int map[][], M, N, dp[][];
	static boolean visited[][];
	static long ans;
	static int dx[] = { -1, 1, 0, 0 }; // 4방탐색을 하기위한 방향배열
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 맵 세로길이
		N = Integer.parseInt(st.nextToken()); // 맵 가로길이

		map = new int[M][N]; // 산의 지형을 저장할 map 배열
		dp = new int[M][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());// 산의 지형 셋팅
				dp[i][j] = -1; //dp배열 init
			}
		}
		
		
		System.out.println(dfs(0,0)); // 경로의 개수
	}


	private static int dfs(int x, int y) {
		if(x == M-1 && y==N-1) {
			//기저조건
			return 1; //끝까지 도착했다 => 경로 1개
		}
		
		if(dp[x][y]== -1) {
			//방문한적 없으면
			
			dp[x][y] = 0; //방문한 적 없다 => 현재 갯수 0개
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(!isIn(nx,ny)) continue;
				if(map[nx][ny] >= map[x][y]) continue;
				
				dp[x][y] += dfs(nx,ny); //끝까지 도착하면 1리턴 => 1씩 증가
			}
		}
		
	
		//(x,y)에 방문한적있으면 => dp배열에 저장값 있다면 dp[x][y] 리턴
		return dp[x][y];
		
	}


	private static boolean isIn(int nx, int ny) {
		// 맵의 범위 안에 있는지 체크하는 메소드
		if (nx < 0 || ny < 0 || nx >= M || ny >= N)
			return false;
		return true;
	}

}