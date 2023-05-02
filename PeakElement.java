package BinarySearch;

public class PeakElement {
    public static void main(String[] args) {
        PeakSolution peakSolution= new PeakSolution();
        int arr [] ={1,0,3};
        int peakElement = peakSolution.findPeakElement(arr);
        System.out.println(peakElement);
    }
}
class PeakSolution {
    public int findPeakElement(int[] nums) {
        if(nums.length==1)
            return 0;
        int low = 0;
        int high = nums.length-1;
        int N = nums.length;
        int prv=0;
        int nxt=0;
        int mid =0;

        while(low<=high){
            mid= low + (high-low)/2;
            prv= (N+mid-1)%N;
            nxt =(mid+1)%N;
            if(mid>0 && mid< N-1){
                if(nums[mid] > nums[prv]&&nums[mid]>nums[nxt]){
                    return mid;
                } else if (nums[mid]<nums[prv]){
                    high=mid-1;
                } else{
                    low = mid+1;
                }
            } else if (mid==0){
                return nums[0]>nums[1]? 0:1;
            } else if (mid ==(N-1)){
                return nums[N-1]>nums[N-2]?(N-1):(N-2);
            }
        }
        return -1;
    }
}
