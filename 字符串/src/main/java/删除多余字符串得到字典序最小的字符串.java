public class 删除多余字符串得到字典序最小的字符串 {
    public static void main(String[] args) {
        System.out.println(minNoRepeatedStr2("bcabc"));
    }

    public static String minNoRepeatedStr(String s) {
        char[] chas = s.toCharArray();
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }
        char[] res = new char[s.length()];
        int len = 0;
        int cur = 0;
        int start = 0;
        while (cur < s.length()) {
            if (map[s.charAt(cur) - 'a'] == -1 || --map[s.charAt(cur) - 'a'] != 0) {
                cur++;
            } else {
                int pick = -1;
                for (int i = start; i <= cur; i++) {
                    if (map[s.charAt(i) - 'a'] != -1 &&
                            (pick == -1 || s.charAt(pick) > s.charAt(i))) {
                        pick = i;
                    }
                }
                res[len++] = s.charAt(pick);
                map[s.charAt(pick) - 'a'] = -1;
                for (int i = pick + 1; i <= cur; i++) {
                    if (map[s.charAt(i) - 'a'] != -1)
                        map[s.charAt(i) - 'a']++;
                }
                start = pick + 1;
                cur = start;
            }
        }
        return String.valueOf(res, 0, len);
    }


    public static String minNoRepeatedStr2(String str) {
        if (str == null) return null;
        if (str.length() < 2) return str;
        boolean[] vis = new boolean[26];
        int[] nums = new int[26];
        for (int i = 0; i < str.length(); i++) {
            nums[str.charAt(i) - 'a']++;
        }
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!vis[c - 'a']) {
                while (buffer.length() > 0) {
                    char last = buffer.charAt(buffer.length() - 1);
                    if (nums[last - 'a'] > 0 && last > c) {
                        vis[last - 'a'] = false;
                        buffer.deleteCharAt(buffer.length() - 1);
                    } else {
                        break;
                    }
                }
                vis[c - 'a'] = true;
                buffer.append(c);
            }
            nums[c - 'a']--;
        }
        return buffer.toString();
    }
}
