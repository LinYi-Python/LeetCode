import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/

//https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
public class KthSmallestElementInaSortedMatrix378 {

}

//binanry search
class Solution1 {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];

        while(left < right){
            int mid = (right - left) / 2 + left;
            if(check(matrix, k, n, mid)){
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[][] matrix, int k, int n, int mid){
        int i = n - 1;
        int j = 0;
        int count = 0;
        while(i >= 0 && j < n){
            if(matrix[i][j] <= mid){
                count += i + 1;
                j++;
            }else{
                i--;
            }
        }
        return count >= k;
    }

}



class Solution2 {
    public int kthSmallest(int[][] matrix, int k) {
        //MinHeap
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });

        int n = matrix.length;
        for(int i = 0; i < Math.min(n, k); i++){
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for(int i = 0; i < k - 1; i++){
            int[] now = pq.poll();
            if(now[2] != n - 1){
                pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
            }
        }
        return pq.poll()[0];
    }
}