package com.dsa.practice.demo.Scaler.Hashing;
import java.util.HashMap;

/*Given an array of integers A and an integer B.
Find the total number of subarrays having sum equals to B.


Problem Constraints
 1 <= length of the array <= 50000
-1000 <= A[i] <= 1000


Input Format
The first argument given is the integer array A.
The second argument given is integer B.


Output Format
Return the total number of subarrays having sum equals to B.


Example Input
Input 1:
A = [1, 0, 1]
B = 1
Input 2:
A = [0, 0, 0]
B = 0


Example Output
Output 1:
4
Output 2:
6


Example Explanation
Explanation 1:
[1], [1, 0], [0, 1] and [1] are four subarrays having sum 1.
Explanation 1:
All the possible subarrays having sum 0.
*/
public class SubarraySumEqualsK {
    public int countSubarraysWithSumK(int[] A, int B) {
        int n = A.length;
        int prefixSum = 0;
        int count = 0;

        // HashMap to store the count of each prefix sum encountered.
        HashMap<Integer, Integer> prefixSumCountMap = new HashMap<>();

        // Initialize the prefix sum as 0 with a count of 1 (an empty subarray).
        prefixSumCountMap.put(0, 1);

        for (int i = 0; i < n; i++) {
            prefixSum += A[i]; // Calculate the current prefix sum.

            int sumToFind = prefixSum - B; // The sum to find to make a subarray sum equal to B.

            if (prefixSumCountMap.containsKey(sumToFind)) {
                count += prefixSumCountMap.get(sumToFind);
            }

            // Update the count for the current prefix sum.
            if (prefixSumCountMap.containsKey(prefixSum)) {
                prefixSumCountMap.put(prefixSum, prefixSumCountMap.get(prefixSum) + 1);
            } else {
                prefixSumCountMap.put(prefixSum, 1);
            }
        }

        return count;
    }

}
