class Solution {

    public int stoneGameII(int[] piles) {

        int n = piles.length;

        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        Integer[][] dp = new Integer[n][n + 1];

        return solve(piles, 0, 1, suffix, dp);
    }

    private int solve(
        int[] piles,
        int i,
        int M,
        int[] suffix,
        Integer[][] dp
    ) {

        if (i >= piles.length) {
            return 0;
        }

        if (dp[i][M] != null) {
            return dp[i][M];
        }

        int ans = 0;

        for (int X = 1;
             X <= 2 * M && i + X <= piles.length;
             X++) {

            int opponent = solve(
                piles,
                i + X,
                Math.max(M, X),
                suffix,
                dp
            );

            int current = suffix[i] - opponent;

            ans = Math.max(ans, current);
        }

        return dp[i][M] = ans;
    }
}