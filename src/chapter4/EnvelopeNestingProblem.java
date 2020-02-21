package chapter4;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class EnvelopeNestingProblem {
    public static void main(String[] args) {

    }

    private static class Envelope {
        int len;
        int wid;

        public Envelope(int len, int wid) {
            this.len = len;
            this.wid = wid;
        }
    }

    private static class EnvelopeComparator implements Comparator<Envelope> {
        @Override
        public int compare(Envelope o1, Envelope o2) {
            return o1.len != o2.len ? o1.len - o2.len : o1.wid - o2.wid;
        }
    }

    public static boolean test() {
        return true;
    }

    public static int maxEnvelopes(int[][] envelopes) {
        Envelope[] envs = new Envelope[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            envs[i] = new Envelope(envelopes[i][0], envelopes[i][1]);
        }
        Arrays.sort(envs, new EnvelopeComparator());
        int[] ends = new int[envelopes.length];
        int boundary = -1;
        for (int i = 0; i < envelopes.length; i++) {
            int l = 0;
            int r = boundary;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (envs[i].wid > ends[mid])
                    l = mid + 1;
                else
                    r = mid - 1;
            }
            boundary = Math.max(boundary, l);
            ends[l] = envs[i].wid;
        }
        return boundary + 1;
    }
}
