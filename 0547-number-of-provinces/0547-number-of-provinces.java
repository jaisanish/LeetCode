class Solution {
    public void dfs(int s,int[][]isConnected,boolean[] vis){
        vis[s]=true;
        for(int i=0;i<isConnected.length;i++){
            if(isConnected[s][i]==1 && !vis[i]){
                dfs(i,isConnected,vis);
            }
        }
    }
    public int findCircleNum(int[][] isConnected) {
        int ans=0;
        int v=isConnected.length;
        boolean vis[]=new boolean[v];
        for(int i=0;i<v;i++){
            if(!vis[i]){
                dfs(i,isConnected,vis);
                ans++;
            }
        }
        return ans;
    }
}