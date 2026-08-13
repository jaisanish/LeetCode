class Solution {
    public int cherryPickup(int[][] grid) {
        int r=grid.length;
        int c=grid[0].length;
        Integer[][][]dp=new Integer[r][c][c];
        return solve(grid,0,0,c-1,r,c,dp);
        
    }
    public int solve(int[][]grid,int i,int j1,int j2,int r, int c,Integer[][][]dp){
        if(j1<0 || j2<0 || j1>=c || j2>=c)return -10000;
        if(i==r-1){
            return j1==j2?grid[i][j1]:grid[i][j1]+grid[i][j2];
        }
        if(dp[i][j1][j2]!=null)return dp[i][j1][j2];

        int value=grid[i][j1];
        if(j1!=j2)value+=grid[i][j2];

        int max=-10000;
        for(int t1=-1;t1<=1;t1++){
            for(int t2=-1;t2<=1;t2++){
                max=Math.max(max,solve(grid,i+1,j1+t1,j2+t2,r,c,dp));
            }
        }
        return dp[i][j1][j2]=value+max;
    }
}