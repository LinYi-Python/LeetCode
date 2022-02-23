import java.util.*;
public class BinaryTreeZigzagLevelOrderTraversal103 {
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}
//https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
//BFS



class BinaryTreeZigzagLevelOrderTraversal103A {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerFirst(root);
        boolean flag = true;
        //if false reverOrder <- offerLast and pollFirst
        //true. inOrder ->  offerFirst and pollLast
        while(!deque.isEmpty()){
            int size = deque.size();
            List<Integer> level = new ArrayList<>();
            if(flag == false){
                for(int i = 0; i < size; i++){
                    TreeNode curr = deque.pollLast();
                    level.add(curr.val);
                    if(curr.right != null){
                        deque.offerFirst(curr.right);
                    }
                    if(curr.left != null){
                        deque.offerFirst(curr.left);
                    }
                }
            }

            if(flag == true){
                for(int i = 0; i < size; i++){
                    TreeNode curr = deque.pollFirst();
                    level.add(curr.val);
                    if(curr.left != null){
                        deque.offerLast(curr.left);
                    }
                    if(curr.right != null){
                        deque.offerLast(curr.right);
                    }
                }
            }
            flag = (flag == true? false: true);

            result.add(level);
        }
        return result;
    }
}
//TC O(N)
//SC O(N)


//DFS

class BinaryTreeZigzagLevelOrderTraversal103B {
    protected void DFS(TreeNode node, int level, List<List<Integer>> results) {
        if (level >= results.size()) {
            LinkedList<Integer> newLevel = new LinkedList<Integer>();
            newLevel.add(node.val);
            results.add(newLevel);
        } else {
            if (level % 2 == 0)
                results.get(level).add(node.val);
            else
                results.get(level).add(0, node.val);
        }

        if (node.left != null) DFS(node.left, level + 1, results);
        if (node.right != null) DFS(node.right, level + 1, results);
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        DFS(root, 0, results);
        return results;
    }
}
//TC O(N)
//SC O(H)