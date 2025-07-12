package randomproblems;

public class Solution {

    public static int uniquePaths(int m, int n) {
        // Write your code here.
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1; // Only one way to reach any cell in the first row or first column
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]; // Sum of ways from top and left cells
                }
            }
        }
        return dp[m - 1][n - 1]; // Return the number of unique paths to the bottom-right corner
    }
}
