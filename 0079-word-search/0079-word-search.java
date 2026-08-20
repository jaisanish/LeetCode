class Solution {
    public boolean dfsCheck(int i,int j,int m,int n,int idx,char[][]board,String word){
        if(idx==word.length())return true;
        
        char current=board[i][j];
        board[i][j]='#';

        int row[]={0,1,0,-1};
        int col[]={1,0,-1,0};
        for(int x=0;x<4;x++){
            int newRow=row[x]+i;
            int newCol=col[x]+j;
            if(newRow<0 || newCol<0 || newRow>=m || newCol>=n )continue;
            if(board[newRow][newCol]==word.charAt(idx) && dfsCheck(newRow,newCol,m,n,idx+1,board,word)){
                board[i][j]=current;
                return true;
            }
        }

        board[i][j]=current;
        return false;
    }
    public boolean exist(char[][] board, String word) {
        int m=board.length;
        int n=board[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(word.charAt(0)==board[i][j]){
                    if(dfsCheck(i,j,m,n,1,board,word))return true;
                }
            }
        }
        return false;
    }
}