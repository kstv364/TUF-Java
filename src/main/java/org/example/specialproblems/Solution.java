package org.example.specialproblems;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> mergedIntervals = new ArrayList<>();
        boolean isInserted = false;

        for (int[] interval : intervals) {
            if (isOverlap(interval, newInterval)) {
                newInterval = getMerged(interval, newInterval);
            } else {
                if (!isInserted && interval[0] > newInterval[0]) {
                    mergedIntervals.add(newInterval);
                    isInserted = true;
                }
                mergedIntervals.add(interval);
            }
        }

        if (!isInserted) {
            mergedIntervals.add(newInterval);
        }

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    private int[] getMerged(int[] interval, int[] newInterval) {
        int[] merged = new int[2];
        merged[0] = Math.min(interval[0], newInterval[0]);
        merged[1] = Math.max(interval[1], newInterval[1]);
        return merged;
    }

    private boolean isOverlap(int[] interval, int[] newInterval) {
        return !(interval[1] < newInterval[0] || newInterval[1] < interval[0]);
    }


    public static int findMajority(int[] arr, int n) {
        // Write your code here.
        int count = 0;
        int candidate = -1;
        // Choose a candidate and increment count if the candidate matches the current element, otherwise decrement count
        for (int i = 0; i < n; i++) {
            if (count == 0) {
                candidate = arr[i];
                count = 1;
            } else if (arr[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }
        // at the end if the there exists a majority element, it will be candidate
        count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == candidate) {
                count++;
            }
        }
        if (count > n / 2) {
            return candidate;
        } else {
            return -1; // No majority element found
        }
    }
}
