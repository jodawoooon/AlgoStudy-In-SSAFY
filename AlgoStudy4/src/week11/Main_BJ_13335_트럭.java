package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BJ_13335_트럭 {
	static int N, W, L, map[];
	static ArrayList<Integer> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //트럭의 개수
		W = Integer.parseInt(st.nextToken()); //다리 위에는 단지 w 대의 트럭만 동시에 올라갈 수 있다.
		L = Integer.parseInt(st.nextToken()); //다리의 최대하중
		
		//다리의 길이는 w 단위길이
		//각 트럭들은 하나의 단위시간(unit time)에 하나의 단위길이만큼만 이동할 수 있다고 가정한다
		
		list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		//트럭의 순서는 바꿀 수 없다
		
	
		int time = 0;
	
		
		map = new int[W];
		while(true) {
			time++;
			
			int curL = 0;
			int num = 0;
			for (int i = 0; i < W; i++) {
				if(map[i]!=0) {
					curL += map[i];
					num++;
				}
			}
			
			
			
			if(num>0) {
				if(map[0] != 0) {
					num--;
					curL -= map[0];
					map[0] = 0;
				}
				
				for (int i = 1; i < W; i++) {
					map[i-1] = map[i];
					map[i] = 0;
				}
			}
			
			if(map[W-1]==0) {
				if(list.size()>0) {
					int weight = list.get(0);
					
					if(curL + weight <= L) {
						map[W-1] = weight;
						num++;
						list.remove(0);
					}
				}
			}
			
			if(list.size()==0 && num==0) break;
		}
		
		
		System.out.println(time);
		
	}
}
