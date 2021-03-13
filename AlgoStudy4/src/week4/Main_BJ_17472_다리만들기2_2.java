package week4;

import java.io.*;
import java.util.*;

public class Main_BJ_17472_다리만들기2_2 {
	
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
	static ArrayList<int[]> pickList;
	static boolean visited[][], tgt[][];
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		ans = Integer.MAX_VALUE;
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		//입력값 배열
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());

			}
		}
		
		
		
		//섬의 갯수, 섬에 번호 붙이기
		int idx=1;
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j]==1&&!visited[i][j]) {
					dfs(idx++,i,j); //idx++하면서 섬에 번호 붙여주기
					islandCnt++;//섬의 갯수 카운트
				}
			}
		}

		map = new int[islandCnt+1][islandCnt+1];
		
		//1번 섬부터 islandCnt번째 섬까지의 다리길이를 관리할 배열
		//arr배열과 같은 i,j 인덱스를 사용하면 다리를 하나씩 붙일수없다.
		//각 섬의 번호를 인덱스로 사용한다.
		
		//예를들면 1번섬 -2번섬은 map[1][2]에 다리 길이를 저장한다
		//이때 다리길이는 최솟값으로 유지해야한다. 
		//따라서 다리길이를 저장하기전 map배열을 Integer.MAX밸류로 초기화했다.
		for (int i = 1; i <= islandCnt; i++) {
			for (int j = 1; j <= islandCnt; j++) {
				map[i][j] = Integer.MAX_VALUE; 
			}
		}

		
		//arr[i][j]!=0이면, 즉 섬이면 다른 섬으로 다리를 이어본다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j]!=0) {
					bridge(i,j);
				}
			}
		}
		
		for (int[] ia : map) {
			System.out.println(Arrays.toString(ia));
		}
		//섬을 잇고 난뒤 그대로 MAX_VALUE로 남아있으면 안된다. (섬인애들은 다시 0으로 되돌려줘야한다)
		//MAX_VALUE인 애들은 (섬) 다시 0으로 초기화해준다.
//		for (int i = 1; i <= islandCnt; i++) {
//			for (int j = 1; j <= islandCnt; j++) {
//				if(map[i][j]==Integer.MAX_VALUE) {
//					map[i][j]=0;
//				}
//			}
//		}
		

		//이렇게 다리를 놓고나면 다리가 너무 많다.
		//이렇게 놓은 다리중에 필요한 다리는 총 섬의 갯수(islandCnt)-1개이다.
		//조합으로 N-1개 다리 선택

		visited = new boolean[islandCnt+1][islandCnt+1];
		tgt = new boolean[islandCnt+1][islandCnt+1];
		
		comb(0,0,0);
		
		
		if(ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}




	private static void comb(int cnt,int idx, int dis) {
		if(cnt==islandCnt-1) {
			//선택한 다리로 모든 섬을 이을 수 있는지 확인한다
			// => bfs
			boolean flag = bfs(1);
			if(flag) {
				ans = Math.min(ans, dis); //ans는 다리길이의 최솟값
			}
			return;
		}
		

		for (int i = idx; i < islandCnt*islandCnt; i++) {
			
			// x= 1111 2222 3333 4444
			// y= 1234 1234 1234 1234
			int x = 1+i/islandCnt;
			int y = 1+i%islandCnt;
			
			 //중복확인 제거
			if(x>=y) continue;
			
			if(map[x][y]!=0) { //섬이 아니면
				//System.out.println(x+" "+y);
				tgt[x][y]=true; //다리선택
				tgt[y][x]=true;
				comb(cnt+1,i+1,dis+map[x][y]); //고른 다리의 길이의 합 저장
				tgt[x][y]=false;
				tgt[y][x]=true;

			}
		}

		
		
	}





	private static boolean bfs(int v) { //섬이 모두 연결되었는지 확인한다.
		Queue<Integer> q = new LinkedList<>();
		boolean[] select = new boolean[islandCnt+1];
		int cnt = 0;
		
		q.add(v); //
		select[v] = true;
	
		while(true) {
			if(q.isEmpty()) break;
			
			int nextV = q.poll();
			cnt++;

			for (int i = 1; i <= islandCnt; i++) {
				if(!select[i]) { //방문하지않았고
					if(tgt[nextV][i]) { //섬 사이에 다리가 이어져있다. (1-2, 2-3, 3-4 등 두섬이 이어져있다)
						
						q.add(i); //큐에 다음 다리를 넣어준다
						select[i]=true;
					}
				}
			}
		}
		
		
		if(cnt==islandCnt) return true;//모든 섬이 이어져있다면 true
		else return false;
	}





	private static void bridge(int x, int y) { //다리를 만드는 함수
		int idx = arr[x][y];
		
		for (int d = 0; d < 4; d++) {
			int nx = x;
			int ny = y;
			int next = 0;
			int dis = 0;
			
			while(true) {
				nx+=dx[d]; //한뱡향으로 쭉이동하면서 다리를 만든다
				ny+=dy[d];
				
				if(nx>=0&&ny>=0&&nx<N&&ny<M) {
					if(arr[nx][ny]!=idx) { //자기 섬이 아니고
						if(arr[nx][ny]!=0) {  //다른섬을 만나면
							next = arr[nx][ny]; //next변수에 다른 섬 번호 저장
							if(dis>1) { //이때의 거리가 1보다 크면
								map[idx][next] = Math.min(dis, map[idx][next]); //다리의 길이를 저장한다.
							}
							break;
						}
						dis++; //계속 바다이면 dis++;
					}else {
						break;
					}

				}else {
					break;
				}
			}
			
		}
	}



	private static void dfs(int idx, int x, int y) { //같은 구역(섬)을 찾는다.
		arr[x][y]=idx;
		visited[x][y] = true;

		for (int d = 0; d < 4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			if(nx>=0&&ny>=0&&nx<N&&ny<M&&!visited[nx][ny]) {

				if(arr[nx][ny]==1&&!visited[nx][ny]) { //섬인공간에서 계속 이동
					
					dfs(idx,nx,ny);
				}
			}
		}
		
	}
}
