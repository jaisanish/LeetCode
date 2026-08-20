class Solution {
    public int[] resultArray(int[] nums) {
        List<Integer>temp=new ArrayList<>();
        temp.add(nums[1]);
        int numsAns[]=new int[nums.length];
        int ptr=0;
        numsAns[ptr]=nums[0];
        for(int i=2;i<nums.length;i++){
            if(numsAns[ptr]>temp.get(temp.size()-1)){
                numsAns[++ptr]=nums[i];
            }
            else temp.add(nums[i]);
        }
        for(int x:temp){
            numsAns[++ptr]=x;
        }
        return numsAns;
    }
}