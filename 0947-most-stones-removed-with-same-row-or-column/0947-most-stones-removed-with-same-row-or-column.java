class Solution {
    static class DisjointSet {
        int[] parent;
        int[] size;
        DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
            for(int i=0;i<n;i++){
                parent[i]=i;
                size[i]=1;
            }
        }

        int find(int node){
            if(parent[node]==node) return node;
            return parent[node]=find(parent[node]);
        }

        void union(int u,int v){
            int pu=find(u);
            int pv=find(v);
            if(pu==pv) return;
            if(size[pu]>=size[pv]){
                parent[pv]=pu;
                size[pu]+=size[pv];
            }else{
                parent[pu]=pv;
                size[pv]+=size[pu];
            }
        }
    }

    public int removeStones(int[][] stones) {
        int n=stones.length;
        DisjointSet ds=new DisjointSet(n);
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(stones[i][0]==stones[j][0] ||stones[i][1]==stones[j][1]){
                    ds.union(i,j);
                }
            }
        }
        int components=0;
        for(int i=0;i<n;i++){
            if(ds.find(i)==i) components++;
        }
        return n-components;
    }
}