package com.dsa.practice.demo.Scaler.LinkedList;
/*Given a sorted linked list, delete all duplicates such that each element appears only once.



Problem Constraints
0 <= length of linked list <= 106



Input Format
First argument is the head pointer of the linked list.



Output Format
Return the head pointer of the linked list after removing all duplicates.



Example Input
Input 1:

 1->1->2
Input 2:

 1->1->2->3->3


Example Output
Output 1:

 1->2
Output 2:

 1->2->3


Example Explanation
Explanation 1:

 Each element appear only once in 1->2.
*/
public class RemoveDuplicates {
    public ListNode deleteDuplicates(ListNode A) {
        // Check if the list has only one node or is empty.
        if (A.next == null) {
            return A;
        }

        ListNode node = A; // Initialize a pointer to traverse the list.
        ListNode sample = A.next; // Initialize a pointer to compare with the current node.

        while (node != null && sample != null) {
            // Compare the value of the current node with the value of the sample node.
            if (node.val == sample.val) {
                // If they have the same value, skip the sample node.
                node.next = sample.next;
                sample = sample.next;
                continue; // Continue to the next iteration without advancing the 'node' pointer.
            }

            // Move to the next node if the values are not the same.
            node = node.next;
        }

        return A; // Return the modified linked list with duplicates removed.
    }

}
