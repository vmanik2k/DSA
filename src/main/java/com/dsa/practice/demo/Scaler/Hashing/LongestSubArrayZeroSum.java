package com.dsa.practice.demo.Scaler.Hashing;
import java.util.HashMap;


/*Given an array A of N integers.
Find the length of the longest subarray in the array which sums to zero.

Note :
while we A[i] multiple times, it may cross the range of integer, so wisely select data type for any operations.



Problem Constraints
1 <= N <= 105
-109 <= A[i] <= 109


Input Format
Single argument which is an integer array A.


Output Format
Return an integer.


Example Input
Input 1:

 A = [1, -2, 1, 2]
Input 2:

 A = [3, 2, -1]


Example Output
Output 1:

3
Output 2:

0


Example Explanation
Explanation 1:

 [1, -2, 1] is the largest subarray which sums up to 0.
Explanation 2:

 No subarray sums up to 0.*/
public class LongestSubArrayZeroSum {


    public int longestSubarrayWithZeroSum(int[] A) {
        // Create a HashMap to store the cumulative sum and its corresponding index.
        HashMap<Long, Integer> sumIndexMap = new HashMap<>();

        long currentSum = 0; // Initialize the current cumulative sum.
        int maxLength = 0; // Initialize the maximum length of the subarray.

        for (int i = 0; i < A.length; i++) {
            currentSum += A[i]; // Calculate the current cumulative sum.

            // If the current cumulative sum is equal to zero, update the maximum length.
            if (currentSum == 0) {
                maxLength = Math.max(maxLength, i + 1);
            }

            // If the current cumulative sum is not in the HashMap, add it with its index.
            if (!sumIndexMap.containsKey(currentSum)) {
                sumIndexMap.put(currentSum, i);
            } else {
                // If the cumulative sum has been encountered before, update the maximum length.
                // That means sum has been previously encountered so the sum from that point till now is zero
                maxLength = Math.max(maxLength, i - sumIndexMap.get(currentSum));
            }
        }

        return maxLength;
    }

}
