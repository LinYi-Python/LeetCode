//https://leetcode-cn.com/problems/repeated-string-match/

//1.
class RepeatedStringMatch686 {
    public int repeatedStringMatch(String a, String b) {
        if(a.equals(b)) return 1;
        int m = a.length(), n = b.length();

        //检查b是否有a没有的字符，如果有不同字符则无法构成子串，直接返回-1
        int[] cnt = new int[26];
        for(char c : a.toCharArray()) cnt[c-'a']++;
        for(char c : b.toCharArray()){
            if(cnt[c-'a'] == 0) return -1;
        }

        //字符串a的三种叠加次数情况分别构成a的叠加字符串，再判断是否包含字符串b
        int len1 =n/m, len2 = n/m+1, len3 = n/m+2;
        String s1 = "", s2 = "", s3 = "";
        for(int i = 0; i < len1; ++i) s1 += a;
        for(int i = 0; i < len2; ++i) s2 += a;
        for(int i = 0; i < len3; ++i) s3 += a;
        if(s1.equals(b) || s1.contains(b)) return len1;
        if(s2.equals(b) || s2.contains(b)) return len2;
        if(s3.equals(b) || s3.contains(b)) return len3;
        return -1;
    }
}

