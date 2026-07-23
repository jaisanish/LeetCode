class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n=nums.length;
        if(n<3)return n;
        int bits=0;
        while((1<<bits)<=n)bits++;
        return 1<<bits;
    }
}