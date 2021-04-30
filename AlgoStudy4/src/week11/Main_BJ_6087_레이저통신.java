package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_6087_레이저통신 {
	
	// W×H 크기의 지도
	// 지도의 각 칸은 빈 칸이거나 벽이며, 두 칸은 'C'로 표시되어 있는 칸이다.
	// 'C'로 표시되어 있는 두 칸을 레이저로 통신하기 위해서 설치해야 하는 거울 개수의 최솟값
	// C에서만 발사할 수 있고, 빈 칸에 거울('/', '\')을 설치해서 방향을 90도 회전시킬 수 있다. 
	
	// '/' ; 왼쪽반사, '\' ; 오른쪽반사
	static int W,H, mirror[][];
	static char map[][];
	static int dx[] = {-1,0,1,0}; //90도씩 우향우 -> 순서 : 상 우 하 좌 
	static int dy[] = {0,1,0,-1};
	
	
	static class Node{
		int x,y;
		int dir;
		int cnt;


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
		
		map = new char[H][W];
		mirror = new int[2][2];
		int idx = 0;
		for (int h = 0; h < H; h++) {
			String str = br.readLine();
			for (int w = 0; w < W; w++) {
				map[h][w] = str.charAt(w);
				if(map[h][w]=='C') {
					mirror[idx][0] = h;
					mirror[idx++][1] = w;
				}
			}
		}
		
		bfs();
		
	}
	private static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		
		//4방 출발
		queue.add(new Node(mirror[0][0], mirror[0][1],0,0));
		queue.add(new Node(mirror[0][0], mirror[0][1],1,0));
		queue.add(new Node(mirror[0][0], mirror[0][1],2,0));
		queue.add(new Node(mirror[0][0], mirror[0][1],3,0));
		
		while(true) {
			Node n = queue.poll();
			int x = n.x;
			int y = n.y;
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(!isIn(nx,ny)) continue;
			}
		}
		
	}
	
	private static boolean isIn(int nx, int ny) {
		if(nx<0||ny<0||nx>H||ny>W) return false; //맵 range 체크
		return true;
	}
}
