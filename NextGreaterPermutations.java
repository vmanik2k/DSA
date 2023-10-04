package CRM_ENCRYPTION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NextGreaterPermutations {

    public List<Integer> nextPermutation(List<Integer> A) {
        List<Integer> res = new ArrayList<Integer>();
        int brk =-1;
        //Case 1 check for the dip to get the element to be replaced with the greater element
        for(int i = A.size()-2;i>=0;i--){
            if(A.get(i)<A.get(i+1)){
                brk = i;
                break;
            }
        }
        // If the element is at last permutation then just simply reverse the array
        if(brk==-1){
            Collections.reverse(A);
            return A;
        }
        // Replace the array with the next greater element
        for(int i = A.size()-1;i>brk;i--){
            if(A.get(i)>A.get(brk)){
                swap(A,i,brk);
                break;
            }
        }
        int start = brk+1;
        int end = A.size();
        // reverse the list from the breakpoint+1 to the last
        List<Integer> myList = A.subList(start,end);
        Collections.reverse(myList);
        for(int a : myList){
            A.set(start,a);
            start++;
        }
        return A;

    }
    void swap(List<Integer> A ,int a,int b){
        int temp = A.get(a);
        A.set(a,A.get(b));
        A.set(b,temp);
    }
    public static void main(String[] args) {
        Integer num [] ={1,2,3};
        System.out.println(new NextGreaterPermutations().nextPermutation(Arrays.asList(num)));
    }
}
///Total Permutations

/*class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        compute(nums,0,result);
        return result;
    }
public void compute(int [] nums,int ind,List<List<Integer>> result){
    if(ind == nums.length){
        List<Integer> res = new ArrayList<Integer>();
        for(int a :nums){
            res.add(a);
        }
        result.add(res);
        return;
    }
    for(int i =ind;i<nums.length;i++){
        swap(nums,i,ind);
        compute(nums,ind+1,result);
        swap(nums,i,ind);
    }
}

public void swap(int []nums,int i,int ind){
    int temp = nums[i];
    nums[i] = nums[ind];
    nums[ind] = temp;
}
}*/