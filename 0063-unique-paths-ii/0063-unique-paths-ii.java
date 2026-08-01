class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        if(obstacleGrid[0][0]==1 || obstacleGrid[m-1][n-1]==1)return 0;
        int prev[]=new int[n];
        for(int i=0;i<m;i++){
            int curr[]=new int[n];
            for(int j=0;j<n;j++){
                if(i==0 && j==0)curr[j]=1;
                else if(obstacleGrid[i][j]==1)curr[j]=0;
                else{
                    int up=0,left=0;
                    if(i>0)up=prev[j];
                    if(j>0)left=curr[j-1];
                    curr[j]=up+left;
                }
            }
            prev=curr;
        }
        return prev[n-1];
    }
}