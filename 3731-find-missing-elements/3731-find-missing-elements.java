class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        boolean[] seen = new boolean[101];

        int min = 101, max = 0;
        for (int num : nums) {
            seen[num] = true;
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = min + 1; i < max; i++) {
            if (!seen[i]) {
                ans.add(i);
            }
        }
        return ans;
    }
}