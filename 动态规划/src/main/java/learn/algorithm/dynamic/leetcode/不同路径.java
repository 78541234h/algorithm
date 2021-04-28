package learn.algorithm.dynamic.leetcode;

import java.util.Iterator;
import java.util.Vector;

public class 不同路径 {
    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7));
    }

    public static int uniquePaths(int m, int n) {
        return nChooseK(m + n - 2, Math.min(m, n) - 1);
    }

    public static int nChooseK(int n, int k) {
        if (k == 0)
            return 1;
        Vector<Integer> divisors = new Vector<>(k);
        for (int i = 0; i < k - 1; i++) {
            divisors.add(k - i);
        }
        int res = 1;
        for (int i = 0; i < k; i++) {
            int multplier = n - i;
            Iterator<Integer> it = divisors.iterator();
            while(it.hasNext()) {
                int factor = it.next();
                if(multplier % factor == 0) {
                    multplier /= factor;
                    it.remove();
                }
            }
            res *= multplier;
        }
        return res;
    }
}
