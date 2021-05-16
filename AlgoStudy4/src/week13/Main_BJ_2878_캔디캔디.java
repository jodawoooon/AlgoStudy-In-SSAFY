package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_2878_캔디캔디 {
	
	static int M,N,want[];
	static long sum,ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); //사탕 개수
		N = Integer.parseInt(st.nextToken()); //나누어 줄 친구 수
		
		//원하는 사탕 개수 정보. -> 못받으면 분노
		//분노 수치 = 못 받는 사탕 개수의 제곱
		
		want = new int[N];
		
		for (int i = 0; i < N; i++) {
			want[i] = Integer.parseInt(br.readLine()); //각 친구들이 받고 싶어하는 사탕의 수
			sum += want[i];
		}
		
		//사탕을 적절히 나누어 주어 친구들의 분노의 합을 최소화
		
		sum -= M;
		Arrays.sort(want);
		
		
		for (int i = 0; i < want.length; i++) {
			long w = Math.min(want[i], sum / N--);
			ans += w*w;
			sum -= w;
		}
		
		System.out.println(ans%((long)Math.pow(2, 64)));
	}
}
