class Solution {
    public int countPaths(int n, int[][] roads) {
        List<List<long[]>>adj=new ArrayList<>();
        for(int i=0;i<n;i++)adj.add(new ArrayList<>());
        for(int[]e:roads){
            adj.get(e[0]).add(new long[]{e[1],e[2]});
            adj.get(e[1]).add(new long[]{e[0],e[2]});
        }

        long []dist=new long[n];
        long []ways=new long[n];
        for(int i=0;i<n;i++)dist[i]=Long.MAX_VALUE;
        dist[0]=0;
        ways[0]=1;

        PriorityQueue<long[]>pq=new PriorityQueue<>((a,b)->Long.compare(a[1], b[1]));
        pq.offer(new long[]{0,0});

        while(!pq.isEmpty()){
            long cur[]=pq.poll();
            int node=(int)cur[0];
            long cdist=cur[1];

            if(cdist>dist[node])continue;

            for(long e[]:adj.get(node)){
                int v=(int)e[0];
                if(cdist+e[1]<dist[v]){
                    dist[v]=cdist+e[1];
                    ways[v]=ways[node];
                    pq.offer(new long[]{v,dist[v]});

                }
                else if(cdist+e[1]==dist[v]){
                    ways[v]=(ways[v]+ways[node])%1_000_000_007;
                }
            }
        }
        return (int)ways[n-1];

    }
}