package BinarySearch;

public class SmallestLetterGreaterThanTarget {
    public static void main(String[] args) {
        Smallest smallest= new Smallest();
        char [] arr = {'c','f','j'};
        smallest.nextGreatestLetter(arr,'c');
    }
}
class Smallest  {
    public char nextGreatestLetter(char[] letters, char target) {
        int start=0;
        int mid=0;
        int end=letters.length-1;
        char res='#';
        while(start<=end){
            mid= start+(end-start)/2;
            if(letters[mid]==target){
                start=mid+1;
            }else if(letters[mid]>target){
                res=letters[mid];
                end=mid-1;

            }else{
                start=mid+1;
            }
        }
        return res=='#'?letters[0]:res;
    }
}