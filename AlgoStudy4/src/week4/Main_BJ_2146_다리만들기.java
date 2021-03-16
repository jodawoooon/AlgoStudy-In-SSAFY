package week4;

import java.io.*;
import java.util.*;

public class Main_BJ_2146_다리만들기 {
	
	/*
	 * 다리는 바다에만 건설
	 * 다리로 모든 섬을 연결
	 * 다리의 길이는 2이상
	 * 다리의 방향은 가로 또는 세로
	 * 
	 * 
	 * 섬에서 시작해서.. 
	 * 
	 */
	
	static int N,M,arr[][],ans,islandCnt;
	static boolean visited[][];

	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				//disMap[i][j] = Integer.MAX_VALUE;
			}
		}
		
		
		//섬의 갯수, 섬에 번호 붙이기
		int idx=1;
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j]==1&&!visited[i][j]) {
					dfs(idx++,i,j);
					islandCnt++;
				}
			}
		}
		for (int[] ia : arr) {
			System.out.println(Arrays.toString(ia));
		}
		System.out.println(islandCnt);
		
		
		
		
		ans=Integer.MAX_VALUE;

		
		//섬 주변의 바다의 거리를 구한다 => BFS
		//다른 육지를 만나면 min 갱신
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j]==1) {
					
					for (int d = 0; d < 4; d++) {
						int nx = i+dx[d];
						int ny = j+dy[d];
						if(nx>=0&&ny>=0&&nx<N&&ny<M&&!visited[nx][ny]) {

							if(arr[nx][ny]==0) {
								
								bfs(i,j);
							}
						}
					}

				}
			}
		}
		
		System.out.println(ans);
	}



	private static void bfs(int i, int j) {
		visited = new boolean[N][M];
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(i,j,0));
		visited[i][j] = true;
		int idx = arr[i][j];
		
		
		
		while(true) {
			if(q.isEmpty()) break;
			
			Node n = q.poll();
			int x = n.x;
			int y = n.y;
			int dis = n.dis;
			
			if(arr[x][y]!=idx&&arr[x][y]!=0) {
				ans = Math.min(ans, dis-1);
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				if(nx>=0&&ny>=0&&nx<N&&ny<M&&!visited[nx][ny]) {

					
					
					if((arr[nx][ny]!=idx||arr[nx][ny]==0)&&!visited[nx][ny]) {
						q.add(new Node(nx,ny,dis+1));
						visited[nx][ny]=true;
					}
				}
			}
			
		}
		
	}



	private static void dfs(int idx, int x, int y) {
		arr[x][y]=idx;
		visited[x][y] = true;

		for (int d = 0; d < 4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			if(nx>=0&&ny>=0&&nx<N&&ny<M&&!visited[nx][ny]) {

				if(arr[nx][ny]==1&&!visited[nx][ny]) {
					
					dfs(idx,nx,ny);
				}
			}
		}
		
	}
}
class Node{
	int x;
	int y;
	int dis;
	public Node(int x, int y, int dis) {
		this.x = x;
		this.y = y;
		this.dis = dis;
	}
	
}