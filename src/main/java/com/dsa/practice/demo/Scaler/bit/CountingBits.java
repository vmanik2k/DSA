package com.dsa.practice.demo.Scaler.bit;

import java.util.Arrays;

public class CountingBits {

    public int[] countBits(int n) {
        if(n==0){
            return new int[]{0};
        }
        if(n==1){
            return new int[]{0,1};
        }
        int ans [] = new int[n+1];
        ans[0] = 0;
        ans[1] = 1;
        for(int i =2;i<ans.length;i++){
            if(i%2==0){
                ans[i] = ans[i/2];
            }else{
                ans[i] = ans[i/2]+1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CountingBits().countBits(5)));
    }
}