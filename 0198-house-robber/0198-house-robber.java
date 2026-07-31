class Solution {
    public int rob(int[] nums) {
        int next = 0;
        int nextNext = 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            int curr = Math.max(nums[i] + nextNext, next);
            nextNext = next;
            next = curr;
        }
        return next;
    }
}