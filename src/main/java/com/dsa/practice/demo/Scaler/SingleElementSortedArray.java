package com.dsa.practice.demo.Scaler;

public class SingleElementSortedArray {
    public static void main(String[] args) {
        test solution=new test();
            solution.singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8});
    }
}
class test {
    public int singleNonDuplicate(int[] nums) {
        if(nums.length==1)
            return nums[0];
        int start=0;
        int end=nums.length-1;
        int mid =0;
        int next=0;
        int prev=0;
        int N = nums.length;
        while(start<=end){
            mid=start+(end-start)/2;
            next=(mid+1)%N;
            prev=(mid+N-1)%N;
            if(nums[mid]!=nums[next] && nums[mid] != nums[prev] ){
                return nums[mid];
            }
            if(mid%2==0){
                if(nums[mid]!=nums[next]){
                    end=mid-1;
                }else{
                    start=mid+1;
                }
            }else{
                if(nums[mid]!=nums[prev]){
                    end=mid-1;
                }else{
                    start =mid+1;
                }
            }

        }
        return nums[mid];
    }
}
