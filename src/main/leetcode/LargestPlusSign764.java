import java.util.*;

public class LargestPlusSign764 {
}

//dp
class LargestPlusSign764A {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        Set<Integer> ban = new HashSet();
        int[][] dp = new int[n][n];

        for(int[] mine: mines){
            ban.add(mine[0] * n + mine[1]);
        }
        int ans = 0;
        int count;

        for(int r = 0; r < n; r++){
            count = 0;
            for(int c = 0; c < n; c++){
                count = ban.contains(r*n + c) ? 0 : count + 1;
                dp[r][c] = count;
            }

            count = 0;
            for(int c = n - 1; c >= 0; c--){
                count = ban.contains(r * n + c)? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c] , count);
            }
        }

        for(int c = 0; c < n; c++){
            count = 0;
            for(int r = 0; r < n; r++){
                count = ban.contains(r*n + c) ? 0: count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }

            count = 0;
            for(int r = n - 1; r >= 0; r--){
                count = ban.contains(r*n + c)? 0: count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
                ans = Math.max(ans, dp[r][c]);
            }
        }

        return ans;
        //TC O(N^2)
        //SC O(N^2)


    }
}

// 0 1 1 1 0 1 1 0
// 0 1 2 3 0 1 2 0