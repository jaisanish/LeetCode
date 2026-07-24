class Solution {
    private static final int MAX_XOR = 2048; // 2^11

    public int uniqueXorTriplets(int[] nums) {
        boolean[] pair = new boolean[MAX_XOR];
        boolean[] seen = new boolean[MAX_XOR];

        int n = nums.length;

        // All distinct pair XORs
        for (int i = 0; i < n; i++) {
            seen[nums[i]] = true; // (i, i, i) or repeated indices
            for (int j = i + 1; j < n; j++) {
                pair[nums[i] ^ nums[j]] = true;
            }
        }

        // (a ^ b) ^ c
        for (int x = 0; x < MAX_XOR; x++) {
            if (!pair[x]) continue;
            for (int num : nums) {
                seen[x ^ num] = true;
            }
        }

        int ans = 0;
        for (boolean b : seen) {
            if (b) ans++;
        }

        return ans;
    }
}