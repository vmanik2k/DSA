package com.dsa.practice.demo.Scaler.BinarySearch;

/*Given a matrix of integers A of size N x M and an integer B. Write an efficient algorithm that searches for integer B in matrix A.

This matrix A has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than or equal to the last integer of the previous row.
Return 1 if B is present in A, else return 0.

NOTE: Rows are numbered from top to bottom, and columns are from left to right.



Problem Constraints
1 <= N, M <= 1000
1 <= A[i][j], B <= 106



Input Format
The first argument given is the integer matrix A.
The second argument given is the integer B.



Output Format
Return 1 if B is present in A else, return 0.



Example Input
Input 1:

A = [
      [1,   3,  5,  7]
      [10, 11, 16, 20]
      [23, 30, 34, 50]
    ]
B = 3
Input 2:

A = [
      [5, 17, 100, 111]
      [119, 120, 127, 131]
    ]
B = 3


Example Output
Output 1:

1
Output 2:

0


Example Explanation
Explanation 1:

 3 is present in the matrix at A[0][1] position so return 1.
Explanation 2:

 3 is not present in the matrix so return 0.*/
public class MatrixSearchCode {
        public int searchMatrix(int[][] matrix, int target) {
            int left = 0;
            int rows = matrix.length;
            int cols = matrix[0].length;
            int right = (rows * cols) - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int row_x = mid / cols;
                int col_x = mid % cols;
                int element = matrix[row_x][col_x];

                if (element == target) {
                    return 1; // Target found, return 1 to indicate success.
                } else if (element < target) {
                    left = mid + 1; // Adjust the left pointer to search in the right subarray.
                } else {
                    right = mid - 1; // Adjust the right pointer to search in the left subarray.
                }
            }

            return 0; // Target not found, return 0 to indicate failure.
        }


}
