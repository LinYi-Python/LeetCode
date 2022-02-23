import java.util.ArrayList;

public class NumberofMatchingSubsequences792 {
    public int numMatchingSubseq(String s, String[] words) {
        int ans = 0;
        ArrayList<Node>[] heads = new ArrayList[26];
        for(int i = 0; i < 26; i++){
            heads[i] = new ArrayList<Node>();
        }
        for(String word: words){
            heads[word.charAt(0) - 'a'].add(new Node(word, 0));
        }

        for(char c: s.toCharArray()){
            ArrayList<Node> old_bucket = heads[c - 'a'];
            heads[c - 'a'] = new ArrayList<Node>();

            for(Node node: old_bucket){
                node.index++;
                if (node.index == node.word.length()){
                    ans++;
                } else {
                    heads[node.word.charAt(node.index) - 'a'].add(node);
                }
            }
            old_bucket.clear();
        }
        return ans;
    }

    public static void main(String[] args) {
        NumberofMatchingSubsequences792 test = new NumberofMatchingSubsequences792();
        String s = "abcde";
        String[] words = {"a","bb","acd","ace"};
        int result = test.numMatchingSubseq(s, words);
        System.out.println(result);
    }

    class Node{
        String word;
        int index;
        Node(String w, int i){
            word = w;
            index = i;
        }
    }
}

