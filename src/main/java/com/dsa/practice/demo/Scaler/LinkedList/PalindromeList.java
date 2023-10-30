package com.dsa.practice.demo.Scaler.LinkedList;
/*Given a singly linked list A, determine if it's a palindrome. Return 1 or 0, denoting if it's a palindrome or not, respectively.



Problem Constraints
1 <= |A| <= 105



Input Format
The first and the only argument of input contains a pointer to the head of the given linked list.



Output Format
Return 0, if the linked list is not a palindrome.

Return 1, if the linked list is a palindrome.



Example Input
Input 1:

A = [1, 2, 2, 1]
Input 2:

A = [1, 3, 2]


Example Output
Output 1:

 1
Output 2:

 0


Example Explanation
Explanation 1:

 The first linked list is a palindrome as [1, 2, 2, 1] is equal to its reversed form.
Explanation 2:

 The second linked list is not a palindrom as [1, 3, 2] is not equal to [2, 3, 1].*/
public class PalindromeList {
    public int isPalindrome(ListNode A) {
        // Initialize 'temp' to traverse the list and calculate its length.
        ListNode temp = A;
        int length = 0;
        while (temp != null) {
            temp = temp.next;
            length++;
        }

        // Calculate the position of the middle node.
        int middle = (length + 1) / 2;

        // Initialize 'curr' to find the middle node.
        ListNode curr = A;
        for (int i = 1; i < middle; i++) {
            curr = curr.next;
        }

        // Separate the second half of the list and reverse it.
        ListNode newMid = curr.next;
        curr.next = null;
        ListNode prev = null;
        while (newMid != null) {
            ListNode nextTemp = newMid.next;
            newMid.next = prev;
            prev = newMid;
            newMid = nextTemp;
        }

        // Initialize 'listSize' to keep track of the number of elements in the second half.
        int listSize = 0;
        ListNode second = prev;

        // Count the number of elements in the second half.
        while (prev != null) {
            prev = prev.next;
            listSize++;
        }

        // Reset 'prev' and 'firstList' to the start of the list.
        prev = A;
        ListNode firstList = A;

        // Compare the first and second halves for palindrome equality.
        while (listSize > 0) {
            if (second.val != firstList.val) {
                return 0; // Not a palindrome.
            }
            firstList = firstList.next;
            second = second.next;
            listSize--;
        }

        return 1; // It's a palindrome.
    }

}
