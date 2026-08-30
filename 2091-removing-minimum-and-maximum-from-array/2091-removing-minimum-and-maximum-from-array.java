class Solution {
    public int minimumDeletions(int[] nums) {
        int minValue=nums[0],minIdx=0;
        int maxValue=nums[0],maxIdx=0;
        int n=nums.length;
        for(int i=1;i<n;i++){
            if(nums[i]>maxValue){
                maxValue=nums[i];
                maxIdx=i;
            }
            if(nums[i]<minValue){
                minValue=nums[i];
                minIdx=i;
            }
        }
        int a=Math.min(minIdx,maxIdx);
        int b=Math.max(minIdx,maxIdx);
        int front=b+1;
        int back=n-a;
        int both=(a+1)+(n-b);
        return Math.min(front,Math.min(back,both));
    }
}