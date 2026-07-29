class Solution {

    private static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] cnt = new int[26];

        for (char c : s.toCharArray())
            cnt[c - 'a']++;

        char mid = 0;
        int[] half = new int[26];
        int len = 0;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1)
                mid = (char) ('a' + i);

            half[i] = cnt[i] / 2;
            len += half[i];
        }

        if (countWays(half) < k)
            return "";

        StringBuilder left = new StringBuilder();

        while (left.length() < len) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0)
                    continue;

                half[c]--;

                long ways = countWays(half);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                }

                k -= ways;
                half[c]++;
            }
        }

        StringBuilder ans = new StringBuilder(left);

        if (mid != 0)
            ans.append(mid);

        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private long countWays(int[] cnt) {

        int total = 0;
        for (int x : cnt)
            total += x;

        long ans = 1;

        for (int f : cnt) {
            ans *= nCr(total, f);

            if (ans >= LIMIT)
                return LIMIT;

            total -= f;
        }

        return ans;
    }

    private long nCr(int n, int r) {

        if (r > n)
            return 0;

        r = Math.min(r, n - r);

        long ans = 1;

        for (int i = 1; i <= r; i++) {
            ans = ans * (n - i + 1) / i;

            if (ans >= LIMIT)
                return LIMIT;
        }

        return ans;
    }
}