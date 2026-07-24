class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if (n == 1) return 1;

        HashSet<Integer> pairXor = new HashSet<>();

        // All pair XORs (i < j)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pairXor.add(nums[i] ^ nums[j]);
            }
        }

        HashSet<Integer> ans = new HashSet<>();

        // Triplets where all 3 indices are same
        for (int x : nums)
            ans.add(x);

        // Triplets using a pair and one element
        for (int px : pairXor) {
            for (int x : nums) {
                ans.add(px ^ x);
            }
        }

        return ans.size();
    }
}