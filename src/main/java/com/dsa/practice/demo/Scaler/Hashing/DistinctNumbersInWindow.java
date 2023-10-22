package com.dsa.practice.demo.Scaler.Hashing;

import java.util.HashMap;
/*You are given an array of N integers, A1, A2 ,..., AN and an integer B. Return the of count of distinct numbers in all windows of size B.

Formally, return an array of size N-B+1 where i'th element in this array contains number of distinct elements in sequence Ai, Ai+1 ,..., Ai+B-1.

NOTE: if B > N, return an empty array.



Problem Constraints
1 <= N <= 106

1 <= A[i] <= 109


Input Format
First argument is an integer array A
Second argument is an integer B.



Output Format
Return an integer array.



Example Input
Input 1:

 A = [1, 2, 1, 3, 4, 3]
 B = 3
Input 2:

 A = [1, 1, 2, 2]
 B = 1


Example Output
Output 1:

 [2, 3, 3, 2]
Output 2:

 [1, 1, 1, 1]


Example Explanation
Explanation 1:

 A=[1, 2, 1, 3, 4, 3] and B = 3
 All windows of size B are
 [1, 2, 1]
 [2, 1, 3]
 [1, 3, 4]
 [3, 4, 3]
 So, we return an array [2, 3, 3, 2].
Explanation 2:

 Window size is 1, so the output array is [1, 1, 1, 1].*/
public class DistinctNumbersInWindow {

    public int[] distinctNumbersInWindow(int[] A, int B) {
        // Initialize the array to store the results.
        int[] distinctCount = new int[A.length - B + 1];

        // Create a HashMap to keep track of the frequency of elements in the current window.
        HashMap<Integer, Integer> elementFrequencyMap = new HashMap<>();

        // Calculate the distinct numbers in the initial window of size 'B'.
        for (int i = 0; i < B; i++) {
            int currentElement = A[i];
            elementFrequencyMap.put(currentElement, elementFrequencyMap.getOrDefault(currentElement, 0) + 1);
        }

        distinctCount[0] = elementFrequencyMap.size(); // Store the distinct count for the initial window.

        int leftPointer = 0;
        int resultIndex = 1;

        // Slide the window to the right and maintain the count of distinct elements.
        for (int rightPointer = B; rightPointer < A.length; rightPointer++) {
            int newElement = A[rightPointer];
            int oldElement = A[leftPointer];

            // Update the frequency of the new element.
            elementFrequencyMap.put(newElement, elementFrequencyMap.getOrDefault(newElement, 0) + 1);

            // Decrease the frequency of the element going out of the window.
            elementFrequencyMap.put(oldElement, elementFrequencyMap.get(oldElement) - 1);

            // Remove the element from the HashMap if its frequency becomes 0.
            if (elementFrequencyMap.get(oldElement) == 0) {
                elementFrequencyMap.remove(oldElement);
            }

            // Calculate the count of distinct elements in the current window.
            distinctCount[resultIndex] = elementFrequencyMap.size();
            resultIndex++;
            leftPointer++;
        }

        return distinctCount;
    }

}
