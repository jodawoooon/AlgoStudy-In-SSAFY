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
	static int W,H, point[][], ans;
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
		
		
		System.out.println(ans);
		
	}
	private static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		boolean visited[][][] = new boolean[H][W][4];
		
		//4방 출발
		for (int d = 0; d < 4; d++) {
			queue.add(new Node(point[0][0], point[0][1],d,0));
			visited[point[0][0]][point[0][1]][d]=true;
		}
		
		while(true) {
			Node n = queue.poll();
			int x = n.x;
			int y = n.y;
			int d = n.dir;
			//System.out.println("===============");
			//System.out.println(x+" "+y+" "+d+" "+n.cnt);
			
			if(x==point[1][0]&&y==point[1][1]) { //최종위치 도착하면
				//System.out.println(n.cnt);
				//ans = Math.min(ans, n.cnt); //거울몇개썼는지 갱신
				break;
			}
			
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(!isIn(nx,ny)) continue;
			
			if(visited[nx][ny][d]) continue;
			if(map[nx][ny]=='*') continue;
			visited[nx][ny][d] = true;
			
			//System.out.println(nx+" "+ny+" "+d+" "+n.cnt);
			//System.out.println(map[nx][ny]);
//			System.out.println("===============");
			
			
			if(map[nx][ny]=='C') ans = Math.min(ans, n.cnt);
			queue.add(new Node(nx,ny,d,n.cnt)); //그냥 직진
		
			queue.add(new Node(nx,ny,(d+1)%4, n.cnt+1)); //오른쪽 회전 거울 세우기
			queue.add(new Node(nx,ny,(d+3)%4, n.cnt+1)); //왼쪽회전
				
		}
		
		
	}
	
	private static boolean isIn(int nx, int ny) {
		if(nx<0||ny<0||nx>=H||ny>=W) return false; //맵 range 체크
		return true;
	}
}
