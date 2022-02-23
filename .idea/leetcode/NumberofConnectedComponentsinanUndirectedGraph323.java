import java.util.*;
public class NumberofConnectedComponentsinanUndirectedGraph323 {
}

//BFS
class NumberofConnectedComponentsinanUndirectedGraph323A {
    public int countComponents(int n, int[][] edges) {

        //build the graph
        List<Integer>[] adj = new ArrayList[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }

        //ADD driction
        for(int[] edge: edges){
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        //BFS
        int res = 0;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                bfs(adj, visited, i);
                res++;
            }
        }
        return res;
    }
    /**
     * @param adj     邻接表
     * @param u       从 u 这个顶点开始广度优先遍历
     * @param visited 全局使用的 visited 布尔数组
     */
    private void bfs(List<Integer>[] adj, boolean[] visited, int u){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(u);
        visited[u] = true;
        //poll one and generate it next and offer it to queue
        while(!queue.isEmpty()){
            Integer front = queue.poll();
            List<Integer> successors = adj[front];
            for(int successor : successors){
                if(!visited[successor]){
                    queue.offer(successor);
                    visited[successor] = true;
                }
            }
        }
    }
}


//带权有向图、且所有权重都非负的单源最短路径问题：使用 Dijkstra 算法；
//带权有向图的单源最短路径问题：Bellman-Ford 算法；
//一个图的所有结点对的最短路径问题：Floy-Warshall 算法。
//
//
//作者：力扣 (LeetCode)
//链接：https://leetcode-cn.com/leetbook/read/bfs/e6occ6/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

//Union-Find
//Disjoint-set data structure
//https://leetcode-cn.com/problems/number-of-connected-components-in-an-undirected-graph/solution/java-bing-cha-ji-lu-jing-ya-suo-by-lyl-3-uhhm/
//并查集，路径上的节点全部压缩到根结点下，获取ab各自的根结点，如果相同则说明已经联通，否则直接将两个根结点联通，集合减一

class NumberofConnectedComponentsinanUndirectedGraph323B {
    public int countComponents(int n, int[][] edges) {
        Union u = new Union(n);
        for(int[] arr:edges){
            u.merge(arr[0],arr[1]);
        }
        return u.unit;
    }

    class Union{
        int[] root;
        int unit;
        int n;
        public Union(int n){
            this.n=n;
            this.unit=n;
            root=new int[n];
            for(int i=0;i<n;i++){
                root[i]=i;
            }
        }
        int find(int x){
            return root[x]==x ?x : (root[x]=find(root[x]));
        }
        void merge(int a,int b){
            int aa = find(a),bb = find(b);
            if(aa==bb)return;
            root[aa]=bb;
            unit-=1;
        }
    }
}


