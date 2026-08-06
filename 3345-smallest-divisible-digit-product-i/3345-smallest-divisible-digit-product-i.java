class Solution {
    public int smallestNumber(int n, int t) {
        while(true){
            int prod=1;
            int copy=n;
            while(copy!=0){
                prod*=copy%10;
                copy/=10;
            }

            if(copy==0 && prod%t==0){
                return n;
            }
            n++;
        }
    }
}