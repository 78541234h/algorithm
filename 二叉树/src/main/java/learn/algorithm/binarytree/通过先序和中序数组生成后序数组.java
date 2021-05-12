package learn.algorithm.binarytree;

public class 通过先序和中序数组生成后序数组 {
    public static void main(String[] args) {
        通过先序和中序数组生成后序数组 solution = new 通过先序和中序数组生成后序数组();
        int[] pre = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] mid = {3, 2, 5, 4, 6, 1, 7, 8};
        int[] post = solution.postArr(pre, mid);
        for (int i : post) {
            System.out.print(i + " ");
        }
    }


    public int[] postArr(int[] preArr, int[] midArr) {
        int len = preArr.length;
        int[] res = new int[len];
        int[] pos = {(len - 1)};
        process(res, pos, preArr, 0, len - 1, midArr, 0, len - 1);
        return res;
    }


    private void process(int[] res, int[] pos, int[] preArr, int ps, int pe, int[] midArr, int ms, int me) {
        if (ps > pe) return;
        res[pos[0]--] = preArr[ps];
        int lps, lpe, rps, rpe, lms, lme, rms, rme;
        lps = lpe = rps = rpe = lms = lme = rms = rme = 0;

        for (int i = ms; i <= me; i++) {
            if (preArr[ps] == midArr[i]) {
                lps = ps + 1;
                lpe = ps + i - ms;
                rps = lpe + 1;
                rpe = pe;
                lms = ms;
                lme = i - 1;
                rms = i + 1;
                rme = me;
                break;
            }
        }
        process(res, pos, preArr, rps, rpe, midArr, rms, rme);
        process(res, pos, preArr, lps, lpe, midArr, lms, lme);
    }



}
