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
        int ans=Integer.MAX_VALUE;

        if(minIdx>maxIdx){
            ans=Math.min(minIdx+1,ans);
            ans=Math.min(n-maxIdx,ans);
        }    
        else{
            ans=Math.min(maxIdx+1,ans);
            ans=Math.min(n-minIdx,ans);
        }
        
        int temp=0;
        if(minIdx>maxIdx){
            temp+=maxIdx+1;
            temp+=n-minIdx;
        } 
        else {
            temp+=minIdx+1;
            temp+=n-maxIdx;
        }

        return Math.min(ans,temp);
    }
}