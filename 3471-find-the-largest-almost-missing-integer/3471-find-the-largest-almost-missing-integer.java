class Solution { 
    public int largestInteger(int[] nums, int k) { 
        
        int n = nums.length;
        HashMap<Integer, Integer> hs = new HashMap<>();

        for (int x : nums) {
            hs.put(x, hs.getOrDefault(x, 0) + 1);
        }

        // k = 1
        if (k == 1) {
            int max = -1;
            for (int x : nums) {
                if (hs.get(x) == 1) {
                    max = Math.max(max, x);
                }
            }
            return max;
        }

        // k = n
        if (k == n) {
            int max = 0;
            for (int x : nums) {
                max = Math.max(max, x);
            }
            return max;
        }

        // 1 < k < n
        int possible1 = -1;
        int possible2 = -1;

        if (hs.get(nums[0]) == 1)
            possible1 = nums[0];

        if (hs.get(nums[n - 1]) == 1)
            possible2 = nums[n - 1];

        return Math.max(possible1, possible2);
    }
}