class Solution {
    // Factors [2, 3, 5, 7] for digits 0..9
    private static final int[][] F = {
        {0,0,0,0}, {0,0,0,0}, {1,0,0,0}, {0,1,0,0}, {2,0,0,0},
        {0,0,1,0}, {1,1,0,0}, {0,0,0,1}, {3,0,0,0}, {0,2,0,0}
    };

    public String smallestNumber(String num, long t) {
        int[] need = new int[4];
        long[] p = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            while (t % p[i] == 0) { need[i]++; t /= p[i]; }
        }
        if (t > 1) return "-1"; // Prime factor > 7 cannot be formed by single digits

        int n = num.length(), zIdx = num.indexOf('0');
        
        // 1. Check if num itself is valid (no '0' and product divisible by t)
        if (zIdx == -1) {
            int[] have = new int[4];
            for (char c : num.toCharArray()) 
                for (int k = 0; k < 4; k++) have[k] += F[c - '0'][k];
            if (minLen(subtract(need, have)) == 0) return num;
        }

        // 2. Build prefix factors up to the first '0'
        int limit = (zIdx == -1) ? n : zIdx;
        int[][] pref = new int[limit + 1][4];
        for (int i = 0; i < limit; i++) {
            for (int k = 0; k < 4; k++) pref[i + 1][k] = pref[i][k] + F[num.charAt(i) - '0'][k];
        }

        // 3. Backtrack from right to left to find the smallest valid change
        char[] ans = num.toCharArray();
        for (int i = limit; i >= 0; i--) {
            int startD = (i == zIdx) ? 1 : (i < n ? num.charAt(i) - '0' + 1 : 1);
            if (i == n) continue;

            for (int d = startD; d <= 9; d++) {
                int[] rem = subtract(need, pref[i]);
                rem = subtract(rem, F[d]);

                if (minLen(rem) <= n - 1 - i) {
                    ans[i] = (char) ('0' + d);
                    fill(ans, i + 1, rem);
                    return new String(ans);
                }
            }
        }

        // 4. If same length is impossible, build a longer number
        int targetLen = Math.max(n + 1, minLen(need));
        char[] longer = new char[targetLen];
        fill(longer, 0, need);
        return new String(longer);
    }

    // Exact minimum single-digit count needed to supply remaining factors
    private int minLen(int[] req) {
        int c2 = Math.max(0, req[0]), c3 = Math.max(0, req[1]);
        int c5 = Math.max(0, req[2]), c7 = Math.max(0, req[3]);
        int len = c7 + c5 + (c3 / 2) + (c2 / 3);
        int r2 = c2 % 3, r3 = c3 % 2;
        if (r2 == 2 && r3 == 1) len += 2;      // Factors 2, 2, 3 -> needs 2 digits (e.g. 6,2)
        else if (r2 > 0 || r3 > 0) len += 1;   // Any other non-zero remainder fits in 1 digit
        return len;
    }

    // Greedily fill position by position with the smallest valid digit
    private void fill(char[] arr, int pos, int[] req) {
        for (int i = pos; i < arr.length; i++) {
            for (int d = 1; d <= 9; d++) {
                int[] nxt = subtract(req, F[d]);
                if (minLen(nxt) <= arr.length - 1 - i) {
                    arr[i] = (char) ('0' + d);
                    req = nxt;
                    break;
                }
            }
        }
    }

    private int[] subtract(int[] a, int[] b) {
        int[] res = new int[4];
        for (int i = 0; i < 4; i++) res[i] = a[i] - b[i];
        return res;
    }
}