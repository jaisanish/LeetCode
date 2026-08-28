class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();

        // -----------------------------------------
        // 1. Count characters
        // -----------------------------------------

        int[] count = new int[26];

        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        // -----------------------------------------
        // 2. Check whether palindrome is possible
        // -----------------------------------------

        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {

            if (count[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        // -----------------------------------------
        // 3. Build frequency of FIRST HALF
        // -----------------------------------------

        int[] halfCount = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        int halfLen = n / 2;

        // =================================================
        // IMPORTANT:
        // FIRST check whether we can make the first half
        // EXACTLY equal to target's first half.
        //
        // This can already produce a palindrome greater
        // than target because the difference may happen
        // in the SECOND half.
        // =================================================

        int[] remaining = halfCount.clone();

        StringBuilder firstHalf = new StringBuilder();

        boolean possible = true;

        for (int i = 0; i < halfLen; i++) {

            int c = target.charAt(i) - 'a';

            if (remaining[c] == 0) {
                possible = false;
                break;
            }

            firstHalf.append((char) ('a' + c));
            remaining[c]--;
        }

        if (possible) {

            String candidate = buildPalindrome(
                firstHalf,
                middle,
                n
            );

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        // =================================================
        // If exact first half didn't work,
        // now try making the first half GREATER.
        //
        // Start from RIGHT to get the smallest answer.
        // =================================================

        for (int pivot = halfLen - 1; pivot >= 0; pivot--) {

            remaining = halfCount.clone();

            firstHalf = new StringBuilder();

            // -----------------------------------------
            // Keep everything BEFORE pivot equal
            // to target
            // -----------------------------------------

            possible = true;

            for (int i = 0; i < pivot; i++) {

                int c = target.charAt(i) - 'a';

                if (remaining[c] == 0) {
                    possible = false;
                    break;
                }

                firstHalf.append((char) ('a' + c));
                remaining[c]--;
            }

            if (!possible) {
                continue;
            }

            // -----------------------------------------
            // At pivot, choose smallest character
            // GREATER than target[pivot]
            // -----------------------------------------

            int targetChar = target.charAt(pivot) - 'a';

            int bigger = -1;

            for (int c = targetChar + 1; c < 26; c++) {

                if (remaining[c] > 0) {
                    bigger = c;
                    break;
                }
            }

            if (bigger == -1) {
                continue;
            }

            firstHalf.append((char) ('a' + bigger));
            remaining[bigger]--;

            // -----------------------------------------
            // Fill rest of FIRST HALF in ascending order
            // -----------------------------------------

            for (int c = 0; c < 26; c++) {

                while (remaining[c] > 0) {

                    firstHalf.append((char) ('a' + c));
                    remaining[c]--;
                }
            }

            // -----------------------------------------
            // Build palindrome
            // -----------------------------------------

            String candidate = buildPalindrome(
                firstHalf,
                middle,
                n
            );

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        return "";
    }

    private String buildPalindrome(
        StringBuilder firstHalf,
        char middle,
        int n
    ) {

        StringBuilder result = new StringBuilder();

        // First half
        result.append(firstHalf);

        // Middle character for odd length
        if (n % 2 == 1) {
            result.append(middle);
        }

        // Reverse of first half
        for (int i = firstHalf.length() - 1; i >= 0; i--) {
            result.append(firstHalf.charAt(i));
        }

        return result.toString();
    }
}