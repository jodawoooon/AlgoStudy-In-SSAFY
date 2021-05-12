package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14499_G5_주사위굴리기 {
	/*
	1. 윗면이 1이고 동쪽이 3인 방향으로 놓여짐. 모든 면이 0
	
	2. 주사위를 굴렸을 때, 
	0임 : 주사위바닥 dice[6] -> 칸
	0이 아님 : 칸 -> 주사위 바닥 dice[6], 칸에 쓰여 있는 수는 0
	
	주사위가 이동했을 때 마다 상단에 쓰여 있는 값 => dice[1]
	*/
	static int N, M, x, y, K, map[][], dice[];
	
	static int dx[] = {0,0,0,-1,1}; // ,동,서,북,남
	static int dy[] = {0,1,-1,0,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //세로크기
		M = Integer.parseInt(st.nextToken()); //가로크기
		
		map = new int[N][M]; //지도
		dice = new int[7];
		
		x = Integer.parseInt(st.nextToken()); //좌표x
		y = Integer.parseInt(st.nextToken()); //좌표y
		K = Integer.parseInt(st.nextToken()); //명령의개수
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//맵 셋팅
		
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int k = Integer.parseInt(st.nextToken()); //명령
			
			//1. 다음 위치
			int nx = x + dx[k];
			int ny = y + dy[k];
			
			if(!isIn(nx,ny)) continue;
			// 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시해야 하며, 출력도 하면 안 된다. -> continue
			
			
			//2. 주사위 굴리기
			int tmp[] = new int[7];
			for (int j = 1; j < 7; j++) {
				tmp[j] = dice[j];
			}
			//임시 주사위 복사
			
			//동,서,남,북에 따라 굴러가는 형태가 다름
			
			switch(k) {
			case 1: //동쪽 
				dice[1] = tmp[4];
				dice[4] = tmp[6];
				dice[3] = tmp[1];
				dice[6] = tmp[3];
				break;
			case 2: //서
				dice[1] = tmp[3];
				dice[4] = tmp[1];
				dice[3] = tmp[6];
				dice[6] = tmp[4];
				break;
			case 3: //북
				dice[1] = tmp[5];
				dice[2] = tmp[1];
				dice[5] = tmp[6];
				dice[6] = tmp[2];
				break;
			case 4: //남
				dice[1] = tmp[2];
				dice[2] = tmp[6];
				dice[5] = tmp[1];
				dice[6] = tmp[5];
				break;
			}

			
			if(map[nx][ny]==0) { // 이동한 칸에 쓰여 있는 수가 0이면
				map[nx][ny] = dice[6]; //주사위의 바닥면에 쓰여 있는 수가 칸에 복사
			}else if(map[nx][ny]!=0) { //0이 아닌 경우에는
				dice[6] = map[nx][ny]; //칸에 쓰여 있는 수가 주사위의 바닥면으로 복사
				map[nx][ny] = 0; //칸에 쓰여 있는 수는 0
			}
			
			System.out.println(dice[1]);
			//주사위가 이동했을 때 마다 상단에 쓰여 있는 값을 출력
			
			x = nx; //현좌표 변경
			y = ny;
		}
	}
	
	private static boolean isIn(int nx, int ny) {
		if(nx<0||ny<0||nx>=N||ny>=M) return false;
		return true;
	}
}
