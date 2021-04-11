package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_7579_앱 {
	/*
	 * 앱의 비활성화 문제를 스마트하게 해결하기 위한 프로그램
	 * m ; 앱 Ai이 사용하는 메모리
	 * c ; 앱을 비활성화한 후에 다시 실행하고자 할 경우, 추가적으로 들어가는 비용(시간 등)을 수치화 한 것을
	 * 사용자가 새로운 앱 B를 실행하고자 하여, 추가로 M 바이트의 메모리가 필요하다고 하자. 
	 * 
	 *  필요한 메모리 M 바이트를 확보하기 위한 앱 비활성화의 최소의 비용
	 */

	static int N, M;
	static int m[], c[], dp[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //N 앱의 갯수
		M = Integer.parseInt(st.nextToken()); //M 필요한 메모리
		
		m = new int[N+1];
		c = new int[N+1];
		//배열 선언
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			m[i] = Integer.parseInt(st.nextToken());
		}
		//앱 A1~AN이 사용중인 바이트 수 m배열 
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			c[i] = Integer.parseInt(st.nextToken());
		}
		//앱 A1~AN을 비활성화 했을 경우의 비용 c배열
		

		//앱 비활성화의 최소의 비용을 계산하라 => dp
		
		//M : 확보해야 하는 메모리의 크기 => 배낭의 크기
		//m[] ; 메모리 사용량(가치)
		//c[] ; 재 실행 비용(비용)
		
		
		dp = new int[N+1][100*100+1];
		//dp 값 : 메모리 사용량
		//dp[i][j] ; i는 앱의 번호, j는 비용
		//dp[N][j] ; 1번앱~N번앱까지 모두 고려하였을때 j비용 이하로 얻을 수 있는 최대 메모리
		//비용은 N과 C의 최댓값인 100*100을 최댓값으로 두었다.
		
		
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= 100*100; j++) {
				if(c[i]>j) {
					
					//비용보다 i번째 앱을 지우는 비용이 크므로 i번째 앱을 지울 수 없다.
					//dp[i][j]는 i-1번째 앱까지 선택하는 경우에서의 최대값 => dp[i-1][j]
					dp[i][j] = dp[i-1][j];
					
				}else {
					
					/*i번째 앱을 지울 수 있다.
					 * 
					i번째 앱을 지운다면 : dp[i-1][j]
					i번째 앱을 안지운다면 : m[i] + dp[i-1][j-c[i]];
						i번째 앱의 메모리가치(m[i]) + 이전 행의 j-c[i] 비용으로 얻을 수 있는 최대가치값 (dp[i-1][j-c[i])
					
					둘 중 큰 값을 dp[i][j]에 저장
					 * 
					 */
					dp[i][j] = Math.max(dp[i-1][j], m[i] + dp[i-1][j-c[i]]);
				}

			}
		} 
		
		for (int j = 1; j <= 100*100; j++) {
			//dp배열에는 j 비용으로 얻을 수 있는 최대 메모리바이트가 저장되어있다.
			//따라서 j비용을 최솟값부터 증가시키면서, dp[N][j]가 M바이트를 확보했다면
			//그 값이 M 바이트를 확보하기 위한 앱 비활성화의 최소의 비용이므로
			//값을 출력하고 종료한다.
			if(dp[N][j]>=M) {
				System.out.println(j);
				break;
			}
		}
		
	}
}
