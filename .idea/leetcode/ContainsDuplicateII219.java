//https://leetcode-cn.com/problems/contains-duplicate-ii/
import java.util.*;
//1.
class ContainsDuplicateII219A {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums.length == 0){
            return false;
        }
        for(int i = 0; i < nums.length; i++){
            for(int j = 1; j <= k; j++){
                if(i + j < nums.length){
                    if(nums[i] == nums[i + j]){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
////TC KN
// SC 1


//2.hashmap
class ContainsDuplicateII219B {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (map.containsKey(num) && i - map.get(num) <= k) {
                return true;
            }
            map.put(num, i);
        }
        return false;
    }
}
//TC N
//SC 1



//3.sliding window
class ContainsDuplicateII219C {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
}
//TC N
//SC K


