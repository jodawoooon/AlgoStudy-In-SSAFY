package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_14719_빗물 {
	
	static int H, W, map[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		
		st = new StringTokenizer(br.readLine());
		for (int w = 0; w < W; w++) {
			//블록이 쌓인 높이를 의미하는 0이상 H이하의 정수
			int h = Integer.parseInt(st.nextToken());
			for (int i = 0; i < h; i++) {
				map[H-i-1][w] = 1; //블록 위치 마킹
			}
		}
		
		//2차원 세계에서는 한 칸의 용량은 1이다. 고이는 빗물의 총량을 출력하여라.
		//빗물이 전혀 고이지 않을 경우 0을 출력하여라.
		
		
		for (int i = 0; i < args.length; i++) {
			
		}
		for (int[] ia : map) {
			System.out.println(Arrays.toString(ia));
		}
	}
}
