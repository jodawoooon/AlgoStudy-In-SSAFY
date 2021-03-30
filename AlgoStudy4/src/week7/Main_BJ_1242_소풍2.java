package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1242_소풍2 {
	static int N,K,M,cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //사람 수
		//1번부터 N번까지 시계방향으로 원형으로 앉았다
		
		K = Integer.parseInt(st.nextToken());  
		//KIN 게임 : 1번부터 K까지 센다. K를 말하면 퇴장장한다.
		//다음 자리부터 다시 1부터 센다.
		
		M = Integer.parseInt(st.nextToken()); 
		//동호는 M번 학생이다. 동호가 몇번으로 퇴장당하는지 구해라.
		
		
		int tgt = 0;
		
		//큐 쓰니까 메모리 초과ㅏ
		// 규칙 찾기
		
		// 5 2 3
		// 1 2 3 4 5
		// 3 4 5 1
		// 5 1 3
		// 3 5
		// 3
		//k를 외칠사람을 맨 앞으로 옮기기
		
		
		//ans = 5
		
		for (int i = N; i < args.length; i++) {
			
		}
		
	}
}
