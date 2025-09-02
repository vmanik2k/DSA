package com.dsa.practice.demo.Scaler.twodMatrix;
//Leet Code 48
public class RotateImage {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int m =n;
        for(int i =0;i<n;i++){
            for(int j =i;j<m;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        int row =n-1;
        int col = m-1;
        for(int i =0;i<n;i++){
            for(int j =0;j<m/2;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][col-j];
                matrix[i][col-j] = temp;
            }
        }



    }
}

// 1 2 3
// 4 5 6
// 7 8 9

// 1 4 7
// 2 5 8
// 3 6 9

// 7 4 1
// 8 5 2
// 9 6 3

// 5 1  9 11
// 2 4  8 10
//13 3  6 7
//15 14 12 16

//5  2  13 15
//1  4. 3  14
//9  8  6. 12
//11 10 7  16

//15. 13  2  5
//14  3   4. 1
//12. 6.  8. 9
//16. 7.  10 11
