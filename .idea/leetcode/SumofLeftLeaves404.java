//https://leetcode-cn.com/problems/sum-of-left-leaves/

class SumofLeftLeaves404 {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }else{
            return dfs(root);
        }
    }

    public int dfs(TreeNode root){
        int ans = 0;
        if(root.left != null){
            if(isLeftLeaves(root.left)){
                ans += root.left.val;
            }else{
                ans += dfs(root.left);
            }
        }
        if(root.right != null && !isLeftLeaves(root.right)){
            ans += dfs(root.right);
        }

        return ans;
    }

    public boolean isLeftLeaves(TreeNode root){
        if(root.left == null && root.right == null){
            return true;
        }else{
            return false;
        }
    }
}