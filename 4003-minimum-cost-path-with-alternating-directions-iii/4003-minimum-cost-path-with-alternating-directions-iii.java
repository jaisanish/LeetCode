class Solution {
    public long minCost(int m, int n, int[][] penalty) {
        
        long[][][]dist=new long[m][n][2];
        for(long[][]layer:dist){
            for(long[]row:layer){
                Arrays.fill(row,Long.MAX_VALUE);
            }
        }
        dist[0][0][0]=1;

        PriorityQueue<long[]>pq=new PriorityQueue<>(
            (a,b)->Long.compare(a[0],b[0]));
        pq.offer(new long[]{1,0,0,0});
        int dirs[][]={{0,1},{0,-1},{1,0},{-1,0}};

        while(!pq.isEmpty()){
            long cur[]=pq.poll();
            long cost=cur[0];
            int i=(int)cur[1];
            int j=(int)cur[2];
            int p=(int)cur[3];

            if(cost>dist[i][j][p])continue;

            int newP=p^1;
            long wc=cost+penalty[i][j];
            if(wc<dist[i][j][newP]){
                dist[i][j][newP]=wc;
                pq.offer(new long[]{wc,i,j,newP});
            }

            for(int d=0;d<4;d++){
                int ni=i+dirs[d][0];
                int nj=j+dirs[d][1];
                if(ni<0 || ni>=m || nj<0 || nj>= n)continue;
                long ec=(long)(ni+1)*(nj+1);
                boolean isFreeDir=(p==0)?(d==0||d==2):(d==1||d==3);

                long moveCost=cost+ec+(isFreeDir?0:penalty[i][j]);
                if(moveCost<dist[ni][nj][newP]){
                    dist[ni][nj][newP]=moveCost;
                    pq.offer(new long[]{moveCost,ni,nj,newP});
                }
            }
        }
        return Math.min(dist[m-1][n-1][0],dist[m-1][n-1][1]);
    }
}