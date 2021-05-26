import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class 找到字符串最长无重复子串 {
    public static void main(String[] args) {
        String str = "tmmzuxt";
        System.out.println(longestNoRepeatedSubString(str));
    }

    public static String longestNoRepeatedSubString(String str) {
        if (str == null) return null;
        if (str.length() < 2) return str;
        //char to index map, start from 1
        int[] idxMap = new int[256];
        int resEnd = 0;
        int maxLen = 0;
        int curLen = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (idxMap[c] == 0) {
                curLen++;
                if (curLen > maxLen) {
                    maxLen = curLen;
                    resEnd = i;
                }
            } else {
                //第一次这里ｊ笔误成ｉ了。。。
                for (int j = i - curLen; j < idxMap[c] - 1; j++) {
                    idxMap[str.charAt(j)] = 0;
                }
                curLen = i + 1 - idxMap[c];
            }
            idxMap[c] = i + 1;
        }
        return str.substring(resEnd + 1 - maxLen, resEnd + 1);
    }
}
