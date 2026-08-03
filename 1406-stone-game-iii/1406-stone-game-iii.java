class Solution {
    public String stoneGameIII(int[] stoneValue) {
        Integer memo[]=new Integer[stoneValue.length];
        int scoreDifference = solve(stoneValue,memo, 0);
        
        if (scoreDifference > 0) {
            return "Alice";
        } else if (scoreDifference < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }

    private int solve(int[] stoneValue,Integer memo[], int i) {
        // Base case: No stones left to take
        if (i == stoneValue.length) {
            return 0;
        }
        if(memo[i]!=null)return memo[i];
        // Initialize to a very small number because negative scores are possible
        int maxDifference = Integer.MIN_VALUE;
        int currentScore = 0;

        // We can take up to 3 stones, but we must stay within the array bounds
        for (int x = 0; x < 3 && (i + x) < stoneValue.length; x++) {
            currentScore += stoneValue[i + x];
            int differenceIfWeTakeXStones = currentScore - solve(stoneValue,memo, i + x + 1);
            maxDifference = Math.max(maxDifference, differenceIfWeTakeXStones);
        }

        return memo[i]=maxDifference;
    }
}