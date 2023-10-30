package com.dsa.practice.demo.Scaler.LinkedList;
/*Reverse a linked list A from position B to C.

NOTE: Do it in-place and in one-pass.



Problem Constraints
1 <= |A| <= 106

1 <= B <= C <= |A|



Input Format
The first argument contains a pointer to the head of the given linked list, A.

The second arugment contains an integer, B.

The third argument contains an integer C.



Output Format
Return a pointer to the head of the modified linked list.



Example Input
Input 1:

 A = 1 -> 2 -> 3 -> 4 -> 5
 B = 2
 C = 4

Input 2:

 A = 1 -> 2 -> 3 -> 4 -> 5
 B = 1
 C = 5


Example Output
Output 1:

 1 -> 4 -> 3 -> 2 -> 5
Output 2:

 5 -> 4 -> 3 -> 2 -> 1


Example Explanation
Explanation 1:

 In the first example, we want to reverse the highlighted part of the given linked list : 1 -> 2 -> 3 -> 4 -> 5
 Thus, the output is 1 -> 4 -> 3 -> 2 -> 5
Explanation 2:

 In the second example, we want to reverse the highlighted part of the given linked list : 1 -> 4 -> 3 -> 2 -> 5
 Thus, the output is 5 -> 4 -> 3 -> 2 -> 1
*/
public class ReverseLinkedListBToC {
    public ListNode reverseBetween(ListNode A, int B, int C) {
        ListNode node = A;
        int n = 0;

        // Calculate the length of the linked list.
        while (node != null) {
            node = node.next;
            n++;
        }

        // If B is the first element and C is the last element, reverse the entire list.
        if (B == 1 && C == n) {
            return reverseLinkedList(A);
        }

        node = A; // Reset 'node' to the head of the list.
        ListNode nodeStart = A;
        ListNode start = null;
        ListNode end = null;

        // Find the node at position B and its previous node (start).
        for (int i = 1; i < B; i++) {
            start = nodeStart;
            nodeStart = nodeStart.next;
        }

        if (C != n) {
            // Find the node at position C and its next node (end).
            for (int i = 1; i <= C; i++) {
                end = node;
                node = node.next;
            }

            // Disconnect the part to be reversed from the rest of the list.
            end.next = null;
        }

        // Reverse the part of the list from B to C.
        ListNode newList = reverseLinkedList(nodeStart);
        //If B==1 then start will be null
        if (start != null) {
            start.next = newList;

            if (end == null) {
                return A;
            }
        }

        // Find the end of the new reversed portion.
        ListNode temp = A;
        while (temp.next != null) {
            temp = temp.next;
        }

        // Connect the reversed portion to the remaining list.
        temp.next = node;

        // If B is 1, the head of the list has changed, so return the new head.
        if (B == 1) {
            return newList;
        }

        // Otherwise, return the original head with the reversed portion inserted.
        return A;
    }

    public ListNode reverseLinkedList(ListNode node) {
        ListNode prev = null;
        ListNode curr = node;

        // Reverse the linked list.
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }

}
