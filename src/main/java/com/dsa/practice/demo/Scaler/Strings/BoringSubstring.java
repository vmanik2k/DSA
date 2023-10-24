package com.dsa.practice.demo.Scaler.Strings;

import java.util.Arrays;

/*You are given a string A of lowercase English alphabets. Rearrange the characters of the given string A such that there is no boring substring in A.

A boring substring has the following properties:

Its length is 2.
Both the characters are consecutive, for example - "ab", "cd", "dc", "zy" etc.(If the first character is C then the next character can be either (C+1) or (C-1)).
Return 1 if it is possible to rearrange the letters of A such that there are no boring substrings in A else, return 0.



Problem Constraints
1 <= |A| <= 105



Input Format
The only argument given is a string A.



Output Format
Return 1 if it is possible to rearrange the letters of A such that there are no boring substrings in A else, return 0.



Example Input
Input 1:

 A = "abcd"
Input 2:

 A = "aab"


Example Output
Output 1:

 1
Output 2:

 0


Example Explanation
Explanation 1:

 String A can be rearranged into "cadb" or "bdac"
Explanation 2:

 No arrangement of string A can make it free of boring substrings.*/
public class BoringSubstring {
    //You have to find a permutation from this string such that there is no boring string present in that permutation
    // Function to check if it's possible to rearrange the characters of the string
    // such that there are no boring substrings.
    public int solve(String A) {
        // Separate characters into even and odd groups.
        String even = "";
        String odd = "";

        for (int i = 0; i < A.length(); i++) {
            char ch = A.charAt(i);
            if ((ch - 'a') % 2 == 0) {
                even += ch;
            } else {
                odd += ch;
            }
        }

        // Convert the even and odd strings to character arrays and sort them.
        char[] evenArr = even.toCharArray();
        char[] oddArr = odd.toCharArray();
        Arrays.sort(evenArr);
        Arrays.sort(oddArr);

        // Get the first and last characters of the even and odd groups.
        int oddEnd = (int) oddArr[odd.length() - 1];
        int oddStart = (int) oddArr[0];
        int evenStart = (int) evenArr[0];
        int evenEnd = (int) evenArr[even.length() - 1];

        // Check for boring substrings.
        if (((oddEnd != evenStart + 1) && (oddEnd != evenStart - 1)) ||
                ((evenEnd != oddStart + 1) && (evenEnd != oddStart - 1)) ||
                ((oddEnd != evenEnd + 1) && (oddEnd != evenEnd - 1)) ||
                ((evenStart != oddStart + 1) && (evenStart != oddStart - 1))) {
            return 1; // Boring substring found, return 1.
        } else {
            return 0; // No boring substring found, return 0.
        }
    }
}
