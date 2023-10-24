package com.dsa.practice.demo.Scaler.PrimeNumbers;
/*Given an array of integers A, find and return the count of divisors of each element of the array.

NOTE: The order of the resultant array should be the same as the input array.



Problem Constraints
1 <= length of the array <= 100000
1 <= A[i] <= 106



Input Format
The only argument given is the integer array A.



Output Format
Return the count of divisors of each element of the array in the form of an array.



Example Input
Input 1:

 A = [2, 3, 4, 5]
Input 2:

 A = [8, 9, 10]


Example Output
Output 1:

 [2, 2, 3, 2]
Output 1:

 [4, 3, 4]


Example Explanation
Explanation 1:

 The number of divisors of 2 : [1, 2], 3 : [1, 3], 4 : [1, 2, 4], 5 : [1, 5]
 So the count will be [2, 2, 3, 2].
Explanation 2:

 The number of divisors of 8 : [1, 2, 4, 8], 9 : [1, 3, 9], 10 : [1, 2, 5, 10]
 So the count will be [4, 3, 4].*/
public class CountOfDivisors {
    public int[] countDivisors(int[] A) {
        int res[] = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            int count = 0;

            // Iterate from 1 to the square root of the current element
            for (int j = 1; j * j <= A[i]; j++) {
                if (A[i] % j == 0) {
                    // If j is a divisor of A[i], increment the count
                    if (A[i] / j == j) {
                        count++;
                    } else {
                        // If both j and A[i]/j are divisors, increment the count by 2
                        count += 2;
                    }
                }
            }
            res[i] = count; // Store the count of divisors for the current element in the result array
        }
        return res;
    }

}
