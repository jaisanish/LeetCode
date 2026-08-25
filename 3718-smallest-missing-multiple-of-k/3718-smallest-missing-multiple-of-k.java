class Solution {
    public int missingMultiple(int[] nums, int k) {
        int hash[]=new int[101];
        for(int x:nums){
            if(x%k==0)hash[x/k]++;
        }
        for(int i=1;i<101;i++){
            if(hash[i]==0)return i*k;
        }
        return 101;
    }
}