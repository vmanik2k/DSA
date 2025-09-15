package com.dsa.practice.demo.Scaler.Trie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        Boolean[] dp = new Boolean[s.length()+1];
        return solve(0,s,new HashSet<>(wordDict),dp);
    }

    private boolean solve(int idx, String s, Set<String> wordDict, Boolean[] dp) {
        if(idx ==s.length()){
            return true;
        }
        if(dp[idx]!=null){
            return dp[idx];
        }

        for(int i = idx+1;i<=s.length();i++){
            String word = s.substring(idx,i);
            if(wordDict.contains(word) && solve(i,s,wordDict,dp)){
                return dp[idx] = true;
            }
        }
        return dp[idx] = false;
    }


    public static void main(String[] args) {
        System.out.println(new WordBreak().wordBreak("leetcode", Arrays.asList("leet","code")));
        System.out.println(new WordBreak().wordBreak("applepenapple", Arrays.asList("apple","pen")));
        System.out.println(new WordBreak().wordBreak("applepenmangoapple", Arrays.asList("apple","pen")));
        System.out.println(new WordBreak().wordBreak("aaaaaaa", Arrays.asList("aaaa","aaa")));
        System.out.println(new WordBreak().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
    }
}
