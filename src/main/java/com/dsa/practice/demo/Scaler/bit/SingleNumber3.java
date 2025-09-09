package com.dsa.practice.demo.Scaler.bit;

import java.util.Arrays;

public class SingleNumber3 {

    public int[] singleNumber(int[] nums) {
        int xor2no = 0;
        for (int num : nums) {
            xor2no ^= num;
        }

        int lowestBit = xor2no & (-xor2no);

        int[] result = new int[2];
        for (int num : nums) {
            if ((lowestBit & num) == 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SingleNumber3().singleNumber(new int[]{1, 2, 2, 1, 3, 4, 5, 4})));
    }
}