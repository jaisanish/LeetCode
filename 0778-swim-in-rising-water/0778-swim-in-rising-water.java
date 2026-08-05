class Solution {
    public int swimInWater(int[][] grid) {
        int directions[][]={{0,1},{1,0},{0,-1},{-1,0}};
        int n=grid.length;

        PriorityQueue<int[]>pq=new PriorityQueue<>((a,b)->Integer.compare(a[2],b[2]));
        pq.offer(new int[]{0,0,grid[0][0]});
        boolean vis[][]=new boolean[n][n];
        vis[0][0] = true;


        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            if(cur[0]==n-1 && cur[1]==n-1)
                return cur[2];

            for(int[] d:directions){
                int nr=cur[0]+d[0];
                int nc=cur[1]+d[1];

                if(nr>=0 && nr<n && nc>=0 && nc<n && !vis[nr][nc]){
                    vis[nr][nc]=true;
                    pq.offer(new int[]{
                        nr,
                        nc,
                        Math.max(cur[2],grid[nr][nc])
                    });
                }
            }
        }

        return -1;
    }
}