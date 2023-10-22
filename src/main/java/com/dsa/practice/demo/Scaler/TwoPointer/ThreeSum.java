package com.dsa.practice.demo.Scaler.TwoPointer;

import java.util.Arrays;
/*Given an array A of N integers, find three integers in A such that the sum is closest to a given number B. Return the sum of those three integers.

Assume that there will only be one solution.



Problem Constraints
-108 <= B <= 108
1 <= N <= 104
-108 <= A[i] <= 108


Input Format
First argument is an integer array A of size N.

Second argument is an integer B denoting the sum you need to get close to.



Output Format
Return a single integer denoting the sum of three integers which is closest to B.



Example Input
Input 1:

A = [-1, 2, 1, -4]
B = 1
Input 2:


A = [1, 2, 3]
B = 6


Example Output
Output 1:

2
Output 2:

6


Example Explanation
Explanation 1:

 The sum that is closest to the target is 2. (-1 + 2 + 1 = 2)
Explanation 2:

 Take all elements to get exactly 6.*/
public class ThreeSum {

    public int findClosestThreeSum(int[] A, int B) {
        int n = A.length;

        // Sort the array to make it easier to work with.
        Arrays.sort(A);

        int closestSum = A[0] + A[1] + A[2]; // Initialize with the sum of the first three elements.
        int minDifference = Math.abs(B - closestSum); // Initialize with the absolute difference.

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int currentSum = A[i] + A[left] + A[right];
                int currentDifference = Math.abs(B - currentSum);

                if (currentDifference == 0) {
                    return currentSum; // If an exact match is found, return the sum.
                }

                if (currentDifference < minDifference) {
                    // If the current sum is closer to the target, update the closest sum and difference.
                    minDifference = currentDifference;
                    closestSum = currentSum;
                }

                if (currentSum < B) {
                    left++; // Move the left pointer to increase the sum.Because right pointer is already at the max value
                } else {
                    right--; // Move the right pointer to decrease the sum.
                }
            }
        }

        return closestSum;
    }


    public static void main(String[] args) {
        System.out.println(new ThreeSum().findClosestThreeSum(new int[]{2,1,-9,-7,-8,2,-8,2,3,-8},-1));
        System.out.println("============================");
        System.out.println(new ThreeSum().findClosestThreeSum(new int[]{5,-2,-1,-10,10},5));
    }
}
//[-10,-2,-1,5,10]