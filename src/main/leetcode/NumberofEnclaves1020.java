//https://leetcode-cn.com/problems/number-of-enclaves/
import java.util.*;

//DFS
public class NumberofEnclaves1020 {
    public int numEnclaves(int[][] grid) {
        int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int enclaves = numEnclaves(grid, direction, visited);
        return enclaves;
    }

    private int numEnclaves(int[][] grid, int[][] direction, boolean[][] visited){
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; i++){
            dfs(grid, i, 0, visited, direction);
            dfs(grid, i, n - 1, visited, direction);
        }

        for(int j = 0; j < n; j++){
            dfs(grid, 0, j, visited, direction);
            dfs(grid, m - 1, j, visited, direction);
        }

        int enclaves = 0;
        for(int i = 0; i < m - 1; i++){
            for(int j = 0; j < n - 1; j++){
                if(grid[i][j] == 1 && !visited[i][j]){
                    enclaves++;
                }
            }
        }
        return enclaves;
    }

    private void dfs(int[][] grid, int row, int col, boolean[][] visited, int[][] direction) {
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length ||grid[row][col] == 0 || visited[row][col] ){
            return;
        }
        visited[row][col] = true;
        for(int[] dir: direction){
            dfs(grid, row + dir[0], col + dir[1], visited, direction);
        }
    }

    public static void main(String[] args) {
        NumberofEnclaves1020 test = new NumberofEnclaves1020();
        int[][] grid = {{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
        int result = test.numEnclaves(grid);
        System.out.println(result);
    }
}

//TC O(MN)
//SC O(MN)



//BFS
class NumberofEnclaves1020A {
    public static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<int[]>();
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) {
                visited[i][0] = true;
                queue.offer(new int[]{i, 0});
            }
            if (grid[i][n - 1] == 1) {
                visited[i][n - 1] = true;
                queue.offer(new int[]{i, n - 1});
            }
        }
        for (int j = 1; j < n - 1; j++) {
            if (grid[0][j] == 1) {
                visited[0][j] = true;
                queue.offer(new int[]{0, j});
            }
            if (grid[m - 1][j] == 1) {
                visited[m - 1][j] = true;
                queue.offer(new int[]{m - 1, j});
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int currRow = cell[0], currCol = cell[1];
            for (int[] dir : dirs) {
                int nextRow = currRow + dir[0], nextCol = currCol + dir[1];
                if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && grid[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }
        }
        int enclaves = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    enclaves++;
                }
            }
        }
        return enclaves;
    }
}

//TC O(MN)
//SC O(MN)




//Union- Find
//union- find 介绍 https://www.cnblogs.com/noking/p/8018609.html
class NumberofEnclaves1020B {
    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int index = i * n + j;
                    if (j + 1 < n && grid[i][j + 1] == 1) {
                        uf.union(index, index + 1);
                    }
                    if (i + 1 < m && grid[i + 1][j] == 1) {
                        uf.union(index, index + n);
                    }
                }
            }
        }
        int enclaves = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1 && !uf.isOnEdge(i * n + j)) {
                    enclaves++;
                }
            }
        }
        return enclaves;
    }
}

class UnionFind {
    private int[] parent;
    private boolean[] onEdge;
    private int[] rank;

    public UnionFind(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        parent = new int[m * n];
        onEdge = new boolean[m * n];
        rank = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int index = i * n + j;
                    parent[index] = index;
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        onEdge[index] = true;
                    }
                }
            }
        }
    }

    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public void union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);
        if (rootx != rooty) {
            if (rank[rootx] > rank[rooty]) {
                parent[rooty] = rootx;
                onEdge[rootx] |= onEdge[rooty];
            } else if (rank[rootx] < rank[rooty]) {
                parent[rootx] = rooty;
                onEdge[rooty] |= onEdge[rootx];
            } else {
                parent[rooty] = rootx;
                onEdge[rootx] |= onEdge[rooty];
                rank[rootx]++;
            }
        }
    }

    public boolean isOnEdge(int i) {
        return onEdge[find(i)];
    }
}

//TC O(MN * a(MN))   a() is Ackermann 反阿克曼函数
//SC O(MN)
