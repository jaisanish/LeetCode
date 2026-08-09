class Solution {

    public int stoneGameII(int[] piles) {

        int n = piles.length;

        // suffix[i] = total stones from i to n-1
        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        int[][] dp = new int[n + 1][n + 1];

        // i must go backwards
        for (int i = n - 1; i >= 0; i--) {

            // M can range from 1 to n
            for (int M = 1; M <= n; M++) {

                int ans = 0;

                // Try taking X piles
                for (int X = 1;
                     X <= 2 * M && i + X <= n;
                     X++) {

                    int opponent =
                        dp[i + X][Math.max(M, X)];

                    int current =
                        suffix[i] - opponent;

                    ans = Math.max(ans, current);
                }

                dp[i][M] = ans;
            }
        }

        return dp[0][1];
    }
}