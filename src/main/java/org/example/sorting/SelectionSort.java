package org.example.sorting;
import java.util.Arrays;

public class SelectionSort {

    public int[] selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int max = Integer.MIN_VALUE;
            int maxi = -1;
            for (int j = 0; j < nums.length - i; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                    maxi = j;
                }
            }
            swap(nums, maxi, nums.length - i - 1);

        }
        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int linearSearch(int nums[], int target) {
        //Your code goes here
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public int largestElement(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }

    public int secondLargestElement(int[] nums) {
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                secondMax = max;
                max = nums[i];
            } else if (nums[i] > secondMax && nums[i] < max) {
                secondMax = nums[i];
            }
        }
        return secondMax;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        for(int i=0; i < nums.length; i++) {
            if(nums[i] == 1) {
                int count = 0;
                while(i < nums.length && nums[i] == 1) {
                    count++;
                    i++;
                }
                maxCount = Math.max(maxCount, count);
            }
        }
        return maxCount;
    }

    public void rotateArrayByOne(int[] nums) {
        int first = nums[0];
        for (int i = 0; i < nums.length - 1; i++) {
            nums[i] = nums[i + 1];
        }
        nums[nums.length - 1] = first;
    }

    public void rotateArray(int[] nums, int k) {
        k = k % nums.length; // Handle cases where k is greater than the length of the array
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}

