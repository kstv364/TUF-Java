package org.example.recursion;

import javax.print.attribute.EnumSyntax;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Solution {

    public double myPow(double x, int n) {
        //your code goes here
        if(n<0) return 1.0/myPow(x,-n);
        if(n==0) return 1;
        double half = myPow(x, n/2);
        if(n%2==1){
            return half*half*x;
        }
        else{
            return half*half;
        }
    }


    public List<String> generateParenthesis(int n) {
        //your code goes here
        List<String> answers = new ArrayList<>();
        generateBracket(0,0,"", answers, n);
        return answers;
    }

    private void generateBracket(int open, int close, String current, List<String> answers, int n) {
        if(open + close == 2*n && open == close) {
            answers.add(current);
        }

        if(open<n){
            generateBracket(open + 1, close, current+'(', answers,n);
        }

        if(close<open){
            generateBracket(open, close + 1, current+')', answers,n);
        }
    }

    public List<List<Integer>> powerSet(int[] nums) {
        //your code goes here
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        recursionutil(nums, current, 0, ans);
        return ans;
    }

    private void recursionutil(int[] nums, List<Integer> current, int i,  List<List<Integer>> ans) {
        if(i>=nums.length){
            ans.add(new ArrayList<>(current));
            return;
        }

        recursionutil(nums,current, i+ 1,ans );
        current.add(nums[i]);
        recursionutil(nums, current,i+1,ans);
        current.remove(current.size() - 1);
    }

    public boolean checkSubsequenceSum(int[] nums, int k) {
        AtomicBoolean ans = new AtomicBoolean(false);
        GeneralRecur(nums, 0, 0, k, ans);
        return ans.get();
    }

    private void GeneralRecur(int[] nums, int i, int cursum, int k, AtomicBoolean ans) {
        if (ans.get()) return; // Early exit if already found

        if (cursum == k) {
            ans.set(true);
            return;
        }

        if (i == nums.length) return;

        GeneralRecur(nums, i + 1, cursum, k, ans);              // Exclude nums[i]
        GeneralRecur(nums, i + 1, cursum + nums[i], k, ans);    // Include nums[i]
    }

    public int countSubsequenceWithTargetSum(int[] nums, int k) {
        //your code goes here
        return checkutilWithAns(nums, 0, 0, k);
    }

    private int checkutilWithAns(int[] nums, int i, int cursum, int k) {
        if (cursum == k) {
           return 1;
        }
        if (i == nums.length) return 0;

        int answers = checkutilWithAns(nums, i + 1, cursum, k);              // Exclude nums[i]
        answers += checkutilWithAns(nums, i + 1, cursum + nums[i], k);
        return answers;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //your code goes here
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        combinationSumUtil(candidates, target, 0, current, ans);
        return ans;
    }

    private void combinationSumUtil(int[] candidates, int target, int i, List<Integer> current, List<List<Integer>> ans) {
        if(i>=candidates.length){
            if(target==0){
                ans.add(new ArrayList<>(current));
            }
            return;
        }
        if(target <0 ){
            return;
        }
        current.add(candidates[i]);
        combinationSumUtil(candidates, target - candidates[i], i, current, ans);
        current.remove(current.size() - 1);
        combinationSumUtil(candidates, target, i+1, current, ans);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //your code goes here
        Set<List<Integer>> ans = new HashSet<>();
        List<Integer> current = new ArrayList<>();
        combinationSumUtil2(candidates, target, 0, current, ans);
        return new ArrayList<>(ans);
    }

    private void combinationSumUtil2(int[] candidates, int target, int i, List<Integer> current, Set<List<Integer>> ans) {
        if(i>=candidates.length){
            if(target==0){
                List<Integer> copy = new ArrayList<>(current);
                Collections.sort(copy);
                ans.add(copy);
            }
            return;
        }
        if(target <0 ){
            return;
        }
        current.add(candidates[i]);
        combinationSumUtil2(candidates, target - candidates[i], i+1, current, ans);
        current.remove(current.size() - 1);
        combinationSumUtil2(candidates, target, i+1, current, ans);
    }

    public List<Integer> subsetSums(int[] nums) {
        //yor code goes here
        List<Integer> ans = new ArrayList<>();
        recur(nums, 0, 0, ans);
        return ans;
    }

    private void recur(int[] nums, int i, int curSum, List<Integer> ans) {
       if(i>=nums.length){
           ans.add(curSum);
           return;
       }
       recur(nums, i+1,curSum + nums[i], ans);
       recur(nums, i+1, curSum, ans);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //your code goes here
        HashSet<List<Integer>> ans = new HashSet<>();
        Arrays.sort(nums);
        List<Integer> current = new ArrayList<>();
        recurrence(nums, 0,current, ans);
        return new ArrayList<>(ans);
    }

    private void recurrence(int[] nums, int i, List<Integer> current, HashSet<List<Integer>> ans) {
        if(i>=nums.length){
            ans.add(new ArrayList<>(current));
            return;
        }
        current.add(nums[i]);
        recurrence(nums, i+1, current, ans);
        current.remove(current.size() -1);
        recurrence(nums, i+1, current, ans);
    }


    public List<List<Integer>> combinationSum3(int k, int n) {
        //your code goes here
        int[] numbers = new int[]{1, 2, 3, 4, 5,6,7,8,9};
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        combinationUtil(numbers, 0, k, n, 0,  current, ans );
        return ans;
    }

    private void combinationUtil(int[] numbers, int i, int k, int n, int curSum, List<Integer> current, List<List<Integer>> ans) {
        // ✅ Prune early
        if (curSum > n || current.size() > k) return;

        // ✅ Found valid combination
        if (current.size() == k) {
            if (curSum == n) {
                ans.add(new ArrayList<>(current));
            }
            return;
        }

        for (int j = i; j < numbers.length; j++) {
            current.add(numbers[j]);
            combinationUtil(numbers, j + 1, k, n, curSum + numbers[j], current, ans);
            current.remove(current.size() - 1);
        }
    }

    public List<List<String>> partition(String s) {
        //your code goes here
        List<List<String>> ans = new ArrayList<>();
        List<String> current = new ArrayList<>();
        partrecur(s, 0 ,current, ans);
        return ans;
    }

    private void partrecur(String s, int currentIndex, List<String> current, List<List<String>> ans) {

        if(currentIndex == s.length()) {
            ans.add(new ArrayList<>(current));
            return;
        }
        for(int i=currentIndex; i<s.length();i++){
            String sub = s.substring(currentIndex, i+1);
            if(isPalin(sub)){
                current.add(sub);
                partrecur(s, i, current, ans);
                current.remove(current.size() - 1);
            }
        }
    }

    private boolean isPalin(String sub) {
        int i=0, j = sub.length()-1;
        while(i<=j){
            if(sub.charAt(i) != sub.charAt(j)){
                return false;
            }
            i++; j--;
        }
        return true;
    }
}
