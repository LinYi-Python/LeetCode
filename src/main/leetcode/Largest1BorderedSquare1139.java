/**
 * @ClassName Largest1BorderedSquare1139
 * @Description TODO
 * @Author LinPython
 * @Date 2/22/22 17:30
 * @Version 1.0
 **/
public class Largest1BorderedSquare1139 {

}

class Largest1BorderedSquare1139A {
    public int largest1BorderedSquare(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m + 1][n + 1][2];

        //dp[][][0] is direction from left to right;
        //dp[][][1] is direction from up to down;
        int maxSide = 0;
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(grid[i - 1][j - 1] == 1){
                    dp[i][j][0] = dp[i][j - 1][0] + 1;
                    dp[i][j][1] = dp[i - 1][j][1] + 1;
                    int curSide = Math.min(dp[i][j][0], dp[i][j][1]);
                    if(curSide <= maxSide){
                        continue;
                    }

                    for(; curSide > maxSide; curSide--){
                        if(dp[i - curSide + 1][j][0] >= curSide && dp[i][j - curSide + 1][1] >= curSide){
                            maxSide = curSide;
                            break;
                        }
                    }
                }
            }
        }
        return maxSide * maxSide;
    }
}