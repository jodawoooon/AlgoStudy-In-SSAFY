package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_6087_레이저통신2 {
	
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
				if(map[h][w]=='C') { //레이저의 위치 저장
					point[idx][0] = h;
					point[idx][1] = w;
					idx++;
				}
				
			}
			
		}
		
		bfs();
		
		
		System.out.println(ans);
		
	}
	
	private static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		int visited[][] = new int[H][W];
		
		for (int d = 0; d < 4; d++) { //4방탐색 시작
			queue.add(new Node(point[0][0], point[0][1], d, 0));
			
		}
		visited[point[0][0]][point[0][1]]=1; //visit체크
	
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			int x = n.x;
			int y = n.y;
			int d = n.dir;
			int nc = n.cnt;
			
			if(x==point[1][0]&&y==point[1][1]) { //최종위치 도착하면
				//System.out.println(n.cnt);
				ans = Math.min(ans, n.cnt); //거울몇개썼는지 갱신
				continue;
			}
			
			
			
			int nx = x + dx[d];
			int ny = y + dy[d];

			if(!isIn(nx,ny)) continue;
			if(map[nx][ny]=='*') continue;
			if(visited[nx][ny]==0||visited[nx][ny]>=nc){ //방문 안했으면 간다 //방문 했으나 더 적은 cost로 갱신 가능하면 간다
				visited[nx][ny] = nc;
				queue.add(new Node(nx,ny,d,nc)); //직진
				queue.add(new Node(nx,ny,(d+1)%4,nc+1)); // 오른쪽90도회전
				queue.add(new Node(nx,ny,(d+3)%4,nc+1)); // 왼쪽90도 회전
			}
			
		}
		
		
	}
	
	private static boolean isIn(int nx, int ny) {
		if(nx<0||ny<0||nx>=H||ny>=W) return false; //맵 range 체크
		return true;
	}
}