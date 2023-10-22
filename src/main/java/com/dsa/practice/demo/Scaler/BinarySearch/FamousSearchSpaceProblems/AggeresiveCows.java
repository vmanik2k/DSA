package com.dsa.practice.demo.Scaler.BinarySearch.FamousSearchSpaceProblems;

import java.util.Arrays;

/*Farmer John has built a new long barn with N stalls. Given an array of integers A of size N where each element of the array represents the location of the stall and an integer B which represents the number of cows.

His cows don't like this barn layout and become aggressive towards each other once put into a stall. To prevent the cows from hurting each other, John wants to assign the cows to the stalls, such that the minimum distance between any two of them is as large as possible. What is the largest minimum distance?



Problem Constraints
2 <= N <= 100000
0 <= A[i] <= 109
2 <= B <= N



Input Format
The first argument given is the integer array A.
The second argument given is the integer B.



Output Format
Return the largest minimum distance possible among the cows.



Example Input
Input 1:

A = [1, 2, 3, 4, 5]
B = 3
Input 2:

A = [1, 2]
B = 2


Example Output
Output 1:

 2
Output 2:

 1


Example Explanation
Explanation 1:

 John can assign the stalls at location 1, 3 and 5 to the 3 cows respectively. So the minimum distance will be 2.
Explanation 2:

 The minimum distance will be 1.*/
public class AggeresiveCows {
    public int maxMinDistanceForCows(int[] A, int B) {
        Arrays.sort(A); // Sort the stalls in ascending order.
        int n = A.length - 1;
        int r = A[n] - A[0]; // Maximum possible distance between the first and last stall.
        int l = 1;
        int ans = -1;

        // Binary search to find the maximum minimum distance that can be maintained between cows.
        while (l <= r) {
            int mid = l + (r - l) / 2;

            // Check if it's possible to maintain a minimum distance of 'mid' between cows.
            if (isPossibleToMaintainDistance(A, mid, B)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public boolean isPossibleToMaintainDistance(int[] A, int mid, int cow) {
        int lastCowIndex = 0;
        int cowCount = 1;
        int cowDistance = A[0];

        for (int i = 1; i < A.length; i++) {
            if (A[i] - A[lastCowIndex] < mid) {
                cowDistance += A[i];
            } else {
                cowDistance = A[i];
                lastCowIndex = i;
                cowCount++;
            }
        }

        // Check if it's possible to place 'B' cows with a minimum distance of 'mid' between them.
        return cowCount >= cow;
    }

}
