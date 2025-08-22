import java.util.ArrayList;
/*
Given an even number A ( greater than 2 ), return two prime numbers whose sum will be equal to the given number.

If there is more than one solution possible, return the lexicographically smaller solution.

If [a, b] is one solution with a <= b, and [c,d] is another solution with c <= d, then 
[a, b] < [c, d], If a < c OR a==c AND b < d. 
NOTE: A solution will always exist. Read Goldbach's conjecture.



Problem Constraints
4 <= A <= 2*107



Input Format
First and only argument of input is an even number A.



Output Format
Return a integer array of size 2 containing primes whose sum will be equal to given number.



Example Input
 4


Example Output
 [2, 2]


Example Explanation
 There is only 1 solution for A = 4.
*/
public class PrimeSum {

public int[] primesum(int A) {
    ArrayList<Integer> primeNumbers = new ArrayList<>();
    boolean[] sieve = new boolean[A + 1];

    // Initialize the sieve array, assuming all numbers are prime initially.
    for (int i = 2; i <= A; i++) {
        sieve[i] = true;
    }

    // Sieve of Eratosthenes to find prime numbers.
    for (int i = 2; i <= A; i++) {
        if (sieve[i] == false) {
            continue;
        }
        for (int j = 2 * i; j <= A; j += i) {
            sieve[j] = false;
        }
        primeNumbers.add(i);
    }

    // Iterate through the list of prime numbers to find a pair that sums up to A.
    for (int i = 0; i < primeNumbers.size(); i++) {
        int currentPrime = primeNumbers.get(i);
        int difference = A - currentPrime;

        if (primeNumbers.contains(difference)) {
            // If a matching prime is found, return the pair as an array.
            int[] result = new int[2];
            result[0] = currentPrime;
            result[1] = difference;
            return result;
        }
    }

    // If no such pair is found, return an array with a single element 0.
    return new int[]{0};
}

}
