package com.dsa.practice.demo.Scaler.BinarySearch;

/*Given a sorted array of integers A of size N and an integer B,
where array A is rotated at some pivot unknown beforehand.

For example, the array [0, 1, 2, 4, 5, 6, 7] might become [4, 5, 6, 7, 0, 1, 2].

Your task is to search for the target value B in the array. If found, return its index; otherwise, return -1.

You can assume that no duplicates exist in the array.

NOTE: You are expected to solve this problem with a time complexity of O(log(N)).


Problem Constraints
1 <= N <= 1000000
1 <= A[i] <= 109
All elements in A are Distinct.


Input Format
The First argument given is the integer array A.
The Second argument given is the integer B.


Output Format
Return index of B in array A, otherwise return -1


Example Input
Input 1:

A = [4, 5, 6, 7, 0, 1, 2, 3]
B = 4
Input 2:

A : [ 9, 10, 3, 5, 6, 8 ]
B : 5


Example Output
Output 1:

 0
Output 2:

 3


Example Explanation
Explanation 1:

Target 4 is found at index 0 in A.
Explanation 2:

Target 5 is found at index 3 in A.*/
public class SearchInaRotatedSortedArray {
    public int search(int[] nums, int target) {
        // Check if the array has only one element.
        if (nums.length == 1) {
            return (nums[0] == target) ? 0 : -1; // Return 0 if the single element is the target, otherwise -1.
        }

        int pivot = -1;
        int l = 0;
        int r = nums.length - 1;

        // Find the pivot index where the array is rotated.
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < nums[0]) {
                pivot = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        if (pivot == -1) {
            // If there's no pivot, perform a binary search on the entire array.
            return binarySearch(nums, 0, nums.length - 1, target);
        } else if (nums[0] <= target && nums[pivot - 1] >= target) {
            // If the target is in the left part of the rotated array, binary search the left subarray.
            return binarySearch(nums, 0, pivot - 1, target);
        } else {
            // If the target is in the right part of the rotated array, binary search the right subarray.
            return binarySearch(nums, pivot, nums.length - 1, target);
        }
    }
    /**
     * Binary search to find a target element in a sorted array.
     *
     * @param A      The sorted array to search in.
     * @param l      The left index of the search range.
     * @param r      The right index of the search range.
     * @param target The target element to find.
     * @return The index of the target element in the array or -1 if not found.
     */
    public int binarySearch(int[] A, int l, int r, int target) {
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (A[mid] == target) {
                return mid; // Target found, return its index.
            } else if (A[mid] > target) {
                r = mid - 1; // Adjust the right pointer to search in the left subarray.
            } else {
                l = mid + 1; // Adjust the left pointer to search in the right subarray.
            }
        }

        return -1; // Target not found, return -1.
    }

}
