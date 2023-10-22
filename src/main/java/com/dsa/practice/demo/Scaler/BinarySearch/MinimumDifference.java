package com.dsa.practice.demo.Scaler.BinarySearch;

import java.util.Arrays;
/*You are given a 2-D matrix C of size A × B.
You need to build a new 1-D array of size A by taking one element from each row of the 2-D matrix in such a way that the cost of the newly built array is minimized.

The cost of an array is the minimum possible value of the absolute difference between any two adjacent elements of the array.

So if the newly built array is X, the element picked from row 1 will become X[1], element picked from row 2 will become X[2], and so on.

Determine the minimum cost of the newly built array.



Problem Constraints
2 <= A <= 1000
2 <= B <= 1000
1 <= C[i][j] <= 106



Input Format
The first argument is an integer A denoting number of rows in the 2-D array.
The second argument is an integer B denoting number of columns in the 2-D array.
The third argument is a 2-D array C of size A x B.



Output Format
Return an integer denoting the minimum cost of the newly build array.



Example Input
Input 1:

 A = 2
 B = 2
 C = [ [8, 4]
      [6, 8] ]
Input 2:

 A = 3
 B = 2
 C = [ [7, 3]
       [2, 1]
       [4, 9] ]


Example Output
Output 1:

 0
Output 2:

 1


Example Explanation
Explanation 1:

 Newly build array : [8, 8]. The minimum cost will be 0 since the absolute difference will be 0(8-8).
Explanation 2:

 Newly build array : [3, 2, 4]. The minimum cost will be 1 since the minimum absolute difference will be 1(3 - 2).
*/
public class MinimumDifference {
        public int solve(int A, int B, int[][] C) {
            for(int i =0;i<A;i++){
                Arrays.sort(C[i]);
            }
            int ans = Integer.MAX_VALUE;
            for(int i =0;i<A-1;i++){
                for(int j=0;j<B;j++){
                    // Search smallest element in the
                    // next row which is greater than
                    // or equal to the current element
                    int temp = binarySearch(0,B-1,C[i+1],C[i][j]);

                    //Element which is smaller than or equal and calculate the difference
                    ans = Math.min(ans,Math.abs(C[i+1][temp]-C[i][j]));

                    // largest element which is smaller than the current
                    // element in the next row must be just before
                    // smallest element which is greater than or equal
                    // to the current element because rows are sorted.
                    if(temp-1>0){
                        ans = Math.min(ans,Math.abs(C[i+1][temp-1]-C[i][j]));

                    }
                }
            }
            return ans;
        }
        public int binarySearch(int l,int r,int A[],int target){
            while(l<=r){
                int mid= l+(r-l)/2;

                if(A[mid]==target){
                    return mid;
                }else if(A[mid]<target){
                    l=mid+1;
                }else{
                    r=mid-1;
                }
            }
            return l==A.length?l-1:l;
        }

}
