package com.dsa.practice.demo.Scaler.Hashing;
import java.util.HashMap;

/*Misha likes finding all Subarrays of an Array. Now she gives you an array A of N elements and told you to find the number of subarrays of A, that have unique elements.

Since the number of subarrays could be large, return value % 109 +7.



Problem Constraints

1 <= N <= 105

1 <= A[i] <= 106



Input Format

The only argument given is an Array A, having N integers.



Output Format

Return the number of subarrays of A, that have unique elements.



Example Input

Input 1:

 A = [1, 1, 3]
Input 2:

 A = [2, 1, 2]


Example Output

Output 1:

 4
Output 1:

 5

 Example Explanation

Explanation 1:

 Subarrays of A that have unique elements only:
 [1], [1], [1, 3], [3]
Explanation 2:

 Subarrays of A that have unique elements only:
 [2], [1], [2, 1], [1, 2], [2]
 */
public class CountSubarrays {

    public int countSubarraysWithDistinctElements(int[] A) {
        HashMap<Integer, Integer> elementCountMap = new HashMap<>();
        int left = 0;
        int right = 0;
        int mod = (int) (Math.pow(10, 9) + 7);
        long count = 0;

        while (left <= right && right < A.length) {
            if (elementCountMap.containsKey(A[right])) {
                // Decrease the count of the element at the left pointer and move the left pointer to the right. If the element in the hashmap
                //that means we cant create a unique subarray if the element is present in it
                elementCountMap.put(A[left], elementCountMap.get(A[left]) - 1);
                if (elementCountMap.get(A[left]) == 0) {
                    elementCountMap.remove(A[left]);
                }
                left++;
            } else {
                // Increase the count of the element at the right pointer and calculate the subarrays.
                elementCountMap.put(A[right], elementCountMap.getOrDefault(A[right], 0) + 1);
                count = (count + (right - left + 1)) % mod;
                right++;
            }
        }
        return (int) (count % mod);
    }

}
