class Solution {
    public int change(int amount, int[] coins) {

        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        // Amount 0 can always be formed in 1 way
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int idx = n - 1; idx >= 0; idx--) {

            for (int curr = 1; curr <= amount; curr++) {

                int take = 0;
                int notTake = 0;

                if (curr >= coins[idx]) {
                    take = dp[idx][curr - coins[idx]];
                }

                if (idx < n - 1) {
                    notTake = dp[idx + 1][curr];
                }

                dp[idx][curr] = take + notTake;
            }
        }

        return dp[0][amount];
    }
}