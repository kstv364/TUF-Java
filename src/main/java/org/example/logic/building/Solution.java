package org.example.logic.building;

import java.util.*;

public class Solution {
    public void moveZeroes(int[] nums) {
        int lastNonZeroFoundAt = 0; // Pointer for the last non-zero element found
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroFoundAt++] = nums[i]; // Move non-zero element to the front
            }
        }
        // Fill the remaining positions with zeros
        for (int i = lastNonZeroFoundAt; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public int removeDuplicates(int[] nums) {
        int lastUniqueFoundAt = 0; // Pointer for the last unique element found
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[lastUniqueFoundAt]) {
                lastUniqueFoundAt++;
                nums[lastUniqueFoundAt] = nums[i]; // Move unique element to the front
            }
        }
        return lastUniqueFoundAt + 1;
    }

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2; // Sum of first n natural numbers
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num; // Calculate the sum of the elements in the array
        }
        return expectedSum - actualSum; // The missing number is the difference
    }

    public int[] unionArray(int[] nums1, int[] nums2) {
        int lastUniqueFoundAt1 = 0; // Pointer for the last unique element found
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] != nums1[lastUniqueFoundAt1]) {
                lastUniqueFoundAt1++;
                nums1[lastUniqueFoundAt1] = nums1[i]; // Move unique element to the front
            }

        }
        int lastUniqueFoundAt2 = 0; // Pointer for the last unique element found
        for (int i = 0; i < nums2.length; i++) {
            if (nums2[i] != nums2[lastUniqueFoundAt2]) {
                lastUniqueFoundAt2++;
                nums2[lastUniqueFoundAt2] = nums2[i]; // Move unique element to the front
            }
        }
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0, k = 0;
        while (i <= lastUniqueFoundAt1 && j <= lastUniqueFoundAt2) {
            if (nums1[i] < nums2[j]) {
                result.add(nums1[i]);
                i++;
            } else if (nums1[i] > nums2[j]) {
                result.add(nums2[j]);
                j++;
            } else {
                result.add(nums1[i]);
                i++;
                j++;
            }
        }
        while (i <= lastUniqueFoundAt1) {
            result.add(nums1[i]);
            i++;
        }
        while (j <= lastUniqueFoundAt2) {
            result.add(nums2[j]);
            j++;
        }
        int[] resultArray = new int[result.size()];
        for (int l = 0; l < result.size(); l++) {
            resultArray[l] = result.get(l);
        }
        return resultArray;
    }

    public int[] intersectionArray(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        List<Integer> result = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                int current = nums1[i];
                result.add(current);
                while (i < nums1.length && nums1[i] == current) {
                    i++;
                }
                while (j < nums2.length && nums2[j] == current) {
                    j++;
                }
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public ArrayList<Integer> leaders(int[] nums) {
        ArrayList<Integer> leaders = new ArrayList<>();
        int maxFromRight = nums[nums.length - 1];
        leaders.add(maxFromRight); // The rightmost element is always a leader

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > maxFromRight) {
                maxFromRight = nums[i];
                leaders.add(maxFromRight);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = leaders.size() - 1; i >= 0; i--) {
            result.add(leaders.get(i));
        }
        return result;
    }

    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int posIndex = 0;
        int negIndex = 1;

        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                result[posIndex] = nums[i];
                posIndex += 2;
            } else {
                result[negIndex] = nums[i];
                negIndex += 2;
            }
        }
        return result;
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> valuesWithIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (valuesWithIndex.containsKey(target - nums[i])) {
                return new int[]{i, valuesWithIndex.get(target - nums[i])};
            }
            valuesWithIndex.put(nums[i], i);
        }
        return new int[]{0, 0};
    }

    public List<List<Integer>> threeSumBrute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        HashSet<List<Integer>> uniqueTriplets = new HashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(nums[i]);
                        triplet.add(nums[j]);
                        triplet.add(nums[k]);
                        triplet.sort(Integer::compareTo); // Sort to ensure uniqueness
                        uniqueTriplets.add(triplet);
                    }
                }
            }
        }

        result.addAll(uniqueTriplets);
        return result;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        HashSet<List<Integer>> uniqueTriplets = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            HashSet<Integer> seen = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                int complement = -(nums[i] + nums[j]);
                if (seen.contains(complement)) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(nums[j]);
                    triplet.add(complement);
                    triplet.sort(Integer::compareTo); // Sort to ensure uniqueness
                    uniqueTriplets.add(triplet);
                }
                seen.add(nums[j]);
            }
        }
        return new ArrayList<>(uniqueTriplets);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        HashSet<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                HashSet<Integer> seen = new HashSet<>();
                for (int k = j + 1; k < n; k++) {
                    int complement = target - (nums[i] + nums[j] + nums[k]);
                    if (seen.contains(complement)) {
                        List<Integer> quadruplet = new ArrayList<>();
                        quadruplet.add(nums[i]);
                        quadruplet.add(nums[j]);
                        quadruplet.add(nums[k]);
                        quadruplet.add(complement);
                        quadruplet.sort(Integer::compareTo); // Sort to ensure uniqueness
                        result.add(quadruplet);
                    }
                    seen.add(nums[k]);
                }
            }
        }
        return new ArrayList<>(result);
    }

    public void sortZeroOneTwo(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int i = 0;
        for (int num = 0; num <= 2; num++) {
            int count = countMap.getOrDefault(num, 0);
            for (int j = 0; j < count; j++) {
                nums[i++] = num; // Fill the array with the counted numbers
            }
        }
    }

    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        int majorityCount = nums.length / 2;
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            if (countMap.get(num) > majorityCount) {
                return num; // Return the majority element if found
            }
        }
        return -1; // If no majority element is found
    }

    public List<Integer> majorityElementTwo(int[] nums) {
        Set<Integer> ans = new HashSet<>();
        HashMap<Integer, Integer> countMap = new HashMap<>();
        int threshold = nums.length / 3;
        for(int num : nums){
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            if(countMap.get(num) > threshold){
                ans.add(num);
            }
        }
        return new ArrayList<>(ans);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m - 1; // Pointer for the end of nums1
        int j = n - 1; // Pointer for the end of nums2
        int k = m + n - 1; // Pointer for the end of merged array

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--]; // Place larger element at the end
            } else {
                nums1[k--] = nums2[j--]; // Place larger element at the end
            }
        }

        while (j >= 0) {
            nums1[k--] = nums2[j--]; // If any elements left in nums2, copy them
        }
    }
}
