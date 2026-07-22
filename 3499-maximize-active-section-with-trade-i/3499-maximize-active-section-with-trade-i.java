class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ans = 0; // Tracks the total number of '1's already in the string
        int mx = 0;  // Tracks the maximum gain from flipping adjacent '0' blocks
        int pre = Integer.MIN_VALUE; // Length of the previously seen '0' block
        
        int i = 0;
        int n = s.length();
        
        while (i < n) {
            char ch = s.charAt(i);
            int count = 0;
            
            // Count the length of the current contiguous block
            while (i < n && s.charAt(i) == ch) {
                count++;
                i++;
            }
            
            if (ch == '1') {
                ans += count;
            } else {
                // The best trade combines the current '0' block with the previous one
                mx = Math.max(mx, pre + count);
                pre = count;
            }
        }
        
        return ans + mx;
    }
}