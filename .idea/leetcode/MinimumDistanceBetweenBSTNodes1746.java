
//https://www.lintcode.com/problem/1746/
import java.util.*;
class MinimumDistanceBetweenBSTNodes1746A {
    /**
     * @param root:  a Binary Search Tree (BST) with the root node
     * @return: the minimum difference
     */
    Integer res = Integer.MAX_VALUE, pre = null;

    public int minDiffInBST(TreeNode root) {
        // Write your code here.
        if (root.left != null) {
            minDiffInBST(root.left);
        }
        if (pre != null) {
            res = Math.min(res, root.val - pre);
        }
        pre = root.val;
        if (root.right != null) {
            minDiffInBST(root.right);
        }
        return res;
    }
}


class MinimumDistanceBetweenBSTNodes1746B {
    /**
     * @param root:  a Binary Search Tree (BST) with the root node
     * @return: the minimum difference
     */
    public int minDiffInBST(TreeNode root) {
        // Write your code here.
        if(root == null) {
          return 0;
        }

        int minDiff = Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null) {
          stack.push(cur);
          cur = cur.left;
        }
        TreeNode prev = null;

        while(!stack.isEmpty()) {
          cur = stack.pop();
          if(prev != null) {
            minDiff = Math.min(cur.val - prev.val, minDiff);
          }
          prev = cur;

          cur = cur.right;
          while(cur != null) {
            stack.push(cur);
            cur = cur.left;
          }
        }

        return minDiff;
    }
}
