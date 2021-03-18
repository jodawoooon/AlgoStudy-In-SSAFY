package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main_BJ_20056_마법사상어와파이어볼 {
	//마법사 상어가 크기가 N*N인 격자에 파이어볼 M개 발사
	/*
	 * 가장 처음 파이어볼은 각자 위치에서 이동을 대기하고 있다.
	 * i번 파이어볼의 위치는 r,c이고, 질량 m, 방향 d, 속력 s
	 * 격자의 행과 열은 1번부터 N번까지.
	 * 
	 * 파이어볼의 방향은 어떤 칸과 인접한 8개의 칸의 방향 의미.
	 * 7 0 1
	 * 6   2
	 * 5 4 3
	 * 
	 * 마법사가 이동 명령하면
	 * 1. 모든 파이어볼이 자신의 방향 d로 속력 s칸 만큼 이동
	 * 		=> 이동하는 중 같은칸에 여러개의 파이어볼 OK
	 * 2. 이동이 모두 끝난뒤 2개이상의 파이어볼이 있는 칸에는 (int형 배열)
	 * 		1.같은 칸의 파이어볼은 하나로 합쳐진다
	 * 		2.파이어볼은 4개의파이어볼로 나뉘어진다
	 * 		3. 질량 = 합쳐진 파이어볼 질량 합/5
	 * 		       속력 = 합쳐친 파이어볼 속력 합 / 개수
	 *  	    합쳐지는 파이어볼의 방향이 모두 홀수이거나 짝수이면 방향은 0,2,4,6 아니면 1,3,5,7
	 *  	4. 질량이 0인 파이어볼은 소멸된다
	 *  
	 *  이동 K번 후 남아있는 파이어볼 질량의 합.
	 *  
	 */

	static class fireBall{
		int r,c,m,s,d;

		public fireBall(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		
		public fireBall(int m, int s, int d) {
			super();
			this.m = m;
			this.s = s;
			this.d = d;
		}


		@Override
		public String toString() {
			return "fireBall [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}
		
	}
	static int[] dr = {-1,-1,0,1,1,1,0,-1}; //상 우상 우 우하 하 좌하 좌 좌상
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static int N,M,K, ans;
	//static Queue<fireBall> queue ;
	static ArrayList<fireBall> arr[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M =  Integer.parseInt(st.nextToken());
		K =  Integer.parseInt(st.nextToken());
		
		arr =  new ArrayList[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				arr[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			//파이어볼 위치 저장

			arr[r][c].add(new fireBall(m,s,d));
		}
		
		
		for (int k = 0; k < K; k++) {

			move();
			
			
			//이동이 끝난 뒤
			magic();
			

			
			
		}
		
		//이동 k번 끝난후
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				for (int i = 0; i < arr[r][c].size(); i++) {
					ans+=arr[r][c].get(i).m;
				}
			}
		}
		System.out.println(ans);
		
		
	}
	
	private static void magic() {
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if(arr[r][c].size()>=2) { //2개 이상의 파이어볼이 있으면
					int mSum = 0;
					int sSum = 0;
					int cnt = arr[r][c].size();

					int holCnt = 0;
					int jjackCnt = 0;
					
					for (int k = 0; k < cnt; k++) {
						fireBall fb = arr[r][c].get(k);
						
						mSum += fb.m;
						sSum += fb.s;
							
						int dir = fb.d;
						if(dir%2==0) {
							jjackCnt++;
						}else {
							holCnt++;
						}
						
					}
					
					arr[r][c].clear();
					int m = mSum/5;
					int s = sSum/cnt;
					
					if(m==0) { //질량이 0인 파이어볼은 소멸되어없어진다
		
					}else {
						if(jjackCnt==cnt||holCnt==cnt) {
							//방향이 모두 홀수이거나 짝수라면
							//System.out.println("0246");
							arr[r][c].add(new fireBall(m,s,0));
							arr[r][c].add(new fireBall(m,s,2));
							arr[r][c].add(new fireBall(m,s,4));
							arr[r][c].add(new fireBall(m,s,6));
						}else {
							//System.out.println("1357");
							arr[r][c].add(new fireBall(m,s,1));
							arr[r][c].add(new fireBall(m,s,3));
							arr[r][c].add(new fireBall(m,s,5));
							arr[r][c].add(new fireBall(m,s,7));
						}
					}
				}
			}
		}
	}

	private static void move() {
		ArrayList<fireBall> temp[][] = new ArrayList[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				temp[i][j] = new ArrayList<>();
			}
		}
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if(arr[r][c].size()>=1) {
					for (int k = 0; k < arr[r][c].size(); k++) {
						fireBall fb = arr[r][c].get(k);
						int m = fb.m;
						int s = fb.s;
						int d = fb.d;
						
						int nr = (r + dr[d]*(s%N));
						int nc = (c + dc[d]*(s%N));
						
						if(nr<1) { //nr이 0이면 N이되야하고... 만약 -N이면 다시 N
							nr+=N;
						}else if (nr>N) {
							nr-=N;
						}
						
						if (nc>N) {
							nc-=N;
						}else if(nc<1) {
							nc+=N;
						}
						//arr[r][c].remove(k);
						temp[nr][nc].add(new fireBall(m,s,d));
					}
			}
		}
		
		}
		
		
		arr = temp;
	}
	

	
}
