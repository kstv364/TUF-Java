package org.example.binsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

class Solution {
    public int smallestDivisor(int[] nums, int limit) {
        int high = Arrays.stream(nums).sum();
        int low = 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (divideAndSum(nums, mid) <= limit) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private int divideAndSum(int[] nums, int mid) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.ceil((double) num / mid);
        }
        return sum;
    }


    public int minimumRateToEatBananas(int[] nums, int h) {
        int high = Arrays.stream(nums).max().getAsInt();
        int low = 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (timeTakentoEat(nums, mid) <= h) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private int timeTakentoEat(int[] nums, int mid) {
        int timeNeeded = 0;
        for (int i = 0; i < nums.length; i++) {
            timeNeeded += Math.ceil((double) nums[i] / mid);
        }
        return timeNeeded;
    }

    public int roseGarden(int n, int[] nums, int k, int m) {
        int high = Arrays.stream(nums).max().getAsInt();
        int low = 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (bouquetsFormedAtDthDay(nums, k, mid) >= m) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private int bouquetsFormedAtDthDay(int[] nums, int k, int day) {
        int buoquest = 0;
        int streak = 0;
        for (int i = 0; i < nums.length; i++) {
            if (day >= nums[i]) {
                streak++;
            } else {
                streak = 0;
            }
            if (streak == k) {
                streak = 0;
                buoquest++;
            }
        }
        return buoquest;
    }

    public int findMin(ArrayList<Integer> arr) {
        int l = 0, h = arr.size() - 1;
        int ans = Integer.MAX_VALUE;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (arr.get(l) < arr.get(mid)) {
                // left is sorted
                ans = Math.min(ans, arr.get(l));
                l = mid + 1;
            } else {
                ans = Math.min(ans, arr.get(mid));
                h = mid - 1;
            }
        }
        return ans;
    }


    public int search(int[] nums, int k) {
        int l = 0, h = nums.length - 1;
        while (l <= h) {

            int mid = l + (h - l) / 2;
            if (nums[mid] == k) return mid;
            // check existence in sorted part
            if (nums[l] <= nums[mid]) {
                // left sorted
                if (nums[l] <= k && k <= nums[mid]) {
                    // k lies between l and mid - 1
                    h = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // right sorted
                if (nums[mid] <= k && k <= nums[h]) {
                    // k lies between mid +1 , h
                    l = mid + 1;
                } else {
                    h = mid - 1;
                }
            }
        }
        return -1;
    }

    public boolean searchInARotatedSortedArrayII(ArrayList<Integer> nums, int k) {

        int l = 0, h = nums.size() - 1;
        while (l <= h) {

            int mid = l + (h - l) / 2;
            if (nums.get(mid) == k) return true;

            if (nums.get(mid) == nums.get(l) && nums.get(mid) == nums.get(h)){
                // all are equal, possible - eg 31233333
                // don't know which part is sorted
                // reduce search space to skip duplicates
                l+=1; h-=1;
                continue;
            }
            // check existence in sorted part
            if (nums.get(l) <= nums.get(mid)) {
                // left sorted
                if (nums.get(l) <= k && k <= nums.get(mid)) {
                    // k lies between l and mid - 1
                    h = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // right sorted
                if (nums.get(mid) <= k && k <= nums.get(h)) {
                    // k lies between mid +1 , h
                    l = mid + 1;
                } else {
                    h = mid - 1;
                }
            }
        }
        return false;
    }
}