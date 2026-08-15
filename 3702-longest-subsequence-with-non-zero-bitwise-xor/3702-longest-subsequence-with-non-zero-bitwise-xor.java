class Solution {
    public int longestSubsequence(int[] nums) {
        int n=nums.length;
        boolean nonZero=false;
        int xor=0;
        for(int x:nums){
            xor^=x;
            if(x!=0)nonZero=true;
        }
        if(xor!=0)return n;
        if(nonZero==false)return 0;
        return n-1;
    }
}