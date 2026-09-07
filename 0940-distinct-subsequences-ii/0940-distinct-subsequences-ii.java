class Solution {

    public int distinctSubseqII(String s) {

        long[] last = new long[26];

        long dp = 1; // empty subsequence
        long MOD = 1_000_000_007L;

        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';

            long newDp = (2 * dp - last[ch] + MOD) % MOD;

            last[ch] = dp;
            dp = newDp;
        }

        return (int) ((dp - 1 + MOD) % MOD);
    }
}