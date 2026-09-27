class Solution {
    public String reverseParentheses(String s) {
        ArrayDeque<String> stk = new ArrayDeque<>();
        StringBuilder curr = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                stk.push(curr.toString());
                curr.setLength(0);
            } 
            else if (ch == ')') {
                curr.reverse();
                curr.insert(0, stk.pop());
            } 
            else {
                curr.append(ch);
            }
        }

        return curr.toString();
    }
}