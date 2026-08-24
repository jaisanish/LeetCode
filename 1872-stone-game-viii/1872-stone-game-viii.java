class Solution {
    int[] prefix;
    int[] dp;

    int solve(int i) {
        // Base case
        if (i == prefix.length - 1) {
            return prefix[i];
        }

        if (dp[i] != Integer.MIN_VALUE) {
            return dp[i];
        }

        // Take
        int take = prefix[i] - solve(i + 1);

        // Skip
        int skip = solve(i + 1);

        return dp[i] = Math.max(take, skip);
    }

    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        prefix = new int[n];
        dp = new int[n];

        prefix[0] = stones[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }

        Arrays.fill(dp, Integer.MIN_VALUE);

        return solve(1);
    }
}