public class leetcode219 {
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

    public static void main(String[] args) {
        leetcode219 test = new leetcode219();
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        boolean flag = test.containsNearbyDuplicate(nums, k);
        System.out.print(flag);
    }
}