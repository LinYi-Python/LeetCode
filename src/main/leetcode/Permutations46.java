//https://leetcode-cn.com/problems/permutations/

//1.backtrack search
import java.util.*;

public class Permutations46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> output = new ArrayList<Integer>();

        for(int num: nums){
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, ans, 0);
        return ans;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> ans, int first){
        if(first == n){
            ans.add(new ArrayList<Integer>(output));
        }
        for(int i = first; i < n; i++){
            Collections.swap(output, first, i);
            backtrack(n, output, ans, first + 1);
            Collections.swap(output, first, i);
        }
    }
}