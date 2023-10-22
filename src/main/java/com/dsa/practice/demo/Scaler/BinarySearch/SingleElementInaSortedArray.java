package com.dsa.practice.demo.Scaler.BinarySearch;
/*Given a sorted array of integers A where every element appears twice except for one element which appears once, find and return this single element that appears only once.

Elements which are appearing twice are adjacent to each other.

NOTE: Users are expected to solve this in O(log(N)) time.



Problem Constraints
1 <= |A| <= 100000

1 <= A[i] <= 10^9



Input Format
The only argument given is the integer array A.



Output Format
Return the single element that appears only once.



Example Input
Input 1:

A = [1, 1, 7]
Input 2:

A = [2, 3, 3]


Example Output
Output 1:

 7
Output 2:

 2


Example Explanation
Explanation 1:

 7 appears once
Explanation 2:

 2 appears once*/
public class SingleElementInaSortedArray {
        public int findSingleElement(int[] A) {
            // Handle the edge case when the array has only one element.
            if (A.length == 1) {
                return A[0];
            }

            int l = 0;
            int r = A.length - 1;

            while (l <= r) {
                int mid = l + (r - l) / 2;

                // Check if the current element is not equal to its adjacent elements.
                if ((mid - 1) >= 0 && (mid + 1) < A.length) {
                    if (A[mid] != A[mid - 1] && A[mid] != A[mid + 1]) {
                        return A[mid];
                    }
                } else if ((mid + 1) >= A.length) {
                    if (A[mid] != A[mid - 1]) {
                        return A[mid];
                    }
                } else if ((mid - 1) < 0) {
                    if (A[mid] != A[mid + 1]) {
                        return A[mid];
                    }
                }

                // Check if the single element is to the left or right based on its parity.
                if (mid % 2 == 0) {
                    if (A[mid + 1] == A[mid]) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                } else {
                    if (A[mid - 1] == A[mid]) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
            }

            // If no single element is found, return -1 or any other appropriate value.
            return -1;
        }


}
