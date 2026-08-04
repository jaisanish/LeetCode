import java.util.*;

class Solution {

    static class DisjointSet {
        int[] parent;
        int[] size;
        DisjointSet(int n){
            parent=new int[n];
            size=new int[n];
            for(int i=0;i<n;i++){
                parent[i]=i;
                size[i]=1;
            }
        }

        int find(int x){
            if(parent[x]==x)return x;
            return parent[x]=find(parent[x]);
        }

        void union(int u,int v){
            int pu=find(u);
            int pv=find(v);
            if(pu==pv)return;
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
        int maxRow=0;
        int maxCol=0;

        for(int[] stone:stones){
            maxRow=Math.max(maxRow,stone[0]);
            maxCol=Math.max(maxCol,stone[1]);
        }

        DisjointSet ds=new DisjointSet(maxRow+maxCol+2);
        HashSet<Integer> used=new HashSet<>();
        for(int[] stone:stones){
            int row=stone[0];
            int col=stone[1]+maxRow+1;
            ds.union(row,col);
            used.add(row);
            used.add(col);
        }
        int components=0;
        for(int node:used){
            if(ds.find(node)==node)
                components++;
        }
        return stones.length-components;
    }
}