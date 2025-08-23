package com.dsa.practice.demo.Scaler.BFS;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class RottenOranges {

    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        Deque<int[]> queue = new LinkedList<>();
        int orangesCount = 0;
        for(int i =0;i<n;i++){
            for(int j =0;j<m;j++){
                if(grid[i][j]==2){
                    queue.offerLast(new int[]{i,j});
                    orangesCount++;
                }else if(grid[i][j]==1){
                    orangesCount++;
                }
            }
        }
        if(orangesCount==0){
            return 0;
        }
        queue.offerLast(null);
        int minutes=0;
        int rowx[] = {1,0,0,-1};
        int coly[] = {0,1,-1,0};
        while(queue.size()>1){
            if(queue.peekFirst()==null){
                queue.pollFirst();
                queue.offerLast(null);
                minutes++;
                continue;
            }

            int[] temp = queue.pollFirst();
            int row = temp[0];
            int col = temp[1];
            visited[row][col] = true;
            for(int idx =0;idx<rowx.length;idx++){
                int tempRow =row+rowx[idx];
                int tempCol = col+coly[idx];
                if(tempRow>=0 && tempRow<n && tempCol>=0 && tempCol<m && !visited[tempRow][tempCol] && grid[tempRow][tempCol]==1){
                    queue.offerLast(new int[]{tempRow,tempCol});
                    grid[tempRow][tempCol] = 2;
                }
            }

        }

        for(int i =0;i<n;i++){
            for(int j =0;j<m;j++){
                if(grid[i][j]==1){
                    return -1;
                }
            }
        }
        return minutes;
    }

    public static void main(String[] args) {
        System.out.println(new RottenOranges().orangesRotting(new int[][]{{2,2},{1,1},{0,0},{2,0}}));
    }
}
