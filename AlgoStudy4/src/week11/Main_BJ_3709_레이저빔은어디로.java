package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_3709_레이저빔은어디로 {
	static class Node{
		int x,y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static int N, R, map[][], dir;
	static Node ans;
	static int dx[] = {-1,0,1,0}; //상 우 하 좌 => 우향우
	static int dy[] = {0,1,0,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //보드의 크기
			R = Integer.parseInt(st.nextToken()); //우향우 거울의 개수
			
			map = new int[N+2][N+2];
			
			for (int r = 0; r < R; r++) {
				//우향우 거울이 배치된 좌표 x y
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				map[x][y] = -1; //우향우거울 맵핑
			}
			
			//레이저의 좌표
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			map[x][y] = 1; //레이저 맵핑
			
			ans = new Node(0,0);
			dir = checkDir(x,y);
			
			dfs(x,y,dir);
			
			
			System.out.println(ans.x+" "+ans.y);
		}
		
		
	}
	
	
	private static void dfs(int x, int y, int d) {
		
		int nx = x + dx[d];
		int ny = y + dy[d];
		
		if(!isIn(nx,ny)) {
			ans.x = nx;
			ans.y = ny;
			return;
		}
		
		if(map[nx][ny]==-1) {
			d = (d+1)%4;
			dfs(nx,ny,d);
		}else if(map[nx][ny]==0) {
			dfs(nx,ny,d);
		}

	}
	private static boolean isIn(int nx, int ny) {
		if(nx<1||ny<1||nx>N||ny>N) return false;
		return true;
	}


	private static int checkDir(int x, int y) {
		//0:상 1:우 2:하 3:좌
		if(x==0) return 2;//레이저빔이 향하는 방향. 0행에 잇을때는 아래로
		if(x==N+1) return 0; //N+1행 => 위로
		if(y==0) return 1; //0열 => 오른쪽으로
		else return 3; //왼쪽
	}
}
