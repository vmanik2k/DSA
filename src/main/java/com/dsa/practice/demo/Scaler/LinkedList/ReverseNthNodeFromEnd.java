package com.dsa.practice.demo.Scaler.LinkedList;
/*Given a linked list A, remove the B-th node from the end of the list and return its head. For example, Given linked list: 1->2->3->4->5, and B = 2. After removing the second node from the end, the linked list becomes 1->2->3->5. NOTE: If B is greater than the size of the list, remove the first node of the list. NOTE: Try doing it using constant additional space.


Problem Constraints
1 <= |A| <= 106


Input Format
The first argument of input contains a pointer to the head of the linked list. The second argument of input contains the integer B.


Output Format
Return the head of the linked list after deleting the B-th element from the end.


Example Input
Input 1:
A = 1->2->3->4->5
B = 2
Input 2:
A = 1
B = 1


Example Output
Output 1:
1->2->3->5
Output 2:



Example Explanation
Explanation 1:
In the first example, 4 is the second last element.
Explanation 2:
In the second example, 1 is the first and the last element.
*/
public class ReverseNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode A, int B) {
        // Check if the linked list has only one node and needs to be removed.
        if (A.next == null) {
            return null;
        }

        int n = 0; // Initialize a variable to count the number of nodes.
        ListNode node = A; // Start from the head of the linked list.

        // Count the number of nodes in the linked list.
        while (node != null) {
            node = node.next;
            n++;
        }

        node = A; // Reset the 'node' pointer to the head of the list.
        n = n - B; // Calculate the position of the node to be removed from the start.

        // If the position is less than or equal to 0, remove the first node.
        if (n <= 0) {
            return A.next;
        }

        // Traverse to the node just before the one to be removed.
        for (int i = 1; i < n; i++) {
            node = node.next;
        }

        // Update the next reference to skip the node to be removed.
        if (node.next != null) {
            node.next = node.next.next;
        }

        // Return the modified linked list.
        return A;
    }

}
