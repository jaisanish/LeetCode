class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;

        // Step 4: Space Optimization!
        // Instead of an array of size N, we only need to remember the last 3 states.
        // dp1 represents solve(i + 1)
        // dp2 represents solve(i + 2)
        // dp3 represents solve(i + 3)
        // Base case: at index n, there are no stones, so score is 0.
        int dp1 = 0; 
        int dp2 = 0;
        int dp3 = 0;

        // Step 3: Bottom-up loop (Right to Left)
        for (int i = n - 1; i >= 0; i--) {
            int maxDifference = Integer.MIN_VALUE;
            int currentScore = 0;

            // Choice 1: Take 1 stone
            currentScore += stoneValue[i];
            maxDifference = Math.max(maxDifference, currentScore - dp1);

            // Choice 2: Take 2 stones (if they exist)
            if (i + 1 < n) {
                currentScore += stoneValue[i + 1];
                maxDifference = Math.max(maxDifference, currentScore - dp2);
            }

            // Choice 3: Take 3 stones (if they exist)
            if (i + 2 < n) {
                currentScore += stoneValue[i + 2];
                maxDifference = Math.max(maxDifference, currentScore - dp3);
            }

            // Shift our window of 3 variables for the next iteration!
            // The current answer becomes the new i+1 for the next loop.
            dp3 = dp2;
            dp2 = dp1;
            dp1 = maxDifference;
        }

        // dp1 holds the final answer for index 0
        if (dp1 > 0) {
            return "Alice";
        } else if (dp1 < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}