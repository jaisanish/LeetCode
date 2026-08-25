class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            if (x % k == 0) {
                set.add(x / k);
            }
        }
        int i = 1;
        while (set.contains(i)) {
            i++;
        }
        return i * k;
    }
}