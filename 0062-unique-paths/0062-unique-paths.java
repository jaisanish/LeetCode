class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(i==m-1 && j==n-1)dp[i][j]=1;
                else{
                    int down=0;
                    int right=0;
                    if(i+1<m)down=dp[i+1][j];
                    if(j+1<n)right=dp[i][j+1];
                    dp[i][j]=down+right;
                }
            }
        }
        return dp[0][0];
    }
}