package com.dsa.practice.demo.Scaler.twodMatrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length-1;
        int m = matrix[0].length-1;
        int i =0;
        int j =0;
        List<Integer> ans = new ArrayList<>();
        while(i<=n && j<=m){
            for(int p =j;p<=m;p++){
                ans.add(matrix[i][p]);
            }
            i++;
            if(i>n || j>m){
                break;
            }
            for(int p =i;p<=n;p++){
                ans.add(matrix[p][m]);
            }

            m--;
            if(i>n || j>m){
                break;
            }

            for(int p =m;p>=j;p--){
                ans.add(matrix[n][p]);
            }
            n--;
            if(i>n || j>m){
                break;
            }
            for(int p =n;p>=i;p--){
                ans.add(matrix[p][j]);
            }
            j++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new SpiralMatrix().spiralOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
    }
}