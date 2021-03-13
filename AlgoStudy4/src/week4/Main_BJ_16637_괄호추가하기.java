package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16637_괄호추가하기 {
	
	//연산자 우선순위는 모두 동일하기 때문에, 수식을 계산할 때는 왼쪽에서부터 순서대로 계산해야 한다.
	//괄호안에는 연산자가 하나만
	//수식에 괄호를 적절하게 추가해 만들 수 있는 식의 결과의 최댓값을 구하자
	
	//중첩괄호가 안되므로 경우의수가많지않음. 앞 또는 뒤에 씌울수있음
	/*
	 * 3+8x7-9x2
	 * 3+(8x7)-(9x2)
	 * 3+(8x7)-9x2
	 * 3+8x(7-9)x2
	 * 3+8x7-(9x2)
	 * 
	 * 총 5개
	 * 
	1. 순서대로 계산하는경우-> 왼쪽부터 차례대로 계산하기
	2. 왼쪽숫자와  오른쪽에서 이미 계산이 이루어진 결과의 숫자 연산

	
	 */
	
	static int N, ans;
	static ArrayList<Integer> numList;
	static ArrayList<Character> operList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		numList = new ArrayList<>();
		operList = new ArrayList<>();


		ans = Integer.MIN_VALUE;
		N = Integer.parseInt(br.readLine());
		String line = br.readLine();
		for (int n = 0; n < N; n++) {
			char tmp = line.charAt(n);
			if(tmp-'0'<=9&&tmp-'0'>=0) {
				numList.add(tmp-'0'); //숫자 저장
			}else {
				operList.add(tmp); //연산자 저장
			}
		}
	
//		System.out.println(Arrays.toString(numList.toArray()));
//		System.out.println(Arrays.toString(operList.toArray()));
		
		
		dfs(0,numList.get(0));
		
		System.out.println(ans);
		
	}
	private static void dfs(int i, int result) {
		
		if(i>=N/2) {
			ans = Math.max(ans, result);
			//최댓값 저장
			return;
		}
		
		//1. 순서대로 계산하는경우-> 왼쪽부터 차례대로 계산하기
		//System.out.println(result+" "+numList.get(i+1)+" "+operList.get(i));
		int tmpResult1 = cal(result, numList.get(i+1), operList.get(i));

		dfs(i+1, tmpResult1);
		
		
		//2. 왼쪽숫자와  오른쪽에서 이미 계산이 이루어진 결과의 숫자 연산
		if(i==N/2-1) return; //연산자의 갯수는 N/2개이고 마지막 연산자는( N/2-1번째)는 다음에 이미 계산이 이루어질 결과가 없음
	
		int nextResult = cal(numList.get(i+1), numList.get(i+2), operList.get(i+1));
		
		int tmpResult2 =  cal(result, nextResult, operList.get(i));
		System.out.println(tmpResult2+" "+result+" "+nextResult+" "+numList.get(i+1)+numList.get(i+2)+operList.get(i+1));
		dfs(i+2,tmpResult2);


		
		
		
		
	}
	private static int cal(int result, Integer nextResult, Character oper) {
		if(oper=='+') {
			return result+nextResult;
		}else if(oper=='-') {
			return result-nextResult;
		}else //*
			return result*nextResult;

	}
	
}
