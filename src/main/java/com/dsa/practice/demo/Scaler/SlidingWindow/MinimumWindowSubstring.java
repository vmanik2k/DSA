package com.dsa.practice.demo.Scaler.SlidingWindow;

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 ||
                s.length() < t.length()) {
            return new String();
        }
        int[] hashT = new int[128];

        for(int i =0;i<t.length();i++){
            hashT[t.charAt(i)]++;
        }
        String ans = "";
        int calLength = Integer.MAX_VALUE;
        int start = 0,end =0,startIndex =0,count=t.length();
        char[] chS = s.toCharArray();

        while(end<chS.length){
            if(hashT[chS[end++]]-->0){
                count--;
            }
            while(count==0){
                if(end-start<calLength){
                    startIndex = start;
                    calLength = end-start;
                }

                if(hashT[chS[start++]]++==0){
                    count++;
                }
            }
        }

        return calLength == Integer.MAX_VALUE ? new String() :
                new String(chS, startIndex, calLength);
    }

    public static void main(String[] args) {
        new MinimumWindowSubstring().minWindow("ADOBECODEBANC","ABC");
    }
}
