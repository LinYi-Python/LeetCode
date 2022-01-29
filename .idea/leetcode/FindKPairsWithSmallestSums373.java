import java.util.*;

//https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/
public class FindKPairsWithSmallestSums373 {

}



//不对，复杂度太高，没有用到sorted这个条件
class Solution {
    //Java Priority Queue Solution
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> b.sum-a.sum);
        int n = nums1.length, m = nums2.length;
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<m; j++) {
                pair cur = new pair(nums1[i], nums2[j], nums1[i]+nums2[j]);
                if (pq.size()<k) {
                    pq.add(cur);
                }
                else {
                    if (pq.peek().sum>cur.sum) {
                        pq.poll();
                        pq.add(cur);
                    }
                    else break;
                }
            }
        }
        while (pq.size() > 0) {
            pair v = pq.poll();
            List<Integer> temp = new ArrayList<>(Arrays.asList(v.fir, v.sec));
            ans.add(temp);
        }
        return ans;
    }
    public class pair {
        int fir, sec, sum;
        pair(int i, int j, int s) { fir = i; sec = j; sum = s; }
    }
}


//minheap
class Solution2 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2)->{
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i,0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }
}


//binary search
class Solution3 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        /*二分查找第 k 小的数对和的大小*/
        int left = nums1[0] + nums2[0];
        int right = nums1[m - 1] + nums2[n - 1];
        int pairSum = right;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            long cnt = 0;
            int start = 0;
            int end = n - 1;
            while (start < m && end >= 0) {
                if (nums1[start] + nums2[end] > mid) {
                    end--;
                } else {
                    cnt += end + 1;
                    start++;
                }
            }
            if (cnt < k) {
                left = mid + 1;
            } else {
                pairSum = mid;
                right = mid - 1;
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        int pos = n - 1;
        /*找到小于目标值 pairSum 的数对*/
        for (int i = 0; i < m; i++) {
            while (pos >= 0 && nums1[i] + nums2[pos] >= pairSum) {
                pos--;
            }
            for (int j = 0; j <= pos && k > 0; j++, k--) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);
                ans.add(list);
            }
        }

        /*找到等于目标值 pairSum 的数对*/
        pos = n - 1;
        for (int i = 0; i < m && k > 0; i++) {
            while (pos >= 0 && nums1[i] + nums2[pos] > pairSum) {
                pos--;
            }
            for (int j = i; k > 0 && j >= 0 && nums1[j] + nums2[pos] == pairSum; j--, k--) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[j]);
                list.add(nums2[pos]);
                ans.add(list);
            }
        }
        return ans;
    }
}

//https://app.laicode.io/app/problem/

//Given two sorted arrays A and B, of sizes m and n respectively. Define s = a + b, where a is one element from A and b is one element from B. Find the Kth smallest s out of all possible s'.
//
//Assumptions
//
//A is not null and A is not of zero length, so as B
//K > 0 and K <= m * n
//Examples
//
//A = {1, 3, 5}, B = {4, 8}
//
//1st smallest s is 1 + 4 = 5
//2nd smallest s is 3 + 4 = 7
//3rd, 4th smallest s are 9 (1 + 8, 4 + 5)
//5th smallest s is 3 + 8 = 11


class Solution4 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //minHeap
        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>((x1, x2) -> x1.sum - x2.sum);
        int m = nums1.length;
        int n = nums2.length;
        for(int i = 0; i < Math.min(k, n); i++){
            pq.offer(new Pair(0, i, nums1[0] + nums2[i]));
        }
        while(k != 0 && pq.isEmpty()){
            Pair cur = pq.poll();
            List<Integer> res = new ArrayList<>();
            res.add(nums1[cur.row]);
            res.add(nums1[cur.col]);
            result.add(res);
            k--;
            if(cur == null || cur.row == m - 1){
                continue;
            }
            pq.offer(new Pair(cur.row + 1, cur.col, nums1[cur.row + 1] + nums2[cur.col]));
        }
        return result;
    }

    class Pair{
        int row, col, sum;
        Pair(int i, int j, int s){
            row = i;
            col = j;
            sum = s;
        }
    }

    public static void main(String[] args) {
        Solution4 test = new Solution4();
        int[] nums1 = new int[]{1, 7, 11};
        int[] nums2 = new int[]{2, 4 ,6};
    }
}




