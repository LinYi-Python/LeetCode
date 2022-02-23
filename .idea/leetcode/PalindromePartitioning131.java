import java.util.*;
public class PalindromePartitioning131 {
}

//dfs
class PalindromePartitioning131A {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> curr = new ArrayList<>();
        dfs(0, result, curr, s);
        return result;
    }

    private void dfs(int start, List<List<String>> result, List<String> curr, String s){
        //base case
        if(start >= s.length()){
            result.add(new ArrayList<String>(curr));
        }

        for(int end = start; end < s.length(); end++){
            if(isPa(s, start, end)){
                curr.add(s.substring(start, end + 1));
                dfs(end + 1, result, curr, s);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private boolean isPa(String s, int low, int high){
        while(low < high){
            if(s.charAt(low) != s.charAt(high)){
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
}

//TC O(N * 2 ^ N)
//SC O(N)



//dfs + dp
//dp for isvaild()
class PalindromePartitioning131B {
    public List<List<String>> partition(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> curr = new ArrayList<>();
        dfs(result, s, 0, curr, dp);
        return result;
    }

    private void dfs(List<List<String>> result, String s, int start, List<String> curr, boolean[][] dp){
        // base case
        if(start >= s.length()){
            result.add(new ArrayList<>(curr));
        }
        for(int end = start; end < s.length(); end++){
            if(s.charAt(start) == s.charAt(end) && ((end - start) <= 2 || dp[start + 1][end - 1])){
                dp[start][end] = true;
                curr.add(s.substring(start, end + 1));
                dfs(result, s, end + 1, curr, dp);
                curr.remove(curr.size() - 1);
            }
        }
    }


}