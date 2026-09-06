class Solution {
    public int numDistinct(String s, String t) {
        int slen=s.length(),tlen=t.length();
        int dp[][]=new int[slen][tlen];
        for(int row[]:dp)Arrays.fill(row,-1);
        return solve(s,slen,t,tlen,0,0,dp);
    }
    public int solve(String s,int slen,String t,int tlen,int idx,int match,int [][]dp){
        if(match==tlen)return 1;
        if(idx==slen) return 0;
        if (slen - idx < tlen - match) return 0;
        if(dp[idx][match]!=-1)return dp[idx][match];
        //take
        int take=0;
        if(s.charAt(idx)==t.charAt(match)) take=solve(s,slen,t,tlen,idx+1,match+1,dp);
        // not take
        int notTake=solve(s,slen,t,tlen,idx+1,match,dp);
        return dp[idx][match]=take+notTake;
    }
}