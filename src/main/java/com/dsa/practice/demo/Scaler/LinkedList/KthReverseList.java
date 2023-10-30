package com.dsa.practice.demo.Scaler.LinkedList;
/*Given a singly linked list A and an integer B, reverse the nodes of the list B at a time and return the modified linked list.



Problem Constraints
1 <= |A| <= 103

B always divides A



Input Format
The first argument of input contains a pointer to the head of the linked list.

The second arugment of input contains the integer, B.



Output Format
Return a pointer to the head of the modified linked list.



Example Input
Input 1:

 A = [1, 2, 3, 4, 5, 6]
 B = 2
Input 2:

 A = [1, 2, 3, 4, 5, 6]
 B = 3


Example Output
Output 1:

 [2, 1, 4, 3, 6, 5]
Output 2:

 [3, 2, 1, 6, 5, 4]


Example Explanation
Explanation 1:

 For the first example, the list can be reversed in groups of 2.
    [[1, 2], [3, 4], [5, 6]]
 After reversing the K-linked list
    [[2, 1], [4, 3], [6, 5]]
Explanation 2:

 For the second example, the list can be reversed in groups of 3.
    [[1, 2, 3], [4, 5, 6]]
 After reversing the K-linked list
    [[3, 2, 1], [6, 5, 4]]
*/
public class KthReverseList {
    public ListNode reverseList(ListNode A, int B) {
        // Find the length of the linked list.
        ListNode node = A;
        int n = 0;
        while (node != null) {
            node = node.next;
            n++;
        }

        // Reverse groups of size B until you reach the end of the list.
        int i =1;
        int j =i+B-1;
        while(j<=n){
            A= reverseBetween(A,i,j,n);
            i=i+B;
            j=i+B-1;
        }

        return A; // Return the modified linked list.
    }

    public ListNode reverseBetween(ListNode A, int B, int C, int n) {
        ListNode node = A;

        // If B is 1 and C is equal to the length of the list, reverse the entire list.
        if (B == 1 && C == n) {
            return reverseLinkedList(A);
        }

        node = A;
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
