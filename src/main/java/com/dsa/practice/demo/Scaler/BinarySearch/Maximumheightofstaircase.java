package com.dsa.practice.demo.Scaler.BinarySearch;

/*Given an integer A representing the number of square blocks. The height of each square block is 1. The task is to create a staircase of max-height using these blocks.

The first stair would require only one block, and the second stair would require two blocks, and so on.

Find and return the maximum height of the staircase.



Problem Constraints
0 <= A <= 109


Input Format
The only argument given is integer A.



Output Format
Return the maximum height of the staircase using these blocks.



Example Input
Input 1:

 A = 10
Input 2:

 A = 20


Example Output
Output 1:

 4
Output 2:

 5


Example Explanation
Explanation 1:

 The stairs formed will have height 1, 2, 3, 4.
Explanation 2:

 The stairs formed will have height 1, 2, 3, 4, 5.*/
public class Maximumheightofstaircase {
        public int findMaxStaircaseHeight(int A) {
            if (A == 0) {
                return 0; // If there are no blocks, the height of the staircase is 0.
            }

            int currentBlock = 1; // Initialize the height of the current step.

            while (true) {
                A = A - currentBlock; // Subtract the current step height from the remaining blocks.

                // Check if there are no more blocks left or if the next step is too high.
                if (A <= 0 || A < (currentBlock + 1)) {
                    return currentBlock; // Return the maximum height of the staircase.
                }

                currentBlock++; // Move to the next step.
            }
        }

}
