package com.dsa.practice.demo.Scaler.PrimeNumbers;
import java.util.ArrayList;
/*Given an integer A. Find the list of all prime numbers in the range [1, A].


Problem Constraints
1 <= A <= 106



Input Format
Only argument A is an integer.



Output Format
Return a sorted array of prime number in the range [1, A].



Example Input
Input 1:
A = 7
Input 2:
A = 12


Example Output
Output 1:
[2, 3, 5, 7]
Output 2:
[2, 3, 5, 7, 11]


Example Explanation
For Input 1:
The prime numbers from 1 to 7 are 2, 3, 5 and 7.
For Input 2:
The prime numbers from 1 to 12 are 2, 3, 5, 7 and 11.
*/
public class FindPrimes {
    public ArrayList<Integer> findPrimes(int A) {
        boolean sieve[] = new boolean[A + 1];

        // Initialize the sieve array, assuming all numbers from 2 to A are prime
        for (int index = 2; index <= A; index++) {
            sieve[index] = true;
        }

        // Mark the multiples of each prime number as non-prime
        for (int i = 2; i <= A; i++) {
            if (sieve[i]) {
                for (int j = i + i; j <= A; j += i) {
                    sieve[j] = false;
                }
            }
        }

        // Collect the prime numbers into the 'res' ArrayList
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int j = 2; j <= A; j++) {
            if (sieve[j]) {
                res.add(j);
            }
        }

        return res;
    }

}
