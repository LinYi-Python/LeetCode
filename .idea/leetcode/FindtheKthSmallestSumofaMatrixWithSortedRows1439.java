import java.util.*;
public class FindtheKthSmallestSumofaMatrixWithSortedRows1439 {
}
//https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/

class FindtheKthSmallestSumofaMatrixWithSortedRows1439A {
    public int kthSmallest(int[][] mat, int k) {
        if(mat==null ||mat.length==0||mat[0].length==0||k<=0)
            return -1;
        int[] res = mat[0];
        for(int i=1;i<mat.length;i++)
            res = helper(res,mat[i],k);
        return res[k-1];
    }

    private int[] helper(int[] nums1,int[] nums2, int k) {
        int m=nums1.length;
        int n =nums2.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(int i=0;i<n && i<k;i++)
            pq.offer(new Pair(0,i,nums1[0]+nums2[i]));
        int[] res = new int[Math.min(m*n,k)];
        int i=0;
        while(k!=0 && !pq.isEmpty()) {
            Pair p = pq.poll();
            res[i++]=nums1[p.row]+nums2[p.col];
            k--;
            if(p.row==m-1) continue;
            pq.offer(new Pair(p.row+1,p.col,nums1[p.row+1]+nums2[p.col]));
        }
        return res;
    }
    class Pair implements Comparable<Pair> {
        int row;
        int col;
        int sum;
        public Pair(int r,int c,int s) {
            this.row=r;
            this.col=c;
            this.sum=s;
        }
        public int compareTo(Pair b) {
            return this.sum-b.sum;
        }
    }
}


//TC K logK
//SC K



class FindtheKthSmallestSumofaMatrixWithSortedRows1439B {
    public int kthSmallest(int[][] mat, int k) {
        if(mat==null ||mat.length==0||mat[0].length==0||k<=0)
            return -1;
        int[] res = mat[0];
        for(int i=1;i<mat.length;i++)
            res = helper(res,mat[i],k);
        return res[k-1];
    }

    private int[] helper(int[] nums1,int[] nums2, int k) {
        int m=nums1.length;
        int n =nums2.length;
        //minHeap
        PriorityQueue<Pair> pq = new PriorityQueue<>((x1, x2) -> (x1.sum - x2.sum));
        for(int i=0;i<n && i<k;i++)
            pq.offer(new Pair(0,i,nums1[0]+nums2[i]));
        int[] res = new int[Math.min(m*n,k)];
        int i=0;
        while(k!=0 && !pq.isEmpty()) {
            Pair p = pq.poll();
            res[i++]=nums1[p.row]+nums2[p.col];
            k--;
            if(p.row==m-1) continue;
            pq.offer(new Pair(p.row+1,p.col,nums1[p.row+1]+nums2[p.col]));
        }
        return res;
    }
    class Pair {
        int row;
        int col;
        int sum;
        public Pair(int r,int c,int s) {
            this.row=r;
            this.col=c;
            this.sum=s;
        }
        // public int compareTo(Pair b) {
        //     return this.sum-b.sum;
        // }
    }
}


//TC K logK
//SC K

//[1 2 3
// 4 5 6
// 7 8 9  ]