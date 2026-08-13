class Solution {
    public boolean canPartition(int[] nums) {
        int sum=0;
        for(int x:nums)sum+=x;
        if ((sum & 1) == 1) return false;
        Boolean dp[][]=new Boolean[nums.length][(sum/2)+1];
        return solveSubsetSum(nums,sum/2,nums.length-1,dp);
    }
    public boolean solveSubsetSum(int []nums,int target,int i,Boolean dp[][]){
        if(target==0)return true;
        if(i==-1)return false;
        if(dp[i][target]!=null)return dp[i][target];

        boolean notTake=solveSubsetSum(nums,target,i-1,dp);
        boolean take=(nums[i]>target)?false:solveSubsetSum(nums,target-nums[i],i-1,dp);

        return dp[i][target]=notTake||take;

    }
}