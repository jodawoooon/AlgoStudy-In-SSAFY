package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_14938_서강그라운드 {
	//예은이가 얻을 수 있는 최대 아이템 개수를 출력한다.
	static int n, m, r, arr[];
	static int INF = Integer.MAX_VALUE;
	static ArrayList<Node> adjList[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		n = Integer.parseInt(st.nextToken()); //지역의 개수
		m = Integer.parseInt(st.nextToken()); //수색범위
		r = Integer.parseInt(st.nextToken()); //길의 개수
		
		
		adjList = new ArrayList[n+1]; //인접리스트
		for (int i = 1; i <= n; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		//다익스트라
		arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {

			arr[i] = Integer.parseInt(st.nextToken()); //각 구역에 있는 아이템의 수
		}
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			adjList[x].add(new Node(y,l)); //양방향연결
			adjList[y].add(new Node(x,l));
		}
		
		
		
		//PQ 선언
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.weight-o2.weight;
			}
			
		});
		
		int[] weight = new int[n+1];
		
		int ans = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			//가중치를 저장할 배열
			
			for (int k = 1; k <= n; k++) {
				weight[k] = INF;
			}
			
			weight[i] = 0;
			pq.add(new Node(i,0));
			

			while(!pq.isEmpty()) {
				Node n = pq.poll();
				int from = n.v;
				int fWeight = n.weight;
				
				if(fWeight > weight[from]) continue;
				
			
				
				for (Node node : adjList[from]) {
					int to = node.v;
					int tWeight = node.weight + fWeight;
					

					if(tWeight>m) continue;
					
					weight[to] = Math.min(tWeight, weight[to]);
					
					pq.add(new Node(to, tWeight));
				}
			}
			
			int tmp = 0;
			for (int k = 1; k <= n; k++) {
				//System.out.println(weight[k]);
				
				if(weight[k]!=INF) {
					tmp += arr[k];
				}
				
			}
			
			ans = Math.max(tmp, ans);
			
		}
		
		
		
		System.out.println(ans);
		
		
	}
	
	static class Node{
		int v, weight;

		public Node(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}
		
	}
}
