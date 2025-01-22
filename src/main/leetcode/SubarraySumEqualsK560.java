import java.util.*;
public class SubarraySumEqualsK560 {
}

//https://leetcode.com/problems/subarray-sum-equals-k/solution/

//Brute Force
class SubarraySumEqualsK560A {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                int sum = 0;
                for (int i = start; i < end; i++)
                    sum += nums[i];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
}
//TC O(N^3)
//SC O(N)


//Using Cumulative Sum
class SubarraySumEqualsK560B {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                if (sum[end] - sum[start] == k)
                    count++;
            }
        }
        return count;
    }
}
//TC O(N^2)
//SC O(N)


//Approach 3: Without Space
class SubarraySumEqualsK560C {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum=0;
            for (int end = start; end < nums.length; end++) {
                sum+=nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
}
//TC O(N^2)
//SC O(1)


//Approach 4: Using Hashmap
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for(int i = 0; i < nums.length; i++){
            sum+= nums[i];
            if(map.containsKey(sum - k)){
                count += map.get(sum - k);
            } else {
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        return count;
    }
}
//TC O(N)
//SC O(N)