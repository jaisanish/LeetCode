class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int m=triangle.size();
        int prev[]=new int[1];
        prev[0]=triangle.get(0).get(0);
        for(int i=1;i<m;i++){
            int curr[]=new int[i+1];
        
            for(int j=0;j<i+1;j++){
                int above=(j<i)?prev[j]:Integer.MAX_VALUE;
                int aboveleft=(j>0)?prev[j-1]:Integer.MAX_VALUE;
                curr[j]=triangle.get(i).get(j)+Math.min(above,aboveleft);
            }
            prev=curr;
        }
        int min=Integer.MAX_VALUE;
        for(int x:prev)min=Math.min(min,x);
        return min;

    }
}