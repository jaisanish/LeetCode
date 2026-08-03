class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        Integer dp[][]=new Integer[n][n];
        return solve(piles,dp, 0, n - 1) > 0;
    }

    private int solve(int[] piles,Integer[][]dp, int i, int j) {
        if (i == j) return piles[i];               // only one pile left
        if(dp[i][j]!=null)return dp[i][j];
        int left  = piles[i] - solve(piles,dp, i + 1, j); // take left end
        int right = piles[j] - solve(piles,dp, i, j - 1); // take right end
        return dp[i][j]=Math.max(left, right);
    }
}