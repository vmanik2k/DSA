package com.dsa.practice.demo.Scaler.Hashing;

public class SubArrayWithGivenSum {
    public int[] findSubarrayWithGivenSum(int[] A, int B) {
        int[] prefixSum = new int[A.length + 1];

        // Calculate the prefix sum array.
        prefixSum[0] = 0;
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i - 1];
        }

        int i = 0; // Left pointer
        int j = 0; // Right pointer

        while (i <= j && j < prefixSum.length) {
            int sum = prefixSum[j] - prefixSum[i];

            if (sum > B) {
                // If the sum is greater than the target, move the left pointer to the right.
                i++;
            } else if (sum < B) {
                // If the sum is less than the target, move the right pointer to the right.
                j++;
            } else {
                // If the sum is equal to the target, create and return the result subarray.
                int subarrayLength = j - i;
                int[] result = new int[subarrayLength];
                for (int index = 0; index < subarrayLength; index++) {
                    result[index] = A[index + i];
                }
                return result;
            }
        }

        // If no subarray with the given sum is found, return an array with a single element -1.
        return new int[]{-1};
    }

}
