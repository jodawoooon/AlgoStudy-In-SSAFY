package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_6087_레이저통신 {
	
	static int W,H, point[][], ans;
	static char map[][];
	static int dx[] = {-1,0,1,0}; //90도씩 우향우 -> 순서 : 상 우 하 좌 
	static int dy[] = {0,1,0,-1};
	
	
	static class Node{
		int x,y;
		int dir;
		int cnt;


		
		public Node(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}



		public Node(int x, int y, int dir, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}
		
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		ans = Integer.MAX_VALUE;
		map = new char[H][W];
		point = new int[2][2];
		int idx = 0;
		for (int h = 0; h < H; h++) {
			String str = br.readLine();
			for (int w = 0; w < W; w++) {
				map[h][w] = str.charAt(w);
				if(map[h][w]=='C') {
					point[idx][0] = h;
					point[idx++][1] = w;
				}
			}
		}
		
		bfs();
		
		
		System.out.println(ans-1);
		
	}
	private static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		//boolean visited[][][] = new boolean[H][W][4];
		boolean visited[][] = new boolean[H][W];
		

		
		queue.add(new Node(point[0][0], point[0][1],0));
		visited[point[0][0]][point[0][1]] = true;
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			int x = n.x;
			int y = n.y;
			
			if(x==point[1][0]&&y==point[1][1]) { //최종위치 도착하면
				//System.out.println(n.cnt);
				ans = Math.min(ans, n.cnt); //거울몇개썼는지 갱신
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = x;
				int ny = y;
				
				while(true) {
					//벽을 만나기 전까지 or 맵을 벗어나기 전까지
					//if(visited[nx][ny]) break;
					
					if(!isIn(nx,ny)) break;
					if(map[nx][ny]=='*') break;
					
					if(!visited[nx][ny]) {
						visited[nx][ny]=true;
						queue.add(new Node(nx,ny,n.cnt+1));
					}

					nx+=dx[d];
					ny+=dy[d];
				}

			}

				
		}
		
		
	}
	
	private static boolean isIn(int nx, int ny) {
		if(nx<0||ny<0||nx>=H||ny>=W) return false; //맵 range 체크
		return true;
	}
}