class Solution {
    public int maxProduct(int n) {
        int max=0,secMax=0;
        while(n!=0){
            int digit=n%10;
            if(digit>max){
                secMax=max;
                max=digit;
            }
            else if(digit>secMax)secMax=digit;
            n/=10;
        }
        return max*secMax;
    }
}