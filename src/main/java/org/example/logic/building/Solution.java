package org.example.logic.building;

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
}
