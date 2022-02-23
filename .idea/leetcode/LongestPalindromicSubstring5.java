public class LongestPalindromicSubstring5 {
}
//https://leetcode-cn.com/problems/longest-palindromic-substring/

// DP
class LongestPalindromicSubstring5A {
    public String longestPalindrome(String s) {
        int len = s.length();
        if(len < 2){
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        boolean dp[][] = new boolean[len][len];
        for(int i = 0; i < len; i++){
            dp[i][i] = true;
        }


        char[] array = s.toCharArray();
        // l is len of substinrg
        // i is left, j is right [i, j]
        for(int l = 2; l <= len; l++){
            for(int i = 0; i < len; i++){
                int j = i + l - 1;
                if(j >= len){
                    break;
                }
                if(array[i] != array[j]){
                    dp[i][j] = false;
                } else {
                    if(j - i < 3){
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if(dp[i][j] && j - i + 1 > maxLen){
                    maxLen = j - i + 1;
                    begin = i;
                }

            }
        }
        return s.substring(begin, begin + maxLen);
    }
}

//TC O(N ^ 2)
//SC O(N ^ 2)