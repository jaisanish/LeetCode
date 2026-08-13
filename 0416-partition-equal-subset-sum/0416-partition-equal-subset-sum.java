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

        boolean[][] dp = new boolean[n][target + 1];

        // Target 0 is always possible
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        // First element
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < n; i++) {

            for (int t = 1; t <= target; t++) {

                boolean notTake = dp[i - 1][t];

                boolean take = false;

                if (nums[i] <= t) {
                    take = dp[i - 1][t - nums[i]];
                }

                dp[i][t] = take || notTake;
            }
        }

        return dp[n - 1][target];
    }
}