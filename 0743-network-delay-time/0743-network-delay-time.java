class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>>adj=new ArrayList<>();
        for(int i=0;i<n+1;i++) adj.add(new ArrayList<>());
        for(int[]e:times) adj.get(e[0]).add(new int[]{e[1],e[2]});

        PriorityQueue<int[]>pq=new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.offer(new int[]{k,0});

        int dist[]=new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[k]=0;

        while(!pq.isEmpty()){
            int cur[]=pq.poll();
            int node=cur[0];
            int cdist=cur[1];
            if(cdist > dist[node]) continue; // optional optimization
            for(int e[]:adj.get(node)){
                if(cdist+e[1]<dist[e[0]]){
                    dist[e[0]]=cdist+e[1];
                    pq.offer(new int[]{e[0],dist[e[0]]});
                }
            }
        }
        int max=0;
        for(int i=1;i<=n;i++){
            if(dist[i] == Integer.MAX_VALUE)return -1;
            max=Math.max(max,dist[i]);
        }
        return max;
    }
}