package org.example.twopointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int maxScore(int[] cardScore, int k) {
        //your code goes here
        int n = cardScore.length;
        int windowSize = n-k;
        int curSum = 0, totalSum = Arrays.stream(cardScore).sum();
        for(int i=0;i<windowSize;i++) curSum += cardScore[i];

        // cursum minimized;
        int minSum = curSum;
        int l = 0, r = windowSize -1;
        while (r<n-1){
            r++;
            curSum = curSum + cardScore[r] - cardScore[l];
            l++;
            minSum = Math.min(curSum, minSum);
        }
        return totalSum - minSum;
    }

    public int longestNonRepeatingSubstring(String s) {
        //your code goes here
        if(s.length() <= 1) return s.length();

        // aa - corner case
        int n = s.length();
        int l=0, r=0;
        HashMap<Character, Integer> currentWindowSet = new HashMap<>();
        int ans = 0;
        while (r<n){
            if(!currentWindowSet.containsKey(s.charAt(r)) || currentWindowSet.get(s.charAt(r)) == 0){
                currentWindowSet.put(s.charAt(r), currentWindowSet.getOrDefault(s.charAt(r), 0) + 1);

            }
            else{
                // abcdb // abcdd
                while (l<r && currentWindowSet.get(s.charAt(r)) > 0){
                    currentWindowSet.put(s.charAt(l), currentWindowSet.getOrDefault(s.charAt(l), 0) - 1);
                    l++;
                }
                currentWindowSet.put(s.charAt(r), currentWindowSet.getOrDefault(s.charAt(r), 0) + 1);
            }
            r++;
            // found window l to r-1
            ans = Math.max(ans, r-l+1);
        }
        return  ans;
    }

    public int totalFruits(int[] fruits) {
        //your code goes here

        // max window with 2 unique
        int n = fruits.length;
        int l = 0, r = 0, ans = 0;
        HashMap<Integer, Integer> freq = new HashMap<>();
        while(r<n){
            int current = fruits[r];
            freq.put(current, freq.getOrDefault(current, 0) + 1);

            while (freq.keySet().size() > 2){
                int left = fruits[l];
                freq.put(left, freq.getOrDefault(left, 0) - 1);
                if(freq.get(left) == 0){
                    freq.remove(left);
                }
                l++;
            }
            // valid l to r
            ans = Math.max(ans, r-l+1);
            r++;
        }
        return ans;
    }

    public int kDistinctChar(String s, int k) {
        //your code goes here
        int n = s.length();
        int l = 0, r=0, ans = 0;
        HashMap<Character, Integer> characterCount = new HashMap<>();
        while(r<n){
            char current = s.charAt(r);
            characterCount.put(current, characterCount.getOrDefault(current, 0) + 1);
            while (characterCount.keySet().size() > k){
                char left = s.charAt(l);
                characterCount.put(left, characterCount.getOrDefault(left, 0) - 1);
                if(characterCount.get(left) == 0) characterCount.remove(left);
                l++;
            }
            // valid l to r
            ans = Math.max(ans, r-l+1);
            r++;
        }
        return  ans;
    }

    public int characterReplacement(String s, int k) {
        //your code goes here
        //your code goes here
        int n = s.length();
        int l = 0, r=0, ans = 0;
        HashMap<Character, Integer> characterCount = new HashMap<>();
        while(r<n){
            char current = s.charAt(r);
            characterCount.put(current, characterCount.getOrDefault(current, 0) + 1);
            while (replacementNeeded(characterCount, r-l+1) > k ){
                char left = s.charAt(l);
                characterCount.put(left, characterCount.getOrDefault(left, 0) - 1);
                if(characterCount.get(left) == 0) characterCount.remove(left);
                l++;
            }
            // valid l to r
            ans = Math.max(ans, r-l+1);
            r++;
        }
        return  ans;
    }

    private int replacementNeeded(HashMap<Character, Integer> characterCount, int windowSize) {
        int curMax = Integer.MIN_VALUE;
        for(int val : characterCount.values()){
            curMax = Math.max(curMax, val);
        }
        return windowSize - curMax;
    }

    public int numberOfSubstrings(String s) {
        //your code goes here
        int n = s.length();
        int freq[] = new int[3];
        int r= 0, l = 0, count = 0;

        while (r<n){
            int charIndex = s.charAt(r) - 'a';
            freq[charIndex]++;

            while (l < r && !countOfABCAtleast1(freq)){
                charIndex = s.charAt(l) - 'a';
                freq[charIndex]--;
                l++;
            }
            // now l to r is invalid
            count++;
            r++;
        }
        return count;
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        //your code goes here
    }


    private boolean countOfABCAtleast1(int[] freq) {
        int n = freq.length;
        for(int i=0;i<n;i++){
            if(freq[i] < 1) return  false;
        }
        return true;
    }
}
