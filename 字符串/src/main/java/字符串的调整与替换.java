public class 字符串的调整与替换 {
    public static void main(String[] args) {
        String s = "0***12*45**67***89ab*c*d*e";
        char[] chas = s.toCharArray();
        moveStarToHead(chas);
        System.out.println(String.valueOf(chas));

    }

    public static String replaceSpace(String s) {
        if (s == null) return null;
        int spaceNum = 0;
        int moveIdx = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ')
                spaceNum++;
            if (s.charAt(i) != '\0') {
                moveIdx = i;
            }
        }
        int fillIdx = moveIdx + spaceNum * 2;
        char[] res = new char[fillIdx + 1];
        while (moveIdx >= 0) {
            if (s.charAt(moveIdx) == ' ') {
                res[fillIdx--] = '0';
                res[fillIdx--] = '2';
                res[fillIdx--] = '%';
            } else {
                res[fillIdx--] = s.charAt(moveIdx);
            }
            moveIdx--;
        }
        return String.valueOf(res);
    }

    public static void moveStarToHead(char[] chas) {
        if (chas == null || chas.length == 0) return;
        int lastStarPos;
        for (lastStarPos = chas.length - 1; lastStarPos >= 0; lastStarPos--) {
            if (chas[lastStarPos] == '*')
                break;
        }
        if (lastStarPos < 0) return;
        for (int i = lastStarPos; i >= 0; i--) {
            if (chas[i] != '*') {
                chas[lastStarPos--] = chas[i];
                chas[i] = '*';
            }
        }
    }
}
