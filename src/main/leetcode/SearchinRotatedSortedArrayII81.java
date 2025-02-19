/**
 * @ClassName SearchinRotatedSortedArrayII81
 * @Description TODO
 * @Author LinPython
 * @Date 1/24/25 16:40
 * @Version 1.0
 **/
public class SearchinRotatedSortedArrayII81 {
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return false;
        }

        int end = n - 1;
        int start = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return true;
            }

            if (!isBinarySearchHelpful(nums, start, nums[mid])) {
                start++;
                continue;
            }

            boolean pivotArray = existsInFirst(nums, start, nums[mid]);

            boolean targetArray = existsInFirst(nums, start, target);

            if (pivotArray ^ targetArray) {
                if (pivotArray) {
                    start = mid + 1; // pivot in the first, target in the second
                } else {
                    end = mid - 1; // target in the first, pivot in the second
                }
            } else { // If pivot and target exist in same sorted array
                if (nums[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

        }
        return false;
    }


    private boolean isBinarySearchHelpful(int[] arr, int start, int element) {
        return arr[start] != element;
    }

    private boolean existsInFirst(int[] arr, int start, int element) {
        return arr[start] <= element;
    }
}
