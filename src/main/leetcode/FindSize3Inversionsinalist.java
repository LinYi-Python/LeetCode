/**
 * @ClassName FindSize3Inversionsinalist
 * @Description TODO
 * @Author linPython
 * @Date 2/27/22 19:18
 * @Version 1.0
 **/

import java.util.*;
//Inversion is a strictly decreasing subsequence of length 3.
// More formally, given an array, p, an inversion in the array is any time
// some p[i] > p[j] > p[k] and i < j < k. Given an array of
// length n, find the number of inversions.
//
//Example)
//n = 5, arr = [5, 3, 4, 2, 1]
//Array inversions are [5, 3, 2], [5,3,1], [5,4,2], [5,4,1], [5,2,1], [3,2,1], [4,2,1]
//
//n = 4, arr = [4,2,2,1]
//The only inversion is [4,2,1] and we do not count the duplicate inversion.
//
//Is there any solution that can do it within n^2? I can only think of n^3.

class CountIncreasingTriplets {
    private int countTriplets(int[] nums) {
        int[] pos = new int[nums.length];
        for(int i = 0; i < pos.length; i++) {
            pos[i] = i;
        }
        // first row - count of elements lesser than i-th element
        // second row - count of elements greater than i-th element
        int[][] leGr = new int[2][nums.length];
        sort(nums, pos, leGr, 0, nums.length - 1);
        int ans = 0;
        for(int j = 0; j < nums.length; j++) {
            ans += leGr[0][j] * leGr[1][j];
        }
        return ans;
    }

    private void sort(int[] nums, int[] pos, int[][] leGr, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(nums, pos, leGr, lo, mid);
        sort(nums, pos, leGr, mid + 1, hi);
        merge(nums, pos, leGr, lo, mid, hi);
    }

    private void merge(int[] nums, int[] pos, int[][] leGr, int lo, int mid, int hi) {
        int[] auxPos = new int[pos.length];
        for(int i = 0; i < pos.length; i++)
            auxPos[i] = pos[i];
        int i = lo, j = mid + 1, k = lo;

        while(i <= mid && j <= hi) {
            if (nums[pos[i]] < nums[pos[j]]) {
                leGr[1][pos[i]] += hi - j + 1;
                auxPos[k++] = pos[i++];
            } else {
                leGr[0][pos[j]] += i - lo;
                auxPos[k++] = pos[j++];
            }
        }

        while(i <= mid) {
            auxPos[k++] = pos[i++];
        }

        while(j <= hi) {
            leGr[0][pos[j++]] += i - lo;
        }

        System.arraycopy(auxPos, 0, pos, 0, pos.length);
    }

    public static void main(String[] args) {
        CountIncreasingTriplets sol = new CountIncreasingTriplets();
        System.out.println(sol.countTriplets(new int[1]) == 0);
        System.out.println(sol.countTriplets(new int[]{5, 4, 3, 2, 1}) == 0);
        System.out.println(sol.countTriplets(new int[]{1, 2, 3, 4, 5}) == 10);
        System.out.println(sol.countTriplets(new int[]{3, 7, 1, 2, 10, 14, 2, 0, 1}) == 8);
        System.out.println(sol.countTriplets(new int[]{9, 8, 7, 1, 2, 3, 8, 9}) == 11);
        System.out.println(sol.countTriplets(new int[]{10, 9, 8, 7, 6, 1, 2, 3, 4, 5}) == 10);
        System.out.println(sol.countTriplets(new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1}) == 14);
    }


}

public class FindSize3Inversionsinalist {
    public int countTriplets(int[] nums) {
        int[] pos = new int[nums.length];
        for(int i = 0; i < pos.length; i++) {
//            pos[i] = nums.length - i - 1;
            pos[i] = i;
        }
        // first row - count of elements greater than i-th element
        // second row - count of elements smaller than i-th element
        //inverser order

        int[][] leGr = new int[2][nums.length];
        sort(nums, pos, leGr, 0, nums.length - 1);
        int ans = 0;
        for(int j = 0; j < nums.length; j++) {
            ans += leGr[0][j] * leGr[1][j];
        }
        return ans;
    }

    private void sort(int[] nums, int[] pos, int[][] leGr, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(nums, pos, leGr, lo, mid);
        sort(nums, pos, leGr, mid + 1, hi);
        merge(nums, pos, leGr, lo, mid, hi);
    }

    private void merge(int[] nums, int[] pos, int[][] leGr, int lo, int mid, int hi) {
        int[] auxPos = new int[pos.length];
        for(int i = 0; i < pos.length; i++)
            auxPos[i] = pos[i];
        int i = lo, j = mid + 1, k = lo;

        while(i <= mid && j <= hi) {
            if (nums[pos[i]] > nums[pos[j]]) {
                leGr[0][pos[i]] += hi - j + 1;
                auxPos[k++] = pos[i++];
            } else {
                leGr[1][pos[j]] += i - lo;
                auxPos[k++] = pos[j++];
            }
        }

        while(i <= mid) {
            auxPos[k++] = pos[i++];
        }

        while(j <= hi) {
            leGr[1][pos[j++]] += i - lo;
        }

        System.arraycopy(auxPos, 0, pos, 0, pos.length);
    }

    public static void main(String[] args) {
        FindSize3Inversionsinalist sol = new FindSize3Inversionsinalist();
        int[] nums = {5, 4, 3 ,2 ,1, 6, 7, 8, 9, 10};
        int result = sol.countTriplets(nums);
        System.out.println(result);

        System.out.println(sol.countTriplets(new int[1]) == 0);
        System.out.println(sol.countTriplets(new int[]{1, 2, 3, 4, 5}) == 0);
        System.out.println(sol.countTriplets(new int[]{5, 4, 3, 2, 1}) == 10);
        System.out.println(sol.countTriplets(new int[]{1, 0, 2, 14, 10, 2, 1, 7, 3}) == 8);
        System.out.println(sol.countTriplets(new int[]{9, 8, 3, 2, 1, 7, 8, 9}) == 11);
        System.out.println(sol.countTriplets(new int[]{5, 4, 3 ,2 ,1, 6, 7, 8, 9, 10}) == 10);
        System.out.println(sol.countTriplets(new int[]{ 1, 2, 3, 4, 5, 4, 3, 2, 1}) == 14);
    }

}





//N*logN
//private static long maxInversions(List<Integer> arr) {
//    long count = 0;
//
//    for (int i = j; j < arr.size() - 1; j++) {
//        int leftIdx = 0, rightIdx = 0;
//
//        for (int i = 0; i < j; i++) {
//            if (arr.get(i) > arr.get(j)) {
//                leftIdx++;
//            }
//        }
//
//        for (int k = j + 1; k < arr.size(); k++) {
//            if (arr.get(j) > arr.get(k)) {
//                rightIdx++;
//            }
//        }
//
//        count += leftIdx * rightIdx;
//    }
//
//    return count;
//}











class GFG {

    public static void getSmallestAndLargest(String s, int k)
    {
        // Initialize min and max as first substring of size k
        String currStr = s.substring(0, k);
        String lexMin = currStr;
        String lexMax = currStr;

        // Consider all remaining substrings. We consider
        // every substring ending with index i.
        for (int i = k; i < s.length(); i++) {
            currStr = currStr.substring(1, k) + s.charAt(i);
            if (lexMax.compareTo(currStr) < 0)
                lexMax = currStr;
            if (lexMin.compareTo(currStr) > 0)
                lexMin = currStr;
        }

        // Print result.
        System.out.println(lexMin);
        System.out.println(lexMax);
    }

    // Driver Code
    public static void main(String[] args)
    {
        String str = "GeeksForGeeks";
        int k = 3;
        getSmallestAndLargest(str, k);
    }
}

class AncestralNames {
    public static int romanToInt(String roman) {
        int total = 0;
        //create hashmap to store the roman numerals
        HashMap<Character, Integer> romans = new HashMap<>();
        romans.put('I', 1);
        romans.put('V', 5);
        romans.put('X', 10);
        romans.put('L', 50);
        romans.put('C', 100);
        romans.put('D', 500);
        romans.put('M', 1000);
        for (int j = 0; j < roman.length(); j++) {
            char c = roman.charAt(j); //grab first char
            //check to see if next roman is greater
            if (j + 1 < roman.length() && romans.get(c) < romans.get(roman.charAt(j + 1))) {
                //if next roman is greater, you need to subtract
                int add = romans.get(roman.charAt(j + 1)) - romans.get(c);
                total += add;
                j++; //skip over next one since already calculated
            }
            //if less than, just add in order
            else {
                total += romans.get(c);
            }
        }
        return total;
    }

    public static String[] getSortedList(String[] names) {
        Arrays.sort(names, (s1, s2) -> {
            //split the strings up into name,roman
            String[] arr1 = s1.split(" ");
            String[] arr2 = s2.split(" ");

            //grab the numerical values of the romans
            int val1 = romanToInt(arr1[1]);
            int val2 = romanToInt(arr2[1]);

            //if the names are equal, compare the numerals
            if (arr1[0].equals(arr2[0])) {
                //if first one is greater than, push it back
                if (val1 > val2) {
                    return 1;
                }
                //if first one is less than, stay same
                else {
                    return -1;
                }
            }
            else { //if not same, just compare the names
                return arr1[0].compareTo(arr2[0]);
            }
        });
        return names;
    }

    public static void main(String[] args) {
        String[] names = {"Steven XVI", "David IX", "Steven XL", "Mary XV", "Mary XIII", "Mary XX"};
        System.out.println(Arrays.toString(getSortedList(names)));
    }
}



class DominosTilling3D{
    private int mod = 1000000007;

    public int method(int n){
        if(n == 1){
            return 2;
        }
        long[] dp = new long[n + 2];
        long[] dpa = new long[n + 2];
        dp[0] = 1;
        dp[1] = 2;
        dpa[0] = 0;
        dpa[1] = 1;
        for(int i = 2; i <= n; i++){
            dpa[i] = dpa[i - 1] + dp[i - 1] % mod;
            dp[i] = (2 * dpa[i] + 2 * dpa[i - 1] + dp[ i - 2]) % mod;
        }
        return (int) dp[n];
    }

    public static void main(String[] args) {
        DominosTilling3D test = new DominosTilling3D();
        int result = test.method(100);
        System.out.println(result);
    }
}


class CircularPrinter {
    public static void main(String[] args) {
        int[] arr = new int[25];
        int ptr = 0;
        String printer = "AZGB";
        int cost = 0;
        for(Character c : printer.toCharArray()){
            int dest = c-'A';
            int cw = ptr > dest ? (dest+26-ptr) : dest-ptr;
            int acw = ptr < dest ? (ptr+26-dest) : ptr-dest;
            cost+= Math.min(cw, acw);
            ptr = dest;
        }
        System.out.println(cost);
    }
}



//Inversion is a strictly decreasing subsequence of length 3.
//More formally, given an array, p(no duplicate), an inversion in the array is any time some p[i] > p[j] > p[k]
//and i < j < k. Given an array of length n, find the number of inversions.

// Example
// n = 5, arr = [5, 3, 4, 2, 1]
// Array inversions are [5, 3, 2], [5,3,1], [5,4,2], [5,4,1], [5,2,1], [3,2,1], [4,2,1]
// return 7

// first one O(n^2)


//Second one O(n logN )
//Using mergeSort
//The smaller elements on the left of a number will jump from its left to its right during the sorting process.

// ad matrix 2 x n
// left[] left who is bigger
// right[] right who is smaller
// sum += left[i] * right[i]

// [7, 2, 5, 4, 1 ,6]
// index[0, 2, 1, 4, 5, 3]
// [7, 5, 2][6, 4, 1]
// l      m m+1    r
//        i     j
// [7, 6, 5, 4]
// nums[index[i]] > nums[index[j]]
// right[index[i]] += r - j + 1
// newIndex[k++] = index[i++]

//else
// left[index[i]] += i - l
//newIndex[k++] = index[j++]

// copy newIndex to index

class SolutionMergeSort{
    public int count(int[] nums) {
        int[] index = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            index[i] = i;
        }
        //[0, 1, 2, 3]
        int[][] result = new int[2][nums.length];
        //left[] left who is bigger
        //right[] right who is smaller
        merge(nums, index, result, 0, nums.length - 1);
        int sum = 0;
        for(int j = 0; j < nums.length; j++) {
            sum += result[0][j] * result[1][j];
        }
        return sum;
    }

    private void merge(int[] nums, int[] index, int[][] result, int l, int r) {
        ///base case
        if(l >= r) {
            return;
        }
        int mid = (r - l) / 2 + l;
        merge(nums, index, result, l, mid);
        merge(nums, index, result, mid + 1, r);
        mergeSort(nums, index, result, l, r, mid);
    }

    private void mergeSort(int[] nums, int[] index, int[][] result, int l, int r, int mid){
// [7, 2, 5, 4, 1 ,6]
// index[0, 2, 1, 4, 5, 3]
// [7, 5, 2][6, 4, 1]
// l      m m+1    r
//        i     j
// [7, 6, 5, 4]
// nums[index[i]] > nums[index[j]]
// right[index[i]] += r - j + 1
// newIndex[k++] = index[i++]

//else
// left[index[i]] += i - l
//newIndex[k++] = index[j++]
        int[] newIndex = new int[index.length];
        for(int i = 0; i < index.length; i++) {
            newIndex[i] = index[i];
        }
        int i = l, j = mid + 1, k = l;
        // k is for newIndex;
        while(i <= mid && j <= r) {
            if(nums[index[i]] > nums[index[j]]) {
                result[0][index[i]] += r - j + 1;
                newIndex[k++] = index[i++];
            } else {
                result[1][index[j]] += i - l;
                newIndex[k++] = index[j++];
            }
        }

        while(i <= mid) {
            newIndex[k++] = index[i++];
        }

        while(j <= r) {
            result[1][index[j++]] += i - l;
        }

        System.arraycopy(newIndex, 0, index, 0, index.length);
    }
}