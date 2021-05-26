package LeetcodePrograms;

import java.util.ArrayList;
import java.util.Collections;

public class minDistance {
	public static int minTotalDistance2(int[][] grid) {
		int m=grid.length;
		int n=grid[0].length;

		ArrayList<Integer> cols = new ArrayList<Integer>();
		ArrayList<Integer> rows = new ArrayList<Integer>();
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				if(grid[i][j]==1){
					rows.add(i);
					cols.add(j);
				}
			}
		}

		int sum=0;

		for(Integer i: rows){

			sum += Math.abs(i - rows.get(rows.size()/2));
		}

		Collections.sort(cols);

		for(Integer i: cols){
			sum+= Math.abs(i-cols.get(cols.size()/2));
		}

		return sum;
	}

	public static void main(String[] args) {
	int[][]grid ={{}};
		System.out.println(minTotalDistance2(grid));

	}

}
