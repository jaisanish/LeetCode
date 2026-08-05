class Solution {
    public int swimInWater(int[][] grid) {
        PriorityQueue<int[]>pq=new PriorityQueue<>((a,b)->Integer.compare(a[2],b[2]));
        pq.offer(new int[]{0,0,grid[0][0]});
        int n=grid.length;
        boolean vis[][]=new boolean[n][n];

        int directions[][]={{0,1},{1,0},{0,-1},{-1,0}};
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int row = cur[0];
            int col = cur[1];
            int time = cur[2];

            if (vis[row][col]) continue;
            vis[row][col] = true;

            if (row == n - 1 && col == n - 1)
                return time;

            for (int[] d : directions) {
                int nr = row + d[0];
                int nc = col + d[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !vis[nr][nc]) {
                    pq.offer(new int[]{
                        nr,
                        nc,
                        Math.max(time, grid[nr][nc])
                    });
                }
            }
        }

        return -1;
    }
}