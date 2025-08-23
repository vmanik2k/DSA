package com.dsa.practice.demo.Scaler.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        char ch[] = p.toCharArray();
        Arrays.sort(ch);
        String sortedString = new String(ch);

        for (int i = 0; i <= s.length() - p.length(); i++) {
            String temp = s.substring(i, i + p.length());
            char charArray[] = temp.toCharArray();
            Arrays.sort(charArray);
            if (sortedString.equals(new String(charArray))) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new FindAllAnagrams().findAnagrams("cbaebabacd","abc"));
    }
}
