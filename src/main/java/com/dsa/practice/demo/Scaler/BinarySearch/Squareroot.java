package com.dsa.practice.demo.Scaler.BinarySearch;

/*Given an integer A. Compute and return the square root of A.
If A is not a perfect square, return floor(sqrt(A)).

The value of A can cross the range of Integer.

NOTE:
   Do not use the sqrt function from the standard library.
   Users are expected to solve this in O(log(A)) time.


Problem Constraints
0 <= A <= 1010


Input Format
The first and only argument given is the integer A.


Output Format
Return floor(sqrt(A))


Example Input
Input 1:

 11
Input 2:

 9


Example Output
Output 1:

 3
Output 2:

 3


Example Explanation
Explanation 1:
When A = 11 , square root of A = 3.316. It is not a perfect square so we return the floor which is 3.*/
public class Squareroot {
    /**
     * Finds the square root of an integer A using binary search.
     *
     * @param A The input integer for which the square root is to be found.
     * @return The square root of the input integer A.
     */
    public int findSquareRoot(int A) {
        long left = 1;
        long right = A;
        long result = 0;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (mid * mid == A) {
                return (int) mid; // If the square of mid equals A, mid is the square root.
            } else if (mid * mid > A) {
                right = mid - 1; // Adjust the right pointer to search in the left subarray.
            } else {
                result = mid; // Update the result to the current mid value.
                left = mid + 1; // Adjust the left pointer to search in the right subarray.
            }
        }

        return (int) result; // Return the integer part of the result as the square root.
    }

}
