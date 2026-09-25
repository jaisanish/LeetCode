class Solution {

    int idx = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parseExpression(expression);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    // expression = term (',' term)*
    private Set<String> parseExpression(String s) {

        Set<String> result = parseTerm(s);

        while (idx < s.length() && s.charAt(idx) == ',') {
            idx++; // skip ','

            Set<String> next = parseTerm(s);
            result.addAll(next); // union
        }

        return result;
    }

    // term = factor*
    // Example: {a,b}c{d,e}
    private Set<String> parseTerm(String s) {

        Set<String> result = new HashSet<>();
        result.add("");

        while (idx < s.length()
                && s.charAt(idx) != '}'
                && s.charAt(idx) != ',') {

            Set<String> factor = parseFactor(s);

            Set<String> combined = new HashSet<>();

            // Cartesian product
            for (String a : result) {
                for (String b : factor) {
                    combined.add(a + b);
                }
            }

            result = combined;
        }

        return result;
    }

    // factor = letter | '{' expression '}'
    private Set<String> parseFactor(String s) {

        Set<String> result;

        if (s.charAt(idx) == '{') {
            idx++; // skip '{'

            result = parseExpression(s);

            idx++; // skip '}'
        } 
        else {
            result = new HashSet<>();
            result.add(String.valueOf(s.charAt(idx)));
            idx++;
        }

        return result;
    }
}