package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_2564_경비원 {
	/*
	 * 블록의 크기와 상점의 개수 및 위치 그리고 동근이의 위치가 주어질 때
	 *  동근이의 위치와 각 상점 사이의 최단 거리의 합을 구하는 프로그램을 작성하시오.
	 */
	
	static int[][] arr; //상점위치
	static int W,H,N, ans;
	static int tgtDir, tgtDis; //동근위치
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());//가로 = W
		H = Integer.parseInt(st.nextToken());//세로 = H
		N = Integer.parseInt(br.readLine()); //상점의 개수

		arr = new int[N][2];
		
		
		//상점 위치 arr[][], 동근 위치 tgtW, tgtH 저장
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			arr[n][0] = Integer.parseInt(st.nextToken());
			arr[n][1] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		tgtDir = Integer.parseInt(st.nextToken());
		tgtDis = Integer.parseInt(st.nextToken());
		
		
//		for (int[] a : arr) {
//			System.out.println(Arrays.toString(a));
//		}
//		System.out.println(tgtW+" "+tgtH);
		
		for (int n = 0; n < N; n++) {
			int min = Integer.MAX_VALUE;
			
			if(tgtDir+arr[n][0]==3) { 
				//tgtDir==1, 상점Dir==2 이거나 tgtDir==2, 상점Dir==1인 경우
				//동근이가 북 또는 남 위치일때 상점과 마주보고 있다면
				int ans1 = tgtDis+H+arr[n][1]; //왼쪽으로 접근하는 방법
				int ans2 = (W-tgtDis)+H+(W-arr[n][1]);
				min = Math.min(ans1, ans2);
				
			}else if(tgtDir+arr[n][0]==7) {
				//tgtDir==3, 상점Dir==4 이거나 tgtDir==4, 상점Dir==3인 경우
				//동근이가 동 또는 서 위치일때 상점과 마주보고 있다면
				int ans1 = tgtDis+W+arr[n][1];
				int ans2 = (H-tgtDis)+W+(H-arr[n][1]);
				min = Math.min(ans1, ans2);
				
			}else if(tgtDir==arr[n][0]) {
				//동근이와 상점이 같은 방향에 있는 경우
				min = Math.abs(tgtDis-arr[n][1]);
				
			}else {
				//동근이와 상점이 서로 붙어있는 변에 위치할 경우
				//arr[n][0]은 방향, arr[n][1]은 distance
				//동근이의 위치 별 계산
				switch (tgtDir) {
				case 1:
					if(arr[n][0]==3) {
						min = tgtDis+arr[n][1];
					}else if(arr[n][0]==4) {
						min= (W-tgtDis)+arr[n][1];
					}
					break;
				case 2:
					if(arr[n][0]==3) {
						min = tgtDis+H-arr[n][1];
					}else if(arr[n][0]==4) {
						min= (W-tgtDis)+(H-arr[n][1]);
					}
					break;
				case 3:
					if(arr[n][0]==1) {
						min = tgtDis+arr[n][1];
					}else if(arr[n][0]==2) {
						min = (H-tgtDis)+arr[n][1];
					}
					break;
				case 4:
					if(arr[n][0]==1) {
						min = tgtDis+(W-arr[n][1]);
					}else if(arr[n][0]==2) {
						min = (H-tgtDis)+(W-arr[n][1]);
					}
					break;
				}

			}
			ans += min;
		}
		
		System.out.println(ans);
	}
}
