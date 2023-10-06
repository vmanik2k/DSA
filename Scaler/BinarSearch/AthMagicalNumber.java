package BinarySearch.Scaler.BinarSearch;

public class AthMagicalNumber {
    public int solve(int A, int B, int C) {
        int mod =(int)Math.pow(10,9)+7;
        long l=Math.min(B,C); //Its first possible magical number because a number can divide itself
        long r = A*l; // Range were we can find n numbers till start;
        long mul = (B*C);
        long lcm = mul/gcd(B,C); // Calculating LCM
        // System.out.println(lcm);
        long ans=0;
        while(l<=r){
            long mid = l+(r-l)/2;
            long cal = (mid/B+mid/C - mid/lcm);
            //System.out.println(cal);
            //If number is equal to our number then it can be our answer so try to search in the left half for the number
            if(cal==A){
                ans=mid;
                r=mid-1;
            }else if(cal>A){
                r= mid-1;
            }else{
                l=mid+1;
            }
        }
        return (int)(ans%mod);
    }

    int gcd(int B,int C){
        if(B<C){
            return gcd(C,B);
        }
        if(C==0){
            return B;
        }

        return gcd(C,B%C);
    }
}
