package org.example.hashing;

import java.util.HashMap;
import java.util.HashSet;

public class Solution {

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int maxLength = 1;
        int currentLength = 1;

        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        for(int num : numSet){
            if (!numSet.contains(num - 1)) { // Check if it's the start of a sequence
                currentLength = 1;
                int currentNum = num;

                while (numSet.contains(currentNum + 1)) { // Count the length of the sequence
                    currentNum++;
                    currentLength++;
                }

                maxLength = Math.max(maxLength, currentLength);
            }
        }
        return maxLength;
    }

    public int longestSubarray(int[] nums, int k) {
       int curSum = 0;
         int maxLength = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // Initialize with sum 0 at index -1 to handle cases where subarray starts from index 0
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];

            if (map.containsKey(curSum - k)) {
                maxLength = Math.max(maxLength, i - map.get(curSum - k));
            }

            // Store the first occurrence of the current sum
            if (!map.containsKey(curSum)) {
                map.put(curSum, i);
            }
        }
        return maxLength;
    }

    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // Initialize with sum 0 to handle cases where subarray starts from index 0
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];

            int target = sum - k;
            if(map.containsKey(target)) {
                count += map.get(target);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
