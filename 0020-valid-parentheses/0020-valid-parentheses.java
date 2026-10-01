class Solution {
    public boolean isValid(String s) {
        Deque<Character> stk = new ArrayDeque<>();

        for(char c : s.toCharArray()){

            if(c=='(' || c=='[' || c=='{'){
                stk.push(c);
            }
            else{

                if(stk.isEmpty()) return false;

                char top = stk.pop();

                if(c==')' && top!='(') return false;
                if(c==']' && top!='[') return false;
                if(c=='}' && top!='{') return false;
            }
        }

        return stk.isEmpty();
    }
}