class Solution {
public:
    bool checkDivisibility(int n) {
        long product=1,sum=0,copy=n;
        while(copy!=0){
            int digit=copy%10;
            product*=digit;
            sum+=digit;
            copy/=10;
        }
        return n%(product+sum)==0;
    }
};