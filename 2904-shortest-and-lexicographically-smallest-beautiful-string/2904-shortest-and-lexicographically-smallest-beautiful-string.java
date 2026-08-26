class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String smallest = "";
        int left = 0;

        for (int right = 0; right < s.length(); right++) {

            if (s.charAt(right) == '1') {
                k--;
            }

            // Too many 1s
            while (k < 0) {
                if (s.charAt(left) == '1') {
                    k++;
                }
                left++;
            }

            // Remove unnecessary leading zeros
            while (k == 0 && s.charAt(left) == '0') {
                left++;
            }

            if (k == 0) {
                String curr = s.substring(left, right + 1);

                if (smallest.equals("") ||
                    curr.length() < smallest.length() ||
                    (curr.length() == smallest.length()
                        && curr.compareTo(smallest) < 0)) {

                    smallest = curr;
                }
            }
        }

        return smallest;
    }
}