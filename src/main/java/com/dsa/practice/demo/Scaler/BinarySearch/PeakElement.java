package com.dsa.practice.demo.Scaler.BinarySearch;
/*Problem Description
Given an array of integers A, find and return the peak element in it.
An array element is considered a peak if it is not smaller than its neighbors. For corner elements, we need to consider only one neighbor.

NOTE:

It is guaranteed that the array contains only a single peak element.
Users are expected to solve this in O(log(N)) time. The array may contain duplicate elements.


Problem Constraints
1 <= |A| <= 100000

1 <= A[i] <= 109



Input Format
The only argument given is the integer array A.



Output Format
Return the peak element.



Example Input
Input 1:

A = [1, 2, 3, 4, 5]
Input 2:

A = [5, 17, 100, 11]


Example Output
Output 1:

 5
Output 2:

 100


Example Explanation
Explanation 1:

 5 is the peak.*/


public class PeakElement {
        public int findPeak(int[] A) {
            int n = A.length;

            // Edge case: If the array has only one element, it's the peak.
            if (n == 1) {
                return A[0];
            }

            // Check if the first element is a peak.
            if (A[0] > A[1]) {
                return A[0];
            }

            int left = 0;
            int right = n - 1;

            // Check if the last element is a peak.
            if (A[right] > A[right - 1]) {
                return A[right];
            }

            while (left <= right) {
                int mid = left + (right - left) / 2;

                // Check if the middle element is a peak.
                if (A[mid] >= A[mid + 1] && A[mid] >= A[mid - 1]) {
                    return A[mid];
                }
                // If the middle element is smaller than its right neighbor, move right.
                else if (A[mid] < A[mid + 1] && A[mid] > A[mid - 1]) {
                    left = mid + 1;
                }
                // Otherwise, move left.
                else {
                    right = mid - 1;
                }
            }

            // If no peak is found, return -1 or any other appropriate value to indicate no peak.
            return -1;
        }
}

