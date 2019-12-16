public class StringOperation {
    public static void main(String[] args) {
        String s = "asdf";

        switch (s) {
            case "asdf":
                System.out.print("adfsasdfasdf");
                break;
            default:
                break;
        }
    }

    public void rotateWord(char[] str) {

    }

    public void reverse(char[] str) {
        int i = 0;
        int boundary = str.length / 2;
        while (i <= boundary) {
            swap(str, i, str.length - i);
            i++;
        }
    }

    public void swap(char[] str, int left, int right) {
        char tmp = str[left];
        str[left] = str[right];
        str[right] = tmp;
    }

    public int findMinDistBwt2Str(String[] strs, String s1, String s2) {
        int p1 = Integer.MAX_VALUE, p2 = p1;
        String cur;
        int res = p1;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals(s1)) {
                p1 = i;
                cur = s1;
            } else if (strs[i].equals(s2)) {
                p1 = i;
                cur = s2;
            }
        }

        for (int i = p1; i < strs.length; i++) {
        }

        return 0;
    }
}
