class Solution {
    public int change(int amount, int[] coins) {
        Integer dp[][]=new Integer[coins.length][amount+1];
        return solve(amount,coins,0,dp);
    }
    public int solve(int amount,int []coins,int idx,Integer dp[][]){
        if(amount==0) return 1;
        if(amount<0)return 0;
        if(idx==coins.length)return 0;
        if(dp[idx][amount]!=null)return dp[idx][amount];

        int take=solve(amount-coins[idx],coins,idx,dp);
        int notTake=solve(amount,coins,idx+1,dp);
        return dp[idx][amount]=take+notTake;
    }
}