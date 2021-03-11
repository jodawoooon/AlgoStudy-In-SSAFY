package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_BJ_17070_파이프옮기기1 {

	/*
	 * 집 N*N
	 * r은 행의번호 c는 열의번호 (둘다 1부터 시작)
	 * 각 칸은 빈칸이거나 벽이다
	 * 파이프는 연속된 두개의 칸을 차지함
	 * 3가지 방향.
	 * 좌,우  / 상,하  / 좌상,우하
	 * 
	 * 파이프는 빈칸만 차지 
	 * 방향은 우, 우하, 하  방향
	 * 45도씩만 회전 가능
	 * 
	 * 가로 => 가로 or 대각선 
	 * 세로 => 세로 or 대각선
	 * 대각선 => 가로 or 세로 or 대각선
	 * 
	 * 가로 이동 시 => 우 검사
	 * 세로 이동 시 => 하 검사
	 * 대각선 이동시 => 우,하, 우하 검사
	 * 
	 * 
	 * 파이프의 위치를 체크하기. boolean?
	 * 
	 * (1,1)-(1,2)의 파이프의 한쪽끝은 (N,N)으로 이동시키는 방법의 개수. => BFS
	 */
	
	static int N, arr[][], cnt;
	static int dr[] = {0,1,1};//우 하 우하
	static int dc[] = {1,0,1};
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //집의 크기
		
		arr = new int[N+1][N+1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs(1,2);
		System.out.println(cnt);
	}
	
	private static void bfs(int i, int j) {
		Queue<Pipe> queue = new LinkedList<>();
		queue.add(new Pipe(i,j,0));

		while(true) {
			if(queue.isEmpty()) break;
			
			Pipe pipe = queue.poll();
			int r = pipe.r;
			int c = pipe.c;
			int d = pipe.d;
			
			//System.out.println("r: "+r+" c: "+c);
			
			
			if(r==N&&c==N) {

//				for (int[] ia : arr) {
//					System.out.println(Arrays.toString(ia));
//				}

				cnt++;
				
			}
			
			/*
			 * * 가로 => 가로 or 대각선 
			 * 세로 => 세로 or 대각선
			 * 대각선 => 가로 or 세로 or 대각선
			 * 
			 * 가로 이동 시 => 우 검사
			 * 세로 이동 시 => 하 검사
			 * 대각선 이동시 => 우,하, 우하 검사
			 */

			//좌, 상, 좌상 검사해서 -1이 있으면
			//가로, 세로, 대각선인지 알수있다.
			for (int k = 0; k < 3; k++) {
				if(d==0&&k==1) continue;
				if(d==1&&k==0) continue;
				
				int nnr = r + dr[k];
				int nnc = c + dc[k];
				
				if(nnr<=N&&nnc<=N) {

					if(k==0||k==1) {
						if(arr[nnr][nnc]!=1) {

							queue.add(new Pipe(nnr,nnc,k));
						}
					}else if(k==2) {
						if(arr[nnr][nnc]!=1&&arr[nnr-1][nnc]!=1&&arr[nnr][nnc-1]!=1) {
							
							queue.add(new Pipe(nnr,nnc,k));
						}
					}
				}
			}

		}
	}
}

class Pipe{
	int r;
	int c;
	int d;
	public Pipe(int r, int c, int d) {
		super();
		this.r = r;
		this.c = c;
		this.d = d;
	}
	
}