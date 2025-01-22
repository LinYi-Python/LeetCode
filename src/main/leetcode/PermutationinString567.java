import java.util.*;

//Solution0 using hashmap with sildewindow
public class PermutationinString567 {
    public boolean checkInclusion(String s1, String s2){
        int len1 = s1.length();
        int len2 = s2.length();
        if(len1 > len2){
            return false;
        }

        Map<Character, Integer>  mapS1 = new HashMap<>();
        Map<Character, Integer>  mapS2 = new HashMap<>();

        for(char ch: s1.toCharArray()){
            mapS1.put(ch, mapS1.getOrDefault(ch, 0) + 1);
        }

        int index = 0;
        for(int i = 0;  i < len1; i++, index++){
            char ch = s2.charAt(i);
            mapS2.put(ch, mapS2.getOrDefault(ch, 0) + 1);
        }

        while(index < s2.length()){
            if(mapS2.equals(mapS1)){
                return true;
            }

            //[a b c d e f g]
            //   [   ]
            //next, delete b and add e
            char before = s2.charAt(index - len1);
            char after = s2.charAt(index);
            mapS2.put(before, mapS2.get(before) - 1);

            if(mapS2.get(before) == 0){
                mapS2.remove(before);
            }

            mapS2.put(after, mapS2.getOrDefault(after, 0) + 1);
            index++;
        }

        return mapS2.equals(mapS1);
    }
}
//https://leetcode.com/problems/permutation-in-string/


//solution1 using array

//Instead of making use of a special HashMap datastructure just to store the
// frequency of occurence of characters, we can use a simpler array data structure
// to store the frequencies. Given strings contains only lowercase alphabets ('a' to 'z').
// So we need to take an array
// of size 26.The rest of the process remains the same as the last approach.
class PermutationinString567A {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] s1map = new int[26];
        for (int i = 0; i < s1.length(); i++)
            s1map[s1.charAt(i) - 'a']++;
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            int[] s2map = new int[26];
            for (int j = 0; j < s1.length(); j++) {
                s2map[s2.charAt(i + j) - 'a']++;
            }
            if (matches(s1map, s2map))
                return true;
        }
        return false;
    }

    public boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i])
                return false;
        }
        return true;
    }
}

//TC O(L1 + 26 * L1 * (L2 - L1))
//SC O(1)

//solution2 sliding window
//Instead of generating the hashmap afresh for every window considered in s2s2,
// we can create the hashmap just once for the first window in s2s2. Then, later on
// when we slide the window, we know that we remove one preceding character and add
// a new succeeding character to the new window considered. Thus, we can update the
// hashmap by just updating the indices associated with those two characters only.
// Again, for every updated hashmap, we compare all the elements of the hashmap for
// equality to get the required result.
class PermutationinString567B {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (matches(s1map, s2map))
                return true;
            s2map[s2.charAt(i + s1.length()) - 'a']++;
            s2map[s2.charAt(i) - 'a']--;
        }
        return matches(s1map, s2map);
    }

    public boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PermutationinString567B test = new PermutationinString567B();
        String l1 = "ab";
        String l2 = "eidbaooo";
        boolean result = test.checkInclusion(l1, l2);
        System.out.print(result);
    }
}

//TC O(L1 + 26 * L1 * (L2 - L1))
//SC O(1)


//Solution3  Optimized Sliding Window
//The last approach can be optimized, if instead of comparing all the elements of the hashmaps
// for every updated s2maps2map corresponding to every window of s2s2 considered, we keep a
// track of the number of elements which were already matching in the earlier hashmap and
// update just the count of matching elements when we shift the window towards the right.
//
//To do so, we maintain a countcount variable, which stores the number of characters
// (out of the 26 alphabets), which have the same frequency of occurence in s1s1 and
// the current window in s2s2. When we slide the window, if the deduction
// of the last element and the addition of the new element leads to a new
// frequency match of any of the characters, we increment the countcount by 1.
// If not, we keep the countcount intact. But, if a character whose frequency
// was the same earlier(prior to addition and removal) is added, it now leads
// to a frequency mismatch which is taken into account by decrementing the
// same countcount variable. If, after the shifting of the window,
// the countcount evaluates to 26, it means all the characters match
// in frequency totally. So, we return a True in that case immediately.

class PermutationinString567C {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }

        int count = 0;
        for (int i = 0; i < 26; i++)
            if (s1map[i] == s2map[i])
                count++;

        for (int i = 0; i < s2.length() - s1.length(); i++) {
            int r = s2.charAt(i + s1.length()) - 'a', l = s2.charAt(i) - 'a';
            if (count == 26)
                return true;
            s2map[r]++;
            if (s2map[r] == s1map[r])
                count++;
            else if (s2map[r] == s1map[r] + 1)
                count--;
            s2map[l]--;
            if (s2map[l] == s1map[l])
                count++;
            else if (s2map[l] == s1map[l] - 1)
                count--;
        }
        return count == 26;
    }
}

//TC O(L1 +  L1 * (L2 - L1))
//SC O(1)