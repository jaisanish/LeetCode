class Solution {
    public int sumOfDigit(int n){
        int res=0;
        while(n!=0){
            res+=n%10;
            n/=10;
        }
        return res;
    }
    public int smallestIndex(int[] nums) {
        for(int i=0;i<nums.length;i++){
            if(i==sumOfDigit(nums[i])) return i;
        }
        return -1;
    }
}