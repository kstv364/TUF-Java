package org.example.recursion;

import java.util.ArrayList;
import java.util.List;
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

}
