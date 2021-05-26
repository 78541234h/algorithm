public class 最小包含子串的长度 {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        System.out.println(maxSubStrLen(s, "ABC"));
    }

    public static String maxSubStrLen(String s1, String s2) {
        if (s2 == null || s2.length() == 0 || s1 == null || s1.length() == 0 || s1.length() < s2.length())
            return "";
        int[] map1 = new int[256];
        int[] map2 = new int[256];
        for (int i = 0; i < s1.length(); i++) {
            map1[s1.charAt(i)]++;
        }

        for (int i = 0; i < s2.length(); i++) {
            if (map1[s2.charAt(i)]-- == 0) return "";
            map2[s2.charAt(i)]++; //第一次写，这一句忘了加上。
        }

        int l = 0, r = s1.length() - 1;
        while (r >= 0) {
            char c = s1.charAt(r);
            if (map2[c] != 0 && map1[c] == 0) {
                break;
            }
            map1[c]--;
            r--;
        }
        int minLen = r - l + 1;
        int resStart = 0;
        while (true) {
            char lc = s1.charAt(l);
            if (map2[lc] != 0 && map1[lc] == 0) {
                while (++r < s1.length()) {
                    char rc = s1.charAt(r);
                    map1[rc]++;
                    if (lc == rc)
                        break;
                }
            }
            if (r >= s1.length())
                break;
            l++;
            map1[lc]--;
            if (r - l + 1 < minLen) {
                minLen = r - l + 1;
                resStart = l;
            }

        }
        return s1.substring(resStart, resStart + minLen);
    }


    public int minLength(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < str2.length()) {
            return 0;
        }char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i != chas2.length; i++) {
            map[chas2[i]]++;
        }int left = 0;
        int right = 0;
        int match = chas2.length;
        int minLen = Integer.MAX_VALUE;
        while (right != chas1.length) {
            map[chas1[right]]--;
            if (map[chas1[right]] >= 0) {
                match--;
            }if (match == 0) {
                while (map[chas1[left]] < 0) {
                    map[chas1[left++]]++;
                }minLen = Math.min(minLen, right - left + 1);
                match++;
                map[chas1[left++]]++;
            }right++;
        }return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
