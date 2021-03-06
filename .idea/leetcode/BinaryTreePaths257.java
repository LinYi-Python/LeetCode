//https://leetcode-cn.com/problems/binary-tree-paths/
import java.util.*;

class BinaryTreePaths257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<String>();
        constructPaths(root, "", paths);
        return paths;
    }

    public void constructPaths(TreeNode root, String path, List<String> paths){
        if(root != null){
            StringBuilder pathSB = new StringBuilder(path);
            pathSB.append(Integer.toString(root.val));
            if(root.left == null && root.right == null){
                paths.add(pathSB.toString());
            }else{
                pathSB.append("->");
                constructPaths(root.left, pathSB.toString(), paths);
                constructPaths(root.right, pathSB.toString(), paths);
            }
        }
    }
}