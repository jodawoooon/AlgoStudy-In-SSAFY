package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_17281_야구 {
	/*
	 * 야구
	 * N이닝
	 * 1이닝 3아웃 종료 => 공수교대
	 * 9번까지 치고 3아웃아니면 다시 1번ㄴ부터
	 * 타순은 이닝변경되도 순서 유지
	 * 
	 *	안타: 타자와 모든 주자가 한 루씩 진루한다.    1
		2루타: 타자와 모든 주자가 두 루씩 진루한다.   2
		3루타: 타자와 모든 주자가 세 루씩 진루한다.   3
		홈런: 타자와 모든 주자가 홈까지 진루한다.     4
		아웃: 모든 주자는 진루하지 못하고, 공격 팀에 아웃이 하나 증가한다.     0
		
		1번은 4번타자
		가장 많은 득점을 하는 타순 찾고, 그 때의 득점 구하기
		각 이닝에는 아웃을 기록하는 타자가 적어도 한 명 존재한다.
		
		ex)
		2
		4 0 0 0 1 1 1 0 0
		0 0 0 0 0 0 0 0 0
		
		5,6,7이 1,2,3번 타자 나가고 1번이 4번타자 하면 4점
		
		1) 타순 정하기 : 순열
			=> 경기진행
		2) 최대점수 구하기
		
	 */
	static int N, arr[][], tgt[], ans;
	static boolean isSelected[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N= Integer.parseInt(br.readLine());
		
		arr = new int[N+1][10]; //1~N이닝, 1~9
		isSelected = new boolean[10]; //1~9
		tgt = new int[10];
		for (int n = 1; n <= N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int m = 1; m <= 9; m++) {
				arr[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		//4번타자는 무조건 1번
		isSelected[1] = true;

		perm(1);
		System.out.println(ans);
				
	}
	private static void perm(int cnt) {
		if(cnt==10) {
			
			baseball();
			return;
		}
		if(cnt==4) {
			tgt[4] = 1;
			perm(cnt+1);
			return;
		}
		for(int i = 1; i<=9;i++) {
			
			if(isSelected[i]) continue;
			tgt[cnt] = i;
			isSelected[i] = true;
			perm(cnt+1);
			isSelected[i] = false;
			
		}
	}
	private static void baseball() {
		int num = 1;
		int score = 0;
		
		for (int n = 1; n <= N; n++) {
			boolean roo[] = {false,false,false};
			int out = 0;
			
			while(true) {
				if(arr[n][tgt[num]]==0) {
					out++;
				}
				else if(arr[n][tgt[num]]==1) {
					if(roo[2]==true) {
						score++;
						roo[2]=false;
					}
					for (int i = 1; i >=0; i--) {
						if(roo[i]==true) {
							roo[i+1]=true;
							roo[i]=false;
						}
					}
					roo[0] = true;
				}
				else if(arr[n][tgt[num]]==2) {
					
					for (int i = 1; i <=2; i++) {
						if(roo[i]==true) {
							score++;
							roo[i]=false;
						}
					}
					if(roo[0]) {
						roo[2] =true;
						roo[0]=false;
					}
					roo[1]=true;
				}else if(arr[n][tgt[num]]==3) {
					for (int i = 0; i <=2; i++) {
						if(roo[i]==true) {
							score++;
							roo[i]=false;
						}
					}
					roo[2]=true;
				}else if(arr[n][tgt[num]]==4) {
					for (int i = 0; i <=2; i++) {
						if(roo[i]==true) {
							score++;
							roo[i]=false;
						}
					}
					score++;
				}
				num = (num+1)%10;
				if(num==0) {num++;}
				if(out==3)break;
			}

		}
		ans= Math.max(ans, score);
	}
}
