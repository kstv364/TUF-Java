package org.example.logic.building;

import java.util.ArrayList;
import java.util.List;

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
        for(int i= 0; i < nums1.length; i++) {
            if (nums1[i] != nums1[lastUniqueFoundAt1]) {
                lastUniqueFoundAt1++;
                nums1[lastUniqueFoundAt1] = nums1[i]; // Move unique element to the front
            }

        }
        int lastUniqueFoundAt2 = 0; // Pointer for the last unique element found
        for(int i= 0; i < nums2.length; i++) {
            if (nums2[i] != nums2[lastUniqueFoundAt2]) {
                lastUniqueFoundAt2++;
                nums2[lastUniqueFoundAt2] = nums2[i]; // Move unique element to the front
            }
        }
        List<Integer> result = new ArrayList<>();
        int i= 0, j= 0, k= 0;
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
}
