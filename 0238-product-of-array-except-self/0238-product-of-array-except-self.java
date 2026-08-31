class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int ans[]=new int[n];
        int val=1;
        for(int i=n-1;i>=0;i--){
            ans[i]=val*nums[i];
            val=ans[i];
        }
        val=1;
        for(int i=0;i<n;i++){
            if(i<n-1)ans[i]=ans[i+1]*val;
            else ans[i]=val;

            val*=nums[i];
        }
        return ans;
    }
}