class Solution {
    public boolean canPartition(int[] nums) {

        int sum = 0;
        for (int x : nums) {
            sum += x;
        }

        // Odd total cannot be split equally
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        int n = nums.length;

        boolean[] dp = new boolean[target + 1];

        // First element
        if (nums[0] <= target) {
            dp[nums[0]] = true;
        }
        dp[0]=true;

        for (int i = 1; i < n; i++) {
            boolean newDp[]=new boolean[target+1];
            newDp[0]=true;
            for (int t = 1; t <= target; t++) {

                boolean notTake = dp[t];

                boolean take = false;

                if (nums[i] <= t) {
                    take = dp[t - nums[i]];
                }

                newDp[t] = take || notTake;
            }
            dp=newDp;
        }

        return dp[target];
    }
}