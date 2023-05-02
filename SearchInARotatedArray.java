package BinarySearch;

public class SearchInARotatedArray {
    public int search(int[] arr, int target) {
        //Always make decision based on the sorted part and don't make decision based on non sorted part
        if(arr.length==1 )
            return arr[0]==target? 0:-1;

        int low =0;
        int end =arr.length-1;
        int mid=-1;

        while(low<=end){
            mid=low+(end-low)/2;
            if(arr[mid]==target){
                return mid;
            } else if(arr[low]<=arr[mid] ){
                if(arr[low]<=target&&arr[mid]>=target)
                    end=mid-1;
                else
                    low=mid+1;
            } else{
                if(arr[mid]<=target&&arr[end]>=target)
                    low=mid+1;
                else
                    end=mid-1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        SearchInARotatedArray aRotatedArray= new SearchInARotatedArray();
        int arr[]= {2,3,0,1};
        aRotatedArray.search(arr,1);
    }
}
