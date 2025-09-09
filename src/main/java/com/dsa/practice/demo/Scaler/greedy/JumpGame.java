package com.dsa.practice.demo.Scaler.greedy;

class JumpGame {
    public int jump(int[] nums) {
        return solve(nums,0);
    }

    public int solve(int[] nums,int idx){
        if(idx>=nums.length-1){
            return 0;
        }
        if(nums[idx]==0){
            return 0;
        }
        int jumps = Integer.MAX_VALUE;
        for(int i =1;i<=nums[i];i++){
            jumps = Math.min(jumps,1+solve(nums,idx+i));
        }

        return jumps;
    }

    public static void main(String[] args) {
        System.out.println(new JumpGame().jump(new int[]{2,3,1,1,4}));
    }
}