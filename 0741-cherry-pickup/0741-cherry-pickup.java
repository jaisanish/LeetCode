class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        Integer [][][][]dp=new Integer[n][n][n][n];
        int ans = solve(0, 0, 0, 0, n, grid,dp);
        return Math.max(0, ans);
    }

    public int solve(int i1,int j1,int i2,int j2,int n,int[][] grid,Integer [][][][]dp){
        // Out of bounds
        if (i1 >= n || j1 >= n || i2 >= n || j2 >= n) return -1000000000;
        // Thorn
        if (grid[i1][j1] == -1 || grid[i2][j2] == -1) {
            return -1000000000;
        }

        // Both reached destination
        if (i1 == n - 1 && j1 == n - 1 && i2 == n - 1 && j2 == n - 1) {
            return grid[i1][j1];
        }
        if(dp[i1][j1][i2][j2]!=null)return dp[i1][j1][i2][j2];

        // Collect current cherries
        int value = 0;

        if (i1 == i2 && j1 == j2) {
            value += grid[i1][j1];
        } else {
            value += grid[i1][j1];
            value += grid[i2][j2];
        }

        int max = -1000000000;

        // 0 = down
        // 1 = right
        int[] dr = {1, 0};
        int[] dc = {0, 1};

        // Try all 4 combinations
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                int ni1 = i1 + dr[i];
                int nj1 = j1 + dc[i];

                int ni2 = i2 + dr[j];
                int nj2 = j2 + dc[j];

                int next = solve(ni1, nj1, ni2, nj2, n, grid, dp);

                max = Math.max(max, next);
            }
        }

        return dp[i1][j1][i2][j2]= value + max;
    }
}