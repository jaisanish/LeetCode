class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // suf[i] = first unmatched position of word2
        // when matching word2 from right to left using word1[i...]
        int[] suf = new int[n + 1];
        suf[n] = m;

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }

            suf[i] = j + 1;
        }

        int[] ans = new int[m];

        j = 0;
        boolean changed = false;

        for (int i = 0; i < n && j < m; i++) {

            // Case 1: exact match
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }

            // Case 2: use our one allowed mismatch
            else if (!changed && suf[i + 1] <= j + 1) {
                ans[j] = i;
                j++;
                changed = true;
            }
        }

        return j == m ? ans : new int[0];
    }
}