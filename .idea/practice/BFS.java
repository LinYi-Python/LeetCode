//import apple.laf.JRSUIUtils;
//
////import javax.swing.tree.TreeNode;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Queue;
//
//class TreeNode<E> {
//     int val;
//     TreeNode<E> left;
//     TreeNode<E> right;
//     TreeNode(int val) {
//         this.val = val;
//    }
//}
//
//public class BFS {
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> res = new ArrayList<>();
//        if (root == null) {
//            return res;
//        }
//
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            // 注意 1：一定要先把当前队列的结点总数暂存起来
//            int currentSize = queue.size();
//
//            List<Integer> currentLevel = new ArrayList<>();
//            for (int i = 0; i < currentSize; i++) {
//                TreeNode front = queue.poll();
//                currentLevel.add(front.val);
//                // 注意 2：左（右）孩子结点非空才加入队列
//                if (front.left != null) {
//                    queue.offer(front.left);
//                }
//                if (front.right != null) {
//                    queue.offer(front.right);
//                }
//            }
//            res.add(currentLevel);
//        }
//        return res;
//    }
//
//
//}
//
//
//
////二叉树：[3,9,20,null,null,15,7],
////
////
////    3
////   / \
////  9  20
////    /  \
////   15   7
////返回其层序遍历结果：
////
////
////[
////  [3],
////  [9,20],
////  [15,7]
////]
////
////作者：力扣 (LeetCode)
////链接：https://leetcode-cn.com/leetbook/read/bfs/e6e5f7/
////来源：力扣（LeetCode）
////著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


// * Definition for a binary tree node.
 public class TreeNode {
    int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }}
// */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 注意 1：一定要先把当前队列的结点总数暂存起来
            int currentSize = queue.size();

            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < currentSize; i++) {
                TreeNode front = queue.poll();
                currentLevel.add(front.val);
                // 注意 2：左（右）孩子结点非空才加入队列
                if (front.left != null) {
                    queue.offer(front.left);
                }
                if (front.right != null) {
                    queue.offer(front.right);
                }
            }
            res.add(currentLevel);
        }
        return res;
    }
}

public class MainClass {
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list: nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            List<List<Integer>> ret = new Solution().levelOrder(root);

            String out = int2dListToString(ret);

            System.out.print(out);
        }
    }
}