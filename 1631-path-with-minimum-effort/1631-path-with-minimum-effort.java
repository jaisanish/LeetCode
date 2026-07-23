class Solution {
    public int minimumEffortPath(int[][] heights) {
        int n=heights.length;
        int m=heights[0].length;
        int r[]={0,1,0,-1};
        int c[]={1,0,-1,0};

        int[][]diff=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                diff[i][j]=Integer.MAX_VALUE;
            }
        }
        diff[0][0]=0;

        PriorityQueue<int[]> q=new PriorityQueue<>((a,b)->a[2]-b[2]);
        q.offer(new int[]{0,0,0});

        while(!q.isEmpty()){
            int[] cur=q.poll();
            int row=cur[0];
            int col=cur[1];
            int effort=cur[2];
            if(row==n-1 && col==m-1)return effort;
            if(effort > diff[row][col])continue;
            
            for(int i=0;i<4;i++){
                int nr=row+r[i];
                int nc=col+c[i];
                if(nr>=0 && nr<n && nc>=0 && nc<m){
                    int heightDiff=Math.abs(heights[row][col]-heights[nr][nc]);
                    int newEffort = Math.max(effort, heightDiff);
                    if(newEffort<diff[nr][nc]){
                        diff[nr][nc]=newEffort;
                        q.offer(new int[]{nr,nc,newEffort});
                    }
                }
            }
        }
        return diff[n-1][m-1];
    }
}