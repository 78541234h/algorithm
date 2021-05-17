public class 字符串的统计字符串 {
    public static void main(String[] args) {
        String[] strs = {"aaabbadddffc"};
        for (String str : strs) {
            System.out.println(statisticString(str));
        }
        String statistic = statisticString(strs[0]);
        for (int i = 0; i < strs[0].length(); i++) {
            System.out.println(indexOf(statistic, i));
        }
    }

    public static String statisticString(String str) {
        if (str == null || str.length() == 0) return null;
        StringBuilder builder = new StringBuilder();
        int times = 1;
        char pre = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (cur != pre) {
                builder.append(pre);
                builder.append("_");
                builder.append(times);
                builder.append("_");
                times = 1;
            } else {
                times++;
            }
            pre = cur;
        }
        builder.append(pre);
        builder.append("_");
        builder.append(times);
        return builder.toString();
    }

    public static Character indexOf(String str, int position) {
        if (str == null || str.length() < 3)
            throw new RuntimeException();
        if (position < 0) return null;
        if (position == 0) return str.charAt(0);
        position++;
        int count = 0;
        int range = 0;
        boolean isCount = true;
        int cur = 2;
        char c = str.charAt(0);
        while (cur < str.length()) {
            if (!isCount) {
                c = str.charAt(cur);
                cur += 2;
                isCount = true;
            } else {
                if (str.charAt(cur) == '_') {
                    //如果计数阶段结束，但计数为０，那么说明传入的字符串格式错误
                    if (count == 0)
                        throw new RuntimeException();
                    range += count;
                    if (range >= position) {
                        return c;
                    }
                    count = 0;
                    isCount = false;
                } else {
                    //在计数阶段如果发现字符不是数字字符那么顾说明传入字符串格式错误
                    if (str.charAt(cur) < '0' || str.charAt(cur) > '9')
                        throw new RuntimeException();
                    count = count * 10 + (str.charAt(cur) - '0');
                }
                cur++;
            }
        }
        return range + count >= position ? c : null;
    }
}


