//https://leetcode-cn.com/problems/maximum-product-of-word-lengths/

//bit opeartor

//1. Bitmasks + Precomputation : Comparison in \mathcal{O}(1)O(1) time
import java.util.*;
class MaximumProductofWordLengths318A{
    public int bitNumber(char ch) {
        return (int)ch - (int)'a';
    }

    public int maxProduct(String[] words) {
        int n = words.length;
        int[] masks = new int[n];
        int[] lens = new int[n];

        int bitmask = 0;
        for (int i = 0; i < n; ++i) {
            bitmask = 0;
            for (char ch : words[i].toCharArray()) {
                // add bit number bit_number in bitmask
                bitmask |= 1 << bitNumber(ch);
            }
            masks[i] = bitmask;
            lens[i] = words[i].length();
        }

        int maxVal = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j < n; ++j)
                if ((masks[i] & masks[j]) == 0)
                    maxVal = Math.max(maxVal, lens[i] * lens[j]);

        return maxVal;
    }
}




//2.Approach 2: Optimise Number of Comparisons : Bitmasks + Precomputation + Hashmap

class MaximumProductofWordLengths318B {
    public int bitNumber(char ch){
        return (int)ch - (int)'a';
    }

    public int maxProduct(String[] words) {
        Map<Integer, Integer> hashmap = new HashMap();

        int bitmask = 0, bitNum = 0;
        for (String word : words) {
            bitmask = 0;
            for (char ch : word.toCharArray()) {
                // add bit number bitNumber in bitmask
                bitmask |= 1 << bitNumber(ch);
            }
            // there could be different words with the same bitmask
            // ex. ab and aabb
            hashmap.put(bitmask, Math.max(hashmap.getOrDefault(bitmask, 0), word.length()));
        }

        int maxProd = 0;
        for (int x : hashmap.keySet())
            for (int y : hashmap.keySet())
                if ((x & y) == 0) maxProd = Math.max(maxProd, hashmap.get(x) * hashmap.get(y));

        return maxProd;
    }
}
