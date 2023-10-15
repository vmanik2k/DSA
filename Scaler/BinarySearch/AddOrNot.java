package BinarySearch.Scaler.BinarySearch;

import java.util.Arrays;

public class AddOrNot {

    /*
Given an array of integers A of size N and an integer B.

In a single operation, any one element of the array can be increased by 1. You are allowed to do at most B such operations.

Find the number with the maximum number of occurrences and return an array C of size 2, where C[0] is the number of occurrences, and C[1] is the number with maximum occurrence.
If there are several such numbers, your task is to find the minimum one.

Problem Constraints
1 <= N <= 10^5

-10^9 <= A[i] <= 10^9

0 <= B <= 10^9

Input
The first argument given is the integer array A.
The second argument given is the integer B.

A = [3, 1, 2, 2, 1]
 B = 3

Output
Return an array C of size 2, where C[0] is number of occurence and C[1] is the number with maximum occurence.

 [4, 2]

 Explanation
 Apply operations on A[2] and A[4]
 A = [3, 2, 2, 2, 2]
 Maximum occurence =  4
 Minimum value of element with maximum occurence = 2
*/

    public int[] solve(int[] A, int B) {
        Arrays.sort(A);
        int arr[] = new int[2];
        arr[0]=arr[1]=-1;
        long pf[] = new long[A.length+1];
        pf[0]=0;
        for(int i =1;i<pf.length;i++){
            pf[i] = pf[i-1]+A[i-1];
        }
        for(int i =0;i<A.length;i++){
            // Defining the search space minimum and max number of elements
            // that can be converted to a particular element within the B number of operations
            int low =1;
            int high = i+1;
            int max=-1;
            while(low<=high){
                int count = low+(high-low)/2;
                //  System.out.println(count+" "+low+" "+high+" "+i);
                //checking the number of elements that can be converted before that element A[i]
                if(check(pf,count,i,A[i],B)){
                    max=count;
                    low=count+1;
                }else{
                    high=count-1;
                }
            }
            if(arr[0]<max){
                arr[0]=max;
                arr[1] = A[i];
            }
        }
        return arr;
    }

    public boolean check(long pf[],int count,int i,int num,int B){
        //Actual sum of that range
        long temp = pf[i+1]-pf[i+1-count];
        //Total sum that can be formed after converting all the numbers successfullly
        long total = (1L*num*count);

        if(total-temp<=B){
            return true;
        }
        return false;
    }
}
