package week4;

import java.io.*;
import java.util.*;

public class Main_BJ_17472_다리만들기2 {
	
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
	
	static int N,M,arr[][],ans,islandCnt, map[][];
	static boolean visited[][];
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
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

			}
		}
		
		ans = Integer.MAX_VALUE;
		
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

		map = new int[islandCnt+1][islandCnt+1];
		
		for (int i = 1; i <= islandCnt; i++) {
			for (int j = 1; j <= islandCnt; j++) {
				map[i][j] = Integer.MAX_VALUE;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j]!=0) {

					bridge(i,j);
				}
			}
		}
		
		for (int i = 1; i <= islandCnt; i++) {
			for (int j = 1; j <= islandCnt; j++) {
				if(map[i][j]==Integer.MAX_VALUE) {
					map[i][j]=0;
				}
			}
		}
		

		//System.out.println(4);
		//놓은 다리중에 필요한 다리는 섬의갯수-1개이다.
		//조합으로 N-1개 다리 선택
		
		visited = new boolean[11][11];
		comb(0,0,0);
		
		
//		for (int[] ia : map) {
//			System.out.println(Arrays.toString(ia));
//		}
//		
		
		
		if(ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}





	private static void comb(int cnt,int idx, int dis) {
		if(cnt==islandCnt-1) {
			//선택한 다리로 모든 섬을 이을 수 있는지 확인한다
			// => bfs
			boolean flag = bfs();
			if(flag) {
				ans = Math.min(ans, dis);
			}
			return;
		}
		

		for (int i = idx; i < islandCnt*islandCnt; i++) {
			int x = i/islandCnt+1;
			int y = i%islandCnt+1;
			System.out.println(x+" "+y);
			if(x<=y) continue;
			if(map[x][y]!=0) {
				visited[x][y]=true;
				visited[y][x]=true;
				comb(cnt+1,i+1,dis+map[x][y]);
				visited[x][y]=false;
				visited[y][x]=false;
			}
		}

		
		
	}





	private static boolean bfs() {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visit = new boolean[islandCnt+1];
		int cnt = 0;
		
		q.add(1);
		visit[1] = true;
		
		while(true) {
			if(q.isEmpty()) break;
			
			int idx = q.poll();
			cnt++;
			
			for (int i = 1; i <= islandCnt; i++) {
				if(!visit[i]) {
					if(visited[idx][i]) {
						q.add(i);
						visit[i]=true;
					}
				}
			}
		}
		
		if(cnt==islandCnt) return true;
		else return false;
	}





	private static void bridge(int x, int y) {
		int idx = arr[x][y];
		
		for (int d = 0; d < 4; d++) {
			int nx = x;
			int ny = y;
			int next = 0;
			int dis = 0;
			
			while(true) {
				nx+=dx[d];
				ny+=dy[d];
				
				if(nx>=0&&ny>=0&&nx<N&&ny<M) {
					if(arr[nx][ny]!=idx) {
						if(arr[nx][ny]!=0) {
							next = arr[nx][ny];
							if(dis>1) {
								System.out.println("map : "+idx+" "+next);
								map[idx][next] = Math.min(dis, map[idx][next]);
							}
							break;
						}
						dis++;
					}else {
						break;
					}

				}else {
					break;
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
