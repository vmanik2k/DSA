package com.dsa.practice.demo.Scaler.Hashing;
import java.util.HashSet;


/*Given an unsorted integer array A of size N.

Find the length of the longest set of consecutive elements from array A.



Problem Constraints
1 <= N <= 106

-106 <= A[i] <= 106



Input Format
First argument is an integer array A of size N.



Output Format
Return an integer denoting the length of the longest set of consecutive elements from the array A.



Example Input
Input 1:

A = [100, 4, 200, 1, 3, 2]
Input 2:

A = [2, 1]


Example Output
Output 1:

 4
Output 2:

 2


Example Explanation
Explanation 1:

 The set of consecutive elements will be [1, 2, 3, 4].
Explanation 2:

 The set of consecutive elements will be [1, 2].
*/
public class LongestConsecutiveSequence {


    public int longestConsecutiveSequenceLength(final int[] A) {
        HashSet<Integer> elementSet = new HashSet<>();

        // Step 1: Add all elements to a HashSet for efficient lookup.
        for (int i = 0; i < A.length; i++) {
            elementSet.add(A[i]);
        }

        int longestSequenceLength = 0;

        // Step 2: Iterate through the array to find the longest consecutive sequence.
        for (int i = 0; i < A.length; i++) {
            int currentElement = A[i];

            // If A[i]-1 is not in the set, it means A[i] starts a new consecutive sequence.
            if (!elementSet.contains(currentElement - 1)) {
                int sequenceLength = 1;
                int nextElement = currentElement + 1;

                // Check for consecutive elements.
                while (elementSet.contains(nextElement)) {
                    sequenceLength++;
                    nextElement++;
                }

                // Update the longest sequence length found so far.
                longestSequenceLength = Math.max(longestSequenceLength, sequenceLength);
            }
        }

        return longestSequenceLength;
    }

}
