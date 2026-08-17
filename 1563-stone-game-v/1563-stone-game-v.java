class Solution {

    int[][] dp;
    int[] prefix;

    public int stoneGameV(int[] stoneValue) {

        int n = stoneValue.length;

        dp = new int[n][n];

        // -1 means not calculated yet
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(dp[i], -1);
        }

        // Prefix sum
        prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return solve(0, n - 1);
    }

    int solve(int i, int j) {

        // Only one stone -> game ends
        if (i == j) {
            return 0;
        }

        // Already calculated
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int ans = 0;

        // Try every possible split
        for (int k = i; k < j; k++) {

            int leftSum = prefix[k + 1] - prefix[i];
            int rightSum = prefix[j + 1] - prefix[k + 1];

            if (leftSum < rightSum) {

                ans = Math.max(
                    ans,
                    leftSum + solve(i, k)
                );

            } else if (rightSum < leftSum) {

                ans = Math.max(
                    ans,
                    rightSum + solve(k + 1, j)
                );

            } else {

                ans = Math.max(
                    ans,
                    leftSum + Math.max(
                        solve(i, k),
                        solve(k + 1, j)
                    )
                );
            }
        }

        return dp[i][j] = ans;
    }
}