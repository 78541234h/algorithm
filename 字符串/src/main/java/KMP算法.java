
public class KMP算法 {

    public static void main(String[] args) {
        String str = "acbc";
        String match = "bc";
        System.out.print(KMP(str, match));
    }

    public static int KMP(String str, String match) {
        if (match == null || match.length() == 0 || str.length() < match.length()) return -1;
        int[] nextArr = getNextArr(match);
        int len = str.length();
        int strIdx = 0;
        int matchIdx = 0;
        while (strIdx < len) {
            if (str.charAt(strIdx) == match.charAt(matchIdx)) {
                strIdx++;
                matchIdx++;
                if (matchIdx == match.length()) return strIdx - matchIdx;
            } else if (matchIdx != 0) {
                matchIdx = nextArr[matchIdx];
            } else {
                strIdx++;
            }
        }
        return -1;
    }

    private static int[] getNextArr(String str) {
        if (str.length() == 1) {
            return new int[]{-1};
        }
        int[] nextArr = new int[str.length()];
        char[] chas = str.toCharArray();
        nextArr[0] = -1;
        nextArr[1] = 0;
        int cur = 2;
        int compareIdx = 0;
        while (cur < str.length()) {
            if (chas[cur - 1] == chas[compareIdx]) {
                nextArr[cur++] = ++compareIdx;
            } else if (compareIdx == 0) {
                cur++;
            } else {
                compareIdx = nextArr[compareIdx];
            }
        }
        return nextArr;
    }
}
