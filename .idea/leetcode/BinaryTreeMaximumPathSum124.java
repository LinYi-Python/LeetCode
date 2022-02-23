import java.util.*;


//class TreeNode {
//      int val;
//      TreeNode left;
//      TreeNode right;
//      TreeNode() {}
//      TreeNode(int val) { this.val = val; }
//      TreeNode(int val, TreeNode left, TreeNode right) {
//          this.val = val;
//          this.left = left;
//          this.right = right;
//      }
//}


public class BinaryTreeMaximumPathSum124 {
    public int maxPathSum(TreeNode root) {
        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;
        dfs(root, maxSum);
        return maxSum[0];
    }

    private int dfs(TreeNode root, int[] maxSum){
        if(root == null){
            return 0;
        }

        int leftSum = Math.max(dfs(root.left, maxSum), 0);
        int rightSum = Math.max(dfs(root.right, maxSum), 0);

        maxSum[0] = Math.max(maxSum[0], leftSum + rightSum + root.val);
        return root.val + Math.max(leftSum, rightSum);
    }

    public TreeNode createTree(Integer[] arr) {
        // 使用队列来存储每一层的非空节点，下一层的数目要比上一层高
        ArrayDeque<TreeNode> pre = new ArrayDeque<>();
        TreeNode root = new TreeNode(arr[0]);
        pre.addLast(root);
        // 表示要遍历的下一个节点
        int index = 0;
        while (!pre.isEmpty()) {

            ArrayDeque<TreeNode> cur = new ArrayDeque<>();
            while (!pre.isEmpty()) {
                TreeNode node = pre.removeFirst();
                TreeNode left=null;
                TreeNode right=null;
                // 如果对应索引上的数组不为空的话就创建一个节点,进行判断的时候，
                // 要先索引看是否已经超过数组的长度，如果索引已经超过了数组的长度，那么剩下节点的左右子节点就都是空了
                // 这里index每次都会增加，实际上是不必要的，但是这样写比较简单
                if (++index<arr.length&&arr[index]!=null){
                    left=new TreeNode(arr[index]);
                    cur.addLast(left);
                }
                if (++index<arr.length&&arr[index]!=null){
                    right=new TreeNode(arr[index]);
                    cur.addLast(right);
                }
                node.left=left;
                node.right=right;
            }
            pre=cur;
        }


        return root;
    }

    public static void main(String[] args) {

        BinaryTreeMaximumPathSum124 test = new BinaryTreeMaximumPathSum124();
        Integer[] arr={1, 2, 3};
//        Integer[] arr={-10, 9, 20, null, null, 15, 7};
        TreeNode tree = test.createTree(arr);
        int result = test.maxPathSum(tree);
        System.out.println(result);
    }


}