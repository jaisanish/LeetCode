class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int dist[][]=new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }

        for(int edge[]:edges){
            dist[edge[0]][edge[1]]=edge[2];
            dist[edge[1]][edge[0]]=edge[2];
        }

        for(int i=0;i<n;i++)dist[i][i]=0;

        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(dist[i][k]!=Integer.MAX_VALUE && dist[k][j]!=Integer.MAX_VALUE){
                            dist[i][j]=Math.min(dist[i][j],dist[i][k]+dist[k][j]);
                    }
                }
            }
        }

        int countCity=n,city=-1;
        for(int c=0;c<n;c++){
            int count=0;
            for(int a=0;a<n;a++){
                if(c!=a && dist[c][a]<=distanceThreshold){
                    count++;
                }
            }
            if(count<=countCity){
                countCity=count;
                city=c;
            }
        }
        return city;
    }
}