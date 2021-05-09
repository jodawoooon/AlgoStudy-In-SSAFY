package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_10836_G4_여왕벌 {
	/*
	 * 크기가 M*M 벌집, 각 칸에 애벌레 1마리 첫날 아침 모든 애벌레들의 크기는 1이고, 이러한 과정을 N일 동안 반복 커지는 정도 ->
	 * +0, +1, +2 중 하나
	 * 
	 * 1. 0행, 0열은 자신이 자라는 정도를 스스로 결정 입력으로 주어질 것 (왼쪽 제일 아래 칸에서 시작하여 위쪽으로 가면서 읽고, 제일
	 * 위쪽 칸에 도착하면 오른쪽으로 가면서 행의 끝까지 읽었다) 2. 나머지 애벌레들은 자신의 왼쪽(L), 왼쪽 위(D), 위쪽(U)의
	 * 애벌레들이 다 자란 다음, 그 날 가장 많이 자란 애벌레가 자란 만큼 자신도 자란다.
	 */

	static int M, N, map[][], input[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 격자칸 크기
		N = Integer.parseInt(st.nextToken()); // 날짜 수

		map = new int[M][M]; // 벌집 map

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = 1;
			}
		}
		// 첫날은 모두 1

		for (int i = 0; i < N; i++) {

			int zero = 0;
			int one = 0;
			int two = 0;

			st = new StringTokenizer(br.readLine()); // 애벌레들이 자라는 정도 입력받음

			zero = Integer.parseInt(st.nextToken());
			one = Integer.parseInt(st.nextToken());
			two = Integer.parseInt(st.nextToken());

			for (int r = M - 1; r >= 0; r--) {
				if (zero != 0) {
					// map[r][0] += 0;
					zero--;
				} else if (one != 0) {
					map[r][0] += 1;
					one--;
				} else {
					map[r][0] += 2;
					two--;
				}
			}
			// 0열

			for (int c = 1; c < M; c++) {
				if (zero != 0) {
					// map[0][c] += 0;
					zero--;
				} else if (one != 0) {
					map[0][c] += 1;
					one--;
				} else {
					map[0][c] += 2;
					two--;
				}
			}
			// 0행

		}

		for (int c = 1; c < M; c++) {
			for (int r = 1; r < M; r++) {
				int max = Math.max(map[r - 1][c], Math.max(map[r][c - 1], map[r - 1][c - 1]));
				map[r][c] = max;
			}
		} // 나머지

		print();

	}

	private static void print() {
		StringBuilder sb = new StringBuilder();

		for (int r = 0; r < M; r++) {
			for (int c = 0; c < M; c++) {
				sb.append(map[r][c] + " ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
