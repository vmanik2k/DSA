package com.dsa.practice.demo.Scaler.BinarySearch;
/*You are given three positive integers, A, B, and C.

Any positive integer is magical if divisible by either B or C.

Return the Ath smallest magical number. Since the answer may be very large, return modulo 109 + 7.

Note: Ensure to prevent integer overflow while calculating.



Problem Constraints
1 <= A <= 109

2 <= B, C <= 40000



Input Format
The first argument given is an integer A.

The second argument given is an integer B.

The third argument given is an integer C.



Output Format
Return the Ath smallest magical number. Since the answer may be very large, return modulo 109 + 7.



Example Input
Input 1:

 A = 1
 B = 2
 C = 3
Input 2:

 A = 4
 B = 2
 C = 3


Example Output
Output 1:

 2
Output 2:

 6


Example Explanation
Explanation 1:

 1st magical number is 2.
Explanation 2:

 First four magical numbers are 2, 3, 4, 6 so the 4th magical number is 6.*/
public class AthMagicalNumber {
    public int findAthMagicalNumber(int A, int B, int C) {
        int mod = (int) Math.pow(10, 9) + 7;
        long l = Math.min(B, C);  // The first possible magical number because a number can divide itself.
        long r = A * l;  // Range where we can find 'A' magical numbers starting from 'l'.
        long mul = (B * C);
        long lcm = mul / gcd(B, C); // Calculating the least common multiple (LCM) of B and C.

        long ans = 0;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            long cal = (mid / B + mid / C - mid / lcm);

            // If the count of magical numbers is equal to 'A', it can be our answer.
            if (cal == A) {
                ans = mid;
                r = mid - 1; // Search in the left half for a possible lower magical number.
            } else if (cal > A) {
                r = mid - 1; // Adjust the right pointer to search in the left subarray.
            } else {
                l = mid + 1; // Adjust the left pointer to search in the right subarray.
            }
        }
        return (int) (ans % mod);
    }

    int gcd(int B, int C) {
        if (B < C) {
            return gcd(C, B);
        }
        if (C == 0) {
            return B;
        }
        return gcd(C, B % C);
    }

}
