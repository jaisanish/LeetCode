class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;

        // Initialize two tracking sums
        int prev2 = 0;
        int prev = nums[0];

        // Traverse through the array
        for (int i = 1; i < nums.length; i++) {
            // Include current by adding it to two steps back
            int include = nums[i] + prev2;

            // Exclude current by taking previous best
            int exclude = prev;

            // Choose max of include and exclude
            int curr = Math.max(include, exclude);

            // Update tracking variables
            prev2 = prev;
            prev = curr;
        }

        // Final result is stored in prev
        return prev;
    }
}