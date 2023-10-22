package com.dsa.practice.demo.Scaler.BinarySearch;

/*You are given a sorted array A of size N and a target value B.
Your task is to find the index (0-based indexing) of the target value in the array.

If the target value is present, return its index.
If the target value is not found, return the index of least element greater than equal to B.
Your solution should have a time complexity of O(log(N)).

1 <= N <= 106
1 <= A[i] <= 105
1 <= B <= 105

Input 1:

A = [1, 3, 5, 6]
B = 5

Output
2
Example Explanation
Explanation 1:

The target value is present at index 2.


*/
public class SortedInsertPosition {
        public int searchInsert(int[] A, int B) {
            int left = 0;        // Initialize left pointer to the start of the array
            int right = A.length - 1;  // Initialize right pointer to the end of the array

            while (left <= right) {  // Perform a binary search
                int mid = left + (right - left) / 2;  // Calculate the middle index

                if (A[mid] == B) {  // If the middle element is equal to the target value B
                    return mid;     // Return the index of the target value
                } else if (A[mid] > B) {  // If the middle element is greater than B
                    right = mid - 1;    // Update the right pointer to search in the left half
                } else {
                    left = mid + 1;     // If the middle element is less than B, update the left pointer to search in the right half
                }
            }
            // If B is not found, return the index of the least element greater than or equal to B
            return left;
        }

}
