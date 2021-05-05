package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BJ_12608_상어초등학교 {

	static int N, map[][];
	static ArrayList<Integer>[] list;
	static int dx[]= {-1,1,0,0};
	static int dy[]= {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //맵 크기
		
		map = new int[N][N];
		list = new ArrayList[N*N+1];
		
		for (int i = 1; i <= N*N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N*N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); //학생 번호
			for (int j = 0; j < 4; j++) {
				list[num].add(Integer.parseInt(st.nextToken())); //학생이 좋아하는 사람 번호 추가
			}
		}
		//list 셋팅
		
		/*	
		1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
		2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
		3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
		*/
		
		for (int idx = 1; idx <= N*N; idx++) {
			int[]
			
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					if(map[x][y]!=0) continue;
					
					for (int d = 0; d < 4; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						
						if(nx<0||ny<0||nx>=N||ny>=N) continue;
						
						
					}
				}
			}
			
			
		}
		
		
		
		
	}
}
