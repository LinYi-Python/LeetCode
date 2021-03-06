//https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree/
import java.util.*;
//1. hashmap + DFS    , hashmap restore parenet node.

class AllNodesDistanceKinBinaryTree863A {
    Map<Integer, TreeNode> parents = new HashMap<Integer, TreeNode>();
    List<Integer> ans = new ArrayList<Integer>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        findparents(root);
        findAns(target, null, 0, k);
        return ans;
    }

    public void findparents(TreeNode node){
        if(node.left != null){
            parents.put(node.left.val, node);
            findparents(node.left);
        }
        if(node.right != null){
            parents.put(node.right.val, node);
            findparents(node.right);
        }
    }

    public void findAns(TreeNode node, TreeNode from, int depth, int k){
        if(node == null){
            return;
        }
        if(depth == k){
            ans.add(node.val);
            return;
        }
        if(node.left != from){
            findAns(node.left, node, depth + 1, k);
        }
        if(node.right != from){
            findAns(node.right, node, depth + 1, k);
        }
        if(parents.get(node.val) != from){
            findAns(parents.get(node.val), node, depth + 1, k);
        }
    }
}


//2. tree to graph, using dfs and bfs

class AllNodesDistanceKinBinaryTree863B {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        //BFS+DFS
        //DFS找出root到target的路径节点
        //再通过BFS，找到距离为K的节点
        Set<TreeNode> set = new HashSet<>();
        List<TreeNode> listNodes = new ArrayList<>();
        dfs(root,set,target,listNodes);
        int len = listNodes.size();
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<len; i++){
            if(k-i == 0){
                list.add(listNodes.get(i).val);
                break;
            }
            bfs(listNodes.get(i),k-i,list,set);
        }
        return list;
    }
    private boolean dfs(TreeNode node,Set<TreeNode> set,TreeNode target,List<TreeNode> listNodes){
        if(node == null) return false;
        if(node == target || dfs(node.left,set,target,listNodes) || dfs(node.right,set,target,listNodes)){
            set.add(node);
            listNodes.add(node);
            return true;
        }
        return false;
    }
    private void bfs(TreeNode root,int k,List<Integer> list,Set<TreeNode> set){
        Deque<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int len = 0,size = 1;
        while(!q.isEmpty()){
            TreeNode node = q.pollFirst();
            size--;
            if(node == root || !set.contains(node)) {
                if(len == k) {
                    list.add(node.val);
                    continue;
                }
                if(node.left != null) q.addLast(node.left);
                if(node.right != null) q.addLast(node.right);
            }
            if(size == 0){
                len++;
                size = q.size();
            }
        }
    }
}

//    Find all nodes on the path from root to target node through DFS;
//        Then, for the nodes on the path, start traversing the BFS from target to root,
//        and find the nodes on the ki (i is the node position corresponding to listNodes);
//        of course, we add a set set to record the path from root to target node in order to
//        prevent duplicate path nodes. So node. (As for why not directly traversing the set,
//        because Hashset is unordered, and then TreeNode does not support TreeSet)