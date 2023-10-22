package com.dsa.practice.demo.Scaler.BinarySearch;



/**/
/*Given a sorted array of integers A (0-indexed) of size N, find the left most and the right most index of a given integer B in the array A.

Return an array of size 2, such that
          First element = Left most index of B in A
          Second element = Right most index of B in A.
If B is not found in A, return [-1, -1].

Note : Your algorithm's runtime complexity must be in the order of O(log n).


Problem Constraints
1 <= N <= 106
1 <= A[i], B <= 109


Input Format
The first argument given is the integer array A.
The second argument given is the integer B.


Output Format
Return the left most and right most index (0-based) of B in A as a 2-element array. If B is not found in A, return [-1, -1].


Example Input
Input 1:

 A = [5, 7, 7, 8, 8, 10]
 B = 8
Input 2:

 A = [5, 17, 100, 111]
 B = 3


Example Output
Output 1:

 [3, 4]
Output 2:

 [-1, -1]


Example Explanation
Explanation 1:

 The first occurrence of 8 in A is at index 3.
 The last occurrence of 8 in A is at index 4.
 ans = [3, 4]
Explanation 2:

 There is no occurrence of 3 in the array.
*/
public class SearchForARange {
        public int[] searchRange(final int[] A, int B) {
            int left = findLeftMost(A, B);
            int right = findRightMost(A, B);
            int[] range = {left, right}; // Create an array to store the left and right indices.
            return range;
        }

        // Function to find the leftmost occurrence of B in the sorted array A.
        int findLeftMost(int[] A, int B) {
            int l = 0;
            int r = A.length - 1;
            int ans = -1;

            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (A[mid] == B) {
                    ans = mid;
                    r = mid - 1; // Move to the left subarray to find the leftmost occurrence.
                } else if (A[mid] > B) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return ans;
        }

        // Function to find the rightmost occurrence of B in the sorted array A.
        int findRightMost(int[] A, int B) {
            int l = 0;
            int r = A.length - 1;
            int ans = -1;

            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (A[mid] == B) {
                    ans = mid;
                    l = mid + 1; // Move to the right subarray to find the rightmost occurrence.
                } else if (A[mid] > B) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return ans;
        }
}

