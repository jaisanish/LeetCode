class Solution {
    public int[] validSequence(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();
        int suf[]=new int[n+1];
        suf[n]=m;
        int j=m-1;
        for(int i=n-1;i>=0;i--){
            if(j>=0 && word1.charAt(i)==word2.charAt(j))j--;

            suf[i]=j+1;
        }

        int ans[]=new int[m];
        j=0;
        boolean changed=false;
        for(int i=0;i<n && j<m ;i++){
            if(word1.charAt(i)==word2.charAt(j)){
                ans[j]=i;
                j++;
            }
            else if(!changed && suf[i+1]<=j+1){
                ans[j]=i;
                changed=true;
                j++;
            }
        }
        return j==m?ans:new int[0];
    }
}