import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0;

        // litterId[r][c] = bit number of litter at this cell
        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    sr = i;
                    sc = j;
                }
                if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        // No litter
        if (litterCount == 0) {
            return 0;
        }

        int fullMask = (1 << litterCount) - 1;

        /*
            state = {row, col, energy, mask, moves}
        */

        Queue<int[]> q = new ArrayDeque<>();

        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << litterCount];

        q.offer(new int[]{sr, sc, energy, 0, 0});
        visited[sr][sc][energy][0] = true;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!q.isEmpty()) {

            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];
            int eng = cur[2];
            int mask = cur[3];
            int moves = cur[4];

            // Collected everything
            if (mask == fullMask) {
                return moves;
            }

            /*
             * If energy is 0:
             *
             * We can only continue if we are standing on R.
             *
             * But if we are standing on R, our energy should already
             * have been reset when we entered it.
             */
            if (eng == 0) {
                continue;
            }

            for (int k = 0; k < 4; k++) {

                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                char cell = classroom[nr].charAt(nc);

                // Cannot enter obstacle
                if (cell == 'X') {
                    continue;
                }

                // Moving costs 1 energy
                int newEnergy = eng - 1;
                int newMask = mask;

                // Collect litter
                if (cell == 'L') {
                    int id = litterId[nr][nc];
                    newMask |= (1 << id);
                }

                // Reset energy AFTER entering R
                if (cell == 'R') {
                    newEnergy = energy;
                }

                if (!visited[nr][nc][newEnergy][newMask]) {

                    visited[nr][nc][newEnergy][newMask] = true;

                    q.offer(new int[]{
                        nr,
                        nc,
                        newEnergy,
                        newMask,
                        moves + 1
                    });
                }
            }
        }

        return -1;
    }
}