package com.dsa.practice.demo.Scaler.InterviewProblems.Advanced.RIghtAngleTriangle;

import java.util.HashMap;
/*Given two arrays of integers A and B of size N each, where each pair (A[i], B[i]) for 0 <= i < N represents a unique point (x, y) in 2D Cartesian plane.

Find and return the number of unordered triplets (i, j, k) such that (A[i], B[i]), (A[j], B[j]) and (A[k], B[k]) form a right-angled triangle with the triangle having one side parallel to the x-axis and one side parallel to the y-axis.

NOTE: The answer may be large so return the answer modulo (109 + 7).



Problem Constraints
1 <= N <= 105

0 <= A[i], B[i] <= 109



Input Format
The first argument given is an integer array A.
The second argument given is the integer array B.



Output Format
Return the number of unordered triplets that form a right angled triangle modulo (109 + 7).



Example Input
Input 1:

 A = [1, 1, 2]
 B = [1, 2, 1]
Input 2:

 A = [1, 1, 2, 3, 3]
 B = [1, 2, 1, 2, 1]


Example Output
Output 1:

 1
Output 2:

 6


Example Explanation
Explanation 1:

 All three points make a right angled triangle. So return 1.
Explanation 2:

 6 triplets which make a right angled triangle are:    (1, 1), (1, 2), (2, 1)
                                                       (1, 1), (3, 1), (1, 2)
                                                       (1, 1), (3, 1), (3, 2)
                                                       (2, 1), (3, 1), (3, 2)
                                                       (1, 1), (1, 2), (3, 2)
                                                       (1, 2), (3, 1), (3, 2)
*/
public class CountRightAngleTriangles {
    public int solve(int[] A, int[] B) {
        // Initialize HashMaps to count the frequency of x and y coordinates.
        HashMap<Integer, Integer> xcord = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> ycord = new HashMap<Integer, Integer>();

        // Define a constant for modulo operation.
        int mod = (int)(Math.pow(10, 9) + 7);

        // Count the frequency of x and y coordinates.
        for (int i = 0; i < A.length; i++) {
            xcord.put(A[i], xcord.getOrDefault(A[i], 0) + 1);
            ycord.put(B[i], ycord.getOrDefault(B[i], 0) + 1);
        }

        long count = 0;

        // Iterate through the given points to calculate the count of right triangles.
        for (int i = 0; i < A.length; i++) {
            int xcount = (xcord.get(A[i]) - 1) % mod;
            int ycount = (ycord.get(B[i]) - 1) % mod;

            // Calculate the count of right triangles and add it to the total count.
            count += (1L * xcount * ycount) % mod;
        }

        // Return the final count of right triangles, modulo mod.
        return (int)(count % mod);
    }

}

