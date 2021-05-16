package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_BJ_19238_스타트택시3 {
	static int N, M, energy, startX, startY, distance[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};

	static Info[] infos;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); //목표량
		energy = Integer.parseInt(st.nextToken()); //초기연료
		
		
		map = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		//운전을 시작하는 칸
		//M개의 줄에 걸쳐 각 승객의 출발지의 행과 열 번호, 그리고 목적지의 행과 열 번호가 주어진다.
		
		infos = new Info[M+2]; //승객정보 저장할 배열
		
		for (int i = 2; i <=M+1; i++) {
			st = new StringTokenizer(br.readLine());
			int x1= Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			map[x1][y1] = i; //지도에 승객의 출발위치를 승객의 idx로 표시한다.
			infos[i] = new Info(x1,y1,x2,y2); //해당 idx 승객의 출발지,도착지 정보 기억하기

		}
		
		
		
		for (int i = 0; i < M; i++) {

			ArrayList<Point> list = getList(); 
			//startX,startY에서 승객들의 거리를 계산하여 list에 저장하는 method

			
			if(list.size()==0) { //태울수있는 승객이 없으면 종료
				System.out.println(-1); 
				return;
			}
			
			Collections.sort(list, new Comparator<Point>() {
				//최단거리 순으로 정렬한다
				@Override
				public int compare(Point o1, Point o2) {
					if(o1.dis==o2.dis) { //그런승객이 여러명이면
						if(o1.x==o2.x) { //행번호 -> 열번호 순으로 정렬한다.
							return o1.y-o2.y; 
						}else {
							return o1.x-o2.x;
						}
					}else {
						return o1.dis-o2.dis;
					}
				}

			}); //승객 정렬
			
			Point p = list.get(0); //승객을 선택함
			map[p.x][p.y] = 0; //맵에서 제거
			
			
			energy-=p.dis;
			startX = p.x;
			startY = p.y;
			int dsX = infos[p.idx].dsX;
			int dsY = infos[p.idx].dsY;
			int moveDis = go(dsX, dsY);
			if(moveDis==-1) { //목적지로 성공적으로 이동시키지 못했다
				System.out.println(-1); //업무를 끝낸다.
				return;
			}
			
			//성공적으로 승객을 이동시켰다면
			energy -= moveDis; //연료 일단 감소시킴
			startX = dsX; //현재 위치 갱신
			startY = dsY;
			energy += moveDis*2; //소모한 연료량의 2배가 충전된다
		}
		
		System.out.println(energy);
		
	}
	


	private static ArrayList<Point> getList() {
		//현 위치에서 승객들을 찾아서 list에 저장한다.
		boolean visited[][] = new boolean[N+1][N+1];
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(new Node(startX,startY,0));
		visited[startX][startY] = true;
		
		ArrayList<Point> list = new ArrayList<>();
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			int x = n.x;
			int y = n.y;
			int dis = n.dis;
			
			if(energy-dis<0) break; //연료없으면 종료
			if(map[x][y]>1) { //이동중에 만난 승객 (승객의 idx는 2부터)
				list.add(new Point(map[x][y], x,y,dis)); //리스트에 저장
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(!isIn(nx,ny)) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny]==1) continue;
				
				visited[nx][ny] = true;
				queue.add(new Node(nx,ny,dis+1));
			}
			
		}
		
		return list;
	}



	private static int go(int dsX, int dsY) {
		//승객을 이동시킨다.
		
		boolean visited[][] = new boolean[N+1][N+1];
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(new Node(startX,startY,0));
		visited[startX][startY] = true;
		
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			
			int x = n.x;
			int y = n.y;
			int dis = n.dis;
			if(energy-dis<0) { //연료가 부족하면 -1리턴
				return -1;
			}
			
			if(x==dsX&&y==dsY) { //목적지에 도착했다면 distance값 리턴
				return dis;
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				
				if(!isIn(nx,ny)) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny]==1) continue;
				visited[nx][ny] = true;
				queue.add(new Node(nx,ny,dis+1));
			}
		}
		
		return -1; //도착하지 못했다.
	}



	private static boolean isIn(int nx, int ny) {
		if(nx<=0||nx>N||ny<=0||ny>N) return false;
		return true;
	}

	static class Node{
		int x,y,dis;

		public Node(int x, int y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}

		
	}
	
	static class Info {
		int stX,stY,dsX,dsY; //출발지, 도착지 좌표

		public Info(int stX, int stY, int dsX, int dsY) {
			super();
			this.stX = stX;
			this.stY = stY;
			this.dsX = dsX;
			this.dsY = dsY;

		}
		
	}
	
	static class Point {
		int idx, x,y,dis; //승객의 idx와 (x,y)좌표, dis값

		public Point(int idx, int x, int y, int dis) {
			super();
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.dis = dis;
		}

	}

	
}
