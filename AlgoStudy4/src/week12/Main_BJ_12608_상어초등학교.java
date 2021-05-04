package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BJ_12608_상어초등학교 {
	
	static int N, map[][];
	static ArrayList<Integer>[] list;
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
		
		
		
		
		
		
	}
}
