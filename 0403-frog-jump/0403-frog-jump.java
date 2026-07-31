class Solution {
    private Map<Integer, Integer> map = new HashMap<>();
    private Boolean[][] dp;

    public boolean canCross(int[] stones) {
        int n = stones.length;

        // First jump must be 1
        if (n > 1 && stones[1] != 1) return false;

        for (int i = 0; i < n; i++) {
            map.put(stones[i], i);
        }

        dp = new Boolean[n][n + 1];

        return dfs(stones, 1, 1);
    }

    private boolean dfs(int[] stones, int index, int jump) {

        if (index == stones.length - 1)
            return true;

        if (dp[index][jump] != null)
            return dp[index][jump];

        for (int nextJump = jump - 1; nextJump <= jump + 1; nextJump++) {

            if (nextJump <= 0)
                continue;

            int nextStone = stones[index] + nextJump;

            if (map.containsKey(nextStone)) {
                if (dfs(stones, map.get(nextStone), nextJump))
                    return dp[index][jump] = true;
            }
        }

        return dp[index][jump] = false;
    }
}