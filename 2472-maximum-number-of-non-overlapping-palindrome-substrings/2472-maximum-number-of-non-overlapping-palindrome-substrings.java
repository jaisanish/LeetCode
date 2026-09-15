class Solution {
    public int maxPalindromes(String s, int k) {
        if(k==1)return s.length();
        int dp[][]=new int[s.length()][s.length()];
        for(int[] row:dp)Arrays.fill(row,-1);
        return solve(s,k,0,k-1,dp);
    }
    public int solve(String s,int k, int i,int j,int[][]dp){
        if(i>=s.length() || j>=s.length())return 0;
        if(dp[i][j]!=-1)return dp[i][j];
        int take=0;
        if(isPalindrome(s,i,j))take=1+solve(s,k,j+1,j+k,dp);
        int grow=solve(s,k,i,j+1,dp);
        int slide=solve(s,k,i+1,j+1,dp);

        return dp[i][j]=Math.max(take,Math.max(grow,slide));
    }
    public boolean isPalindrome(String s,int i,int j){
        while(i<j){
            if(s.charAt(i++)!=s.charAt(j--))return false;
        }
        return true;
    }
}