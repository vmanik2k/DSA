package com.dsa.practice.demo.Scaler;

import java.io.IOException;

class FindMinumumElement
{

    public static void main(String[] args) throws IOException {
        FindMinumumElement main= new FindMinumumElement();
        System.out.println(var);
        var=setvar();
        System.out.println((3+7-1)%7+"99");
        Solution solution= new Solution();
        //solution.findMin(new int[]{2,1});
        int arr[] = {1,1,2,3,3,4,4,8,8};
        int xor=0;
        for(int i =0;i<arr.length;i++){
            xor=xor^arr[i];
            System.out.print(xor);
        }
    }
    static boolean setvar(){
        return !var;
    }
    public   static boolean var = true;

}

class Solution {
    public int findMin(int[] nums) {
        if(nums.length==1)
            return nums[0];
        int start =0;
        int n = nums.length;
        int end=nums.length-1;
        int mid=0;
        while(start<=end){
            mid=start+(end-start)/2;
            int next = (mid+1)%n;
            int prev =(mid+n-1)%n;
            if(nums[mid]<=nums[next]&&nums[mid]<=nums[prev]){
                return nums[mid];
            }
            else if(nums[start]<nums[mid]&&nums[mid]>nums[end]){
                start=(mid+1)%n;
            }
            else{
                end=(mid+n-1)%n;
            }

        }
        return mid;
    }
}