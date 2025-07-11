package org.example.divideconquer;

import java.util.HashMap;

class Solution {

        public long mergeSort(int[] nums, int left, int right) {
            int cnt = 0;
            if (left >= right) {
                return cnt;
            }
            int mid = left + (right - left) / 2;
            cnt += mergeSort(nums, left, mid);
            cnt += mergeSort(nums, mid + 1, right);
            cnt += countReversePairs(nums, left, mid, right);
            merge(nums, left, mid, right);
            return cnt;
        }

        private int countReversePairs(int[] nums, int left, int mid, int right) {
            int count = 0;
            int j = mid + 1;

            for (int i = left; i <= mid; i++) {
                while (j <= right && nums[i] > 2 * nums[j]) {
                    j++;
                }
                count += (j - (mid + 1));
            }
            return count;
        }

        private void merge(int[] nums, int left, int mid, int right) {
            int n1 = mid - left + 1;
            int n2 = right - mid;

            int[] leftArray = new int[n1];
            int[] rightArray = new int[n2];

            for (int i = 0; i < n1; i++) {
                leftArray[i] = nums[left + i];
            }
            for (int j = 0; j < n2; j++) {
                rightArray[j] = nums[mid + 1 + j];
            }

            int i = 0, j = 0, k = left;
            long inversions = 0;

            while (i < n1 && j < n2) {
                if (leftArray[i] > rightArray[j]) {
                    inversions += (n1 - i + 1); // Count inversions
                    nums[k++] = rightArray[j++];
                } else {
                    nums[k++] = leftArray[i++];
                }
            }

            while (i < n1) {
                nums[k++] = leftArray[i++];
            }
            while (j < n2) {
                nums[k++] = rightArray[j++];
            }
        }


        public int reversePairs(int[] nums) {
            return (int) mergeSort(nums, 0, nums.length - 1);
        }

        public int lower_bound(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right){
                int mid = left + (right - left)/2;
                if(nums[mid] >= target){
                   right = mid - 1;
                }
                else if(nums[mid] < target){
                    left = mid + 1;
                }
            }
            return right;
        }

        public int upperBound(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            int ans = nums.length;
            while (left <= right){
                int mid = left + (right - left)/2;
                if(nums[mid] > target){
                    right = mid - 1;
                    ans = mid;
                }
                else{
                    left = mid + 1;
                }
            }
            return ans;
        }

        public long floorSqrt(long n) {
            if(n<=1) return n;
            long left = 1, right = n;
            long ans = n;
            while(left<=right){
                long mid = left + (right - left)/2;
                if( mid*mid <= n){
                    left = mid + 1;
                    ans = mid;
                }
                else{
                    right = mid -1;
                }
            }
            return ans;
        }

        public int NthRoot(int n, int m) {

            int left =0 , right = m;
            while(left<=right){
                int mid = left + (right - left)/2;
                int curr = (int)Math.pow(mid, n);
                if(curr == m) return mid;
                else if(curr < m) {
                    left = mid + 1;
                }
                else{
                    right = mid -1;
                }
            }
            return -1;
        }

//        You’re given an integer array A of length n. A subsequence is any sequence you get by deleting zero or more elements (without reordering). We want to count how many subsequences of A satisfy both of these parity‑run constraints:
//
//        No three evens in a row: You never pick three consecutive elements in your subsequence that are all even numbers.
//
//        No three odds in a row: You never pick three consecutive elements in your subsequence that are all odd numbers.
//
//        Compute the total number of non‑empty valid subsequences.
        public int countSubsequences(int[] nums) {
            int n = nums.length;
            long[][] dp = new long[n + 1][2];
            dp[0][0] = 1; // Base case: empty subsequence

            // dp[i][0] = number of valid subsequences ending with an even number
            // dp[i][1] = number of valid subsequences ending with an odd number

            for (int i = 1; i <= n; i++) {
                if (nums[i - 1] % 2 == 0) { // Even number
                    dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % 1000000007; // No three evens in a row
                    dp[i][1] = dp[i - 1][0]; // No three odds in a row
                } else { // Odd number
                    dp[i][0] = dp[i - 1][1]; // No three evens in a row
                    dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % 1000000007; // No three odds in a row
                }
            }

            return (int) ((dp[n][0] + dp[n][1] - 1 + 1000000007) % 1000000007); // Subtracting 1 to exclude the empty subsequence
        }

        public int climbStairs(int n) {
            if (n <= 2) {
                return n;
            }
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;

            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }

        private HashMap<Integer, Integer> memo = new HashMap<>();
        private int jumpTo(int [] heights, int pos){
            if(pos == 0) return 0;
            if(memo.containsKey(pos)) return memo.get(pos);
            int left = jumpTo(heights, pos - 1) + Math.abs(heights[pos] - heights[pos - 1]);
            int right = Integer.MAX_VALUE;
            if(pos-2>=0)
                right = jumpTo(heights, pos - 2) + Math.abs(heights[pos] - heights[pos - 2]);
            memo.put(pos, Math.min(left, right));
            return Math.min(left, right);
        }
        public int frogJump1(int[] heights) {
            memo.clear();
            return jumpTo(heights, heights.length - 1);
        }

    public int frogJump(int[] heights) {
        int n = heights.length;
        int[] dp = new int[n];
        dp[0] = 0; // Starting point
        for (int i = 1; i < n; i++) {
            int oneStep = dp[i - 1] + Math.abs(heights[i] - heights[i - 1]);
            int twoSteps = Integer.MAX_VALUE;
            if(i-2 >= 0){
                twoSteps = dp[i - 2] + Math.abs(heights[i] - heights[i - 2]);
            }
            dp[i] = Math.min(oneStep, twoSteps);
        }
        return dp[n - 1];
    }

    private int JumpK(int[] heights, int pos, int k) {
        if (pos == 0) return 0;
        if(memo.containsKey(pos)) return memo.get(pos);
        int minCost = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            int cost = Integer.MAX_VALUE;
            if(pos - j >= 0){
                cost = JumpK(heights, pos - j, k) + Math.abs(heights[pos] - heights[pos - j]);
            }
            minCost = Math.min(minCost, cost);
        }
        memo.put(pos, minCost);
        return minCost;
    }
    public int frogJump(int[] heights, int k) {
            memo.clear();
            return JumpK(heights, heights.length - 1, k);
    }

    }
