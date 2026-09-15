class Solution {
    boolean isPalindrome[][];

    public int maxPalindromes(String s, int k) {
        int n=s.length();
        if(k==1)return n;

        int dp[][]=new int[n][n];
        for(int[] row:dp)Arrays.fill(row,-1);

        isPalindrome=new boolean[n][n];
        for(int L=1;L<=n;L++){
            for(int i=0;i+L<=n;i++){
                int j=i+L-1;
                if(i==j){ //length 1
                    isPalindrome[i][j]=true;
                }
                else if(i+1==j){ //length 2
                    isPalindrome[i][j]=s.charAt(i)==s.charAt(j);
                }
                else{
                    isPalindrome[i][j]=(s.charAt(i)==s.charAt(j)) && isPalindrome[i+1][j-1];
                }
            }
        }


        return solve(s,n,k,0,k-1,dp);
    }

    public int solve(String s,int n,int k, int i,int j,int[][]dp){
        if(i>=n || j>=n)return 0;
        if(dp[i][j]!=-1)return dp[i][j];

        int take=0;
        if(isPalindrome[i][j])take=1+solve(s,n,k,j+1,j+k,dp);
        int grow=solve(s,n,k,i,j+1,dp);
        int slide=solve(s,n,k,i+1,j+1,dp);

        return dp[i][j]=Math.max(take,Math.max(grow,slide));
    }
}