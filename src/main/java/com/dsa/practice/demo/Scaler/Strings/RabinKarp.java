package com.dsa.practice.demo.Scaler.Strings;
/*Given two string A and B of length N and M respectively consisting of lowercase letters. Find the number of occurrences of B in A.


Problem Constraints
1 <= M <= N <= 105



Input Format
Two argument A and B are strings


Output Format
Return an integer denoting the number of occurrences of B in A


Example Input
Input 1:
A = "acbac"
B = "ac"
Input 2:
A = "aaaa"
B = "aa"


Example Output
Output 1:
2
Output 2:
3


Example Explanation
For Input 1:
The string "ac" occurs twice in "acbac".
For Input 2:
The string "aa" occurs thrice in "aaaa".*/
public class RabinKarp {

    public int countOccurrences(String A, String B) {
        // Initialize variables
        long patternHash = 0; // Hash for string B
        int patternLength = B.length() - 1; // Length of string B

        // Calculate the initial hash for pattern B
        for (int i = 0; i <= patternLength; i++) {
            long temp = pow(26, patternLength - i);
            int no = (B.charAt(i) - 'a');
            temp *= no;
            patternHash += temp;
        }

        // Initialize a hash to compare with the pattern
        long hashToCompare = 0;

        // Calculate the initial hash for the first substring of A
        for (int i = 0; i <= patternLength; i++) {
            long temp = pow(26, patternLength - i);
            int no = (A.charAt(i) - 'a');
            temp *= no;
            hashToCompare += temp;
        }

        int count = 0;

        // Check if the initial substring of A matches B
        if (hashToCompare == patternHash) {
            count++;
        }

        int k = 0;

        // Iterate through the remaining substrings of A
        for (int j = patternLength + 1; j < A.length(); j++) {
            long temp = pow(26, patternLength);
            int no = (A.charAt(k) - 'a');
            temp *= no;

            // Update the rolling hash for A
            hashToCompare = (hashToCompare - temp) * 26 + (A.charAt(j) - 'a');

            // Check if the current substring matches B
            if (hashToCompare == patternHash) {
                count++;
            }
            k++;
        }

        return count;
    }

    // Helper function to calculate the power of a number
    int pow(int base, int p) {
        if (p == 0) {
            return 1;
        }
        int a = pow(base, p / 2);
        if (p % 2 == 0) {
            return a * a;
        } else {
            return a * a * base;
        }
    }

    public static void main(String[] args) {
        int ans = new RabinKarp().countOccurrences("abcdefhabc","abc");
        System.out.println(ans);
    }
}
