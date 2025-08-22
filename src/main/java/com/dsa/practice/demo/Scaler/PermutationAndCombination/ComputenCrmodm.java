package com.dsa.practice.demo.Scaler.PermutationAndCombination;

class Solution {

  /*Given three integers A, B, and C, where A represents n, B represents r, and C represents m, find and return the value of nCr % m where nCr % m = (n!/((n-r)!*r!))% m.

x! means factorial of x i.e. x! = 1 * 2 * 3... * x.



Problem Constraints
1 <= A * B <= 106

1 <= B <= A

1 <= C <= 106



Input Format
The first argument given is integer A ( = n).
The second argument given is integer B ( = r).
The third argument given is integer C ( = m).


Output Format
Return the value of nCr % m.



Example Input
Input 1:

 A = 5
 B = 2
 C = 13
Input 2:

 A = 6
 B = 2
 C = 13


Example Output
Output 1:

 10
Output 2:

 2


Example Explanation
Explanation 1:

 The value of 5C2 % 11 is 10.
Explanation 2:

 The value of 6C2 % 13 is 2.
*/
  //This method used as per the constraints
    public int calculateNCrModM(int n, int r, int m) {
        // Create a 2D array to store intermediate results.
        // mat[i][j] will represent the value of nCr % m for n=i and r=j.
        long[][] mat = new long[n + 1][r + 1];

        // Initialize the first row and column of the matrix.
        for (int i = 0; i <= n; i++) {
            mat[i][0] = 1;
        }

        for (int i = 1; i <= r; i++) {
            mat[0][i] = 0;
        }

        // Calculate nCr % m for all values of n and r.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= r; j++) {
                // Use the formula: C(n, r) = C(n-1, r) + C(n-1, r-1) % m
                mat[i][j] = (mat[i - 1][j] + mat[i - 1][j - 1]) % m;
            }
        }

        // The final result is stored in mat[n][r], return it as an integer.
        return (int) (mat[n][r] % m);
    }
}
