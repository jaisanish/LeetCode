class Solution {
    public int maxDepth(String s) {
        int count=0,maxx=0;
        for(char ch:s.toCharArray()){
            if(ch=='('){
                count++;
            }
            else if(ch==')'){
                maxx=Math.max(maxx,count--);
            }
        }
        return maxx;
    }
}