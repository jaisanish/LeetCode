class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) return nums[0];

        return Math.max(
            robRange(nums, 0, n - 2),
            robRange(nums, 1, n - 1)
        );
    }

    private int robRange(int[] nums, int start, int end) {
        int next = 0;
        int nextNext = 0;

        for (int i = end; i >= start; i--) {
            int curr = Math.max(nums[i] + nextNext, next);
            nextNext = next;
            next = curr;
        }

        return next;
    }
}