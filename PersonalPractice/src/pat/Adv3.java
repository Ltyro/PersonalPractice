package pat;

import java.util.Scanner;

public class Adv3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N, M, C1, C2;
		String[] line1 = sc.nextLine().trim().split(" ");
		N = Integer.parseInt(line1[0]);
		M = Integer.parseInt(line1[1]);
		C1 = Integer.parseInt(line1[2]);
		C2 = Integer.parseInt(line1[3]);
		
		String[] line2 = sc.nextLine().trim().split(" ");
		int[] teams = new int[N];
		for(int i = 0; i < N; i ++)
			teams[i] = Integer.parseInt(line2[i]);
		String[][] roadstr = new String[M][3];
		for(int i = 0; i < M; i ++) {
			roadstr[i] = sc.nextLine().trim().split(" ");
		}
		int[][] roads = new int[M][3];
		for(int i = 0; i < M; i ++) 
			for(int j = 0; j < 3; j ++)
				roads[i][j] = Integer.parseInt(roadstr[i][j]);
		
		// number of shortest paths
		int l = 0, d, shortestl = 0, path_num = 0;
		for(int i = 0; i < M; i ++) {
			if(roads[i][0] != C1) 
				continue;
			
			l += roads[i][2];
			d = roads[i][1];
			while(d != C2) {
				for(int j = 0; j < M; j ++) {
					
				}
			}
			
		}
		
		System.out.println();
	}

}
